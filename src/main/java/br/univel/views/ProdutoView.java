package br.univel.views;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import br.univel.model.produto.Produto;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.awt.event.ActionEvent;

public class ProdutoView extends JFrame{
	private JTextField txtDescricao;
	private JTextField txtPreco;
	JLabel lbnCodigo;
	
	public ProdutoView() {
		getContentPane().setLayout(null);
		
		lbnCodigo = new JLabel("Codigo: ");
		lbnCodigo.setBounds(345, 11, 89, 14);
		getContentPane().add(lbnCodigo);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(345, 36, 89, 23);
		getContentPane().add(btnSalvar);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(340, 0, 2, 102);
		getContentPane().add(separator);
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSair.setBounds(345, 68, 89, 23);
		getContentPane().add(btnSair);
		
		JLabel lblDadosProdutos = new JLabel("Dados Produtos");
		lblDadosProdutos.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblDadosProdutos.setHorizontalAlignment(SwingConstants.CENTER);
		lblDadosProdutos.setBounds(0, 0, 335, 35);
		getContentPane().add(lblDadosProdutos);
		
		JLabel lblNome = new JLabel("Descrição: ");
		lblNome.setBounds(10, 40, 68, 14);
		getContentPane().add(lblNome);
		
		txtDescricao = new JTextField();
		txtDescricao.setBounds(10, 58, 213, 20);
		getContentPane().add(txtDescricao);
		txtDescricao.setColumns(10);
		
		JLabel lblPreco = new JLabel("Preco: ");
		lblPreco.setBounds(233, 40, 68, 14);
		getContentPane().add(lblPreco);
		
		txtPreco = new JTextField();
		txtPreco.setBounds(233, 58, 86, 20);
		getContentPane().add(txtPreco);
		txtPreco.setColumns(10);
		
		if(PesqProdutosView.prodAlterar != null){
			atualizaCampos(PesqProdutosView.prodAlterar);
		}
	}
	
	private void atualizaCampos(Produto prodAlterar) {
		lbnCodigo.setText(lbnCodigo.getText()+ " "+ prodAlterar.getId());
		txtDescricao.setText(prodAlterar.getNome());
		txtPreco.setText(prodAlterar.getPreco().toString());
	}
	
	private void limpaCampos(){
		lbnCodigo.setText("Código: ");
		txtDescricao.setText("");
		txtPreco.setText("");
	}
}
