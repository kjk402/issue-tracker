package com.bas.issuetracker.web.domain.milestone;

import java.util.List;
import java.util.Optional;

public interface MilestoneRepository {
    Milestone create(Milestone milestone);

    Optional<Milestone> findById(int id);

    List<Milestone> findAll();

    void update(Milestone milestone);

    void delete(int id);
}
