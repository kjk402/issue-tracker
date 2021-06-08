package com.bas.issuetracker.web.config.bean;

import com.bas.issuetracker.web.config.resolver.CertifiedUserResolver;
import com.bas.issuetracker.web.config.resolver.UserAgentResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ResolverBeanConfig {

    @Bean
    public CertifiedUserResolver certifiedUserResolver() {
        return new CertifiedUserResolver();
    }

    @Bean
    public UserAgentResolver userAgentResolver() {
        return new UserAgentResolver();
    }
}
