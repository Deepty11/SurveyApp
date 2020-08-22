package com.rehnuma.surveyapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ChooseActivity extends AppCompatActivity {

    private Button takeSurveybtn;
    private Button viewSurveybtn;
    private Toolbar toolbar;
    private SharedPreferences sharedPreferences;
    public static final String fileName="login";
    public static final String Username="Username";
    public static final String Email="Email";
    public static final String Password="password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("DoSurvey!");

        takeSurveybtn=(Button)findViewById(R.id.takeSurvey);
        viewSurveybtn=(Button)findViewById(R.id.viewSurvey);

        String username=getIntent().getStringExtra("name");
        TextView user=(TextView)findViewById(R.id.username);
        sharedPreferences=getSharedPreferences(fileName, Context.MODE_PRIVATE);
        if(sharedPreferences.contains(Username)){
            user.setText("Welcome "+sharedPreferences.getString(Username,"")+"!");
        }


        takeSurveybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),TakeSurvey1Activity.class);
                startActivity(intent);

            }
        });

        viewSurveybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ViewSurveyActivity.class));

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.menuHome:
                Toast.makeText(this, "You clicked about", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),ChooseActivity.class));
                break;

            case R.id.menuLogout:
                Toast.makeText(this, "You clicked logout", Toast.LENGTH_SHORT).show();
                sharedPreferences=getSharedPreferences(fileName, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor= sharedPreferences.edit();
                editor.clear();
                editor.apply();
                finish();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                break;

        }
        return true;
    }

//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//    }
}