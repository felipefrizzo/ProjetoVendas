package br.univel.model.vendas.dao;

import br.univel.database.ConnectionDB;
import br.univel.generics.Dao;
import br.univel.generics.Execute;
import br.univel.model.vendas.Venda;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by felipefrizzo on 6/18/16.
 */
public class VendaDAO implements Dao<Venda, Integer> {
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private Connection con = ConnectionDB.getInstance().open();
    private Execute ex = new Execute();
    private List<Venda> list = null;

    @Override
    public void save(Venda venda) {
        try {
            ps = ex.getSqlInsert(con, venda);
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
    public Venda search(Integer integer) {
        Venda venda = new Venda();
        try {
            ps = ex.getSqlSelectById(con, venda, integer);
            rs = ps.executeQuery();
            rs.next();

//            venda = new Venda(rs.getInt("id"), rs.getBigDecimal("valorTotal"));
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
        return venda;
    }

    @Override
    public void update(Venda venda) {
        try {
            ps = ex.getSqlUpdateById(con, venda, venda.getId());
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
            ps = ex.getSqlDeleteById(con, new Venda(), integer);
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
    public List<Venda> listAll() {
        list = null;

        try {
            Venda venda = new Venda();
            list = new ArrayList<Venda>();
            ps = ex.getSqlSelectAll(con, venda);
            rs = ps.executeQuery();

            while (rs.next()) {
//                list.add();
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
