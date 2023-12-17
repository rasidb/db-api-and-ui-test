package com.sqlNotes;

import org.testng.annotations.Test;

import java.sql.*;

public class jdbc_examples {
    @Test
    public void test1() throws SQLException {
        String dbUrl = "jdbc:oracle:thin:@54.152.219.47:1521:xe";
        String dbUsername = "hr";
        String dbPassword = "hr";


        //veritabanına bağlanmak için DriverManager sınıfını kullanarak bir bağlantı nesnesi oluşturur.
        // url, username,psw parametrelerini alır
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);


        // connection bağlantı nesnesi üzerinden statement nesnesi oluşturur.
        //ResultSet.TYPE_SCROLL_INSENSITIVE parametresi satırlar arasında ileri geri gitmeye yarar
        //ResultSet.CONCUR_READ_ONLY sadece okuma amaçlı olduğunu belirtir
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);


        //statement nesnesi üzerinden executeQuery() methodu aracılığıyla  "select * from users1 " sql sorgusunu yürütür
        //sonucu resultSet nesnesinden yönetiriz
        ResultSet resultSet = statement.executeQuery("select * from users1");

    /*
            Bazı resultSet metodları

                resultSet.next() --> Bir sonraki satıra geçer
                resultSet.previous() --> Bir önceki satıra geçer
                resultSet.beforeFirst() --> İlk satırdan önce gider
                resultSet.afterLast() --> Son satırdan sonra gider
                resultSet.last() --> Son satıra gider
                resultSet.absolute(n) --> Belirli bir satıra gider
    */


        //query'de kaç satır olduğunu nasıl buluruz
        //move to last row
        resultSet.last();

        //get the row count
        System.out.println(resultSet.getRow());


        //to move before first row after we use .last() method
        resultSet.beforeFirst();
        System.out.println(resultSet.getRow());


        //print all second column information
        while (resultSet.next()) {
            System.out.println(resultSet.getString(2));
        }


        //3 aşamada db bağlantısını kes
        resultSet.close();
        statement.close();
        connection.close();


    }

    @Test
    public void metaData() throws SQLException {
        String dbUrl = "jdbc:oracle:thin:@54.152.219.47:1521:xe";
        String dbUsername = "hr";
        String dbPassword = "hr";

        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("select * from users1");


        //get the database related data inside the dbMetaData object
        DatabaseMetaData data = connection.getMetaData();
        System.out.println("data.getUserName() = " + data.getUserName());
        System.out.println("data.getDatabaseProductName() = " + data.getDatabaseProductName());
        System.out.println("data.getDriverName() = " + data.getDriverName());
        System.out.println("data.getDriverVersion() = " + data.getDriverVersion());

        //get the resultsetmetadata
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        int columnCount = resultSetMetaData.getColumnCount();
        System.out.println("columnCount = " + columnCount);
        for (int i = 0; i < columnCount; i++) {
            System.out.println("resultSetMetaData.getColumnName(i) = " + resultSetMetaData.getColumnName(i+1));
        }

        resultSet.close();
        statement.close();
        connection.close();
    }
}
