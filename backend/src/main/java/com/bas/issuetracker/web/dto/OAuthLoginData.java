package com.bas.issuetracker.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OAuthLoginData {
    private String clientId;
    private String scope;
}
