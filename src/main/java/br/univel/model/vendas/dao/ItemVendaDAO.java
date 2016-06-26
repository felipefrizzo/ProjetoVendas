package br.univel.model.vendas.dao;

import br.univel.database.ConnectionDB;
import br.univel.generics.Dao;
import br.univel.generics.Execute;
import br.univel.model.produto.Produto;
import br.univel.model.produto.dao.ProdutoDAO;
import br.univel.model.vendas.ItemVenda;
import br.univel.model.vendas.Venda;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by felipefrizzo on 6/26/16.
 */
public class ItemVendaDAO implements Dao<ItemVenda, Integer> {
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private Connection con = null;
    private Execute ex = new Execute();
    private ArrayList<ItemVenda> list = null;

    @Override
    public void save(ItemVenda itemVenda) {
        con = ConnectionDB.getInstance().open();

        try {
            ps = ex.getSqlInsert(con, itemVenda);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public ItemVenda search(Integer integer) {
        con = ConnectionDB.getInstance().open();
        ItemVenda itemVenda = new ItemVenda();
        try {
            ps = ex.getSqlSelectById(con, itemVenda, integer);
            rs = ps.executeQuery();
            rs.next();

            ProdutoDAO produtoDAO = new ProdutoDAO();
            Produto p = produtoDAO.search(rs.getInt("produto"));
            VendaDAO vendaDAO = new VendaDAO();
            Venda v = vendaDAO.search(rs.getInt("venda"));

            itemVenda = new ItemVenda(rs.getInt("id"), rs.getBigDecimal("quantidade"),
                    rs.getBigDecimal("preco"), p, v);
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
        return itemVenda;
    }

    @Override
    public void update(ItemVenda itemVenda) {
        con = ConnectionDB.getInstance().open();

        try {
            ps = ex.getSqlUpdateById(con, itemVenda, itemVenda.getId());
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
        con = ConnectionDB.getInstance().open();
        try {
            ps = ex.getSqlDeleteById(con, new ItemVenda(), integer);
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
    public List<ItemVenda> listAll() {
        con = ConnectionDB.getInstance().open();
        list = null;

        try {
            ItemVenda itemVenda = new ItemVenda();
            list = new ArrayList<ItemVenda>();
            ps = ex.getSqlSelectAll(con, itemVenda);
            rs = ps.executeQuery();

            ProdutoDAO produtoDAO = new ProdutoDAO();
            Produto p = produtoDAO.search(rs.getInt("produto"));
            VendaDAO vendaDAO = new VendaDAO();
            Venda v = vendaDAO.search(rs.getInt("venda"));

            while (rs.next()) {
                list.add(new ItemVenda(rs.getInt("id"), rs.getBigDecimal("quantidade"),
                        rs.getBigDecimal("preco"), p, v));
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
