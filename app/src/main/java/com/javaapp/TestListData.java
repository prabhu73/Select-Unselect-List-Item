package com.javaapp;

import android.os.Parcel;
import android.os.Parcelable;

public class TestListData implements Parcelable {

    private String name;
    private long amount;
    private int id;
    private boolean isItemSelected;

    public TestListData() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getItemSelected() {
        return isItemSelected;
    }

    public void setItemSelected(boolean itemSelected) {
        isItemSelected = itemSelected;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeLong(this.amount);
        dest.writeInt(this.id);
        dest.writeValue(this.isItemSelected);
    }

    protected TestListData(Parcel in) {
        this.name = in.readString();
        this.amount = in.readLong();
        this.id = in.readInt();
        this.isItemSelected = (boolean) in.readValue(Boolean.class.getClassLoader());
    }

    public static final Parcelable.Creator<TestListData> CREATOR = new Parcelable.Creator<TestListData>() {
        @Override
        public TestListData createFromParcel(Parcel source) {
            return new TestListData(source);
        }

        @Override
        public TestListData[] newArray(int size) {
            return new TestListData[size];
        }
    };
}
