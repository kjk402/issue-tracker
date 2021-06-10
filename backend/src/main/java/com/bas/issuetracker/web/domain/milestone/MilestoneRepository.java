package com.bas.issuetracker.web.domain.milestone;

import java.util.Optional;

public interface MilestoneRepository {
    Milestone create(Milestone milestone);

    Optional<Milestone> findById(int id);

    void update(Milestone milestone);
}
