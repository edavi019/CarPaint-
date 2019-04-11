package com.example.edavi019.carpaint;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Spinner spinnerMake = findViewById(R.id.spinnerMake);

        ArrayAdapter<String> makesAdapter =  new ArrayAdapter<String>(Main2Activity.this,
                R.layout.support_simple_spinner_dropdown_item, getResources().getStringArray(R.array.makes));
        makesAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerMake.setAdapter(makesAdapter);
    }
}
