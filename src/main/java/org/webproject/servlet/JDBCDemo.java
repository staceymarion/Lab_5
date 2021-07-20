package org.webproject.servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCDemo {

    public static void main(String[] args) {
        Connection conn;
        Statement stmt;
        try {
            // load the JDBC driver
            Class.forName("org.postgresql.Driver");

            // establish connection
            String url = "jdbc:postgresql://localhost:5432/disastermngt";
            conn = DriverManager.getConnection(url, "postgres", "admin");

            // query the database
            String sql = "select id, report_type, disaster_type, time_stamp, ST_AsText(geom) as geom from report";
            stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery(sql);   // store result in ResultSet

            // print the result     res is the pointer.  first parameter is column name
            if (res != null) {
                while (res.next()) {
                    System.out.println("id: " + res.getString("id"));
                    System.out.println("report_type: " + res.getString("report_type"));
                    System.out.println("disaster: " + res.getString("disaster_type"));
                    System.out.println("time_stamp: " + res.getString("time_stamp"));
                    System.out.println("geom: " + res.getString("geom"));
                }
            }

            // clean up
            stmt.close();
            conn.close();
        }
        // if error occurs, print error trace
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}