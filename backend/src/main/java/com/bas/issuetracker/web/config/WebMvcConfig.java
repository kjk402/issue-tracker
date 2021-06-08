package com.bas.issuetracker.web.config;

import com.bas.issuetracker.web.config.resolver.CertifiedUserResolver;
import com.bas.issuetracker.web.config.resolver.UserAgentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final CertifiedUserResolver certifiedUserResolver;
    private final UserAgentResolver userAgentResolver;

    public WebMvcConfig(CertifiedUserResolver certifiedUserResolver, UserAgentResolver userAgentResolver) {
        this.certifiedUserResolver = certifiedUserResolver;
        this.userAgentResolver = userAgentResolver;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(certifiedUserResolver);
        resolvers.add(userAgentResolver);
    }
}
