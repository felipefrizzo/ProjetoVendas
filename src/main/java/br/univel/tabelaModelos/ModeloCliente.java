package br.univel.tabelaModelos;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.univel.model.cliente.Cliente;
import br.univel.model.produto.Produto;

public class ModeloCliente extends AbstractTableModel{

	List<Cliente> clientes = new ArrayList<Cliente>();
	
	public ModeloCliente(List<Cliente> clientes) {
		this.clientes = clientes;
	}
	
	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public int getRowCount() {
		return clientes.size();
	}
	
	@Override
	public String getColumnName(int column) {
		switch( column) {
		case 0:
			return "Código";
		case 1:
			return "Nome";
		default:
			return "Telefone";
		}
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Cliente c = clientes.get(rowIndex);
		
		switch(columnIndex) {
		case 0:
			return c.getId();
		case 1:
			return c.getNome();
		case 2:
			return c.getEndereco();
		case 3:
			return c.getNumero();
		case 4:
			return c.getComplemento();
		case 5:
			return c.getBairro();
		case 6:
			return c.getCidade();
		case 7:
			return c.getEstado();
		case 8:
			return c.getCep();
		case 9:
			return c.getTelefone();
		case 10:
			return c.getCelular();
		default:
			return "Deu Ruim !";
		}
	}

	public Cliente getCliente(int selecionada) {
		return clientes.get(selecionada);
	}
	
	

}
