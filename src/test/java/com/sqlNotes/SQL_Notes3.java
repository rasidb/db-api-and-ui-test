package com.sqlNotes;

import com.utilities.DBUtils;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;


public class SQL_Notes3 {



    @Test
    public void createTable() {
        // add values to the new table
        String sql = "insert into sqlNotes3 (emp_id, firstname, lastname, jobtitle) " +
                "values ( 5,'rasid', 'babamgul', 'tester')";
        String show = "select * from sqlNotes3";
        DBUtils.createConnection();
        List<List<Object>> rowList = DBUtils.getQueryResultList(show);
        System.out.println(rowList);
        DBUtils.destroy();
    }

    @Test
    public void updateData() {
        String sql = "update sqlNotes3 set jobtitle = 'patates' " +
                "where emp_id =1";
        String show = "select * from sqlNotes3 " +
                "where emp_id = 1";
        DBUtils.createConnection();

        DBUtils.executeUpdate(sql);

        List<List<Object>> queryResultList = DBUtils.getQueryResultList(show);
        System.out.println(queryResultList);

        DBUtils.destroy();

    }
    @Test
    public void deleteData (){
        String sql ="delete from sqlNotes3 " +
                "where emp_id =1";
        String show ="select * from sqlNotes3 ";

        DBUtils.createConnection();
        DBUtils.executeUpdate(sql);
        List<List<Object>> queryResultList = DBUtils.getQueryResultList(show);
        for (List<Object> objects : queryResultList) {
            System.out.println(objects);
        }
        DBUtils.destroy();

    }

}
