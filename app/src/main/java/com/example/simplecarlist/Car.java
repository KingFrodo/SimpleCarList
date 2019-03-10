package com.example.simplecarlist;

import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Car{
    String firstName;
    String lastName;
    String model;
    String brand;
    List<Car> cars = new ArrayList<>();

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
}
