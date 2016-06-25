package br.unive.tabelaModelos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.univel.model.cliente.Cliente;
import br.univel.model.vendas.Venda;

public class ModeloVenda extends AbstractTableModel{

	List<Venda> vendas = new ArrayList<Venda>();
	
	public ModeloVenda(List<Venda> vendas){
		this.vendas = vendas;
	}
	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public int getRowCount() {
		return vendas.size();
	}
	
	@Override
	public String getColumnName(int column) {
		switch( column) {
		case 0:
			return "Código";
		case 1:
			return "Nome Cliente";
		case 2:
			return "Produtos";	
		default:
			return "Vlr Total";
		}
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Venda v = vendas.get(rowIndex);
		
		switch(columnIndex) {
		case 0:
			return v.getId();
		case 1:
			return v.getCliente().getNome();
		case 2:
			
			return "Deu ruim 2";
		default:
			return "Deu Ruim !";
		}
	}

}
