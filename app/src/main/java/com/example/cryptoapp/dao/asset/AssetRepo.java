package com.example.cryptoapp.dao.asset;

import java.util.List;

public class AssetRepo {
    private String address;
    private Float total;
    private List<Asset> assets;

    public AssetRepo(String address, Float total, List<Asset> assets) {
        this.address = address;
        this.total = total;
        this.assets = assets;
    }

    public String getUserId() {
        return address;
    }

    public void setUserId(String userId) {
        this.address = userId;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public List<Asset> getAssets() {
        return assets;
    }

    public void setAssets(List<Asset> assets) {
        this.assets = assets;
    }
}
