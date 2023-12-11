package com.sqlNotes;

import com.utilities.DBUtils;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class SQL_JoinTypes {

    @BeforeTest
    public void init() {
        DBUtils.createConnection();
    }

    @AfterTest
    public void destroy() {
        DBUtils.destroy();
    }
    /*
         users1 (user_id, firstname, lastname, salary, adress_id)
         addres1 (address_id, phonenumber)
     tabloları üzerinde islem yap


    */

    //left join ve right join ve inner join örnegi yap

    @Test
    public void leftJoin() {
        String sql =
                " SELECT a.*, u.* " +
                        "FROM users1 u " +
                        "left join address1 a on u.address_id=a.address_id";
        List<Map<String, Object>> queryResultMap = DBUtils.getQueryResultMap(sql);
        for (Map<String, Object> stringObjectMap : queryResultMap) {
            System.out.println(stringObjectMap);
        }
    }

    @Test
    public void rightJoin() {
        String sql =
                " SELECT a.*, u.* " +
                        "FROM users1 u " +
                        "right join address1 a on u.address_id=a.address_id";
        List<Map<String, Object>> queryResultMap = DBUtils.getQueryResultMap(sql);
        for (Map<String, Object> stringObjectMap : queryResultMap) {
            System.out.println(stringObjectMap);
        }

    }

    @Test
    public void innerJoin(){
        String sql =
                " SELECT a.*, u.* " +
                        "FROM users1 u " +
                        "join address1 a on u.address_id=a.address_id";
        List<Map<String, Object>> queryResultMap = DBUtils.getQueryResultMap(sql);
        for (Map<String, Object> stringObjectMap : queryResultMap) {
            System.out.println(stringObjectMap);
        }
    }

    @Test
    public void union_unionAll(){
        String sql =
                "select address_id from users1 " +
                "union " + //duplicate verileri gösterme
                "select address_id from address1 " +
                "order by address_id ";

        sql ="select address_id from users1 " +
                "union all" +//her şeyi göster ve iki tabloyu alt alta sırala
                "select address_id from address1 " +
                "order by address_id ";
    }


}
