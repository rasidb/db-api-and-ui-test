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
    @Test
    public void length(){
        /* first_name ve last_name nesnelerini birleştirerek fullName ve fullName nesnesinin uzunluğunun olduğu tablo hazırla
        ve  fullname uzunluğunu çoktan aza doğru sırala*/
        DBUtils.createConnection();

        String sql="select first_name || ' ' || last_name as fullName, LENGTH(first_name || last_name) + 1 as length " +
                "from employees " +
                "order by length desc";

        List<Map<String, Object>> queryResultMap = DBUtils.getQueryResultMap(sql);

        for (Map<String, Object> stringObjectMap : queryResultMap) {
            Assert.assertFalse(stringObjectMap.get("LENGTH").equals(0));
        }
        DBUtils.destroy();
    }


}
