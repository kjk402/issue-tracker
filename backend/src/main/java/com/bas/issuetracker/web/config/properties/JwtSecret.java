package com.bas.issuetracker.web.config.properties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Getter
@Setter
@ToString
@Configuration
@ConfigurationProperties(prefix = "jwt")
@PropertySource(value = "classpath:secret.properties")
public class JwtSecret {
    private String issuer;
    private String secretKey;
    private long expireSecond;
}
