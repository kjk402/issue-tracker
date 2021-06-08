package com.bas.issuetracker.config.properties;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class JwtSecretTest {
    @Autowired
    private JwtSecret jwtSecret;

    @Test
    @DisplayName("jwt 설정파일을 불러올 수 있어야 합니다")
    void testJwtSecret() {
        assertThat(jwtSecret)
                .extracting(
                        JwtSecret::getIssuer,
                        JwtSecret::getSecretKey,
                        JwtSecret::getExpireSecond
                ).doesNotContainNull();
    }
}
