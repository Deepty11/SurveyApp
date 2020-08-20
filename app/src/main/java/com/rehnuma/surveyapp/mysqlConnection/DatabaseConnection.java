package com.rehnuma.surveyapp.mysqlConnection;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import com.rehnuma.surveyapp.model.User;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {

    private Statement statement=null;
    private PreparedStatement preparedStatement=null;
    private ResultSet resultSet=null;
    String driverClass="com.mysql.jdbc.Driver";
    String database="jdbc:mysql://127.0.0.1:3306/survey";
    String username="root";
    String password="1234";
    Connection connection=null;
    String connUrl=null;

    @SuppressLint("NewApi")
    public Connection getConnection(){
        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            Class.forName(driverClass);
            connection= DriverManager.getConnection(database,username,password);
//            connection= DriverManager.getConnection(connUrl);


        } catch (ClassNotFoundException e ) {
            Log.e("ERROR1",e.getMessage());
        } catch(SQLException e){
            Log.e("ERROR3",e.getMessage());
        }catch (Exception e){
            Log.e("ERROR2",e.getMessage());
        }
        return connection;

    }

}
