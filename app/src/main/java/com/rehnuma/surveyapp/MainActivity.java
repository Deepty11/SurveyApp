package com.rehnuma.surveyapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rehnuma.surveyapp.model.User;
import com.rehnuma.surveyapp.mysqlConnection.DatabaseConnection;
import com.rehnuma.surveyapp.sqliteConnection.Databasehelper;
import com.rehnuma.surveyapp.sqliteConnection.SurveyDatabaseHelper;


public class MainActivity extends AppCompatActivity {

    private EditText emailText;
    private EditText passwordText;
    private Button loginbtn;
    private TextView alert;
    private TextView createAccount;
    private DatabaseConnection databaseConnection=null;
    private boolean check=false;
    private User user=null;
    private ProgressDialog progressDialog;
    private Databasehelper databasehelper;
    private SurveyDatabaseHelper sd;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailText=(EditText)findViewById(R.id.email);
        passwordText=(EditText)findViewById(R.id.password);
        loginbtn=(Button)findViewById(R.id.submit);
        createAccount=(TextView)findViewById(R.id.createAccount);

        databasehelper=new Databasehelper(this);
        progressDialog=new ProgressDialog(this);
        //sd=new SurveyDatabaseHelper(this);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email=emailText.getText().toString().trim();
                String password=passwordText.getText().toString().trim();
                if(email.equals("")|| password.equals("")){
                    Toast.makeText(MainActivity.this, "Please fill the fileds...", Toast.LENGTH_SHORT).show();
                }else{
                    String res;
                    progressDialog.setMessage("Loading...");
                    progressDialog.show();
                    res=databasehelper.checkUser(email,password);
                    if(!res.equals("")){
                        progressDialog.hide();
                        Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        Intent intent =new Intent(getApplicationContext(),ChooseActivity.class);
                        intent.putExtra("name",res);
                        startActivity(intent);

                    }else {
                        progressDialog.hide();
                        Toast.makeText(MainActivity.this, "Login error", Toast.LENGTH_SHORT).show();

                    }
                }

            }
        });

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),RegistrationActivity.class));
            }
        });

    }


}