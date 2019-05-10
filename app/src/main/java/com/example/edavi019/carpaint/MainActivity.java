package com.example.edavi019.carpaint;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    ArrayList<String> yearList;
    ArrayList<String> makeList;
    ArrayList<String> modelList;
    ArrayList<ResultLists> resultList;
    private boolean success = false;


    Button btnRun;
    Spinner syear, smake, smodel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        /*loadSpinnerYear();
        loadSpinnerMake();
        loadSpinnerModel();*/

        initializeUI();


        btnRun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GatherInfo();


            }
        });

    }


    private void GatherInfo() {
        String year = (String) syear.getSelectedItem();
        String make = (String) smake.getSelectedItem();
        String model = (String) smodel.getSelectedItem();

        Intent i = new Intent(this, ResultActivity.class);
        String SpinYear = year;
        String SpinMake = make;
        String SpinModel = model;

        Bundle bundle = new Bundle();
        bundle.putString("year", SpinYear);
        bundle.putString("make", SpinMake);
        bundle.putString("model", SpinModel);
        i.putExtras(bundle);
        startActivity(i);
    }




/*
        Connection connection;
        try {
            ConnectionHelper conn = new ConnectionHelper();
            connection = conn.connectionclass();
            String resultQuery = "Select Distinct Year, Make, Model, ColorName, ColorCode, Hex FROM CarDB WHERE Year ='" + year + "' " +
                    "and Make = '" + make + "'" + model + "'";
            //String selectQuery = "Select Year, Make, Model, 'Color Name','Color Code' From CarDB WHERE Year =" + year
            //        + " and Make = " + make + "AND Model = " + model;
            // "Select Distinct Model from CarDB Where Year ='" + year1 + "' and Make = '" + make1 + "'";
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(resultQuery);
            if(rs.next()){
                connection.close();
            }else{
                Log.d("test", "GatherInfo:");
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }

    }*/

    private void initializeUI() {
        syear = findViewById(R.id.sYear);
        smake = findViewById(R.id.sMake);
        smodel = findViewById(R.id.sModel);
        btnRun = findViewById(R.id.btnRun);

        loadSpinnerYear();
        loadSpinnerMake();
        //loadSpinnerModel();
        ModelData();
        yearList = new ArrayList<>();
        makeList = new ArrayList<>();
        modelList = new ArrayList<>();
        resultList = new ArrayList<>();


        //smodel.setSelection(0,false);
        //modelList.add(0,"Select a Model");

       /* SyncData orderData = new SyncData();
        orderData.execute("");*/


        syear.setOnItemSelectedListener(year_listener);
        smake.setOnItemSelectedListener(make_listener);
        smodel.setOnItemSelectedListener(model_listener);


    }


    private AdapterView.OnItemSelectedListener year_listener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            Log.d("first1", "LoadingSpinnerYear ");
            Connection connection;
            ArrayList<String> year = new ArrayList<>();
            //yearList.add(0,"Select a Year");
            try {
                ConnectionHelper conn = new ConnectionHelper();
                connection = conn.connectionclass();
                String selectQuery = "Select Distinct Year From CarDB ORDER BY Year DESC";
                Statement stm = connection.createStatement();
                ResultSet rs = stm.executeQuery(selectQuery);
                Log.d("first2", "lSpinnerYEar ");
                if (!rs.next()) {
                    Log.d("nothings next", "Nothings in the Set ");
                } else {
                    do {
                        String id1 = rs.getString("Year");
                        year.add(id1);
                        //Log.d("the year selected is ", String.valueOf(year));
                    }
                    while (rs.next());
                    {
                        //This calls the function that populates Make Spinner with the Updated Query
                        MakeData();
                        String[] array = year.toArray(new String[0]);
                        ArrayAdapter<String> yearAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, year);
                        //yearAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                        //syear.setAdapter(yearAdapter);
                    }

                }
                // yearList.add(0,"Select a Year");


            } catch (SQLException e) {
                e.printStackTrace();

            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    private AdapterView.OnItemSelectedListener make_listener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            ModelData();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    private AdapterView.OnItemSelectedListener model_listener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    private void MakeData() {
        String year1 = syear.getSelectedItem().toString();
        Connection connection;
        PreparedStatement preparedStatement;
        ArrayList<String> make = new ArrayList<>();
        //This calls the Model with updated query from the database
        ModelData();
        try {
            ConnectionHelper conn = new ConnectionHelper();
            connection = conn.connectionclass();
            //String makeQuery = "Select Distinct Make from CarDB Where Year = ?";
            //preparedStatement = conn.connectionclass().prepareStatement(makeQuery);
            //preparedStatement.setString(1,year1);
            //preparedStatement.executeUpdate();
            //ResultSet rs = preparedStatement.executeQuery();
            String makeQuery = "Select Distinct Make from CarDB Where Year = " + year1;
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(makeQuery);
            Log.d("first2", "loadSpinnerData: 2 ");
            if (!rs.next()) {
                Log.d("nothings next", "Nothings in the Set ");
            } else {
                do {
                    String make1 = rs.getString("Make");
                    make.add(make1);
                    Log.d("the make selected is ", make1);
                }
                while (rs.next());
                String[] array = make.toArray(new String[0]);
                ArrayAdapter<String> makeAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, make);
                //makeAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                smake.setAdapter(makeAdapter);

            }
            //With ModelData here it only updated sometimes
            //ModelData();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void ModelData() {
        String year1 = syear.getSelectedItem().toString();
        String make1 = smake.getSelectedItem().toString();
        PreparedStatement preparedStatement;
        Log.d("the make is ", make1);
        Connection connection;
        ArrayList<String> model = new ArrayList<>();
        try {
            ConnectionHelper conn = new ConnectionHelper();
            connection = conn.connectionclass();
            //String modelQuery = "Select Distinct Model from CarDB Where Year = ?  and Make = ?";
            String modelQuery = "Select Distinct Model from CarDB Where Year ='" + year1 + "' and Make = '" + make1 + "'";
            Log.d("The Statement sent1", modelQuery);
            //preparedStatement = conn.connectionclass().prepareStatement(modelQuery);
            //preparedStatement.setString(1,year1);
            //preparedStatement.setString(2,make1);
            //ResultSet rs = preparedStatement.executeQuery();
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(modelQuery);
            Log.d("The Statement sent2", modelQuery);
            Log.d("first2", "loadSpinnerData: Model");
            if (!rs.next()) {
                Log.d("nothings next", "Nothings in the Set ");
            } else {
                do {
                    String model1 = rs.getString("Model");
                    model.add(model1);
                    Log.d("Models in Spinner:", model1);

                }
                while (rs.next());
                //String model1 = rsModel.getString("Model");
                //model.add(model1);
                String[] array = model.toArray(new String[0]);
                ArrayAdapter<String> modelAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, model);
                //modelAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                smodel.setAdapter(modelAdapter);
                String model3 = smodel.getSelectedItem().toString();
                Log.d("Selected model is:", model3);
                //modelList.add(0,"Select a Year");

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
                /*String[] array = model.toArray(new String[0]);
                ArrayAdapter<String> modelAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, model);
                modelAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);*/
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
        ArrayList<String> make = new ArrayList<>();
        try {
            ConnectionHelper conn = new ConnectionHelper();
            connection = conn.connectionclass();
            String selectQuery = "Select Distinct Make From CarDB ORDER BY Make ASC";
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(selectQuery);
            Log.d("first2", "LoadingSpinnerMake ");
            while (rs.next()) {
                String id = rs.getString("Make");
                make.add(id);
            }
            String[] array = make.toArray(new String[0]);
            ArrayAdapter<String> makeAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, make);
            makeAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
            smake.setAdapter(makeAdapter);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadSpinnerModel() {
        Connection connection;
        ArrayList<String> model = new ArrayList<>();
        PreparedStatement preparedStatement;
        try {
            ConnectionHelper conn = new ConnectionHelper();
            connection = conn.connectionclass();
            //String modelQuery = "Select Distinct ? from CarDB";
            String selectQuery = "Select Distinct Model From CarDB Order By Model ASC";
            //preparedStatement = conn.connectionclass().prepareStatement(modelQuery);
            //preparedStatement.setString(1,"Model");
            //ResultSet rs = preparedStatement.executeQuery();
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(selectQuery);
            Log.d("first2", "LoadingSpinnerModel");
            while (rs.next()) {
                String id = rs.getString("Model");
                model.add(id);
            }
            String[] array = model.toArray(new String[0]);
            ArrayAdapter<String> modelAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, model);
            modelAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
            smodel.setAdapter(modelAdapter);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

    /*private class SyncData extends AsyncTask<String, String, String> {
        String year = (String) syear.getSelectedItem();
        String make = (String) smake.getSelectedItem();
        String model = (String) smodel.getSelectedItem();

        ProgressDialog progress;

        @Override
        protected String doInBackground(String... strings) {
            Connection connection;
            String z = "hello";
            try {
                ConnectionHelper conn = new ConnectionHelper();
                connection = conn.connectionclass();
                if (connection == null) {
                    z = "Connection failed";
                } else {
                    String resultQuery = "Select Distinct Year, Make, Model, ColorName, ColorCode, Hex FROM CarDB WHERE Year ='" + year + "' " +
                            "and Make = '" + make + "'" + model + "'";
                    Statement stm = connection.createStatement();
                    ResultSet rs = stm.executeQuery(resultQuery);
                    if (rs != null) {
                        while (rs.next()) {
                            try {
                                resultList.add(String.valueOf(new ResultLists(rs.getString("Year"),
                                        rs.getString("Make"),
                                        rs.getString("Model"),
                                        rs.getString("ColorName"),
                                        rs.getString("ColorCode"),
                                        rs.getString("Hex"))));
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    } else {
                        z = "No data found";

                    }
                }


            } catch (SQLException e) {
                e.printStackTrace();
            }
            {
            }
            return z;
        }

        @Override
        protected void onPostExecute(String args) {
            Connection connection = null;
            if(connection == null){
            }else {
                try{
                    //RecyclerViewAdapter adapter = new RecyclerViewAdapter (MainActivity.this,resultList);

                    //RecyclerViewAdapter = new RecyclerViewAdapter(resultList, MainActivity.this);
                    
                }catch{
                    
                }
            }
            }
        }
    }*/