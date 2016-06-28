package br.univel.views;

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
import br.univel.model.cliente.dao.ClienteDAO;
import br.univel.model.produto.Produto;
import br.univel.model.produto.ProdutoParser;
import br.univel.model.produto.dao.ProdutoDAO;
import br.univel.model.vendas.ItemVenda;
import br.univel.model.vendas.NewVenda;
import br.univel.model.vendas.Venda;
import br.univel.model.vendas.dao.ItemVendaDAO;
import br.univel.model.vendas.dao.NewVendaDAO;
import br.univel.model.vendas.dao.VendaDAO;
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
	static ImplSerializador<List<Cliente>> implCliente;
	static ImplSerializador<List<Produto>> implProduto;
	static ImplSerializador<List<ItemVenda>>   implVenda;
	
	static List<Cliente> clientes;
	static List<Produto> produtos;
	static List<ItemVenda> vendas;
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
		btnRestaurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				restaurarBackup();
			}
		});
		
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
		implVenda =  new ImplSerializador<List<ItemVenda>>();
		implProduto =  new ImplSerializador<List<Produto>>();
		implCliente =  new ImplSerializador<List<Cliente>>();
		
		String msg = "";

		if(checkboxProdutos.isSelected()){
			ProdutoDAO prodDao = new ProdutoDAO();
			produtos = prodDao.listAll();
			
			try {
				implProduto.gravar(produtos, new File("produtos.dat"));
				msg += "\n Backup de Produtos efetuado com sucesso !";
			} catch (SerializadorException e) {
				JOptionPane.showMessageDialog(null, "Erro ao efetuar backup de bakcup !\n" + e.getMessage());
			}
			
		}
		
		if(checkboxClientes.isSelected()){
			ClienteDAO cliDao = new ClienteDAO();
			clientes = cliDao.listAll();
			
			try {
				implCliente.gravar(clientes, new File("clientes.dat"));
				msg += "\n Backup de Clientes efetuado com sucesso !";
			} catch (SerializadorException e) {
				JOptionPane.showMessageDialog(null, "Erro ao efetuar backup de clientes !\n" + e.getMessage());
			}
		}
		if(checkboxVendas.isSelected()){
			ItemVendaDAO itemVendaDAO = new ItemVendaDAO();
			vendas = itemVendaDAO.listAll();
			try {
				implVenda.gravar(itemVendaDAO.listAll(), new File("vendas.dat"));
				msg += "\n Backup de Vendas efetuado com sucesso !";
			} catch (SerializadorException e) {
				JOptionPane.showMessageDialog(null, "Erro ao efetuar backup de vendas !\n" + e.getMessage());
			}
		}
		JOptionPane.showMessageDialog(null, msg);
	}
	
	public void restaurarBackup(){
		implVenda =  new ImplSerializador<List<ItemVenda>>();
		implProduto =  new ImplSerializador<List<Produto>>();
		implCliente =  new ImplSerializador<List<Cliente>>();
		
		String msg = "";
		
		if(checkboxClientes.isSelected()){
			ClienteDAO cliDao = new ClienteDAO();
			msg = msg + "CLIENTES \n";
			try {
				clientes = implCliente.ler(new File("clientes.dat"));
				for(Cliente c : clientes){
					if(cliDao.search(c.getId()) == null){
						cliDao.save(c);						
					}else{
						msg = msg + "\n Cliente com Id " + c.getId()+" já existe no BD.";
					}
				}
				msg = msg + "\n" + "Clientes restaurado com sucesso !";
			} catch (SerializadorException e) {
				System.out.println("Erro restaurando clientes.");
				e.printStackTrace();
			}
		}
		
		if(checkboxProdutos.isSelected()){
			ProdutoDAO prodDao = new ProdutoDAO();
			msg = msg + "\n PRODUTOS";
			try {
				produtos = implProduto.ler(new File("produtos.dat"));
				for(Produto p : produtos){
					if(prodDao.search(p.getId()) == null){
						prodDao.save(p);						
					}else{
						msg = msg + "\n Produto com Id " + p.getId()+" já existe no BD."; 
					}
				}
				msg = msg + "\n" + "Produtos restaurado com sucesso !";
			} catch (Exception e) {
				System.out.println("Erro restaurando clientes.");
				e.printStackTrace();
			}
		}
		
		if(checkboxVendas.isSelected()){
			
//			NewVendaDAO vendaDao = new NewVendaDAO();
//			msg = msg + "\n PRODUTOS";
//			try {
//				produtos = implProduto.ler(new File("produtos.dat"));
//				for(NewVenda v : vendas){
//					if(vendaDao.search(v.getId()) == null){
//						vendaDao.save(v);						
//					}else{
//						msg = msg + "\n Produto com Id " + v.getId()+" já existe no BD."; 
//					}
//				}
//				msg = msg + "\n" + "Produtos restaurado com sucesso !";
//			} catch (Exception e) {
//				System.out.println("Erro restaurando clientes.");
//				e.printStackTrace();
//			}
		}
		JOptionPane.showMessageDialog(null, msg);
	}
}
