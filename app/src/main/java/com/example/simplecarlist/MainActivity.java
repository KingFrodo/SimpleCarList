package com.example.simplecarlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    File file = new File("cars.csv");
    ListView listView;
    List<Car> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);
        list.add(new Car("Lukas", "Foro", "Audi", "Panzer"));
        list.add(new Car("Michael", "Gahbauer", "Mercedes", "Flugzeug"));
        list.add(new Car("Florian", "Eggetsberger", "VW", "Schiff"));

        MyAdapter myAdapter = new MyAdapter(this, R.layout.list_view_layout, list);
        listView.setAdapter(myAdapter);
    }
}
