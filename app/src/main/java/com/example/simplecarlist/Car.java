package com.example.simplecarlist;

import java.io.File;

public class Car {
    String firstName;
    String lastName;
    String model;
    String brand;

    public Car(String firstName, String lastName, String brand, String model) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.model = model;
        this.brand = brand;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getModel() {
        return model;
    }

    public String getBrand() {
        return brand;
    }

    public void readCsv(File file){

    }

    public void writeCsv(File file){

    }
}
