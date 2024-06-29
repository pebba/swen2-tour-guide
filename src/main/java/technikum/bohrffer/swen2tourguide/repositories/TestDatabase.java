package technikum.bohrffer.swen2tourguide.repositories;

import java.sql.*;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class TestDatabase {
    private static final String URL = "jdbc:postgresql://localhost:5433/tourdb"; //swen2 //mtcg = name in db \c mtcg -> table users;
    //steps on laptop = CREATE DATABASE mtcg; \c mtgc, create table users(id varchar(50), name varchar(50)), profit
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "pwd";


    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public static void TestCon(){
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            System.out.println("Connected to the PostgreSQL database!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println("TESTCON");
    }

    public void TestSelect(){
        try (
                Connection con = getConnection();
        Statement pstmt = con.createStatement();
        ResultSet rs = pstmt.executeQuery("select * from tours");
        ){
            while (rs.next()) {
                String name = rs.getString("name");

                System.out.println(name);
            }


        } catch (SQLException e){
            e.printStackTrace();

        }
        System.out.println("TEST");

    }

}
