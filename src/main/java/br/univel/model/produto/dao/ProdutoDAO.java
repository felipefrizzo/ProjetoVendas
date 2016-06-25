package br.univel.model.produto.dao;

import br.univel.database.ConnectionDB;
import br.univel.generics.Dao;
import br.univel.generics.Execute;
import br.univel.model.produto.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by felipefrizzo on 6/18/16.
 */
public class ProdutoDAO implements Dao<Produto, Integer> {
    private PreparedStatement ps  = null;
    private ResultSet rs = null;
    private Connection con = ConnectionDB.getInstance().open();
    private Execute ex = new Execute();
    private List<Produto> list = null;

    @Override
    public void save(Produto produto) {
        try {
            ps = ex.getSqlInsert(con, produto);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Produto search(Integer integer) {
        Produto produto = new Produto();
        try {
            ps = ex.getSqlSelectById(con, produto, integer);
            rs = ps.executeQuery();
            rs.next();

            produto = new Produto(rs.getInt("id"), rs.getString("nome"), rs.getBigDecimal("preco"));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                rs.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return produto;
    }

    @Override
    public void update(Produto produto) {
        try {
            ps = ex.getSqlUpdateById(con, produto, produto.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(Integer integer) {
        try {
            ps = ex.getSqlDeleteById(con, new Produto(), integer);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Produto> listAll() {
        list = null;

        try {
            Produto produto = new Produto();
            list = new ArrayList<Produto>();
            ps = ex.getSqlSelectAll(con, produto);
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Produto(rs.getInt("id"), rs.getString("nome"), rs.getBigDecimal("preco")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                rs.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}
