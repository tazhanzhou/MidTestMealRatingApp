package com.example.midexam;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.Comparator;

public class FoodRating implements Comparable, Serializable {

    public String name;
    public double rating;


    public FoodRating(String name, double rating) {
        this.name = name;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }


    @Override
    public int compareTo(@NonNull Object o) {
        FoodRating otherObject = (FoodRating) o;
        return (this.getRating() < otherObject.getRating() ? -1 :
                (this.getRating() == otherObject.getRating() ? 0 : 1));
    }
}