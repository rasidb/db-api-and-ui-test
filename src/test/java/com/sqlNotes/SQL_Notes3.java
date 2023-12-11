package com.sqlNotes;

import com.github.javafaker.Faker;
import com.utilities.DBUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;
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

        DBUtils.executeQuery(sql);

        List<List<Object>> queryResultList = DBUtils.getQueryResultList(show);
        System.out.println(queryResultList);

        DBUtils.destroy();

    }

    @Test
    public void deleteData() {
        String sql = "delete from sqlNotes3 " +
                "";
        String show = "select * from sqlNotes3 ";

        DBUtils.createConnection();
        DBUtils.executeQuery(sql);
        List<List<Object>> queryResultList = DBUtils.getQueryResultList(show);
        for (List<Object> objects : queryResultList) {
            System.out.println(objects);
        }
        DBUtils.destroy();

    }


    @Test
    public void addNewColumn() {
        DBUtils.createConnection();
        String sql = "alter table sqlNotes3 add salary int not null";
        DBUtils.executeQuery(sql);
        DBUtils.destroy();
    }

    @Test
    public void addSomePeoplesToOurTable() {
        List<String> jobTitles = new ArrayList<>(Arrays.asList("tester", "developer", "dikisci", "manav", "fullStack"));
        Random random = new Random();
        Faker faker = new Faker();
        String sql;
        int id;
        DBUtils.createConnection();
        for (int i = 0; i < 100; i++) {
            String job = jobTitles.get(random.nextInt(4 - 0 + 1));
            String firstName = faker.name().firstName().replaceAll("'", "");
            String lastName = faker.name().lastName().replaceAll("'", "");
            ;
            int salary = random.nextInt(50000 - 11400 + 1);
            id = i + 1;
            // emp_id, firstname, lastname, jobtitle
            sql = "insert into sqlNotes3 values(" +
                    id + "," +
                    "'" + firstName + "'," +
                    "'" + lastName + "'," +
                    "'" + job + "'," +
                    salary +
                    ")";
            DBUtils.executeQuery(sql);
        }
        String show = "select * from sqlNotes3";
        DBUtils.getQueryResultList(show);
        DBUtils.destroy();
    }

    @Test
    public void updatePractice() {
        // db bağlantısını kur
        DBUtils.createConnection();

        // maası 11400'den düşük olanları bulmak için gerekli sorgu
        String sql = "select emp_id, salary\n" +
                " from (select emp_id, salary from sqlNotes3 \n" +
                "                                where salary < 11400 )\n" +
                "order by salary desc";

        // sorguyu gönder ve maası 11400'den düşük olan emp_id'leri list objesine ekle
        List<Map<String, Object>> queryResultMap = DBUtils.getQueryResultMap(sql);

        // tüm emp_id'leri al ve emp_ids list'ine kaydet
        List<Integer> emp_ids = new ArrayList<>();
        for (Map<String, Object> stringObjectMap : queryResultMap) {
            BigDecimal empId = (BigDecimal) stringObjectMap.get("EMP_ID");
            emp_ids.add(empId.intValue());
        }

        // emp_id'lerin maaşlarını 11400 olarak güncelleyen sorguyu oluştur
        for (Integer empId : emp_ids) {
            String changeSalary = "update sqlNotes3 set salary = 11400 where emp_id =" + empId;
            DBUtils.executeQuery(changeSalary);
        }

        // Güncellemeden sonra tekrar sorgu yap
        queryResultMap = DBUtils.getQueryResultMap(sql);

        // Maaşı 11400'den düşük olan kullanıcı olmamalı
        Assert.assertTrue(queryResultMap.isEmpty());

        // DB bağlantısını kapat
        DBUtils.destroy();
    }

    @Test
    public void newDataTables() {
        // Not: Bu test, users1 ve address1 adında iki tablo oluşturur ve bu tabloları doldurur.

        // users1 tablosunu oluştur
        String createUserTableSQL = "CREATE TABLE users1 (" +
                "user_id INT PRIMARY KEY," +
                "firstname VARCHAR(30) NOT NULL," +
                "lastname VARCHAR(30) NOT NULL," +
                "salary INT NOT NULL," +
                "address_id INT UNIQUE" +
                ")";

        // Veritabanı bağlantısını kur ve SQL sorgusunu execute et
        DBUtils.createConnection();
        DBUtils.executeQuery(createUserTableSQL);

        // address1 tablosunu oluştur
        String createAddressTableSQL = "CREATE TABLE address1 (" +
                "address_id INT UNIQUE," +
                "phoneNumber VARCHAR(20)" +
                ")";

        // Veritabanı bağlantısını kur ve SQL sorgusunu execute et
        DBUtils.executeQuery(createAddressTableSQL);

        // Gerekli tablolar için gerekli değişkenleri oluştur
        int user_id;
        String firstName;
        String lastName;
        int salary;
        int addressId;
        String phoneNumber;

        // 100 adet sahte veri ekleyin
        Random random = new Random();
        Faker faker = new Faker();

        // Rastgele addressId için bir liste oluştur
        List<Integer> randomList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            randomList.add(i + 1);
        }

        // Veritabanı bağlantısını kur
        for (int i = 0; i < 100; i++) {
            user_id = i + 1;
            firstName = faker.name().firstName().replaceAll("'", "");
            lastName = faker.name().lastName().replaceAll("'", "");
            salary = random.nextInt(50001 - 11400) + 11400;

            // Rastgele bir addressId seç
            int randomIndex = random.nextInt(randomList.size());
            addressId = randomList.get(randomIndex);

            // Seçilen addressId'yi listeden kaldır
            randomList.remove(randomIndex);

            // Telefon numarasını sahte veri olarak oluştur
            phoneNumber = faker.phoneNumber().cellPhone();

            // users1 tablosuna veri ekle
            DBUtils.executeQuery("INSERT INTO users1 VALUES (" + user_id + ", '" + firstName + "', '" + lastName + "', "
                    + salary + ", " + addressId + ")");

            // address1 tablosuna veri ekle
            DBUtils.executeQuery("INSERT INTO address1 VALUES (" + addressId + ", '" + phoneNumber + "')");
        }

        // Not: users1 ve address1 tablolarını birleştirerek tüm bilgileri getiren SQL sorgusu
        String getAllInformation = "SELECT users1.*, address1.* FROM users1 JOIN address1 ON users1.address_id = address1.address_id";

        // SQL sorgusunu çalıştır ve sonuçları ekrana yazdır
        List<List<Object>> queryResultList = DBUtils.getQueryResultList(getAllInformation);
        for (List<Object> objects : queryResultList) {
            System.out.println(objects);
        }

        // Veritabanı bağlantısını kapat
        DBUtils.destroy();
    }


    @Test
    public void deleteFromData(){
        DBUtils.createConnection();
        String sql ;

        List<Integer> delete=new ArrayList<>(Arrays.asList(1,3,5,9,11,22,19));
        for (Integer integer : delete) {
            sql = "delete from address1" +
                    " where address_id = " + integer;
            DBUtils.executeQuery(sql);

            sql = "delete from users1 " +
                    " where address_id = " + integer + 3;
            DBUtils.executeQuery(sql);
        }
        DBUtils.destroy();
    }
}




