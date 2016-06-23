package br.unive.views;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JButton;

public class ClienteView extends JFrame{
	private JTextField txtNome;
	private JTextField txtEndereco;
	private JTextField textField;
	private JTextField txtBairro;
	private JTextField txtCidade;
	private JTextField textField_1;
	private JTextField txtComplemento;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	public ClienteView() {
		getContentPane().setLayout(null);
		
		JLabel lblDadosCliente = new JLabel("Dados Cliente");
		lblDadosCliente.setHorizontalAlignment(SwingConstants.CENTER);
		lblDadosCliente.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblDadosCliente.setBounds(2, 0, 327, 33);
		getContentPane().add(lblDadosCliente);
		
		JLabel lblNome = new JLabel("Nome: ");
		lblNome.setBounds(14, 39, 34, 14);
		getContentPane().add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setBounds(14, 59, 209, 20);
		getContentPane().add(txtNome);
		txtNome.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(339, 0, 2, 261);
		getContentPane().add(separator);
		
		JLabel lbnCodigo = new JLabel("Codigo");
		lbnCodigo.setBounds(342, 9, 40, 14);
		getContentPane().add(lbnCodigo);
		
		JButton btnNewButton = new JButton("Salvar");
		btnNewButton.setBounds(341, 30, 89, 23);
		getContentPane().add(btnNewButton);
		
		JButton btnSair = new JButton("Sair");
		btnSair.setBounds(341, 58, 89, 23);
		getContentPane().add(btnSair);
		
		JLabel lblEndere = new JLabel("Endereço: ");
		lblEndere.setBounds(14, 87, 52, 14);
		getContentPane().add(lblEndere);
		
		txtEndereco = new JTextField();
		txtEndereco.setColumns(10);
		txtEndereco.setBounds(14, 107, 248, 20);
		getContentPane().add(txtEndereco);
		
		JLabel lbnNumCasa = new JLabel("Numero: ");
		lbnNumCasa.setBounds(267, 87, 52, 14);
		getContentPane().add(lbnNumCasa);
		
		textField = new JTextField();
		textField.setBounds(267, 107, 62, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblBairro = new JLabel("Bairro: ");
		lblBairro.setBounds(14, 135, 52, 14);
		getContentPane().add(lblBairro);
		
		txtBairro = new JTextField();
		txtBairro.setColumns(10);
		txtBairro.setBounds(14, 155, 132, 20);
		getContentPane().add(txtBairro);
		
		JLabel lblCidade = new JLabel("Cidade: ");
		lblCidade.setBounds(156, 135, 52, 14);
		getContentPane().add(lblCidade);
		
		txtCidade = new JTextField();
		txtCidade.setColumns(10);
		txtCidade.setBounds(156, 155, 116, 20);
		getContentPane().add(txtCidade);
		
		JLabel lblEstado = new JLabel("Estado: ");
		lblEstado.setBounds(277, 135, 52, 14);
		getContentPane().add(lblEstado);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(277, 155, 57, 20);
		getContentPane().add(textField_1);
		
		JLabel lblComplemento = new JLabel("Complemento: ");
		lblComplemento.setBounds(14, 186, 72, 14);
		getContentPane().add(lblComplemento);
		
		txtComplemento = new JTextField();
		txtComplemento.setColumns(10);
		txtComplemento.setBounds(14, 205, 132, 20);
		getContentPane().add(txtComplemento);
		
		JLabel lblTelefone = new JLabel("Telefone: ");
		lblTelefone.setBounds(151, 186, 72, 14);
		getContentPane().add(lblTelefone);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(151, 205, 89, 20);
		getContentPane().add(textField_2);
		
		JLabel lblCelular = new JLabel("Celular: ");
		lblCelular.setBounds(247, 186, 72, 14);
		getContentPane().add(lblCelular);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(247, 205, 89, 20);
		getContentPane().add(textField_3);
		
		JLabel lblCep = new JLabel("Cep: ");
		lblCep.setBounds(232, 39, 52, 14);
		getContentPane().add(lblCep);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(232, 59, 97, 20);
		getContentPane().add(textField_4);
	}
}
