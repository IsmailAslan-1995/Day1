package com.InarAcademy.utilities;


import com.fasterxml.jackson.databind.ser.std.MapSerializer;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBUtils {
    private static final String DB_URL=ConfigurationReader.getProperty("db_URL");
    private  static final String DB_USER=ConfigurationReader.getProperty("db_username");
    private static final String DB_PASSWORD= ConfigurationReader.getProperty("db_password");

    public static Connection connection;
    public static Statement statement;
    public static ResultSet resultSet;

    public static void createConnection(){
        try {
             connection= DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
             System.out.println("DB connection is created!!");
         } catch (SQLException e) {
        throw new RuntimeException(e);
        }
    }
    public static void executeQuery(String query){
        try {
            statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            resultSet =statement.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void destroy() throws SQLException {
        if (resultSet != null) {
            resultSet.close();
        }
        if (statement != null) {
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }
    public static List<Map<String,Object>> getQueryResultMap(String query){
        executeQuery(query);
        List<Map<String,Object>> result=new ArrayList<>();
        ResultSetMetaData rsmd;
        try {
            rsmd= resultSet.getMetaData();
            Map<String,Object> colNameValue=new HashMap<>();
            for(int i=0;i<=rsmd.getColumnCount();i++){
                colNameValue.put(rsmd.getColumnName(i),resultSet.getObject(i));
            }
            result.add(colNameValue);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
