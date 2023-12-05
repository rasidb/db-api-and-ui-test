package com.sqlNotes;

import com.utilities.DBUtils;
import org.testng.annotations.Test;

import java.util.List;

public class SQL_Notes1 {
    /*
     GROUP BY
     GROUP BY ifadesi, SELECT ifadesinden alınan satırları belirttiğimiz "job_id" alanına göre gruplara ayırır.
 */
    @Test
    public void group_by(){
        // Veritabanına bağlan
        DBUtils.createConnection();

        // SQL sorgusunu oluştur
        String sql="SELECT job_id, avg(salary) " +
                "FROM employees " +
                "GROUP BY job_id";

        // SQL sorgusunu yürüt ve sonuçları al
        List<List<Object>> queryResultList = DBUtils.getQueryResultList(sql);

        // Sonuçları ekrana yazdır
        for (List<Object> objects : queryResultList) {
            System.out.println(objects);
        }

        // Veritabanı bağlantısını kapat
        DBUtils.destroy();
    }
}
