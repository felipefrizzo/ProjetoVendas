package br.univel;

import br.unive.views.PrincipalView;
import br.univel.database.ConnectionDB;
import br.univel.generics.Execute;

import java.sql.Connection;
import java.sql.SQLException;

public class Principal {
    public Principal() {
    	
    }
    
    public static void main(String[] args) {
        PrincipalView pv = new PrincipalView();
        pv.setEnabled(true);
        pv.setVisible(true);
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

    

}
