package com.example.midexam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static java.lang.Math.abs;

public class ResultActivity extends AppCompatActivity {
    RadioGroup radioGroup;
    Button btnBack;
    EditText editTextName;
    ListView listView;
    ArrayList<FoodRating> listOfFoodRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        initialize();
    }

    private void initialize() {
        radioGroup = findViewById(R.id.radioGroup);
        btnBack = findViewById(R.id.btnBack);
        editTextName = findViewById(R.id.editTextName);
        listView = findViewById(R.id.listView);

        Bundle bundle = getIntent().getBundleExtra("intentExtra");
        Serializable bundledListOfRatings = bundle.getSerializable("bundleExtra");
        listOfFoodRating = (ArrayList<FoodRating>) bundledListOfRatings;

        showDataInListView(listOfFoodRating);
    }

    public void radioGroup(View view) {
        int selectedRadioBtn = radioGroup.getCheckedRadioButtonId();
        switch (selectedRadioBtn) {
            case R.id.rBtnSortD:
                Comparator c = Collections.reverseOrder();
                Collections.sort(listOfFoodRating, c);
                showDataInListView(listOfFoodRating);
                break;
            case R.id.rBtnSortA:
                Collections.sort(listOfFoodRating);
                showDataInListView(listOfFoodRating);
                break;
            case R.id.rBtn1S:
                showDataInListView(filterRatingByStar(listOfFoodRating, 1));
                break;
            case R.id.rBtn2S:
                showDataInListView(filterRatingByStar(listOfFoodRating, 2));
                break;
            case R.id.rBtn3S:
                showDataInListView(filterRatingByStar(listOfFoodRating, 3));
                break;
        }

    }

    private void showDataInListView(ArrayList<FoodRating> a) {
        String[] data = new String[a.size()];
        for (int i = 0; i < a.size(); i++) {
            data[i] = a.get(i).toString();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                ResultActivity.this, android.R.layout.simple_list_item_1, data);
        listView.setAdapter(adapter);
    }

    private ArrayList<FoodRating> filterRatingByStar(ArrayList<FoodRating> a, int starNum) {
        ArrayList<FoodRating> ratingListByStar = new ArrayList<>();
        for (FoodRating r : a) {
            if (abs(r.getRating() - starNum) < 0.1) {
                ratingListByStar.add(r);
            }
        }
        return ratingListByStar;
    }

    public void goBack(View view) {
        if (editTextName.getText().length() != 0) {
            String welcome = "Thank you " + editTextName.getText();
            Intent intent = new Intent();
            intent.putExtra("return_result_tag", welcome);
            setResult(RESULT_OK, intent);
            finish();
        } else Toast.makeText(this,
                "Please enter your name",
                Toast.LENGTH_SHORT).show();
    }
}
