package com.rehnuma.surveyapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
    private SharedPreferences sharedPreferences;
    public static final String fileName="login";
    public static final String Username="Username";
    public static final String Email="Email";
    public static final String Password="password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_survey);

        toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sharedPreferences=getSharedPreferences(fileName, Context.MODE_PRIVATE);
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
                sharedPreferences=getSharedPreferences(fileName, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor= sharedPreferences.edit();
                editor.clear();
                editor.apply();
                finish();
                Toast.makeText(this, "You clicked logout", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                break;

        }
        return true;
    }
}