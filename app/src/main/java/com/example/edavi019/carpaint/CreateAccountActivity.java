package com.example.edavi019.carpaint;

import android.annotation.SuppressLint;
import android.content.AsyncQueryHandler;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.*;


public class CreateAccountActivity extends AppCompatActivity implements View.OnClickListener {
    //layout vars
    Button btnSignUp, btnAlreadyHaveAcc;
    EditText etEmail, etPassword, etRePassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        btnSignUp = findViewById(R.id.btnSignUp);
        btnAlreadyHaveAcc = findViewById(R.id.btnAlreadyHaveAcc);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etRePassword = findViewById(R.id.etRePassword);


        btnSignUp.setOnClickListener(this);
        btnAlreadyHaveAcc.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSignUp:
                RegUser regUser = new RegUser();
                regUser.execute("");
                startActivity(new Intent(this, MainActivity.class));
                break;

            case R.id.btnAlreadyHaveAcc:
                startActivity(new Intent(this, LoginActivity.class));
                break;
        }

    }


    @SuppressLint("StaticFieldLeak")
    public class RegUser extends AsyncTask<String, String, String> {
        //string var "z" to set Toast messages
        String z = "";
        Boolean isSuccess = false;
        String email1 = "";


        @Override
        protected String doInBackground(String... params) {
            try {
                Connection con = connectionclass();   //connect to database
                if (con == null) {
                    z = "Check Your Internet Access!";
                } else {

                    String email = etEmail.getText().toString();
                    String password = etPassword.getText().toString();
                    String repassword = etRePassword.getText().toString();

                    if (email.isEmpty()) {
                        etEmail.setError("Email is required");
                        etEmail.requestFocus();
                    }
                    if (password.isEmpty()) {
                        etPassword.setError("Password is required");
                        etPassword.requestFocus();
                    }
                    if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        etEmail.setError("Please enter a valid email");
                        etEmail.requestFocus();
                    }
                    if (password.length() < 6) {
                        etPassword.setError("Minimum length of six characters is required");
                        etPassword.requestFocus();
                    }
                   /* if (!password.equals(repassword)) {
                        etRePassword.setError("Passwords Did not Match");
                    }*/

                    Log.d("my email", email);
                    Log.d("my password", password);
                        String query = "INSERT INTO Usertest(email,password) Values(?,?)";
                        PreparedStatement preparedStmt = con.prepareStatement(query);
                        preparedStmt.setString(1, email);
                        preparedStmt.setString(2, password);
                        preparedStmt.executeUpdate();
                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery(query);
                        if (rs.next()) {
                            email1 = rs.getString("email"); //Name is the string.
                            z = "query successful";
                            isSuccess = true;
                            con.close();
                        } else {

                            z = "Invalid Query!";
                            isSuccess = false;
                        }
                    }
            } catch (Exception ex) {

                isSuccess = false;
                z = ex.getMessage();

                Log.d("sql error", z);
            }
            return z;
        }
    }

    @SuppressLint({"NewApi", "AuthLeak"})
    public Connection connectionclass() {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String ConnectionURL;
        try {

            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            //database connection string below.
            ConnectionURL = "jdbc:jtds:sqlserver://carpaints.database.windows.net:1433;DatabaseName=CarPaints;user=cp_server_admin@carpaints;password=Immunematerial5;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
            connection = DriverManager.getConnection(ConnectionURL);
        } catch (SQLException se) {

            Log.e("error here 1 : ", se.getMessage());
        } catch (ClassNotFoundException e) {

            Log.e("error here 2 : ", e.getMessage());
        } catch (Exception e) {

            Log.e("error here 3 : ", e.getMessage());
        }
        return connection;

    }
}

