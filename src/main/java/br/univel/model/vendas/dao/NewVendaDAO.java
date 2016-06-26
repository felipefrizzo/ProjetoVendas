package br.univel.model.vendas.dao;

import br.univel.database.ConnectionDB;
import br.univel.generics.Dao;
import br.univel.generics.Execute;
import br.univel.model.cliente.Cliente;
import br.univel.model.cliente.dao.ClienteDAO;
import br.univel.model.vendas.NewVenda;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by felipefrizzo on 6/26/16.
 */
public class NewVendaDAO implements Dao<NewVenda, Integer> {
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private Connection con = null;
    private Execute ex = new Execute();
    private ArrayList<NewVenda> list = null;

    @Override
    public void save(NewVenda newVenda) {
        con = ConnectionDB.getInstance().open();

        try {
            ps = ex.getSqlInsert(con, newVenda);
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
    public NewVenda search(Integer integer) {
        con = ConnectionDB.getInstance().open();
        NewVenda newVenda = new NewVenda();
        try {
            ps = ex.getSqlSelectById(con, newVenda, integer);
            rs = ps.executeQuery();
            rs.next();
            ClienteDAO dao = new ClienteDAO();
            Cliente c = dao.search(rs.getInt("cliente"));
            newVenda = new NewVenda(rs.getInt("id"), c);
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
        return newVenda;
    }

    @Override
    public void update(NewVenda newVenda) {
        con = ConnectionDB.getInstance().open();

        try {
            ps = ex.getSqlUpdateById(con, newVenda, newVenda.getId());
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
        con = ConnectionDB.getInstance().open();
        list = null;

        try {
            NewVenda newVenda = new NewVenda();
            list = new ArrayList<NewVenda>();
            ps = ex.getSqlSelectAll(con, newVenda);
            rs = ps.executeQuery();

            while (rs.next()) {
                ClienteDAO dao = new ClienteDAO();
                Cliente c = dao.search(rs.getInt("cliente"));
                list.add(new NewVenda(rs.getInt("id"), c));
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
