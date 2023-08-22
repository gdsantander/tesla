package com.gsantander.tesla.classes;

import jakarta.validation.constraints.NotBlank;

public class RegisterRequest {

    @NotBlank(message = "{field.notBlank}")
    private String lastName;
    @NotBlank(message = "{field.notBlank}")
    private String firstName;
    @NotBlank(message = "{field.notBlank}")
    private String userName;
    @NotBlank(message = "{field.notBlank}")
    private String password;
    @NotBlank(message = "{field.notBlank}")
    private String email;

    public RegisterRequest(@NotBlank(message = "{field.notBlank}") String lastName, @NotBlank(message = "{field.notBlank}") String firstName, @NotBlank(message = "{field.notBlank}") String userName, @NotBlank(message = "{field.notBlank}") String password, @NotBlank(message = "{field.notBlank}") String email) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.userName = userName;
        this.password = password;
        this.email = email;
    }

    public RegisterRequest() {
    }

    public static RegisterRequestBuilder builder() {
        return new RegisterRequestBuilder();
    }

    public @NotBlank(message = "{field.notBlank}") String getLastName() {
        return this.lastName;
    }

    public @NotBlank(message = "{field.notBlank}") String getFirstName() {
        return this.firstName;
    }

    public @NotBlank(message = "{field.notBlank}") String getUserName() {
        return this.userName;
    }

    public @NotBlank(message = "{field.notBlank}") String getPassword() {
        return this.password;
    }

    public @NotBlank(message = "{field.notBlank}") String getEmail() {
        return this.email;
    }

    public void setLastName(@NotBlank(message = "{field.notBlank}") String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(@NotBlank(message = "{field.notBlank}") String firstName) {
        this.firstName = firstName;
    }

    public void setUserName(@NotBlank(message = "{field.notBlank}") String userName) {
        this.userName = userName;
    }

    public void setPassword(@NotBlank(message = "{field.notBlank}") String password) {
        this.password = password;
    }

    public void setEmail(@NotBlank(message = "{field.notBlank}") String email) {
        this.email = email;
    }

    public static class RegisterRequestBuilder {
        private @NotBlank(message = "{field.notBlank}") String lastName;
        private @NotBlank(message = "{field.notBlank}") String firstName;
        private @NotBlank(message = "{field.notBlank}") String userName;
        private @NotBlank(message = "{field.notBlank}") String password;
        private @NotBlank(message = "{field.notBlank}") String email;

        RegisterRequestBuilder() {
        }

        public RegisterRequestBuilder lastName(@NotBlank(message = "{field.notBlank}") String lastName) {
            this.lastName = lastName;
            return this;
        }

        public RegisterRequestBuilder firstName(@NotBlank(message = "{field.notBlank}") String firstName) {
            this.firstName = firstName;
            return this;
        }

        public RegisterRequestBuilder userName(@NotBlank(message = "{field.notBlank}") String userName) {
            this.userName = userName;
            return this;
        }

        public RegisterRequestBuilder password(@NotBlank(message = "{field.notBlank}") String password) {
            this.password = password;
            return this;
        }

        public RegisterRequestBuilder email(@NotBlank(message = "{field.notBlank}") String email) {
            this.email = email;
            return this;
        }

        public RegisterRequest build() {
            return new RegisterRequest(this.lastName, this.firstName, this.userName, this.password, this.email);
        }

        public String toString() {
            return "RegisterRequest.RegisterRequestBuilder(lastName=" + this.lastName + ", firstName=" + this.firstName + ", userName=" + this.userName + ", password=" + this.password + ", email=" + this.email + ")";
        }
    }
}
