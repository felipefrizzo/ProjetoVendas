package br.unive.tabelaModelos;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.univel.model.cliente.Cliente;
import br.univel.model.produto.Produto;

public class ModeloCliente extends AbstractTableModel{

	List<Cliente> clientes;
	
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
		case 2:
			return "Telefone";
		default:
			return super.getColumnName(column);
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
			return c.getTelefone();
		default:
			return "Deu Ruim !";
		}
	}
	
	

}
