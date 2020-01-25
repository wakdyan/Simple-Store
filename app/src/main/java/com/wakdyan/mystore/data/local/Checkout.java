package com.wakdyan.mystore.data.local;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Checkout {
    @PrimaryKey
    @ColumnInfo(name = "id")
    private int mId;

    @ColumnInfo(name = "amount")
    private int mAmount;

    @ColumnInfo(name = "name")
    private String mName;

    @ColumnInfo(name = "price")
    private String mPrice;

    @ColumnInfo(name = "url")
    private String mUrl;

    public Checkout(int id, int amount, String name, String price, String url) {
        mId = id;
        mAmount = amount;
        mName = name;
        mPrice = price;
        mUrl = url;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public int getAmount() {
        return mAmount;
    }

    public void setAmount(int amount) {
        mAmount = amount;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getPrice() {
        return mPrice;
    }

    public void setPrice(String price) {
        mPrice = price;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }
}
