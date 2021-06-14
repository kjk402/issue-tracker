package com.bas.issuetracker.web.service.label;

import com.bas.issuetracker.web.domain.label.Label;
import com.bas.issuetracker.web.domain.label.LabelRepository;
import com.bas.issuetracker.web.dto.label.LabelMetadata;
import com.bas.issuetracker.web.dto.label.LabelPreviews;
import com.bas.issuetracker.web.exceptions.notfound.LabelNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.bas.issuetracker.web.exceptions.notfound.LabelNotFoundException.LABEL_NOT_FOUND;

@Service
@Transactional(readOnly = true)
public class LabelService {

    private final LabelRepository labelRepository;
    private final LabelDtoConverter labelDtoConverter;

    public LabelService(LabelRepository labelRepository, LabelDtoConverter labelDtoConverter) {
        this.labelRepository = labelRepository;
        this.labelDtoConverter = labelDtoConverter;
    }

    @Transactional
    public Label createLabel(LabelMetadata metadata) {
        Label label = labelDtoConverter.labelMetadataToLabel(metadata);
        return labelRepository.create(label);
    }

    public Label findLabel(int labelId) {
        return labelRepository.findById(labelId)
                .orElseThrow(() -> new LabelNotFoundException(LABEL_NOT_FOUND, labelId));
    }

    public LabelPreviews showLabels() {
        List<Label> labels = labelRepository.findAll();
        return labelDtoConverter.labelsToLabelPreviews(labels);
    }

    @Transactional
    public void updateLabel(int labelId, LabelMetadata metadata) {
        Label label = findLabel(labelId);
        label.updateMetadata(metadata);
        labelRepository.update(label);
    }

    @Transactional
    public void deleteLabel(int labelId) {
        findLabel(labelId);
        labelRepository.delete(labelId);
    }
}
