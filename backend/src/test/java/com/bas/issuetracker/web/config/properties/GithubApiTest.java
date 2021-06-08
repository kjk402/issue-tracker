package com.bas.issuetracker.web.config.properties;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class GithubApiTest {

    @Autowired
    private GithubApi githubApi;

    @Test
    @DisplayName("github api 정보를 불러올 수 있어야 합니다")
    void testGithubApi() {
        assertThat(githubApi)
                .extracting(
                        GithubApi::getLoginUrl,
                        GithubApi::getAccessTokenUrl,
                        GithubApi::getProfileUrl,
                        GithubApi::getScopeKey,
                        GithubApi::getScopeValue
                ).doesNotContainNull();
    }
}
