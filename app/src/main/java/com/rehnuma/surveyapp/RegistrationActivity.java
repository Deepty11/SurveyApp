package com.rehnuma.surveyapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.rehnuma.surveyapp.mysqlConnection.DatabaseConnection;
import com.rehnuma.surveyapp.sqliteConnection.Databasehelper;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class RegistrationActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;
    private Button submitBtn;
    private TextView memberText;
    private ProgressDialog progressDialog;
    private DatabaseConnection databaseConnection;
    private Databasehelper databasehelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        usernameEditText=(EditText)findViewById(R.id.username);
        emailEditText=(EditText)findViewById(R.id.email);
        passwordEditText=(EditText)findViewById(R.id.password);
        confirmPasswordEditText=(EditText)findViewById(R.id.confirmpassword);
        submitBtn=(Button)findViewById(R.id.submit);
        memberText=(TextView)findViewById(R.id.member);


//        databaseConnection=new DatabaseConnection();
        databasehelper=new Databasehelper(this);
        progressDialog=new ProgressDialog(this);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                DoRegister doRegister=new DoRegister();
//                doRegister.execute("");
                String username=usernameEditText.getText().toString().trim();
                String email=emailEditText.getText().toString().trim();
                String password=passwordEditText.getText().toString().trim();
                String confirmPassword=confirmPasswordEditText.getText().toString().trim();

                if(username.equals("")||email.equals("")||password.equals("")||confirmPassword.equals("")){
                    Toast.makeText(RegistrationActivity.this, "Please fill all fields...", Toast.LENGTH_SHORT).show();
                }else{
                    if(!password.equals(confirmPassword)){
                        Toast.makeText(RegistrationActivity.this, "Enter password again....", Toast.LENGTH_SHORT).show();
                    }else{
                        progressDialog.setMessage("Loading...");
                        progressDialog.show();
                        long res=databasehelper.addUser(username,email,password);
                        if(res>0){
                            Toast.makeText(RegistrationActivity.this, "registration Successful...", Toast.LENGTH_SHORT).show();
                            progressDialog.hide();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));

                        }else{
                            progressDialog.hide();
                            Toast.makeText(RegistrationActivity.this, "registration failed...", Toast.LENGTH_SHORT).show();
                        }

                    }
                }

            }
        });
        memberText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
    }



}
