package com.kekwanu.passingdataviaintentsexample;

import java.io.Serializable;

/**
 * Created by onwuneme on 11/19/14.
 */
public class CarSerializable implements Serializable {
    private String make;
    private String model;
    private String year;

    private static final long serialVersionUID = 45L;

    public CarSerializable(String make, String model, String year){

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
}
