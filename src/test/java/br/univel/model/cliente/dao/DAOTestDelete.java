package br.univel.model.cliente.dao;

import br.univel.ConnectionFake;
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
public class DAOTestDelete {
    private Connection connection = null;
    private Execute execute;
    private Cliente cliente;
    private PreparedStatement preparedStatement = null;

    @Before
    public void setUp() {
        connection = new ConnectionFake();

        execute = new Execute();
        cliente = new Cliente();

        try {
            cliente.setId(1);
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
            preparedStatement = execute.getSqlDeleteById(connection, cliente, cliente.getId());
            delete = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(1, delete);
    }

    @After
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
