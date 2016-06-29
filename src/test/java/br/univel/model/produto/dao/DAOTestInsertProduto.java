package br.univel.model.produto.dao;

import br.univel.database.ConnectionDB_dev;
import br.univel.generics.Execute;
import br.univel.model.produto.Produto;
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
public class DAOTestInsertProduto {
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
        preparedStatement = null;
    }

    @Test
    public void test_insert() {
        int insert = 0;
        try {
            produto.setNome("COCA COLA 4l");
            preparedStatement = execute.getSqlInsert(connection, produto);
            insert = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(1, insert);
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