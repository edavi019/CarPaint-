package com.example.edavi019.carpaint;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/*
public class Main2Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    ArrayList<String> yearList;
    ArrayList<String> makeList;
    ArrayList<String> modelList;

    Button btnRun;
    Spinner syear, smake, smodel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        private void initializeUI() {
            syear = findViewById(R.id.sYear);
            smake = findViewById(R.id.sMake);
            smodel = findViewById(R.id.sModel);

            yearList = new ArrayList<>();
            makeList = new ArrayList<>();
            modelList = new ArrayList<>();
            loadSpinnerYear();
            loadSpinnerMake();
            loadSpinnerModel();

       */
/* ArrayAdapter<String> yearAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, yearList);
        yearAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        syear.setAdapter(yearAdapter);

        ArrayAdapter<String> makeAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, makeList);
        makeAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        smake.setAdapter(makeAdapter);

        ArrayAdapter<String> modelAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, modelList);
        modelAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        smodel.setAdapter(makeAdapter);
*//*



            syear.setOnItemSelectedListener(this);
            smake.setOnItemSelectedListener(this);
            smodel.setOnItemSelectedListener(this);

        }

        private void loadSpinnerYear() {
            Log.d("first1", "LoadingSpinnerYear ");
            Connection connection;
            ArrayList<String> year = new ArrayList<>();
            try {
                ConnectionHelper conn = new ConnectionHelper();
                connection = conn.connectionclass();
                String selectQuery = "Select Distinct Year From CarDB ORDER BY Year DESC";
                Statement stm = connection.createStatement();
                ResultSet rs = stm.executeQuery(selectQuery);
                Log.d("first2", "lSpinnerYEar ");
                while (rs.next()) {
                    String id = rs.getString("Year");
                    year.add(id);
                }
                String[] array = year.toArray(new String[0]);
                ArrayAdapter<String> yearAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, year);
                yearAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                syear.setAdapter(yearAdapter);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        private void loadSpinnerMake() {
            Connection connection;
            try {
                ConnectionHelper conn = new ConnectionHelper();
                connection = conn.connectionclass();
                String selectQuery = "Select Distinct Make From CarDB";
                Statement stm = connection.createStatement();
                ResultSet rs = stm.executeQuery(selectQuery);
                ArrayList<String> make = new ArrayList<>();
                Log.d("first2", "LoadingSpinnerMake ");
                while (rs.next()) {
                    String id = rs.getString("Make");
                    make.add(id);
                }
                String[] array = make.toArray(new String[1]);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        private void loadSpinnerModel() {
            Connection connection;
            try {
                ConnectionHelper conn = new ConnectionHelper();
                connection = conn.connectionclass();
                String selectQuery = "Select Distinct Model From CarDB";
                Statement stm = connection.createStatement();
                ResultSet rs = stm.executeQuery(selectQuery);
                ArrayList<String> model = new ArrayList<>();
                Log.d("first2", "LoadingSpinnerModel");
                while (rs.next()) {
                    String id = rs.getString("Model");
                    model.add(id);
                }
                String[] array = model.toArray(new String[2]);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String year = syear.getSelectedItem().toString();
            //loadSpinnerMake();
            Connection connection;
            ArrayList<String> make = new ArrayList<>();
            try {
                ConnectionHelper conn = new ConnectionHelper();
                connection = conn.connectionclass();
                String makeQuery = "Select Distinct Make from CarDB Where Year = " + year;
                Statement stm = connection.createStatement();
                ResultSet rs = stm.executeQuery(makeQuery);
                Log.d("first2", "loadSpinnerData: 2 ");
                while (rs.next()) {
                    String make1 = rs.getString("Make");
                    make.add(make1);
                    Log.d("the make selected is ", make1);
                }
                String[] array = make.toArray(new String[0]);
                ArrayAdapter<String> makeAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, make);
                makeAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                smake.setAdapter(makeAdapter);

            } catch (SQLException e) {
                e.printStackTrace();
            }
            Log.d("the year selected is ", year);


            // Showing selected spinner item
            Toast.makeText(parent.getContext(), "You selected: " + year,
                    Toast.LENGTH_LONG).show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

}
*/
