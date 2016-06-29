package br.univel.model.cliente.dao;

import br.univel.database.ConnectionDB;
import br.univel.database.ConnectionDB_dev;
import br.univel.generics.Execute;
import br.univel.model.cliente.Cliente;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by felipefrizzo on 6/19/16.
 */
public class DAOTestUpdateCliente {
    private Connection connection = null;
    private Execute execute;
    private Cliente cliente;
    private PreparedStatement preparedStatement = null;

    @Before
    public void setUp() {
        connection = new ConnectionDB_dev().getInstance().open();

        execute = new Execute();
        cliente = new Cliente();
        execute.getCreateTable(connection, cliente);
        try {
            cliente.setNome("Felipe Frizzo");
            preparedStatement = execute.getSqlInsert(connection, cliente);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        preparedStatement = null;
    }

    @Test
    public void test_update() {
        int update = 0;
        try {
            cliente.setCidade("Cascavel");
            preparedStatement = execute.getSqlUpdateById(connection, cliente, 1);
            update = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(1, update);
    }

    @After
    public void close() {
        try {
            execute.getDropTable(connection, cliente);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
