package com.bas.issuetracker.config.properties;

import com.bas.issuetracker.web.domain.user.UserAgentEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class OAuthSecretTest {

    @Autowired
    private OAuthSecret oAuthSecret;

    @Test
    @DisplayName("OAuth 시크릿을 불러올 수 있어야 합니다")
    void testOAuthSecret() {
        assertThat(oAuthSecret)
                .extracting(
                        OAuthSecret::getClientIdKey,
                        OAuthSecret::getClientSecretKey,
                        OAuthSecret::getFeClientIdValue,
                        OAuthSecret::getFeClientSecretValue,
                        OAuthSecret::getIosClientIdValue,
                        OAuthSecret::getIosClientSecretValue
                ).doesNotContainNull();
    }

    @Test
    @DisplayName("fe의 클라이언트 ID와 시크릿을 불러올 수 있어야 합니다")
    void testFeData() {
        assertThat(oAuthSecret.clientId(UserAgentEnum.FE)).isNotNull();
        assertThat(oAuthSecret.clientSecret(UserAgentEnum.FE)).isNotNull();
    }

    @Test
    @DisplayName("ios의 클라이언트 ID와 시크릿을 불러올 수 있어야 합니다")
    void testiosData() {
        assertThat(oAuthSecret.clientId(UserAgentEnum.IOS)).isNotNull();
        assertThat(oAuthSecret.clientSecret(UserAgentEnum.IOS)).isNotNull();
    }
}
