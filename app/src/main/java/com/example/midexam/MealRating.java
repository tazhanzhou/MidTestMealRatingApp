package com.example.midexam;

import androidx.annotation.NonNull;

public class MealRating extends FoodRating {

    public MealRating(String name, double rating) {
        super(name, rating);
    }

    @Override
    public String toString() {
        return "Meal: " + name + " Rating: " + rating;
    }

}
