package com.sqlNotes;

import com.utilities.DBUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;
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

    @Test
    public void distinct(){
        //birbirinden farklı job_id'leri getir

        //db bağlantısı kur
        DBUtils.createConnection();

        //sql sorgusunu oluştur
        String sql ="select distinct job_id " +
                "from employees";

        //sorgunun sonucunu liste olarak al
        List<List<Object>> queryResultList = DBUtils.getQueryResultList(sql);

        //birbirinden farklı job_id'leri yazdır
        for (List<Object> job_id : queryResultList) {
            System.out.println(job_id);

            //db bağlantısından çık
            DBUtils.destroy();
        }

        Assert.assertTrue(queryResultList.size()!=0);
    }
    @Test
    public void practice(){
        //get me all info who is working as IT _PROG or SA_REP

        //db bağlantısı kur
        DBUtils.createConnection();

        //sql sorgusunu oluştur
        String sql ="select * " +
                "from employees " +
                "where job_id in ('IT_PROG', 'SA_REP')";

        //sorgu sonucunu liste olarak al
        List<List<Object>> queryResultList = DBUtils.getQueryResultList(sql);

        for (List<Object> users : queryResultList) {
            //job_id'nin it_prog ya da sa_rep olduğunu doğrula
            Assert.assertTrue(users.get(6).equals("IT_PROG") || users.get(6).equals("SA_REP"));
        }

        //db bağlantısından çık
        DBUtils.destroy();
    }
    @Test
    public void practice2(){
        //how many employees making more than 8000
        //db bağlantısını yap
        DBUtils.createConnection();
        // sql sorgusunu hazırla
        String sql ="select * " +
                "from employees " +
                "where salary >8000";
        //sql sorgusunu gönder ve bir map olarak depola
        List<Map<String, Object>> queryResultMap = DBUtils.getQueryResultMap(sql);

        for (Map<String, Object> objects : queryResultMap) {
            //8000den fazla olduğunu doğrula
            BigDecimal salary = (BigDecimal) objects.get("SALARY");
            Assert.assertTrue(salary.compareTo(new BigDecimal(8000))>0);
        }
        DBUtils.destroy();
    }

    @Test
    public void practice3(){
        //en fazla maaş alandan en az maaş alana kadar sırala

    }
}