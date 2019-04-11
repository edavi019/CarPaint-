package com.example.edavi019.carpaint;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginActivity extends AppCompatActivity {

    EditText etEmail, etPassword;
    Button btnLogin, btnRegister;
    ProgressBar loginprogress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        loginprogress = findViewById(R.id.loginProgress);

        loginprogress.setVisibility(View.GONE);

      /*  btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);*/

      btnLogin.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent = new Intent(v.getContext(),MainActivity.class);
              CheckLogin checkLogin = new CheckLogin();
              checkLogin.execute("");
              v.getContext().startActivity(intent);

          }
      });
      btnRegister.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent = new Intent(v.getContext(),CreateAccountActivity.class);
              v.getContext().startActivity(intent);


          }
      });

    }



    @SuppressLint("StaticFieldLeak")
    public class CheckLogin extends AsyncTask<String,String,String>{
        String z  = "";
        Boolean isSuccess = false;

        @Override
        protected void onPreExecute() {
            loginprogress.setVisibility(View.VISIBLE);
/*
            super.onPreExecute();
*/
        }

        @Override
        protected String doInBackground(String... strings) {

            String email = etEmail.getText().toString();
            String password = etPassword.getText().toString();
            Log.d("my email1", email);
            Log.d("my password1", password);
            if(email.trim().equals("") || password.trim().equals("")) {
                z = "Please enter username and password";
            }else{
                try {
                    Connection con = connectionclass();   //connect to database
                    if(con == null){
                        z = "Check Your Internet Access!";
                }else{
                        String query = "Select * from Usertest where email = " + email + "and password =" + password;
                        Statement stm = con.createStatement();
                        Log.d("my email2", email);
                        Log.d("my password2", password);
                        ResultSet rs = stm.executeQuery(query);
                        if(rs.next()){
                            z = "Login Successful";
                            isSuccess = true;
                            con.close();
                        }else{
                            z = "Invalid Credentials";
                            isSuccess = false;
                        }
                    }
                }catch (Exception ex){
                    isSuccess = false;
                    z = ex.getLocalizedMessage();
                }
            }
            return z;
        }
        @Override
        protected void onPostExecute(String s) {

            if(isSuccess){
                Toast.makeText(LoginActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
            }

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

   /* @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //Button Login
            case R.id.btnLogin:
                CheckLogin checkLogin = new CheckLogin();
                checkLogin.execute("");
                startActivity(new Intent(this, MainActivity.class));
                break;

            //Button Register
            case R.id.btnRegister:
                startActivity(new Intent(this, CreateAccountActivity.class));
                break;
        }

    }*/
}