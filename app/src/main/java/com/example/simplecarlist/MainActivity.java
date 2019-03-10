package com.example.simplecarlist;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    File file = new File("cars.csv");
    ListView listView;
    List<Car> cars = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);
        readAssets();

        MyAdapter myAdapter = new MyAdapter(this, R.layout.list_view_layout, cars);
        listView.setAdapter(myAdapter);
    }

    private InputStream getInputStreamForAsset(String filename){
        AssetManager assets = getAssets();

        try {
            return assets.open(filename);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void readAssets(){
        InputStream in = getInputStreamForAsset(file.getName());
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String line;

        try {
            line = br.readLine();

            while ((line = br.readLine()) != null) {
                String[] split = line.split(",");
                String firstName = split[1];
                String lastName = split[2];
                String brand = split[11];
                String model = split[12];
                
                Car car = new Car(firstName, lastName, brand, model);
                cars.add(car);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
