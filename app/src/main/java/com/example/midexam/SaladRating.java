package com.example.midexam;

import androidx.annotation.NonNull;

public class SaladRating extends FoodRating{

    public SaladRating(String name, double rating) {
        super(name, rating);
    }
    @Override
    public String toString() {
        return "Salad: " + name + " Rating: " + rating;
    }
}
