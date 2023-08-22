package com.gsantander.tesla.classes;

import com.gsantander.tesla.model.TslUser;
import jakarta.validation.constraints.NotBlank;

public class PasswordChangeRequest {

    private TslUser user;
    @NotBlank(message = "{field.notBlank}")
    private String newPassword;
    @NotBlank(message = "{field.notBlank}")
    private String newPasswordValidation;

    public TslUser getUser() {
        return user;
    }

    public void setUser(TslUser user) {
        this.user = user;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPasswordValidation() {
        return newPasswordValidation;
    }

    public void setNewPasswordValidation(String newPasswordValidation) {
        this.newPasswordValidation = newPasswordValidation;
    }

}
