package com.example.assignment_2;

import android.os.Parcel;
import android.os.Parcelable;

public class Product implements Parcelable {
    protected String product_Name;
    protected double product_Price;
    protected int product_quentity;

    public Product() {
    }

    protected Product(Parcel in) {
        product_Name = in.readString();
        product_Price = in.readDouble();
        product_quentity = in.readInt();
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

    public String getProduct_Name() {
        return product_Name;
    }

    public double getProduct_Price() {
        return product_Price;
    }

    public int getProduct_quentity() {
        return product_quentity;
    }

    public void setProduct_quentity(int product_quentity) {
        this.product_quentity = product_quentity;
    }

    public Product(String product_Name, double product_Price, int product_quentity) {
        this.product_Name = product_Name;
        this.product_Price = product_Price;
        this.product_quentity = product_quentity;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(product_Name);
        parcel.writeDouble(product_Price);
        parcel.writeInt(product_quentity);
    }
}

