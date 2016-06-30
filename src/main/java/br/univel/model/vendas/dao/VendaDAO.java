package br.univel.model.vendas.dao;

import br.univel.database.ConnectionDB;
import br.univel.generics.Dao;
import br.univel.generics.Execute;
import br.univel.model.vendas.NewVenda;
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
public class VendaDAO implements Dao<NewVenda, Integer> {
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private Connection con = ConnectionDB.getInstance().open();
    private Execute ex = new Execute();
    private List<NewVenda> list = null;

    @Override
    public void save(NewVenda venda) {
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
    public NewVenda search(Integer integer) {
        NewVenda venda = new NewVenda();
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
    public void update(NewVenda venda) {
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
            ps = ex.getSqlDeleteById(con, new NewVenda(), integer);
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
    public List<NewVenda> listAll() {
        list = null;

        try {
            NewVenda venda = new NewVenda();
            list = new ArrayList<NewVenda>();
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
