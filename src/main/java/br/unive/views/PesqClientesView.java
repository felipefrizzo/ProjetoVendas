package br.unive.views;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingConstants;

import br.unive.tabelaModelos.ModeloCliente;
import br.univel.model.cliente.Cliente;
import br.univel.model.cliente.ClienteParser;
import br.univel.model.produto.ProdutoParser;
import br.univel.modelo.readerURL.ReaderURL;
import br.univel.readerArquivo.ReaderArquivo;

import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

public class PesqClientesView extends JFrame{
	private JTextField textField;
	private JTable tableClientes;
	private List<Cliente> listaClientes = new ArrayList<Cliente>();
	public PesqClientesView() {
		// para testar, depois vou substituir pelos dados do banco
		ClienteParser clip = new ClienteParser();
		ReaderArquivo reader = new ReaderArquivo();
		listaClientes = clip.getCliente(reader.lerArquivo(new File("clientes.csv")));
		
		JLabel lblClientes = new JLabel("Clientes");
		lblClientes.setHorizontalAlignment(SwingConstants.CENTER);
		lblClientes.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JSeparator separator = new JSeparator();
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel label_1 = new JLabel("Pesquisa");
		
		JButton button = new JButton("Excluir");
		
		JButton button_1 = new JButton("Inserir");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClienteView cv = new ClienteView();
				cv.setVisible(true);
				cv.setSize(450,301);
			}
		});
		
		JButton button_2 = new JButton("Alterar");
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 328, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblClientes, GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
								.addComponent(separator, GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE))))
					.addPreferredGap(ComponentPlacement.RELATED, 10, GroupLayout.PREFERRED_SIZE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(button_1, GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
						.addComponent(button_2, GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
						.addComponent(button, GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
						.addComponent(btnSair, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(14)
							.addComponent(button_1)
							.addGap(6)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(button_2)
									.addGap(5)
									.addComponent(button))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(label_1)
									.addGap(7)
									.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblClientes, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
						.addComponent(btnSair))
					.addContainerGap())
		);
		
		tableClientes = new JTable();
		scrollPane.setViewportView(tableClientes);
		getContentPane().setLayout(groupLayout);
		Consultar();
	}
	
	public void Consultar(){
		ModeloCliente model = new ModeloCliente(getListaClientes());
		tableClientes.setModel(model);		
	}

	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}
}