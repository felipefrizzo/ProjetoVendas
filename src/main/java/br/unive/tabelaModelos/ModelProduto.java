package br.unive.tabelaModelos;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.univel.model.produto.Produto;

public class ModelProduto extends AbstractTableModel{

	private List<Produto> listaProdutos = new ArrayList<>();  

	public ModelProduto(List<Produto> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}
	
	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public int getRowCount() {
		return listaProdutos.size();
	}
	
	@Override
	public String getColumnName(int column) {
		switch( column) {
		case 0:
			return "Código";
		case 1:
			return "Descrição";
		case 2:
			return "Preço";
		default:
			return super.getColumnName(column);
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Produto p = listaProdutos.get(rowIndex);

		switch (columnIndex) {
		case 0:
			return p.getId();
		case 1:
			return p.getNome();
		case 2:
			return p.getPreco();
		default:
			return "erro";
		}
	}

}
