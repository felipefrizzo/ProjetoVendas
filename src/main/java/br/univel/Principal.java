package br.univel;

import br.univel.database.ConnectionDB;
import br.univel.generics.Execute;

import java.sql.Connection;
import java.sql.SQLException;

public class Principal {
    public Principal() {

    }

    public void dropTable(Object obj) {
        Execute ex = new Execute();
        Connection con = ConnectionDB.getInstance().open();
        ex.getDropTable(con, obj);
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable(Object obj) {
        Execute ex = new Execute();
        Connection con = ConnectionDB.getInstance().open();
        ex.getCreateTable(con, obj);
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        Principal principal = new Principal();
    }
}
