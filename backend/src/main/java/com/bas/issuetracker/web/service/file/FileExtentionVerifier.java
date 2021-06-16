package com.bas.issuetracker.web.service.file;

import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class FileExtentionVerifier {
    private final HashSet<String> availableExtention = new HashSet<>();

    public FileExtentionVerifier() {
        availableExtention.add("jpg");
        availableExtention.add("jpeg");
        availableExtention.add("gif");
        availableExtention.add("png");
    }

    public boolean isAvailable(String extention) {
        return availableExtention.contains(extention);
    }
}
