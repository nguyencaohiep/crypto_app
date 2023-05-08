package com.example.cryptoapp.dao;

import com.example.cryptoapp.dao.asset.AssetRepo;

import java.util.List;

public class ResponeAssets {
    private Boolean status;
    private String code;
    private String message;
    private AssetRepo data;

    public ResponeAssets(Boolean status, String code, String message, AssetRepo data) {
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

    public AssetRepo getData() {
        return data;
    }

    public void setData(AssetRepo data) {
        this.data = data;
    }
}
