package br.unive.tabelaModelos;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.univel.model.vendas.ItemVenda;
import br.univel.model.vendas.NewVenda;

public class ModeloItemVenda extends AbstractTableModel{

	List<ItemVenda> itens = new ArrayList<ItemVenda>();
	
	public ModeloItemVenda(List<ItemVenda> itens){
		this.itens = itens;
	}
	@Override
	public int getColumnCount() {
		return 5;
	}

	@Override
	public int getRowCount() {
		return itens.size();
	}
	
	@Override
	public String getColumnName(int column) {
		switch( column) {
		case 0:
			return "Código";
		case 1:
			return "Descrição";
		case 2:
			return "Qtd";
		case 3:
			return "Vlr Unit";
		default:
			return "Vlr Total";
		}
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		ItemVenda iv = itens.get(rowIndex);
		
		switch(columnIndex) {
		case 0:
			return iv.getId();
		case 1:
			return iv.getProduto().getNome();
		case 2:
			return iv.getQuantidade();
		case 3:
			return iv.getProduto().getPreco();
		default:
			return iv.getQuantidade().multiply(iv.getProduto().getPreco());
		}
	}

}
