package br.univel.database;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by felipefrizzo on 4/24/16.
 */
public class ConnectionDB {
    private Connection con;
    private static ConnectionDB inst;
    private String url;
    private String username;
    private String password;
    private String driver = "org.postgresql.Driver";
<<<<<<< HEAD
    private String url = "jdbc:postgresql://localhost:5432/vendas";
    private String username = "postgres";
    private String password = "123";
=======
//    private String url = "jdbc:postgresql://192.168.99.100:5432/vendas";
//    private String username = "postgres";
//    private String password = "root";
>>>>>>> origin/master

    public ConnectionDB() {

    }

    public static ConnectionDB getInstance() {
        if (inst == null)
            return inst = new ConnectionDB();
        return inst;
    }

    public Connection open() {
        try {

            File file = new File("config.properties");
            FileInputStream fileInput = new FileInputStream(file);
            Properties properties = new Properties();
            properties.load(fileInput);
            url = "jdbc:postgresql://" + properties.getProperty("database");
            username = properties.getProperty("username");
            password = properties.getProperty("password");

            fileInput.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, username, password);
            return con;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void close() {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
