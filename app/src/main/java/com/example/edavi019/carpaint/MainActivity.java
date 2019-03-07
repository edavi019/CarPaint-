package com.example.edavi019.carpaint;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.security.auth.login.LoginException;


public class MainActivity extends AppCompatActivity {

    Button btnRun;
    ProgressBar progressBar;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRun = findViewById(R.id.btnRun);
        progressBar = findViewById(R.id.progressBar);

        btnRun.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                CheckLogin checkLogin = new CheckLogin();
                checkLogin.execute("");
            }
        });

    }

    @SuppressLint("StaticFieldLeak")
    public class CheckLogin extends AsyncTask<String,String,String>
    {
        String z = "";
        Boolean isSuccess = false;
        String email = "";

        protected void onPreExecute(){
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(String r){

            progressBar.setVisibility(View.GONE);
            Toast.makeText(MainActivity.this, r, Toast.LENGTH_LONG).show();
            if(isSuccess){

                tvResult = findViewById(R.id.tvResult);
                tvResult.setText(email);
            }
        }
        @Override
        protected String doInBackground(String... params){
            try{
                Connection con = connectionclass();   //connect to database
                if(con == null){
                    z = "Check Your Internet Access!";
                } else{
                    // Change below query according to your own database.
                    String query = "SELECT * FROM Usertest";
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(query);
                    if(rs.next()){

                        email = rs.getString("email"); //Name is the string.
                        z = "query successful";
                        isSuccess=true;
                        con.close();
                    }
                    else{
                        z = "Invalid Query!";
                        isSuccess = false;
                    }
                }
            }
            catch (Exception ex){

                isSuccess = false;
                z = ex.getMessage();

                Log.d ("sql error", z);
            }

            return z;
        }
    }

    @SuppressLint({"NewApi", "AuthLeak"})
    public Connection connectionclass(){

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String ConnectionURL;

        try{

            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            //database connection string below.
            ConnectionURL = "jdbc:jtds:sqlserver://carpaints.database.windows.net:1433;DatabaseName=CarPaints;user=cp_server_admin@carpaints;password=Immunematerial5;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
            connection = DriverManager.getConnection(ConnectionURL);
        }
        catch (SQLException se){

            Log.e("error here 1 : ", se.getMessage());
        }
        catch (ClassNotFoundException e){

            Log.e("error here 2 : ", e.getMessage());
        }
        catch (Exception e){

            Log.e("error here 3 : ", e.getMessage());
        }
        return connection;




    }

}