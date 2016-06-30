package br.univel.model.cliente.dao;

import br.univel.database.ConnectionDB;
import br.univel.generics.Dao;
import br.univel.generics.Execute;
import br.univel.model.cliente.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by felipefrizzo on 6/18/16.
 */
public class ClienteDAO implements Dao<Cliente, Integer> {
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private Connection con = null;
    private Execute ex = new Execute();
    private List<Cliente> list = null;

    @Override
    public void save(Cliente cliente) {
        con = ConnectionDB.getInstance().open();
        try {
            ps = ex.getSqlInsert(con, cliente);
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
    public Cliente search(Integer integer) {
        con = ConnectionDB.getInstance().open();
        Cliente cliente = new Cliente();
        try {
            ps = ex.getSqlSelectById(con, cliente, integer);
            rs = ps.executeQuery();
            
            while (rs.next()) {

            cliente = new Cliente(rs.getInt("id"), rs.getString("nome"),
                    rs.getString("endereco"), rs.getInt("numero"), rs.getString("Complemento"),
                    rs.getString("bairro"), rs.getString("cidade"), rs.getString("estado"),
                    rs.getString("cep"), rs.getString("telefone"), rs.getString("celular"));
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
        return cliente;
    }

    @Override
    public void update(Cliente cliente) {
        con = ConnectionDB.getInstance().open();
        try {
            ps = ex.getSqlUpdateById(con, cliente, cliente.getId());
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
            ps = ex.getSqlDeleteById(con, new Cliente(), integer);
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
    public List<Cliente> listAll() {
        con = ConnectionDB.getInstance().open();
        list = null;

        try {
            Cliente cliente = new Cliente();
            list = new ArrayList<Cliente>();
            ps = ex.getSqlSelectAll(con, cliente);
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Cliente(rs.getInt("id"), rs.getString("nome"), rs.getString("endereco"),
                        rs.getInt("numero"), rs.getString("Complemento"), rs.getString("bairro"),
                        rs.getString("cidade"), rs.getString("estado"), rs.getString("cep"),
                        rs.getString("telefone"), rs.getString("celular")));
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
