package com.bas.issuetracker.web.service.users;

import com.bas.issuetracker.web.domain.user.User;
import com.bas.issuetracker.web.domain.user.UserRepository;
import com.bas.issuetracker.web.dto.UserWithToken;
import com.bas.issuetracker.web.service.oauth.TokenService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final UserDtoConverter userDtoConverter;

    public UserService(UserRepository userRepository, TokenService tokenService, UserDtoConverter userDtoConverter) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
        this.userDtoConverter = userDtoConverter;
    }

    @Transactional
    public UserWithToken processLogin(User loginRequester) {
        User user = createIfNotFound(loginRequester);
        updateWithNewToken(user);
        return userDtoConverter.userToUserWithToken(user);
    }

    private User createIfNotFound(User user) {
        Optional<User> optionalUser = userRepository.findUserByAuthenticatedByAndOauthId(user.getAuthenticatedBy(), user.getOauthId());
        if (optionalUser.isPresent()) {
            user = optionalUser.get();
        } else {
            save(user);
        }
        return user;
    }

    private void updateWithNewToken(User user) {
        String token = tokenService.createToken(user.getId());
        user.updateToken(token);
        userRepository.updateAccessToken(user.getId(), user.getAccessToken());
    }

    private User save(User user) {
        return userRepository.save(user);
    }
}
