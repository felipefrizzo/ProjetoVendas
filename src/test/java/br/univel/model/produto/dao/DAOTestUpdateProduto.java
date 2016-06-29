package br.univel.model.produto.dao;

import br.univel.database.ConnectionDB_dev;
import br.univel.generics.Execute;
import br.univel.model.produto.Produto;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by felipefrizzo on 6/19/16.
 */
public class DAOTestUpdateProduto {
    private Connection connection = null;
    private Execute execute;
    private Produto produto;
    private PreparedStatement preparedStatement = null;

    @Before
    public void setUp() {
        connection = new ConnectionDB_dev().getInstance().open();

        execute = new Execute();
        produto = new Produto();
        execute.getCreateTable(connection, produto);
        try {
            produto.setNome("COCCA COLA 70l");
            preparedStatement = execute.getSqlInsert(connection, produto);
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
            produto.setPreco(new BigDecimal(3.40));
            preparedStatement = execute.getSqlUpdateById(connection, produto, 1);
            update = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(1, update);
    }

    @After
    public void close() {
        try {
            execute.getDropTable(connection, produto);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
