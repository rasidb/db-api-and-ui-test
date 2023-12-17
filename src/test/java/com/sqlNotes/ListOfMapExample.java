package com.sqlNotes;

import com.utilities.DBUtils;
import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListOfMapExample {
    @Test
    public void mapExample() throws SQLException {
        String dbUrl = "jdbc:oracle:thin:@54.152.219.47:1521:xe";
        String dbUsername = "hr";
        String dbPassword = "hr";


        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("select * from users1");

        //in order to get column names  we need resultSetMetaData
        ResultSetMetaData rsmd = resultSet.getMetaData();

        //    Map<String,Object> queryData = new HashMap<>();
        //  queryData.put("user_id",);
        //queryData.put("firstname",);
        //queryData.put("lastname",);
        //queryData.put("salary",);
        //queryData.put("address_id",);

        List<Map<String, Object>> listOfMap = new ArrayList<>();

        resultSet.next();
        Map<String, Object> queryRealData = new HashMap<>();
        queryRealData.put(rsmd.getColumnName(1), resultSet.getString(1));
        queryRealData.put(rsmd.getColumnName(2), resultSet.getString(2));
        queryRealData.put(rsmd.getColumnName(3), resultSet.getString(3));
        queryRealData.put(rsmd.getColumnName(4), resultSet.getString(4));
        queryRealData.put(rsmd.getColumnName(5), resultSet.getString(5));
        resultSet.next();

        listOfMap.add(queryRealData);
        queryRealData = new HashMap<>();
        resultSet.next();

        queryRealData.put(rsmd.getColumnName(1), resultSet.getString(1));
        queryRealData.put(rsmd.getColumnName(2), resultSet.getString(2));
        queryRealData.put(rsmd.getColumnName(3), resultSet.getString(3));
        queryRealData.put(rsmd.getColumnName(4), resultSet.getString(4));
        queryRealData.put(rsmd.getColumnName(5), resultSet.getString(5));
        listOfMap.add(queryRealData);


        resultSet.beforeFirst();
        List<Map<String, Object>> result = new ArrayList<>();
        Map<String, Object> resultMap;
        while (resultSet.next()) {
            resultMap = new HashMap<>();
            resultMap.put(rsmd.getColumnName(1), resultSet.getString(1));
            resultMap.put(rsmd.getColumnName(2), resultSet.getString(2));
            resultMap.put(rsmd.getColumnName(3), resultSet.getString(3));
            resultMap.put(rsmd.getColumnName(4), resultSet.getString(4));
            resultMap.put(rsmd.getColumnName(5), resultSet.getString(5));
            result.add(resultMap);
        }
        for (Map<String, Object> stringObjectMap : result) {
            System.out.println(stringObjectMap);
        }
        System.out.println(rsmd.getColumnCount());

        resultSet.close();
        statement.close();
        connection.close();
    }

    @Test
    public void test3() throws SQLException {
        String dbUrl = "jdbc:oracle:thin:@54.152.219.47:1521:xe";
        String dbUsername = "hr";
        String dbPassword = "hr";

        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("select * from users1");
        ResultSetMetaData rsmd = resultSet.getMetaData();

        resultSet.beforeFirst();
        List<Map<String, Object>> queryListOfMap = new ArrayList<>();
        Map<String, Object> map;
        int columnCount = rsmd.getColumnCount();
        while (resultSet.next()) {
            map = new HashMap<>();
            for (int i = 0; i < columnCount; i++) {
                map.put(rsmd.getColumnName(i + 1), resultSet.getObject(i + 1));
            }
            queryListOfMap.add(map);
        }



        resultSet.close();
        statement.close();
        connection.close();
    }
}
