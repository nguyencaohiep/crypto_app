package com.example.cryptoapp.dao;

public class ResponeToken {
    private Boolean status;
    private String code;
    private String message;
    private Token device_token;

    public ResponeToken(Boolean status, String code, String message, Token device_token) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.device_token = device_token;
    }


    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Token getDevice_token() {
        return device_token;
    }

    public void setDevice_token(Token device_token) {
        this.device_token = device_token;
    }
}
