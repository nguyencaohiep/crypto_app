package com.example.cryptoapp.dao;

import java.util.List;

public class ResponeDetail {
    private Boolean status;
    private String code;
    private String message;
    private Crypto data;

    public ResponeDetail(Boolean status, String code, String message, Crypto data) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.data = data;
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

    public Crypto getData() {
        return data;
    }

    public void setData(Crypto data) {
        this.data = data;
    }
}
