package com.rehnuma.surveyapp.sqliteConnection;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.rehnuma.surveyapp.ViewSurveyActivity;
import com.rehnuma.surveyapp.model.SurveyQuestions;

import java.util.ArrayList;
import java.util.List;

public class SurveyDatabaseHelper extends SQLiteOpenHelper {
    private static final String databaseName="Survey1.db";
    private static final String tableName="surveyReports";
    private static final String col1="id";
    private static final String col2="timestamp";
    private static final String col3="question1";
    private static final String col4="question2";
    private static final String col5="question3";
    private static final String col6="question4";
    private static final String col7="question5";


    public SurveyDatabaseHelper(@Nullable Context context) {
        super(context, databaseName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE surveyReports (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "timestamp TEXT, " +
                "question1 TEXT," +
                "question2 TEXT," +
                "question3 TEXT," +
                "question4 TEXT," +
                "question5 TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+tableName);
        onCreate(db);

    }

    public long addSurveys(SurveyQuestions sq){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("timestamp",sq.getTimestamp());
        contentValues.put("question1",sq.getQuestion1());
        contentValues.put("question2",sq.getQuestion2());
        contentValues.put("question3",sq.getQuestion3());
        contentValues.put("question4",sq.getQuestion4());
        contentValues.put("question5",sq.getQuestion5());


        long res=sqLiteDatabase.insert("surveyReports",null,contentValues);
        sqLiteDatabase.close();
        return res;

    }
    public ArrayList<SurveyQuestions> showrecords(){
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        String[] columns={ col1,col2,col3,col4,col5,col6,col7 };

        //Cursor cursor=sqLiteDatabase.query(tableName,columns,null,null,null,null,null);
        Cursor cursor=sqLiteDatabase.rawQuery("select * from surveyReports",null);

        ArrayList<SurveyQuestions> resultList=new ArrayList<>();

        if(cursor.getCount()==0){
            //no data found
            cursor.close();
            sqLiteDatabase.close();
            return null;

        }else{
            while(cursor.moveToNext()){
                SurveyQuestions sq= new SurveyQuestions();
                sq.setTimestamp(cursor.getString(1));
                sq.setQuestion1(cursor.getString(2));
                sq.setQuestion2(cursor.getString(3));
                sq.setQuestion3(cursor.getString(4));
                sq.setQuestion4(cursor.getString(5));
                sq.setQuestion5(cursor.getString(6));
                resultList.add(sq);

            }
            cursor.close();
            sqLiteDatabase.close();
            return resultList;

        }
    }
}
