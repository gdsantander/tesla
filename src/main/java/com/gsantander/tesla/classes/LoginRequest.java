package com.gsantander.tesla.classes;

import jakarta.validation.constraints.NotBlank;

public class LoginRequest {

    @NotBlank(message = "{field.notBlank}")
    private String userName;
    @NotBlank(message = "{field.notBlank}")
    private String password;

    public LoginRequest(@NotBlank(message = "{field.notBlank}") String userName, @NotBlank(message = "{field.notBlank}") String password) {
        this.userName = userName;
        this.password = password;
    }

    public LoginRequest() {
    }

    public static LoginRequestBuilder builder() {
        return new LoginRequestBuilder();
    }

    public @NotBlank(message = "{field.notBlank}") String getUserName() {
        return this.userName;
    }

    public @NotBlank(message = "{field.notBlank}") String getPassword() {
        return this.password;
    }

    public void setUserName(@NotBlank(message = "{field.notBlank}") String userName) {
        this.userName = userName;
    }

    public void setPassword(@NotBlank(message = "{field.notBlank}") String password) {
        this.password = password;
    }

    public static class LoginRequestBuilder {
        private @NotBlank(message = "{field.notBlank}") String userName;
        private @NotBlank(message = "{field.notBlank}") String password;

        LoginRequestBuilder() {
        }

        public LoginRequestBuilder userName(@NotBlank(message = "{field.notBlank}") String userName) {
            this.userName = userName;
            return this;
        }

        public LoginRequestBuilder password(@NotBlank(message = "{field.notBlank}") String password) {
            this.password = password;
            return this;
        }

        public LoginRequest build() {
            return new LoginRequest(this.userName, this.password);
        }

        public String toString() {
            return "LoginRequest.LoginRequestBuilder(userName=" + this.userName + ", password=" + this.password + ")";
        }
    }
}
