package com.example.food_master;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class food_register extends AppCompatActivity {

    Spinner spnFood;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food);

        spnFood = findViewById(R.id.spnChoose);

        ArrayAdapter GiveAdapter = ArrayAdapter.createFromResource(this,
                R.array.음식, android.R.layout.simple_spinner_item);
        GiveAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnFood.setAdapter(GiveAdapter);

        findViewById(R.id.btnRegister).setOnClickListener(
                new Button.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                        DatabaseReference rootRef = firebaseDatabase.getReference();
                        String Food = spnFood.getSelectedItem().toString();

                        food food = new food(Food);

                        DatabaseReference personRef = rootRef.child("food");
                        personRef.push().setValue(food);
                    }
                });
    }
}
