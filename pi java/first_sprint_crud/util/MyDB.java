/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author USER
 */

package first_sprint_crud.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class MyDB {

    private final String url = "jdbc:mysql://localhost:3306/coching";
    public  static String url_upload = "file:///C:/coachingProjet/public/adminAssets/img/";
    public static String url_target = "C:/coachingProjet/public/adminAssets/img/";
    private final String username = "root";
    private final String password = "";
    private Connection connection;
    private static MyDB instance;

    private MyDB() {
        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connexion etablie");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static MyDB getInstance(){
        if(instance == null)
            instance = new MyDB();
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }


}