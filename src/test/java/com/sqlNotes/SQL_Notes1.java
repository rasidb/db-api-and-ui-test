package com.sqlNotes;

import com.utilities.DBUtils;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

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
    @Test
    public void having_avg(){
        // veritabanına bağlan
        DBUtils.createConnection();

        //sql sorgusunu oluştur (birbirinden farklı pozisyonlar bu pozisyonlarda çalışan kişi sayıları ve ortalama maaşları arasından min 10000 kazananlar)
        String sql = "select job_id, avg(salary), count(*) ,sum(salary)" +
                " from employees" +
                " group by job_id" +
                " having avg(salary)>10000";

        //sql sorgusunu yürüt ve sonuçları list olarak al
        List<List<Object>> queryResultList = DBUtils.getQueryResultList(sql);

        //sonuçları ekrana yazdır
        for (List<Object> stringObjectMap : queryResultList) {
            System.out.println(stringObjectMap);
        }

        // Veritabanı bağlantısını kapat
        DBUtils.destroy();
    }
}
