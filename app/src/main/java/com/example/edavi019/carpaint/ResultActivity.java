package com.example.edavi019.carpaint;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {
     ArrayList<ResultLists> resultListsArrayList = new ArrayList<>();
     RecyclerViewAdapter recyclerViewAdapter;
    private RecyclerView recyclerView;
    private boolean success = false;
    RecyclerView.LayoutManager mLayoutManager;
    TextView tvYear,tvMake,tvModel;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.hasFixedSize();

        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        resultListsArrayList = new ArrayList<>();

        recyclerViewAdapter = new RecyclerViewAdapter(resultListsArrayList,ResultActivity.this);
        recyclerView.setAdapter(recyclerViewAdapter);


        tvYear = findViewById(R.id.tvYear);
        tvMake = findViewById(R.id.tvMake);
        tvModel = findViewById(R.id.tvModel);

        SyncData orderData = new SyncData();
        orderData.execute("");
    }

    @SuppressLint("StaticFieldLeak")
    private class SyncData extends AsyncTask<String,String,String>{

        Bundle bundle = getIntent().getExtras();

        String year = bundle.getString("year");
        String make = bundle.getString("make");
        String model = bundle.getString("model");


        @Override
        protected String doInBackground(String... strings) {
            Connection connection;
            try{
                ConnectionHelper conn = new ConnectionHelper();
                connection = conn.connectionclass();
                Log.d("value of year", year);
                Log.d("value of make", make);
                Log.d("value of year", model);
                if(connection == null){
                    success = false;
                }else{
                    String resultQuery = "Select Distinct Year, Make, Model, ColorName, ColorCode, Hex FROM CarDB WHERE Year = '" + year +
                            "' and Make = '" + make +
                            "' and Model = '" + model + "'";

                    Log.d("resultQuery", resultQuery);
                    Statement stm = connection.createStatement();
                    ResultSet rs = stm.executeQuery(resultQuery);
                    if(rs != null){
                        while(rs.next()){
                            try{
                                resultListsArrayList.add(new ResultLists(rs.getString("ColorName"),
                                        rs.getString("ColorCode"),
                                        rs.getString("Hex")));
                            }catch(Exception ex){
                                ex.printStackTrace();
                            }
                        }
                        success = true;
                    }else{
                        success = false;
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
                Writer writer = new StringWriter();
                e.printStackTrace(new PrintWriter(writer));
                Log.d("Writer", writer.toString());
                success = false;
            }
            return "didnt work" ;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(!success){
            }else{
                try{

                    tvYear.setText(year);
                    tvMake.setText(make);
                    tvModel.setText(model);


                    recyclerViewAdapter = new RecyclerViewAdapter(resultListsArrayList, ResultActivity.this);
                    recyclerView.setAdapter(recyclerViewAdapter);


                }catch (Exception Ex){

                }

            }


        }
    }

}
