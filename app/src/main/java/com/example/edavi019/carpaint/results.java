package com.example.edavi019.carpaint;

import android.speech.RecognitionListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class results extends AppCompatActivity {

    private static final String TAG = "results";

    //vars
    private ArrayList<String> mColorImage = new ArrayList<>();
    private ArrayList<String> mColorName = new ArrayList<>();
    private ArrayList<String> mColorCode = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
/*        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        Log.d(TAG, "onCreate: started");
        initImageBitMaps();
    }
    private void initImageBitMaps(){
        //Here, he put image urls and image name into the lists
        //ex] mColorImage.add("Https:/www.hahaha.com");
        //mColorName.add("fuckin red");

        mColorImage.add("#FF004965");
        mColorName.add("Inferno Red");
        mColorCode.add("P1234");

        mColorImage.add("#FF4E2B2D");
        mColorName.add("Baby Green");
        mColorCode.add("G1423");

        mColorImage.add("#FF004965");
        mColorName.add("supa pink");
        mColorCode.add("g10923434245");

        mColorImage.add("#FFD4D7D5");
        mColorName.add("Really really green ");
        mColorCode.add("P14");

        mColorImage.add("#FF3A5E71");
        mColorName.add("Bippidy bobbity boop");
        mColorCode.add("asdf34");

        mColorImage.add("#FFE5E4D9");
        mColorName.add("Bippidy bobbity boop");
        mColorCode.add("asdf34");

        mColorImage.add("#FF424345");
        mColorName.add("Bippidy bobbity boop");
        mColorCode.add("asdf34");

        //Write function to split database reply into 3 columns, hex, name, code, then add like above to list, itll be dynamic, values added come from db

        initRecyclerView();
        //make call to initRecyclerView
    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview");
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mColorImage, mColorName, mColorCode);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }*/
}
