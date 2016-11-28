package com.example.souvik.jsoupdemo.models;

/**
 * Created by souvik on 27/11/16.
 */

public class MovieModels {

    private String imageURL;
    private String name;
    private double rating;

    public String getName(){
        return name;
    }

    public void setName(String s){
        name = s;
    }

    public String getImageURL(){
        return imageURL;
    }

    public void setImageURL(String s){
        imageURL = s;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
