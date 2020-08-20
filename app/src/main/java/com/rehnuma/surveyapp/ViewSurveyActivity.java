package com.rehnuma.surveyapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.LabeledIntent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rehnuma.surveyapp.model.SurveyQuestions;
import com.rehnuma.surveyapp.sqliteConnection.SurveyDatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class ViewSurveyActivity extends AppCompatActivity {
    private SurveyDatabaseHelper sd;
    private SurveyQuestions sq;
    private LinearLayout linearLayout;
    private  ArrayList<SurveyQuestions> resultList;
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_survey);

        toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //linearLayout=(LinearLayout)findViewById(R.id.linearLayout);
        sd=new SurveyDatabaseHelper(this);
        resultList=new ArrayList<SurveyQuestions>();
        resultList=sd.showrecords();

        recyclerView=findViewById(R.id.rclView);
        recyclerView.setHasFixedSize(true);
        mLayoutManager=new LinearLayoutManager(this);
        mAdapter=new ExampleAdapter(resultList);

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);

        //setting the title
        toolbar.setTitle("DoSurvey!");
        toolbar.setTitleTextColor(Color.WHITE);
//        if(resultList==null){
//            Toast.makeText(this, "No records found", Toast.LENGTH_SHORT).show();
//        }else{
//            for(SurveyQuestions sq1:resultList){
//                TextView textView= new TextView(this);
//                textView.setText(sq1.getTimestamp()+"/n"
//                                +sq1.getQuestion1()+"/n"
//                               +sq1.getQuestion2()+"/n"
//                               +sq1.getQuestion3()+"/n"
//                                +sq1.getQuestion4()+"/n"
//                                +sq1.getQuestion5()+"/n");
//                linearLayout.addView(textView);
//
//            }
//        }
   }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_view, menu);
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