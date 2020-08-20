package com.rehnuma.surveyapp.sqliteConnection;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Databasehelper extends SQLiteOpenHelper {

    private static final String databaseName="Survey.db";
    private static final String tableName="user";
    private static final String col1="id";
    private static final String col2="username";
    private static final String col3="email";
    private static final String col4="password";

    public Databasehelper(@Nullable Context context) {
        super(context, databaseName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE user (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "username TEXT, " +
                "email TEXT,password TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+tableName);
        onCreate(db);

    }

    public long addUser(String username, String email, String password){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("username",username);
        contentValues.put("email",email);
        contentValues.put("password",password);

        long res=sqLiteDatabase.insert("user",null,contentValues);
        sqLiteDatabase.close();
        return res;

    }
    public String checkUser(String email,String password){
        String[] columns={ col1,col2,col3,col4 };
        SQLiteDatabase sqLiteDatabase=getReadableDatabase();
        String selection= col3+ " =? "+" AND "+ col4+ " =? ";
        String[] selectionArgs={ email, password };
        String result="";
        Cursor cursor=sqLiteDatabase.query(tableName,columns,selection,selectionArgs,null,null,null);
        int count=cursor.getCount();
        StringBuffer stringBuffer=new StringBuffer();
        while(cursor.moveToNext()){
            stringBuffer.append(cursor.getString(1));
        }
        cursor.close();
        sqLiteDatabase.close();
        result=stringBuffer.toString();
        return result;

    }
}
