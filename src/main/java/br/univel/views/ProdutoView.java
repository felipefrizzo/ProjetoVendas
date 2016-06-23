package br.univel.views;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProdutoView extends JFrame{
	private JTextField textField;
	private JTextField textField_1;
	public ProdutoView() {
		getContentPane().setLayout(null);
		
		JLabel lbnCodigo = new JLabel("Codigo: ");
		lbnCodigo.setBounds(345, 11, 46, 14);
		getContentPane().add(lbnCodigo);
		
		JButton btnNewButton = new JButton("Salvar");
		btnNewButton.setBounds(345, 36, 89, 23);
		getContentPane().add(btnNewButton);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(340, 0, 2, 102);
		getContentPane().add(separator);
		
		JButton btnCancelar = new JButton("Sair");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setBounds(345, 68, 89, 23);
		getContentPane().add(btnCancelar);
		
		JLabel lblDadosProdutos = new JLabel("Dados Produtos");
		lblDadosProdutos.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblDadosProdutos.setHorizontalAlignment(SwingConstants.CENTER);
		lblDadosProdutos.setBounds(0, 0, 335, 35);
		getContentPane().add(lblDadosProdutos);
		
		JLabel lblNome = new JLabel("Descrição: ");
		lblNome.setBounds(10, 40, 68, 14);
		getContentPane().add(lblNome);
		
		textField = new JTextField();
		textField.setBounds(10, 58, 213, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblPreco = new JLabel("Preco: ");
		lblPreco.setBounds(233, 40, 68, 14);
		getContentPane().add(lblPreco);
		
		textField_1 = new JTextField();
		textField_1.setBounds(233, 58, 86, 20);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
	}
}
