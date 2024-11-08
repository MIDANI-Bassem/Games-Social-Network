package org.gameloom.connect.game.email;

import lombok.Data;
import lombok.Getter;

@Getter
public enum EmailTemplateName {
    ACTIVATE_ACCOUNT("activate_account")
    ;
    private String templateName;
    EmailTemplateName(String templateName) {
        this.templateName = templateName;
    }
}
