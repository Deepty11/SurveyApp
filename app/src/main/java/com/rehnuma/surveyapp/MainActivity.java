package com.rehnuma.surveyapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
    private SharedPreferences sharedPreferences;

    public static final String fileName="login";
    public static final String Username="Username";
    public static final String Email="Email";
    public static final String Password="password";
    private String res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences=getSharedPreferences(fileName, Context.MODE_PRIVATE);
        if(sharedPreferences.contains(Username)){
            Intent intent =new Intent(getApplicationContext(),ChooseActivity.class);
            intent.putExtra("name",Username);
            startActivity(intent);
        }

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

                    progressDialog.setMessage("Loading...");
                    progressDialog.show();
                    res=databasehelper.checkUser(email,password);
                    if(!res.equals("")){
                        SharedPreferences.Editor editor=sharedPreferences.edit();
                        editor.putString(Username,res);
                        editor.putString(Email,email);
                        editor.putString(Password,password);
                        editor.commit();
                        progressDialog.hide();
                        Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        Intent intent =new Intent(getApplicationContext(),ChooseActivity.class);
                        intent.putExtra("name",res);
                        startActivity(intent);

                    }else {
                        progressDialog.hide();
                        Toast.makeText(MainActivity.this, "Login error", Toast.LENGTH_SHORT).show();
                        emailText.setText("");
                        passwordText.setText("");

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