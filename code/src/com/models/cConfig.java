package com.models;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Statement;

import javax.sql.DataSource;

import com.entity.userEntity;


import java.sql.ResultSet;
import java.sql.SQLException;

public class cConfig {
    
    // ini untuk mendefinisikan koneksi ke database akatsuki
    // private static final String koneksi = "com.mysql.jdbc.Driver"; 
    private static final String url = "jdbc:mysql://localhost:3306/movierent";
    private static final String user = "root";
    private static final String pass = "";

    // ini untuk instansisasi object dari class yg sudah di import
    private static Connection connect ;
    private static Statement statement ;
    private static ResultSet resultData ;
    private DataSource dataSource;

    // Method static connection
    public static void connection(){ 
        try {
            // Regist Driver
            // Class.forName(koneksi);

            // buat koneksi db
            connect = DriverManager.getConnection(url, user, pass);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public cConfig(){ 
        try {
            // Regist Driver
            // Class.forName(koneksi);

            // buat koneksi db
            connect = DriverManager.getConnection(url, user, pass);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public String LoginValidate(String userid, String password) {
        cConfig.connection();
        
       
        // Validasi input tidak boleh kosong
        if (userid.equals("")|| password.equals("")) {
            return "userid and password cannot be empty!";
        }

        userEntity user = new userEntity();
        user.setUserId(userid);
        user.setPassword(password);
        

        // Validasi user exists pada DB
        if (!CheckUserExists(user.getUserid())) {
            return "userid is not exists, please try again.";
        }
    
        // Validasi userid & password valid
        if (!UserPasswordValid(user)) {
            return "userid and password is not valid, please try again";
        }
        return "";
    }

    public cConfig (DataSource dataSource) {
    this.dataSource = dataSource;
    }

    public static boolean CheckUserExists(String userid) {
    String sql = "SELECT * FROM user WHERE name = ? ";
    cConfig.connection();

    try (
        PreparedStatement stmt = connect.prepareStatement(sql);
    ) {
        stmt.setString(1,userid);
        ResultSet resultSet = stmt.executeQuery();

        if (resultSet.next()) {
            // User Valid
            resultSet.close();
            return true;
        } else {
            resultSet.close();
            return false;
        }
    } catch (SQLException ex) {
        throw new RuntimeException(ex);
    }
}

    public static boolean UserPasswordValid(userEntity user) {
    String sql = "SELECT * FROM user WHERE name = ? and pass = ?";
    cConfig.connection();
    try (
        PreparedStatement stmt = connect.prepareStatement(sql);
    ) {
        stmt.setString(1,user.getUserid());
        stmt.setString(2,user.getPassword());
        ResultSet resultSet = stmt.executeQuery();

        if (resultSet.next()) {
            // User Valid
            resultSet.close();
            return true;
        } else {
            resultSet.close();
            return false;
        }
        } catch (SQLException ex) {
        throw new RuntimeException(ex);
        }
    }

    public static boolean register( String regUsername, String regPass ) {
        cConfig.connection();
        
        boolean data = false ;

        try {

            statement = connect.createStatement();

            String query = "INSERT INTO user VALUES (" + null + ", '" + regUsername + "', '" + regPass +"')" ;

            if(!statement.execute(query)){
                data = true;
            }
            


            // close statement dan koneksi
            statement.close();
            connect.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public static String dataMovie() {
        cConfig.connection();

        // nilai default var data
        String data = "Data Masih Kosong";

        try {

            // buat object statement yang ambil dari koneksi
            statement = connect.createStatement();

            // Querry MYSQL
            String query = "SELECT * FROM movie";

            // eksekusi querry
            resultData = statement.executeQuery(query);

            // set var data jadi null
            data = "";
            
            // looping pengisian variabel data
            while(resultData.next()){
                data += resultData.getString("movieId") +". Movie Title '" + resultData.getString("title") + "' Genre '" +resultData.getString("genre") +"'" +"\n" ; 
            }

            // close statement and connection
            statement.close();
            connect.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public static boolean addMovie( String title, String genre ) {
        cConfig.connection();
        
        boolean data = false ;

        try {

            statement = connect.createStatement();

            String query = "INSERT INTO movie VALUES (" + null + ", '" + title + "', '" + genre +"')" ;

            if(!statement.execute(query)){
                data = true;
            }

            // close statement dan koneksi
            statement.close();
            connect.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }
    
    public static boolean delMovie(String movieId ) {
        cConfig.connection();
        
        boolean data = false ;

        try {
            statement = connect.createStatement();

            String query = "DELETE FROM movie WHERE movieId="+movieId  ;

            if(!statement.execute(query)){
                data = true;
            }
            
            // close statement dan koneksi
            statement.close();
            connect.close();
            System.out.println("SUCESS - Movie has been deleted");
        } catch (Exception e) {
            
            System.out.println("Error - This film is being rented");
        }

        return data;
    }
    
    public static String dataRent() {
        cConfig.connection();

        // nilai default var data
        String data = "Data Masih Kosong";

        try {

            // buat object statement yang ambil dari koneksi
            statement = connect.createStatement();

            // Querry MYSQL
            String query = "SELECT rent.rentId, user.name, movie.title, rent.rentDate, rent.returnDate FROM user JOIN rent ON user.userId = rent.userId JOIN movie ON rent.movieId = movie.movieId;";

            // eksekusi querry
            resultData = statement.executeQuery(query);

            // set var data jadi null
            data = "";
            
            // looping pengisian variabel data
            while(resultData.next()){
                data += resultData.getString("rentId") +". user '" + resultData.getString("name") + "' movie '"+ resultData.getString("title") + "' rent date '"+ resultData.getString("rentDate") + "' return date '"+ resultData.getString("returnDate") +"' " +" \n"; 
            }

            // close statement and connection
            statement.close();
            connect.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public static String activeRent() {
        cConfig.connection();

        // nilai default var data
        String data = "Data Masih Kosong";

        try {

            // buat object statement yang ambil dari koneksi
            statement = connect.createStatement();

            // Querry MYSQL
            String query = "SELECT rent.rentId, user.name, movie.title, rent.rentDate, rent.returnDate FROM user JOIN rent ON user.userId = rent.userId JOIN movie ON rent.movieId = movie.movieId WHERE rent.returnDate IS NULL;";

            // eksekusi querry
            resultData = statement.executeQuery(query);

            // set var data jadi null
            data = "";
            
            // looping pengisian variabel data
            while(resultData.next()){
                data += resultData.getString("rentId") +". user '" + resultData.getString("name") + "' movie '"+ resultData.getString("title") + "' rent date '"+ resultData.getString("rentDate") + "' return date '"+ resultData.getString("returnDate") +"' " +" \n"; 
            }

            // close statement and connection
            statement.close();
            connect.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public static boolean addRent( String movieId, String ticektRent) {
        cConfig.connection();
        
        boolean data = false ;

        try {

            statement = connect.createStatement();

            String query = "INSERT INTO rent VALUES (" + null + ", CURRENT_TIMESTAMP ," + null + ", '" + movieId + "' , '" + ticektRent + "')"  ;

            if(!statement.execute(query)){
                data = true;
            }

            // close statement dan koneksi
            statement.close();
            connect.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public static boolean returnRent( String movieId) {
        cConfig.connection();
        
        boolean data = false ;

        try {

            statement = connect.createStatement();

            String query = "UPDATE rent SET returnDate ="+"current_timestamp()"+" WHERE rentId =" + movieId   ;

            if(!statement.execute(query)){
                data = true;
            }

            // close statement dan koneksi
            statement.close();
            connect.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public static boolean delRent(String rentId ) {
        cConfig.connection();
        
        boolean data = false ;

        try {
            statement = connect.createStatement();

            String query = "DELETE FROM rent WHERE rentId="+rentId  ;

            if(!statement.execute(query)){
                data = true;
            }
            
            // close statement dan koneksi
            statement.close();
            connect.close();
            System.out.println("SUCESS - Rent has been deleted");
        } catch (Exception e) {
            
        }

        return data;
    }
    
    public static String dataUser() {
        cConfig.connection();

        // nilai default var data
        String data = "Data Masih Kosong";

        try {

            // buat object statement yang ambil dari koneksi
            statement = connect.createStatement();

            // Querry MYSQL
            String query = "SELECT * FROM user";

            // eksekusi querry
            resultData = statement.executeQuery(query);

            // set var data jadi null
            data = "";
            
            // looping pengisian variabel data
            while(resultData.next()){
                data += resultData.getString("userId") +"." + resultData.getString("name") + "\n" ; 
            }

            // close statement and connection
            statement.close();
            connect.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public static String ticketRent(String userid) {
        cConfig.connection();

        String data = "Data tidak di temukan";

        try {

            statement = connect.createStatement();
            
            String query = "SELECT * FROM user Where name = '"+userid+"'";

            resultData = statement.executeQuery(query);

            data = "";

            while(resultData.next()) {
                data +=  "your movie rental ticket is : " + resultData.getString("userId") ; 
            }

            // close statement
            statement.close();
            connect.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;

        
    }

    public static boolean upPassDataUser(String passBaru, String userid ) {
        cConfig.connection();
        
        boolean data = false ;

        try {

            statement = connect.createStatement();

            String query = "UPDATE user SET pass ='"+passBaru+"' WHERE name ='"+userid+"'"  ;

            if(!statement.execute(query)){
                data = true;
            }
            


            // close statement dan koneksi
            statement.close();
            connect.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }   


}
