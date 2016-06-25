package br.univel.views;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import br.univel.model.cliente.Cliente;

import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClienteView extends JFrame{
	private JTextField txtNome;
	private JTextField txtEndereco;
	private JTextField txtNumero;
	private JTextField txtBairro;
	private JTextField txtCidade;
	private JTextField txtEstado;
	private JTextField txtComplemento;
	private JTextField txtTelefone;
	private JTextField txtCelular;
	private JTextField txtCep;
	private JLabel lbnCodigo;
	
	Cliente c ;
	
	public ClienteView() {
		getContentPane().setLayout(null);
		
		JLabel lblDadosCliente = new JLabel("Dados Cliente");
		lblDadosCliente.setHorizontalAlignment(SwingConstants.CENTER);
		lblDadosCliente.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblDadosCliente.setBounds(2, 0, 327, 33);
		getContentPane().add(lblDadosCliente);
		
		JLabel lblNome = new JLabel("Nome: ");
		lblNome.setBounds(14, 39, 116, 14);
		getContentPane().add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setBounds(14, 59, 209, 20);
		getContentPane().add(txtNome);
		txtNome.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(339, 0, 2, 261);
		getContentPane().add(separator);
		
		lbnCodigo = new JLabel("Codigo");
		lbnCodigo.setBounds(342, 9, 82, 14);
		getContentPane().add(lbnCodigo);
		
		JButton btnNewButton = new JButton("Salvar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnNewButton.setBounds(341, 30, 89, 23);
		getContentPane().add(btnNewButton);
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSair.setBounds(341, 58, 89, 23);
		getContentPane().add(btnSair);
		
		JLabel lblEndere = new JLabel("Endereço: ");
		lblEndere.setBounds(14, 87, 72, 14);
		getContentPane().add(lblEndere);
		
		txtEndereco = new JTextField();
		txtEndereco.setColumns(10);
		txtEndereco.setBounds(14, 107, 248, 20);
		getContentPane().add(txtEndereco);
		
		JLabel lbnNumCasa = new JLabel("Numero: ");
		lbnNumCasa.setBounds(267, 87, 62, 14);
		getContentPane().add(lbnNumCasa);
		
		txtNumero = new JTextField();
		txtNumero.setBounds(267, 107, 62, 20);
		getContentPane().add(txtNumero);
		txtNumero.setColumns(10);
		
		JLabel lblBairro = new JLabel("Bairro: ");
		lblBairro.setBounds(14, 135, 72, 14);
		getContentPane().add(lblBairro);
		
		txtBairro = new JTextField();
		txtBairro.setColumns(10);
		txtBairro.setBounds(14, 155, 132, 20);
		getContentPane().add(txtBairro);
		
		JLabel lblCidade = new JLabel("Cidade: ");
		lblCidade.setBounds(156, 135, 84, 14);
		getContentPane().add(lblCidade);
		
		txtCidade = new JTextField();
		txtCidade.setColumns(10);
		txtCidade.setBounds(156, 155, 116, 20);
		getContentPane().add(txtCidade);
		
		JLabel lblEstado = new JLabel("Estado: ");
		lblEstado.setBounds(277, 135, 52, 14);
		getContentPane().add(lblEstado);
		
		txtEstado = new JTextField();
		txtEstado.setColumns(10);
		txtEstado.setBounds(277, 155, 57, 20);
		getContentPane().add(txtEstado);
		
		JLabel lblComplemento = new JLabel("Complemento: ");
		lblComplemento.setBounds(14, 186, 97, 14);
		getContentPane().add(lblComplemento);
		
		txtComplemento = new JTextField();
		txtComplemento.setColumns(10);
		txtComplemento.setBounds(14, 205, 132, 20);
		getContentPane().add(txtComplemento);
		
		JLabel lblTelefone = new JLabel("Telefone: ");
		lblTelefone.setBounds(151, 186, 72, 14);
		getContentPane().add(lblTelefone);
		
		txtTelefone = new JTextField();
		txtTelefone.setColumns(10);
		txtTelefone.setBounds(151, 205, 89, 20);
		getContentPane().add(txtTelefone);
		
		JLabel lblCelular = new JLabel("Celular: ");
		lblCelular.setBounds(247, 186, 72, 14);
		getContentPane().add(lblCelular);
		
		txtCelular = new JTextField();
		txtCelular.setColumns(10);
		txtCelular.setBounds(247, 205, 89, 20);
		getContentPane().add(txtCelular);
		
		JLabel lblCep = new JLabel("Cep: ");
		lblCep.setBounds(232, 39, 72, 14);
		getContentPane().add(lblCep);
		
		txtCep = new JTextField();
		txtCep.setColumns(10);
		txtCep.setBounds(232, 59, 97, 20);
		getContentPane().add(txtCep);
		
		// Alterar
		if(PesqClientesView.cliAlterar != null){
			atualizaCampos(PesqClientesView.cliAlterar);
		}
	}
	
	public void atualizaCampos(Cliente c){
		lbnCodigo.setText(lbnCodigo.getText() + " " + c.getId());
		txtNome.setText(c.getNome());
		txtEndereco.setText(c.getEndereco());
		txtCep.setText(c.getCep());
		txtBairro.setText(c.getBairro());
		txtNumero.setText(Integer.toString(c.getNumero()));
		txtEstado.setText(c.getEstado());
		txtCidade.setText(c.getCidade());
		txtTelefone.setText(c.getTelefone());
		txtCelular.setText(c.getCelular());
	}
	
	public void limparCampos(){
		lbnCodigo.setText("Código: ");
		txtNome.setText("");
		txtEndereco.setText("");
		txtCep.setText("");
		txtBairro.setText("");
		txtNumero.setText("");
		txtEstado.setText("");
		txtCidade.setText("");
		txtTelefone.setText("");
		txtCelular.setText("");
	}
}




