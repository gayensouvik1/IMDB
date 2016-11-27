package com.example.souvik.jsoupdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.souvik.jsoupdemo.models.MovieModels;

import java.util.List;

/**
 * Created by souvik on 27/11/16.
 */

public class MovieAdapter extends ArrayAdapter {

    private List<MovieModels> movieModelsList;
    private int resource;
    private LayoutInflater inflater;
    private Context context;

    public MovieAdapter(Context context, int resource, List<MovieModels> objects) {
        super(context, resource,objects);
        movieModelsList = objects;
        this.resource = resource;
        this.context = context;

    }

    public View getView(int position, View convertView, ViewGroup parent){

        if(convertView==null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.content,null);

        }

        TextView name;
        name = (TextView)convertView.findViewById(R.id.name);
        name.setText(movieModelsList.get(position).getName());

        return convertView;
    }

    
}
