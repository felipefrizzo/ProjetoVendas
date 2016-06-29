package br.univel.model.venda.dao;

import br.univel.database.ConnectionDB_dev;
import br.univel.generics.Execute;
import br.univel.model.cliente.Cliente;
import br.univel.model.cliente.dao.ClienteDAO;
import br.univel.model.vendas.NewVenda;
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
public class DAOTestUpdateVenda {
    private Connection connection = null;
    private Execute execute;
    private NewVenda venda;
    private PreparedStatement preparedStatement = null;

    @Before
    public void setUp() {
        connection = new ConnectionDB_dev().getInstance().open();
        execute = new Execute();
        execute.getCreateTable(connection, new Cliente());
        try {
            Cliente cliente = new Cliente();
            cliente.setNome("Felipe Frizzo");
            preparedStatement = execute.getSqlInsert(connection, cliente);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        venda = new NewVenda();
        execute.getCreateTable(connection, venda);
        try {
            Cliente cliente =new ClienteDAO().search(1);
            venda.setCliente(cliente);
            venda.setCliente(cliente);
            preparedStatement = execute.getSqlInsert(connection, venda);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        preparedStatement = null;
    }

    @Test
    public void test_update() {
        try {
            Cliente cliente = new Cliente();
            cliente.setNome("Felipe Frizzo");
            preparedStatement = execute.getSqlInsert(connection, cliente);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        int update = 0;
        try {
            Cliente cliente =new ClienteDAO().search(2);
            venda.setCliente(cliente);
            preparedStatement = execute.getSqlUpdateById(connection, venda, 1);
            update = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(1, update);
    }

    @After
    public void close() {
        try {
            execute.getDropTable(connection, venda);
            execute.getDropTable(connection, new Cliente());
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
