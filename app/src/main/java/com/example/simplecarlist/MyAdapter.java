package com.example.simplecarlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends ArrayAdapter<Car> {
    private List<Car> cars = new ArrayList<>();
    private int layoutId;
    private LayoutInflater inflater;

    public MyAdapter(Context context, int resource, List<Car> objects) {
        super(context, resource, objects);

        this.cars = objects;
        this.layoutId = resource;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Car car = cars.get(position);
        View listItem = (convertView == null) ? inflater.inflate(this.layoutId, null) : convertView;

        ((TextView) listItem.findViewById(R.id.vorname)).setText(car.getFirstName());
        ((TextView) listItem.findViewById(R.id.nachname)).setText(car.getLastName());
        ((TextView) listItem.findViewById(R.id.marke)).setText(car.getBrand());
        ((TextView) listItem.findViewById(R.id.model)).setText(car.getModel());
        return listItem;
    }
}
