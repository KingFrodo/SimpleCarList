package com.example.simplecarlist;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.AssetManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    MyAdapter myAdapter;
    File file = new File("cars.csv");
    ListView listView;
    List<Car> cars = new ArrayList<>();
    List<String> brands = new ArrayList<>();
    List<String> carStrings = new ArrayList<>();
    Spinner hersteller;
    View view;
    EditText fn;
    EditText nn;
    EditText ma;
    EditText mo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view=View.inflate(this, R.layout.dialog, null);
        brands.add(" ");
        listView = (ListView) findViewById(R.id.listView);
        hersteller = (Spinner) findViewById(R.id.hersteller);
        fn = (EditText) view.findViewById(R.id.fn);
        nn = (EditText) view.findViewById(R.id.nn);
        ma = (EditText) view.findViewById(R.id.ma);
        mo = (EditText) view.findViewById(R.id.mo);

        readAssets();
        for (int i = 0; i < cars.size(); i++) {
            brands.add(cars.get(i).getBrand());
            carStrings.add(cars.get(i).getFirstName() + " " + cars.get(i).getLastName() + " " + cars.get(i).getBrand() + " " + cars.get(i).getModel());
        }

        myAdapter = new MyAdapter(this, R.layout.list_view_layout, cars);
        listView.setAdapter(myAdapter);

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, brands);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hersteller.setAdapter(arrayAdapter);
        filterBySpinner();

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

        FloatingActionButton addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog();
            }
        });
    }

    private InputStream getInputStreamForAsset(String filename){
        AssetManager assets = getAssets();

        try {//14
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

    private void filterBySpinner(){
        String text = hersteller.getSelectedItem().toString();

        if(text.equals(" ")){
        }

        else{
            myAdapter.getFilter().filter(text);
        }
    }

    private void dialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view).setCancelable(false).setNegativeButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String fnString = fn.getText().toString();
                String nnString = nn.getText().toString();
                String maString = ma.getText().toString();
                String moString = mo.getText().toString();

                Car car = new Car(fnString, nnString, maString, moString);
                cars.add(car);
                carStrings.add(fnString + " " + nnString + " " + maString + " " + moString);

                try {
                    FileOutputStream fos = openFileOutput("cars.csv", MODE_APPEND);
                    PrintWriter pw = new PrintWriter(new OutputStreamWriter(fos));
                    pw.println(cars.size() + "," + fnString + "," + nnString + "," + "/,/,/,/,/,/,/,/," + maString + "," + moString +",/,/");
                    pw.flush();
                    pw.close();
                } catch(IOException ex){
                    ex.printStackTrace();
                }

                dialog.cancel();
            }
        }).show();
    }
}
