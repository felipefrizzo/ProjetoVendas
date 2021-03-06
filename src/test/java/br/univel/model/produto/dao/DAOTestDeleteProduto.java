package br.univel.model.produto.dao;

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
public class DAOTestDeleteProduto {
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
    public void test_delete() {
        int delete = 0;
        try {
            preparedStatement = execute.getSqlDeleteById(connection, cliente, 1);
            delete = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(1, delete);
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
