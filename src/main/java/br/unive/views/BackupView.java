package br.unive.views;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import br.univel.exception.SerializadorException;
import br.univel.generics.ImplSerializador;
import br.univel.model.cliente.Cliente;
import br.univel.model.produto.Produto;
import br.univel.model.produto.ProdutoParser;
import br.univel.model.vendas.Venda;
import br.univel.modelo.readerURL.ReaderURL;

import javax.swing.JCheckBox;
import java.awt.Checkbox;
import java.awt.event.ActionListener;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class BackupView extends JFrame{
	static Cliente c1, c2;
	static Venda v1, v2;
	
	static ImplSerializador<List<Cliente>> implCliente;
	static ImplSerializador<List<Produto>> implProduto;
	static ImplSerializador<List<Venda>>   implVenda;
	
	static List<Cliente> clientes;
	static List<Produto> produtos;
	static List<Venda> vendas;
	static ArrayList produtosVenda;
	
	public JCheckBox checkboxProdutos;
	public JCheckBox checkboxClientes;
	public JCheckBox checkboxVendas; 
	public BackupView() {
		
		JLabel lblBackup = new JLabel("Backup");
		lblBackup.setHorizontalAlignment(SwingConstants.CENTER);
		lblBackup.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JSeparator separator = new JSeparator();
		
		JButton btnEfetuar = new JButton("Efetuar");
		btnEfetuar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				efetuarBackup();
			}
		});
		
		JButton btnRestaurar = new JButton("Restaurar");
		
		checkboxProdutos = new JCheckBox("Produtos");
		
		checkboxClientes = new JCheckBox("Clientes");
		
		checkboxVendas = new JCheckBox("Vendas");
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblBackup, GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(checkboxClientes, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(checkboxProdutos)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(checkboxVendas, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)))))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnSair, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnEfetuar, GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
						.addComponent(btnRestaurar, GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE))
					.addGap(145))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblBackup, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(separator, GroupLayout.PREFERRED_SIZE, 3, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(checkboxProdutos)
								.addComponent(checkboxVendas))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(checkboxClientes))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnEfetuar)
							.addGap(6)
							.addComponent(btnRestaurar)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSair)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
		
	}
	
	public void efetuarBackup(){
		implVenda =  new ImplSerializador<List<Venda>>();
		implProduto =  new ImplSerializador<List<Produto>>();
		implCliente =  new ImplSerializador<List<Cliente>>();
		
		String msg = "";
		
		c1 = new Cliente();
		c1.setId(1);
		c1.setNome("Matheus Zandoná");
		c1.setEndereço("|R:  Bento gonçalves");
		c1.setNumero(736);
		c1.setBairro("JD União");
		c1.setCidade("Cascavel");
		c1.setEstado("Paraná");
		c1.setCelular("45 9961-9609");
		c1.setTelefone("45 3333-4444");
		
		c2 = new Cliente();
		
		c2.setId(2);
		c2.setNome("Maria");
		c2.setEndereço("R:  Carlos Gomes");
		c2.setNumero(653);
		c2.setBairro("Maria Luiza");
		c2.setCidade("Cascavel");
		c2.setEstado("Paraná");
		c2.setCelular("45 9988-9969");
		c2.setTelefone("45 3333-4444");
		
		
		// efetuar backup de produtos
		if(checkboxProdutos.isSelected()){
			ProdutoParser prodp = new ProdutoParser();
			ReaderURL reader = new ReaderURL();
			produtos = prodp.getProduto(reader.lerUrl());
			
			try {
				implProduto.gravar(produtos, new File("produtos.dat"));
				msg += "\n Backup de Produtos efetuado com sucesso !";
			} catch (SerializadorException e) {
				JOptionPane.showInternalMessageDialog(null, "Erro ao efetuar backup de bakcup !\n" + e.getMessage());
			}
			
		}
		// efetuar backup de produtos
		if(checkboxClientes.isSelected()){
			
			clientes = new ArrayList<Cliente>();
			clientes.add(c1);
			clientes.add(c2);
			
			try {
				implCliente.gravar(clientes, new File("clientes.dat"));
				msg += "\n Backup de Clientes efetuado com sucesso !";
			} catch (SerializadorException e) {
				JOptionPane.showInternalMessageDialog(null, "Erro ao efetuar backup de clientes !\n" + e.getMessage());
			}
		}
		// para efetua backup de vendas
		if(checkboxVendas.isSelected()){
			
			Produto prodVenda1 = new Produto();
			prodVenda1 = new Produto();
			prodVenda1.setId(1);
			prodVenda1.setNome("Coca Cola");
			prodVenda1.setPreco(new BigDecimal(8.00));
			
			Produto prodVenda2 = new Produto();
			prodVenda2 = new Produto();
			prodVenda2.setId(2);
			prodVenda2.setNome("Achocolatado");
			prodVenda2.setPreco(new BigDecimal(5.00));
			
			produtosVenda = new ArrayList<Produto>();
			
			produtosVenda.add(prodVenda1);
			produtosVenda.add(prodVenda2);
			
			vendas =  new ArrayList<Venda>();
			v1 = new Venda(c1,produtosVenda);
			v2 = new Venda(c2, produtosVenda);
			vendas.add(v1);
			vendas.add(v2);
			
			try {
				implVenda.gravar(vendas, new File("vendas.dat"));
				msg += "\n Backup de Vendas efetuado com sucesso !";
			} catch (SerializadorException e) {
				JOptionPane.showInternalMessageDialog(null, "Erro ao efetuar backup de vendas !\n" + e.getMessage());
			}
		}
		System.out.println(msg);
	}
	
	public void restaurar(){
		if(checkboxClientes.isSelected()){
			//implCliente.ler("clientes.dat");
		}
		
		if(checkboxProdutos.isSelected()){
			
		}
		
		if(checkboxVendas.isSelected()){
			
		}
	}
}
