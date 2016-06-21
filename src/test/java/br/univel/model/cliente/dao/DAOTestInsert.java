package br.univel.model.cliente.dao;

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
public class DAOTestInsert {
    private Connection connection = null;
    private Execute execute;
    private Cliente cliente;
    private PreparedStatement preparedStatement = null;

    @Before
    public void setUp() {
        connection = new ConnectionFake();

        execute = new Execute();
        cliente = new Cliente();
        preparedStatement = null;
    }

    @Test
    public void test_insert() {
        int insert = 0;
        try {
            cliente.setId(1);
            preparedStatement = execute.getSqlInsert(connection, cliente);
            insert = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(1, insert);
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