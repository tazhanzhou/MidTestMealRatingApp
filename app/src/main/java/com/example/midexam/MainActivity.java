package com.example.midexam;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements
        View.OnClickListener, AdapterView.OnItemSelectedListener {


    Spinner spinner;
    ImageView imageViewMeal;
    RatingBar ratingBar;
    Button btnAdd, btnShowAll, btnMeal, btnSalad;

    String listMeal[] = {"Beef", "Chicken", "Lamb"};
    int mealPicture[] = {R.drawable.beef, R.drawable.chicken, R.drawable.lamb};

    String listSalad[] = {"Pepper", "Spinach", "Corn"};
    int saladPicture[] = {R.drawable.pepper, R.drawable.spinach, R.drawable.corn};

    ArrayList<FoodRating> listOfFoodRating;
    ArrayAdapter<String> mealAdapter;
    ArrayAdapter<String> saladAdapter;
    int switchSource = 1;
    String customerNameString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
    }

    private void initialize() {

        listOfFoodRating = new ArrayList<>();

        // Reference to ratingBar.................................
        ratingBar = findViewById(R.id.ratingBar);
        //........................................................


        imageViewMeal = findViewById(R.id.imageView);

        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);

        btnShowAll = findViewById(R.id.btnShowAll);
        btnShowAll.setOnClickListener(this);

        btnMeal = findViewById(R.id.btnMeal);
        btnMeal.setOnClickListener(this);

        btnSalad = findViewById(R.id.btnSalad);
        btnSalad.setOnClickListener(this);


        // Initialize spinner -----------------------------------
        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        mealAdapter = new ArrayAdapter<>(this,
                R.layout.support_simple_spinner_dropdown_item,
                listMeal);
        spinner.setAdapter(mealAdapter);
        //-------------------------------------------------------
    }

    // AdapterView.OnItemSelectedListener ----------------------------------------------------------
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
        // 'parent' The AdapterView where the click happened
        // 'i' is index of selected item in spinner,
        // so we can assign the corresponding image reference
        // from our image array to our imageView
        if (switchSource == 1) {
            int image = mealPicture[i];
            imageViewMeal.setImageResource(image);
        } else {
            int image = saladPicture[i];
            imageViewMeal.setImageResource(image);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    //----------------------------------------------------------------------------------------------

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnAdd:
                addRating();
                break;

            case R.id.btnShowAll:
                showAllRating();
                break;
            case R.id.btnMeal:
                goMeal();
                break;
            case R.id.btnSalad:
                goSalad();
                break;
        }
    }

    private void addRating() {
        if (switchSource == 1) {
            String meal = spinner.getSelectedItem().toString();

            // Read ratingBar ....................................
            double rating = (double) ratingBar.getRating();
            //....................................................

            // Create new object and add it to our model array....
            MealRating mealRating = new MealRating(meal, rating);
            listOfFoodRating.add(mealRating);
            //....................................................

            // Reset rating bar for next time
            ratingBar.setRating(0);
        } else {
            String salad = spinner.getSelectedItem().toString();

            // Read ratingBar ....................................
            int rating = (int) ratingBar.getRating();
            //....................................................

            // Create new object and add it to our model array....
            SaladRating saladRating = new SaladRating(salad, rating);
            listOfFoodRating.add(saladRating);
            //....................................................

            // Reset rating bar for next time
            ratingBar.setRating(0);
        }
    }

    private void showAllRating() {
        Bundle bundle = new Bundle();
        bundle.putSerializable("bundleExtra", listOfFoodRating);

        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("intentExtra", bundle);
        startActivityForResult(intent,1);
    }

    private void goSalad() {
        switchSource = 2;
        saladAdapter = new ArrayAdapter<>(this,
                R.layout.support_simple_spinner_dropdown_item,
                listSalad);
        spinner.setAdapter(saladAdapter);
    }

    private void goMeal() {
        switchSource = 1;
        saladAdapter = new ArrayAdapter<>(this,
                R.layout.support_simple_spinner_dropdown_item,
                listMeal);
        spinner.setAdapter(mealAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String receivedData = (String) data.getStringExtra("return_result_tag");
        if (resultCode == RESULT_OK)
            setTitle(receivedData);
    }

}