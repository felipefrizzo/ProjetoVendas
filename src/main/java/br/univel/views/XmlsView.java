package br.univel.views;

import javax.swing.JFrame;
import javax.management.modelmbean.XMLParseException;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JCheckBox;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import br.univel.generics.EIXml;
import br.univel.model.cliente.Cliente;
import br.univel.model.cliente.ClienteParser;
import br.univel.model.cliente.ClientesLista;
import br.univel.model.produto.Produto;
import br.univel.model.produto.ProdutoLista;
import br.univel.model.produto.ProdutoParser;
import br.univel.model.produto.dao.ProdutoDAO;
import br.univel.model.vendas.Venda;
import br.univel.model.vendas.VendasLista;
import br.univel.modelo.readerURL.ReaderURL;
import br.univel.readerArquivo.ReaderArquivo;

import java.awt.event.ActionListener;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class XmlsView extends JFrame{
	
	JCheckBox checkBoxProdutos;
	JCheckBox checkBoxClientes;
	JCheckBox checkBoxVendas;
	
	static EIXml<ProdutoLista> XMLprodutos;
	static EIXml<ClientesLista> XMLclientes;
	static EIXml<VendasLista> XMlvendas;

	static List<Venda> vendas;
	static List<Produto> produtos;
	static List<Cliente> clientes;
	static ArrayList<Produto> produtosVenda;
	
	static ProdutoLista pl;
	static ClientesLista cl;
	static VendasLista vl;
	
	static Cliente c1;
	static Cliente c2;
	
	static Venda v1;
	static Venda v2;
	
	public XmlsView() {
		
		checkBoxProdutos = new JCheckBox("Produtos");
		checkBoxProdutos.setBounds(10, 44, 69, 23);
		
		checkBoxClientes = new JCheckBox("Clientes");
		checkBoxClientes.setBounds(10, 67, 69, 23);
		
		checkBoxVendas = new JCheckBox("Vendas");
		checkBoxVendas.setBounds(81, 44, 69, 23);
		
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
			
			ProdutoParser prodp = new ProdutoParser();
			ReaderURL reader = new ReaderURL();
			produtos = prodp.getProduto(reader.lerUrl());
			
			pl = new ProdutoLista();
			pl.setProdutos(produtos);
			
			try {
				XMLprodutos.gerarXml(pl, new File("produtos.xml"));
				msg += "\n Xml de Produtos gerado com sucesso !"; 
			} catch (Exception e) {
				msg = "Erro ao gerar xml Produtos! \n" + e.getMessage();
			}
		}
		
		if(checkBoxClientes.isSelected()){
			
			ClienteParser cp = new ClienteParser();
			ReaderArquivo readerArq = new ReaderArquivo();
			
			clientes = new ArrayList<>();
			clientes = cp.getCliente(readerArq.lerArquivo(new File("clientes.csv")));
			
			cl = new ClientesLista();
			cl.setClientes(clientes);
			try {
				XMLclientes.gerarXml(cl, new File("clientes.xml"));
				msg += "\n Xml de Clientes gerado com sucesso !"; 
			} catch (Exception e) {
				msg = "Erro ao gerar xml de Clientes! \n" + e.getMessage();
			}			
		}
		
		if(checkBoxVendas.isSelected()){
			
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
			
			produtosVenda = new ArrayList<>();
			
			produtosVenda.add(prodVenda1);
			produtosVenda.add(prodVenda2);
			
			
			Venda venda1 = new Venda(c1,produtosVenda);
			vendas = new ArrayList<>();
			vendas.add(venda1);
			
			Venda venda2 = new Venda(c2,produtosVenda);
			vendas.add(venda2);
			
			vl = new VendasLista();
			vl.setVendas(vendas);
			
			try {
				XMlvendas.gerarXml(vl, new File("vendas.xml"));				
				msg += "\n Xml de Vendas gerado com sucesso !"; 
			} catch (Exception e) {
				msg = "Erro ao gerar xml de Vendas ! \n" + e.getMessage();
			}			
		}
		System.out.println(msg);
	}
	
	public void importarXml(){
		
	}
}
