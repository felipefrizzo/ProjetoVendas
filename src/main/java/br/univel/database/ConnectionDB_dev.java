package br.univel.database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by felipefrizzo on 6/21/16.
 */
public class ConnectionDB_dev {
    private Connection con;
    private static ConnectionDB inst;
    private String driver = "org.postgresql.Driver";
    private String url;
    private String username;
    private String password;

    public ConnectionDB_dev() {

    }

    public static ConnectionDB getInstance() {
        if (inst == null)
            return inst = new ConnectionDB();
        return inst;
    }

    public Connection open() {
        try {

            File file = new File("config_dev.properties");
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
