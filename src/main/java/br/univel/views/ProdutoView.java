package br.univel.views;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import br.univel.model.produto.Produto;
import br.univel.model.produto.dao.ProdutoDAO;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.awt.event.ActionEvent;

public class ProdutoView extends JFrame{
	private JTextField txtDescricao;
	private JTextField txtPreco;
	JLabel lbnCodigo;
	public static boolean inserindo = false;   
	ProdutoDAO prodDao = new ProdutoDAO();
	
	public ProdutoView() {
		getContentPane().setLayout(null);
		
		lbnCodigo = new JLabel("");
		lbnCodigo.setBounds(393, 11, 54, 14);
		getContentPane().add(lbnCodigo);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(inserindo){
					Produto p = new Produto();
//					p.setId(Integer.parseInt(lbnCodigo.getText()));
					p.setNome(txtDescricao.getText());
					p.setPreco(new BigDecimal(txtPreco.getText().replaceAll(",", ".")));
					prodDao.save(p);
				}else{ // alterando
//					PesqProdutosView.prodAlterar.setId(Integer.parseInt(lbnCodigo.getText()));
					PesqProdutosView.prodAlterar.setNome(txtDescricao.getText());
					PesqProdutosView.prodAlterar.setPreco(new BigDecimal(txtPreco.getText().replaceAll(",", ".")));
					prodDao.update(PesqProdutosView.prodAlterar);
				}
			}
		});
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
		
		JLabel lblCdigo = new JLabel("Código: ");
		lblCdigo.setBounds(345, 11, 49, 14);
		getContentPane().add(lblCdigo);
		
		if(PesqProdutosView.prodAlterar != null){
			atualizaCampos(PesqProdutosView.prodAlterar);
		}
	}
	
	private void atualizaCampos(Produto prodAlterar) {
		lbnCodigo.setText(String.valueOf(prodAlterar.getId()));
		txtDescricao.setText(prodAlterar.getNome());
		txtPreco.setText(prodAlterar.getPreco().toString());
	}
	
	private void limpaCampos(){
		lbnCodigo.setText("");
		txtDescricao.setText("");
		txtPreco.setText("");
	}
}
