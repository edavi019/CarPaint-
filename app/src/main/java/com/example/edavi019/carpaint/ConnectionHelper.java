package com.example.edavi019.carpaint;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ConnectionHelper {

    String ConnectionURL;
    private Boolean isSuccess;
    private String z = "";

    @SuppressLint({"NewApi", "AuthLeak"})
    public Connection connectionclass(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String ConnectionURL;

        try{

            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            //database connection string below.
            ConnectionURL = "jdbc:jtds:sqlserver://carpaints.database.windows.net:1433;DatabaseName=CarPaints;user=cp_server_admin@carpaints;password=Immunematerial5;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
            connection = DriverManager.getConnection(ConnectionURL);
        }
        catch (SQLException se){

            Log.e("error here 1 : ", se.getMessage());
        }
        catch (ClassNotFoundException e){

            Log.e("error here 2 : ", e.getMessage());
        }
        catch (Exception e){

            Log.e("error here 3 : ", e.getMessage());
        }
        return connection;

   }


   /*public List<String> getAllLabels() {
       connectionclass();
       Connection connection;
       List<String> labels = new ArrayList<String>();
       try {
           ConnectionHelper conn = new ConnectionHelper();
           connection = conn.connectionclass();
           String selectQuery = "Select Distinct Year From CarDB";
           Statement stm = connection.createStatement();
           ResultSet rs = stm.executeQuery(selectQuery);
           ArrayList<String> year = new ArrayList<String>();
           while (rs.next()) {
               String id = rs.getString("Year");
               year.add(id);
           }
       } catch (SQLException e) {
           e.printStackTrace();
       }
   }*/
}

