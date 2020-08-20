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
import android.widget.CheckBox;
import android.widget.Toast;

import com.rehnuma.surveyapp.model.SurveyQuestions;

public class TakeSurvey3Activity extends AppCompatActivity {
    private Button next,prev;
    private SurveyQuestions sq;
    private CheckBox yesCheck,noCheck;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_survey3);
         toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);

        //setting the title
        toolbar.setTitle("DoSurvey!");
        sq=(SurveyQuestions)getIntent().getSerializableExtra("sq");
        next=(Button)findViewById(R.id.next);
        prev=(Button)findViewById(R.id.prev);
        yesCheck=(CheckBox)findViewById(R.id.yes);
        noCheck=(CheckBox)findViewById(R.id.no);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String checked="";
                if(yesCheck.isChecked()){
                    checked="Yes";
                }else if(noCheck.isChecked()){
                    checked="No";
                }
                sq.setQuestion3(checked);
                Intent intent =new Intent(getApplicationContext(),TakeSurvey4Activity.class);
                intent.putExtra("sq",sq);
                startActivity(intent);
            }
        });
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(),TakeSurvey2Activity.class);
                intent.putExtra("sq",sq);
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_3, menu);
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