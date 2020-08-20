package com.rehnuma.surveyapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.rehnuma.surveyapp.model.SurveyQuestions;

public class TakeSurvey4Activity extends AppCompatActivity {
    private Button next,prev,veryGoodBtn,goodBtn,naturalBtn,badBtn,veryBadBtn;
    private SurveyQuestions sq;
    private String result="";
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_survey4);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);

        //setting the title
        toolbar.setTitle("DoSurvey!");

        sq=(SurveyQuestions)getIntent().getSerializableExtra("sq");
        next=(Button)findViewById(R.id.next);
        prev=(Button)findViewById(R.id.prev);
        veryBadBtn=(Button)findViewById(R.id.verybad);
        badBtn=(Button)findViewById(R.id.bad);
        veryGoodBtn=(Button)findViewById(R.id.verygood);
        goodBtn=(Button)findViewById(R.id.good);
        naturalBtn=(Button)findViewById(R.id.natural);


        veryBadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result="Very Bad";
                veryBadBtn.setBackgroundColor(getResources().getColor(R.color.colorTeal));
            }
        });
        badBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result="Bad";
                badBtn.setBackgroundColor(getResources().getColor(R.color.colorTeal));

            }
        });
        naturalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result="Natural";
                naturalBtn.setBackgroundColor(getResources().getColor(R.color.colorTeal));

            }
        });
        goodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result="Good";
                goodBtn.setBackgroundColor(getResources().getColor(R.color.colorTeal));

            }
        });
        veryGoodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result="Very Good";
                veryGoodBtn.setBackgroundColor(getResources().getColor(R.color.colorTeal));

            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(result.equals("")){
                    Toast.makeText(TakeSurvey4Activity.this, "Please select one...", Toast.LENGTH_SHORT).show();
                }else{
                    sq.setQuestion4(result);
                    Intent intent =new Intent(getApplicationContext(),TakeSurvey5Activity.class);
                    intent.putExtra("sq",sq);
                    startActivity(intent);
                }
            }
        });
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(),TakeSurvey3Activity.class);
                intent.putExtra("sq",sq);
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_4, menu);
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