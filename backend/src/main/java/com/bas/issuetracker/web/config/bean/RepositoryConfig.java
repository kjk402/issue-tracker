package com.bas.issuetracker.web.config.bean;

import com.bas.issuetracker.web.domain.user.UserRepository;
import com.bas.issuetracker.web.service.users.UserDAO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfig {

    private final UserDAO userDAO;

    public RepositoryConfig(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Bean
    public UserRepository userRepository() {
        return userDAO;
    }
}
