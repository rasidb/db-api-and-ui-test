package com.sqlNotes;

import com.github.javafaker.Faker;
import com.utilities.DBUtils;
import org.testng.annotations.Test;

import java.util.*;


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
                "";
        String show ="select * from sqlNotes3 ";

        DBUtils.createConnection();
        DBUtils.executeUpdate(sql);
        List<List<Object>> queryResultList = DBUtils.getQueryResultList(show);
        for (List<Object> objects : queryResultList) {
            System.out.println(objects);
        }
        DBUtils.destroy();

    }


    @Test
    public void addNewColumn(){
        DBUtils.createConnection();
        String sql ="alter table sqlNotes3 add salary int not null";
        DBUtils.executeUpdate(sql);
        DBUtils.destroy();
    }

    @Test
    public void addSomePeoplesToOurTable(){
       List<String> jobTitles =new ArrayList<>(Arrays.asList("tester","developer","dikisci","manav","fullStack"));
        Random random=new Random();
        Faker faker =new Faker();
        String sql ;
        int id ;
        DBUtils.createConnection();
        for (int i = 0; i < 100; i++) {
          String job= jobTitles.get(random.nextInt(4-0+1));
         String firstName =faker.name().firstName().replaceAll("'","");
         String lastName=faker.name().lastName().replaceAll("'","");;
         int salary = random.nextInt(50000-11400+1);
         id =i+1;
         // emp_id, firstname, lastname, jobtitle
            sql ="insert into sqlNotes3 values(" +
                    id +","+
                    "'"+firstName+"',"+
                    "'"+lastName+"',"+
                    "'"+job+"',"+
                    salary+
                    ")";
            DBUtils.executeUpdate(sql);
        }
        String show ="select * from sqlNotes3";
        DBUtils.getQueryResultList(show);
        DBUtils.destroy();

    }



}
