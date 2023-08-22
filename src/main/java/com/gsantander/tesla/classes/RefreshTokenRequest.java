package com.gsantander.tesla.classes;

import jakarta.validation.constraints.NotBlank;

public class RefreshTokenRequest {

    @NotBlank(message = "{field.notBlank}")
    private String refreshToken;

    public RefreshTokenRequest(@NotBlank(message = "{field.notBlank}") String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public RefreshTokenRequest() {
    }

    public static RefreshTokenRequestBuilder builder() {
        return new RefreshTokenRequestBuilder();
    }

    public @NotBlank(message = "{field.notBlank}") String getRefreshToken() {
        return this.refreshToken;
    }

    public void setRefreshToken(@NotBlank(message = "{field.notBlank}") String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public static class RefreshTokenRequestBuilder {
        private @NotBlank(message = "{field.notBlank}") String refreshToken;

        RefreshTokenRequestBuilder() {
        }

        public RefreshTokenRequestBuilder refreshToken(@NotBlank(message = "{field.notBlank}") String refreshToken) {
            this.refreshToken = refreshToken;
            return this;
        }

        public RefreshTokenRequest build() {
            return new RefreshTokenRequest(this.refreshToken);
        }

        public String toString() {
            return "RefreshTokenRequest.RefreshTokenRequestBuilder(refreshToken=" + this.refreshToken + ")";
        }
    }
}
