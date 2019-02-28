package com.example.edavi019.carpaint;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.microsoft.windowsazure.mobileservices.*;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etEmail, etPassword;
    Button btnLogin, btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);

        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLogin:
                //signIn();
               // startActivity(new Intent(this, MainActivity.class));
                break;

            case R.id.btnRegister:
                startActivity(new Intent(this, CreateAccountActivity.class));
                break;
        }

    }
}
