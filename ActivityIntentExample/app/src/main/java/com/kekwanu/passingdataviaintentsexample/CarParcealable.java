package com.kekwanu.passingdataviaintentsexample;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by onwuneme on 11/19/14.
 */
public class CarParcealable implements Parcelable {
    private String make;
    private String model;
    private String year;

    private static final long serialVersionUID = 45L;

    public CarParcealable(String make, String model, String year){

        this.make = make;
        this.model = model;
        this.year = year;
    }

    public String getMake() {
        return make;
    }

    public String getModel(){
        return model;
    }

    public String getYear() {
        return year;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public CarParcealable(Parcel in){
        readFromParcel(in);
    }

    public static final Parcelable.Creator<CarParcealable> CREATOR = new Creator<CarParcealable>() {
        public CarParcealable createFromParcel(Parcel source) {

            return new CarParcealable(source);
        }
        public CarParcealable[] newArray(int size) {
            return new CarParcealable[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(make);
        dest.writeString(model);
        dest.writeString(year);
    }

    public void readFromParcel(Parcel source){
        make = source.readString();
        model = source.readString();
        year = source.readString();
    }
}
