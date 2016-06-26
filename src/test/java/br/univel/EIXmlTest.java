package br.univel;

import static org.junit.Assert.*;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.postgresql.jdbc2.optional.SimpleDataSource;

import br.univel.database.ConnectionDB;
import br.univel.generics.EIXml;
import br.univel.model.cliente.Cliente;
import br.univel.model.cliente.ClienteParser;
import br.univel.model.cliente.ClientesLista;
import br.univel.model.cliente.dao.ClienteDAO;
import br.univel.model.produto.Produto;
import br.univel.model.produto.ProdutoLista;
import br.univel.model.produto.ProdutoParser;
import br.univel.model.produto.dao.ProdutoDAO;
import br.univel.model.vendas.Venda;
import br.univel.model.vendas.VendasLista;
import br.univel.modelo.readerURL.ReaderURL;
import br.univel.readerArquivo.ReaderArquivo;


public class EIXmlTest {
	
	static ConnectionDB conn;
	
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
	
	@BeforeClass
	public static void  antesCriarClasse() throws Exception{
		
		conn = new ConnectionDB();
		conn.open();
		
//		ProdutoParser prodp = new ProdutoParser();
//		ReaderURL reader = new ReaderURL();
//		produtos = prodp.getProduto(reader.lerUrl());
		ProdutoDAO prodDao = new ProdutoDAO();
		produtos = prodDao.listAll();
		
		pl = new ProdutoLista();
		pl.setProdutos(produtos);
		
//		ClienteParser cp = new ClienteParser();
//		ReaderArquivo readerArq = new ReaderArquivo();
//		clientes = cp.getCliente(readerArq.lerArquivo(new File("clientes.csv")));
		ClienteDAO cliDao = new ClienteDAO();
		clientes = cliDao.listAll();
		
		cl = new ClientesLista();
		cl.setClientes(clientes);
		
//		clientes para venda
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
		
		XMLprodutos = new EIXml<>();
		XMLclientes = new EIXml<>();
		XMlvendas = new EIXml<>();
	}
	
	@AfterClass
	public static void  depoisCriarClasse() throws Exception{
		
	}
	
	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void testGerarXmlProdutos() {
		assertEquals(true, XMLprodutos.gerarXml(pl, new File("produtos.xml")));
	}
	
	@Test
	public void testImportarXmlProdutos() {
		assertNotNull(XMLprodutos.importarXml(pl, new File("produtos.xml")));
	}
	
	@Test
	public void testGerarXmlClientes() {
		assertEquals(true, XMLclientes.gerarXml(cl, new File("clientes.xml")));
	}
	
	@Test
	public void testImportarXmlClientes() {
		assertNotNull(XMLclientes.importarXml(cl, new File("clientes.xml")));
	}
	
	@Test   
	public void testGerarXmlVendas() {
		assertEquals(true, XMlvendas.gerarXml(vl, new File("vendas.xml")));
	}
	
	@Test
	public void testImportarXmlVendas() {
		assertNotNull(XMlvendas.importarXml(vl, new File("vendas.xml")));
	}

}
