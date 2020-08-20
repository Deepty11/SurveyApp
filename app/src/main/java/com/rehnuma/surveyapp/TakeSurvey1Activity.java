package com.rehnuma.surveyapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.rehnuma.surveyapp.model.SurveyQuestions;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TakeSurvey1Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner spinner;
    private Button next,prev;
    private SurveyQuestions sq;
    private Date timestamp;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_survey1);
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);

        //setting the title
        toolbar.setTitle("DoSurvey!");

        spinner=(Spinner)findViewById(R.id.spinner);
        next=(Button)findViewById(R.id.next);
        prev=(Button)findViewById(R.id.prev);


//        Log.i("selected:",selected);
//        Toast.makeText(this, selected, Toast.LENGTH_SHORT).show();
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selected=spinner.getSelectedItem().toString();
                sq=new SurveyQuestions();
                timestamp=new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                String strDate= formatter.format(timestamp);
                //String time= timestamp.toString();
                sq.setTimestamp(strDate);
                sq.setQuestion1(selected);

                Intent intent=new Intent(getApplicationContext(),TakeSurvey2Activity.class);
                intent.putExtra("sq",sq);
                startActivity(intent);
            }
        });
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),ChooseActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String selected=parent.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_1, menu);
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