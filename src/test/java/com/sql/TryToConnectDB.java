package com.sql;

import com.utilities.DBUtils;
import org.testng.annotations.Test;

import java.util.List;

public class TryToConnectDB {
    @Test
    public void connectingDB() {
        DBUtils.createConnection();
        List<List<Object>> queryResultList = DBUtils.getQueryResultList("select * from employees");
        for (List<Object> objects : queryResultList) {
            System.out.println(objects);
        }
        DBUtils.destroy();

    }
}
