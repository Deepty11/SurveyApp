package com.rehnuma.surveyapp;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rehnuma.surveyapp.model.SurveyQuestions;
import com.rehnuma.surveyapp.sqliteConnection.SurveyDatabaseHelper;

public class TakeSurvey5Activity extends AppCompatActivity {
    private Button submit,prev;
    private EditText addressText;
    private SurveyQuestions sq;
    private String address;
    private SurveyDatabaseHelper sd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_survey5);
        sq=(SurveyQuestions)getIntent().getSerializableExtra("sq");
        submit=(Button)findViewById(R.id.submit);
        prev=(Button)findViewById(R.id.prev);
        addressText=(EditText)findViewById(R.id.address);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);

        //setting the title
        toolbar.setTitle("DoSurvey!");

        sd=new SurveyDatabaseHelper(this);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                address=addressText.getText().toString().trim();
                if(address.equals("")){
                    Toast.makeText(TakeSurvey5Activity.this, "Please enter address", Toast.LENGTH_SHORT).show();
                }else{
                    sq.setQuestion5(address);
                    Log.i("timestamp:",sq.getTimestamp());
                    Log.i("q1:",sq.getQuestion1());
                    Log.i("q2:",sq.getQuestion2());
                    Log.i("q3:",sq.getQuestion3());
                    Log.i("q4:",sq.getQuestion4());
                    Log.i("q5:",sq.getQuestion5());
                    long res=sd.addSurveys(sq);
                    if(res>0){
                        Toast.makeText(TakeSurvey5Activity.this, "survey added successfully!", Toast.LENGTH_LONG).show();
                        Intent intent =new Intent(getApplicationContext(),FinalActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(TakeSurvey5Activity.this, "Survey error", Toast.LENGTH_SHORT).show();
                    }


                }
            }
        });
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(),TakeSurvey4Activity.class);
                intent.putExtra("sq",sq);
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_5, menu);
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
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                break;

        }
        return true;
    }
}