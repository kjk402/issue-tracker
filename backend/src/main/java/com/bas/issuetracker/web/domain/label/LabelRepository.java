package com.bas.issuetracker.web.domain.label;

import java.util.List;
import java.util.Optional;

public interface LabelRepository {
    Label create(Label label);

    Optional<Label> findById(int id);

    List<Label> findAll();

    void update(Label label);

    void delete(int id);
}
