package br.unive.views;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.List;

import javax.swing.SwingConstants;

import br.unive.tabelaModelos.ModelProduto;
import br.univel.model.produto.Produto;

import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PesqProdutosView extends JFrame{
	private JTextField txtPesquisa;
	private JTable tableProdutos;
	private List<Produto> listaProdutos;
	public PesqProdutosView() {
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 89, 438, 150);
		getContentPane().add(scrollPane);
		
		tableProdutos = new JTable();
		scrollPane.setColumnHeaderView(tableProdutos);
		
		txtPesquisa = new JTextField();
		txtPesquisa.setColumns(10);
		txtPesquisa.setBounds(10, 63, 328, 20);
		getContentPane().add(txtPesquisa);
		
		JLabel lblProdutos = new JLabel("Produtos");
		lblProdutos.setHorizontalAlignment(SwingConstants.CENTER);
		lblProdutos.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblProdutos.setBounds(0, 0, 448, 28);
		getContentPane().add(lblProdutos);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 32, 438, 4);
		getContentPane().add(separator);
		
		JLabel label_1 = new JLabel("Pesquisa");
		label_1.setBounds(10, 42, 42, 14);
		getContentPane().add(label_1);
		
		JButton btnInserir = new JButton("Inserir");
		btnInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProdutoView pv = new ProdutoView();
				pv.setVisible(true);
				pv.setSize(463,142);
			}
		});
		btnInserir.setBounds(458, 3, 91, 23);
		getContentPane().add(btnInserir);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProdutoView pv = new ProdutoView();
				pv.setVisible(true);
				pv.setSize(463,142);
			}
		});
		btnAlterar.setBounds(458, 32, 91, 23);
		getContentPane().add(btnAlterar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(458, 60, 91, 23);
		getContentPane().add(btnExcluir);
	}
	
	public void consulta(){
		ModelProduto model = new ModelProduto(getListaProdutos());
		tableProdutos.setModel(model);
	}

	public List<Produto> getListaProdutos() {
		return listaProdutos;
	}

	public void setListaProdutos(List<Produto> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}
}
