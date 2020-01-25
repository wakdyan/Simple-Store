package com.wakdyan.mystore.data.local;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Product implements Parcelable{
    @PrimaryKey()
    @ColumnInfo(name = "id")
    private int mId;

    @ColumnInfo(name = "vote")
    private double mVoteAverage;

    @ColumnInfo(name = "favorite")
    private boolean isFavorite;

    @ColumnInfo(name = "name")
    private String mName;

    @ColumnInfo(name = "description")
    private String mDescription;

    @ColumnInfo(name = "price")
    private String mPrice;

    @ColumnInfo(name = "url")
    private String mUrl;

    public Product(int id, double voteAverage, String name, String description, String price, String url) {
        mId = id;
        mVoteAverage = voteAverage;
        mName = name;
        mDescription = description;
        mPrice = price;
        mUrl = url;
    }

    protected Product(Parcel in) {
        mId = in.readInt();
        mVoteAverage = in.readDouble();
        isFavorite = in.readByte() != 0;
        mName = in.readString();
        mDescription = in.readString();
        mPrice = in.readString();
        mUrl = in.readString();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public double getVoteAverage() {
        return mVoteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        mVoteAverage = voteAverage;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(mId);
        parcel.writeDouble(mVoteAverage);
        parcel.writeByte((byte) (isFavorite ? 1 : 0));
        parcel.writeString(mName);
        parcel.writeString(mDescription);
        parcel.writeString(mPrice);
        parcel.writeString(mUrl);
    }
}
