package com.gsantander.tesla.classes;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gsantander.tesla.tools.TslConstants;

import java.util.Date;

public class AuthResponse {

    private String accessToken = "";
    private String refreshToken = "";
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern=TslConstants.PATTERN_TIME_FORMAT_JSON, timezone= TslConstants.TIME_ZONE)
    private Date expirationDate;

    public String getAccessToken() {
        return this.accessToken;
    }

    public String getRefreshToken() {
        return this.refreshToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

}
