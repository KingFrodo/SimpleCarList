package com.example.simplecarlist;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

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
    List<String> firstNames = new ArrayList<>();
    List<String> lastNames = new ArrayList<>();
    List<String> brands = new ArrayList<>();
    List<String> models = new ArrayList<>();
    List<String> carStrings = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);
        readAssets();
        for (int i = 0; i < cars.size(); i++) {
            carStrings.add(cars.get(i).getFirstName() + cars.get(i).getLastName() + cars.get(i).getBrand() + cars.get(i).getModel());
        }

        final MyAdapter myAdapter = new MyAdapter(this, R.layout.list_view_layout, cars);
        listView.setAdapter(myAdapter);

        SearchView searchView = (SearchView) findViewById(R.id.searchView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                if (carStrings.contains(query)) {
                    myAdapter.getFilter().filter(query);
                } else {
                    Toast.makeText(MainActivity.this, "No Match found", Toast.LENGTH_LONG).show();
                }

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                myAdapter.getFilter().filter(newText);
                return false;
            }
        });
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
