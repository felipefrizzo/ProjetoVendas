package br.univel.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by felipefrizzo on 6/21/16.
 */
public class ConnectionDB_dev {
    private Connection con;
    private static ConnectionDB inst;
    private String driver = "org.postgresql.Driver";
    private String url = "jdbc:postgresql://192.168.99.100:5432/vendas_dev";
    private String username = "postgres";
    private String password = "{root}";

    public ConnectionDB_dev() {

    }

    public static ConnectionDB getInstance() {
        if (inst == null)
            return inst = new ConnectionDB();
        return inst;
    }

    public Connection open() {
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
