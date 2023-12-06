package com.sqlNotes;

import com.utilities.DBUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class SQL_Notes2 {
    @Test
    public void as(){
        // first_name ve last_name nesnelerini birleştirerek fullname isimli bi nesne oluştur
        // ve tüm kullanıcıların isimlerini alfabetik olarak sırala

        DBUtils.createConnection();

        String sql ="select first_name || ' ' || last_name as fullName " +
                "from employees";

        List<Map<String, Object>> queryResultMap = DBUtils.getQueryResultMap(sql);
        for (Map<String, Object> fullname : queryResultMap) {
            //FULLNAME nesnesinin null olmadığını doğrula
            Assert.assertNotNull(fullname);
        }
        DBUtils.destroy();
    }
}
