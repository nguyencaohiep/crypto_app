package com.example.cryptoapp.dao;

import java.util.List;

public class ResponeData {
    private Boolean status;
    private String code;
    private String message;
    private List<Crypto> data;


    public ResponeData(Boolean status, String code, String message, List<Crypto> data) {
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

    public List<Crypto> getData() {
        return data;
    }

    public void setData(List<Crypto> data) {
        this.data = data;
    }
}
