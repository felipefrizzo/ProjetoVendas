package br.univel.views;

import br.univel.generics.EIXml;
import br.univel.model.cliente.Cliente;
import br.univel.model.cliente.ClientesLista;
import br.univel.model.cliente.dao.ClienteDAO;
import br.univel.model.produto.Produto;
import br.univel.model.produto.ProdutoLista;
import br.univel.model.produto.dao.ProdutoDAO;
import br.univel.model.vendas.ItemVenda;
import br.univel.model.vendas.NewVenda;
import br.univel.model.vendas.VendasLista;
import br.univel.model.vendas.dao.ItemVendaDAO;
import br.univel.model.vendas.dao.NewVendaDAO;
import br.univel.model.vendas.dao.VendaDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XmlsView extends JFrame{

	JCheckBox checkBoxProdutos;
	JCheckBox checkBoxClientes;
	JCheckBox checkBoxVendas;

	static EIXml<ProdutoLista> XMLprodutos;
	static EIXml<ClientesLista> XMLclientes;
	static EIXml<VendasLista> XMlvendas;

	static List<NewVenda> vendas;
	static List<Produto> produtos;
	static List<Cliente> clientes;
	static ArrayList<Produto> produtosVenda;

	static ProdutoLista pl;
	static ClientesLista cl;
	static VendasLista vl;

	static Cliente c1;
	static Cliente c2;

	static NewVenda v1;
	static NewVenda v2;

	public XmlsView() {

		checkBoxProdutos = new JCheckBox("Produtos");
		checkBoxProdutos.setBounds(10, 44, 100, 23);

		checkBoxClientes = new JCheckBox("Clientes");
		checkBoxClientes.setBounds(10, 67, 100, 23);

		checkBoxVendas = new JCheckBox("Vendas");
		checkBoxVendas.setBounds(112, 44, 83, 23);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 35, 203, 10);

		JLabel lblXmls = new JLabel("Xmls");
		lblXmls.setBounds(10, 1, 193, 28);
		lblXmls.setHorizontalAlignment(SwingConstants.CENTER);
		lblXmls.setFont(new Font("Tahoma", Font.BOLD, 18));

		JButton btnGerar = new JButton("Gerar");
		btnGerar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gerarXml();
			}
		});
		btnGerar.setBounds(213, 7, 91, 23);

		JButton btnImportar = new JButton("Importar");
		btnImportar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				importarXml();
			}
		});
		btnImportar.setBounds(213, 35, 91, 23);
		getContentPane().setLayout(null);
		getContentPane().add(lblXmls);
		getContentPane().add(checkBoxProdutos);
		getContentPane().add(checkBoxClientes);
		getContentPane().add(checkBoxVendas);
		getContentPane().add(btnImportar);
		getContentPane().add(btnGerar);
		getContentPane().add(separator);

		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSair.setBounds(215, 67, 89, 23);
		getContentPane().add(btnSair);
	}

	public void gerarXml(){
		XMLclientes = new EIXml<>();
		XMLprodutos = new EIXml<>();
		XMlvendas = new EIXml<>();
		String msg = "";

		if(checkBoxProdutos.isSelected()){
			pl = new ProdutoLista();

			ProdutoDAO produtoDAO = new ProdutoDAO();
			List<Produto> produto = produtoDAO.listAll();
			pl.setProdutos(produto);

			try {
				XMLprodutos.gerarXml(pl, new File("produtos.xml"));
				msg += "\n Xml de Produtos gerado com sucesso !";
			} catch (Exception e) {
				msg = "Erro ao gerar xml Produtos! \n" + e.getMessage();
			}
		}

		if(checkBoxClientes.isSelected()){
			cl = new ClientesLista();
			ClienteDAO clienteDAO = new ClienteDAO();
			cl.setClientes(clienteDAO.listAll());
			try {
				XMLclientes.gerarXml(cl, new File("clientes.xml"));
				msg += "\n Xml de Clientes gerado com sucesso !";
			} catch (Exception e) {
				msg = "Erro ao gerar xml de Clientes! \n" + e.getMessage();
			}
		}

		if(checkBoxVendas.isSelected()){
			vl = new VendasLista();
			NewVendaDAO newVendaDAO = new NewVendaDAO();
			ItemVendaDAO itemVendaDAO = new ItemVendaDAO();

//			vl.setVendas(newVendaDAO.listAll());
			vl.setItemVendas(itemVendaDAO.listAll());
			try {
				XMlvendas.gerarXml(vl, new File("vendas.xml"));
				msg += "\n Xml de Vendas gerado com sucesso!";
			} catch (Exception e) {
				msg = "Erro ao gerar xml de Vendas ! \n" + e.getMessage();
			}
		}
		JOptionPane.showMessageDialog(null, msg);
	}

	public void importarXml(){
		XMLclientes = new EIXml<>();
		XMLprodutos = new EIXml<>();
		XMlvendas = new EIXml<>();
		
		String msg = "";
		
		pl = new ProdutoLista();
		cl = new ClientesLista();
		vl = new VendasLista();
		
		produtos = new ArrayList<Produto>();
		clientes = new ArrayList<Cliente>();
		vendas = new ArrayList<NewVenda>();
		
		if(checkBoxProdutos.isSelected()){
			pl = XMLprodutos.importarXml(pl, new File("produtos.xml"));
			produtos = pl.getProdutos();
			ProdutoDAO pdao = new ProdutoDAO();
			for(Produto p : produtos){
				if((pdao.search(p.getId()).getId()) > 0){					
					pdao.save(p);
				}else{
					msg += "Produto " + p.getId() + " já existe no BD.\n";
				}
			}
		}
		
		if(checkBoxClientes.isSelected()){
			cl = XMLclientes.importarXml(cl, new File("clientes.xml"));
			clientes = cl.getClientes();
			ClienteDAO cdao = new ClienteDAO();
			for(Cliente c : clientes){
				if(cdao.search(c.getId()).getId() > 0){
					cdao.save(c);
				}else{
					msg += "Cliente " + c.getId() + " já existe no BD.\n";
				}
			}
		}
		if(checkBoxVendas.isSelected()){
//			Percebemos um erro na criacao do banco de dados e quando vimos já era tarde.
//			vl = XMlvendas.importarXml(vl, new File("vendas.xml"));
//			vendas = vl.getVendas();
//			VendaDAO vd = new VendaDAO();
//			ItemVendaDAO ivd = new ItemVendaDAO();
//			for(NewVenda nv : vendas){
//				try {
//					if(vd.search(nv.getId()).getId() > 0){
//						vd.save(nv);
//						for(ItemVenda iv : nv.getItemVendas()){
//							if(ivd.search(iv.getId()).getId() > 0){
//								ivd.save(iv);
//							}
//						}
//					}else{
//						msg += "Venda " + nv.getId() + " já existe no BD.\n"; 
//					}
//				} catch (Exception e) {
//					
//				}
//			}
		}
		JOptionPane.showMessageDialog(null, msg);
	}
}
