package com.example.assignment_2;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class HistoryProduct extends Product implements Parcelable {

    String purshaseDate;

    public HistoryProduct(String product_Name, double product_Price, int product_quentity, String purshaseDate) {
        super(product_Name, product_Price, product_quentity);
        this.purshaseDate = purshaseDate;
    }

    protected HistoryProduct(Parcel in) {
        // name price quentity
      //  product_Name = in.readString();
        super(in);
        purshaseDate = in.readString();

    }

    public static final Creator<HistoryProduct> CREATOR = new Creator<HistoryProduct>() {
        @Override
        public HistoryProduct createFromParcel(Parcel in) {
            return new HistoryProduct(in);
        }

        @Override
        public HistoryProduct[] newArray(int size) {
            return new HistoryProduct[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel,i);
        parcel.writeString(purshaseDate);
    }
}
