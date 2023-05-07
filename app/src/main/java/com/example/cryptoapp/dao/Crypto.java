package com.example.cryptoapp.dao;

public class Crypto {
//    private int id;
    private int id;
    private String name;
    private String symbol;
    private String type;
    private float totalSupply;
    private String image;
    private float marketCap;
    private float volume24h;
    private float priceUSD;

    public Crypto(int id, String name, String symbol, String type, float totalSupply, String image, float marketCap,
                  float volume24h, float priceUSD) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.type = type;
        this.totalSupply = totalSupply;
        this.image = image;
        this.marketCap = marketCap;
        this.volume24h = volume24h;
        this.priceUSD = priceUSD;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getTotalSupply() {
        return totalSupply;
    }

    public void setTotalSupply(float totalSupply) {
        this.totalSupply = totalSupply;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(float marketCap) {
        this.marketCap = marketCap;
    }

    public float getVolume24h() {
        return volume24h;
    }

    public void setVolume24h(float volume24h) {
        this.volume24h = volume24h;
    }

    public float getPriceUSD() {
        return priceUSD;
    }

    public void setPriceUSD(float priceUSD) {
        this.priceUSD = priceUSD;
    }
}
