package com.bas.issuetracker.web.config.bean;

import com.bas.issuetracker.web.domain.label.LabelRepository;
import com.bas.issuetracker.web.domain.milestone.MilestoneRepository;
import com.bas.issuetracker.web.domain.user.UserRepository;
import com.bas.issuetracker.web.service.label.LabelDAO;
import com.bas.issuetracker.web.service.milestone.MilestoneDAO;
import com.bas.issuetracker.web.service.users.UserDAO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfig {

    private final UserDAO userDAO;
    private final MilestoneDAO milestoneDAO;
    private final LabelDAO labelDAO;

    public RepositoryConfig(UserDAO userDAO, MilestoneDAO milestoneDAO, LabelDAO labelDAO) {
        this.userDAO = userDAO;
        this.milestoneDAO = milestoneDAO;
        this.labelDAO = labelDAO;
    }

    @Bean
    public UserRepository userRepository() {
        return userDAO;
    }

    @Bean
    public MilestoneRepository milestoneRepository() {
        return milestoneDAO;
    }

    @Bean
    public LabelRepository labelRepository() {
        return labelDAO;
    }
}