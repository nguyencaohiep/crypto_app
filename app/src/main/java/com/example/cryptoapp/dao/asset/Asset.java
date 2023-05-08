package com.example.cryptoapp.dao.asset;

public class Asset {
    private String tokenName;
    private Float amount;
    private String image;


    public Asset(String tokenName, Float amount, String image) {
        this.tokenName = tokenName;
        this.amount = amount;
        this.image = image;
    }

    public String getTokenName() {
        return tokenName;
    }

    public void setTokenName(String tokenName) {
        this.tokenName = tokenName;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
