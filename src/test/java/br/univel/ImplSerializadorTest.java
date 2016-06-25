package br.univel;

import static org.junit.Assert.*;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.univel.exception.SerializadorException;
import br.univel.generics.ImplSerializador;
import br.univel.model.cliente.Cliente;
import br.univel.model.cliente.ClienteParser;
import br.univel.model.produto.Produto;
import br.univel.model.produto.ProdutoParser;
import br.univel.model.vendas.Venda;
import br.univel.modelo.readerURL.ReaderURL;
import br.univel.readerArquivo.ReaderArquivo;


public class ImplSerializadorTest {

	static Venda v1, v2;
	
	static Cliente c1, c2;
	
	static ImplSerializador<List<Cliente>> implCliente;
	static ImplSerializador<List<Produto>> implProduto;
	static ImplSerializador<List<Venda>>   implVenda;
	
	static List<Cliente> clientes;
	static List<Produto> produtos;
	static List<Venda> vendas;
	static ArrayList produtosVenda;

	
	@BeforeClass
	public static void antesCriarClasse() throws Exception {
		ProdutoParser prodp = new ProdutoParser();
		ReaderURL reader = new ReaderURL();
		produtos = prodp.getProduto(reader.lerUrl());
		
		ClienteParser cp = new ClienteParser();
		ReaderArquivo readerArq = new ReaderArquivo();
		clientes = cp.getCliente(readerArq.lerArquivo(new File("clientes.csv")));
		
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
		
		produtosVenda = new ArrayList<Produto>();
		
		produtosVenda.add(prodVenda1);
		produtosVenda.add(prodVenda2);
		
		v1 = new Venda(c1,produtosVenda);
		v2 = new Venda(c2, produtosVenda);
		vendas =  new ArrayList<Venda>();
		vendas.add(v1);
		
		implCliente =  new ImplSerializador<List<Cliente>>();
		implProduto =  new ImplSerializador<List<Produto>>();
		implVenda =  new ImplSerializador<List<Venda>>();
	}

	@Test
	public void testGravarCliente() throws SerializadorException {
		assertEquals(true, implCliente.gravar(clientes, new File("clientes.dat")));
	}

	@Test
	public void testLerCliente() throws SerializadorException {
		assertNotNull(implCliente.ler(new File("clientes.dat")));
	}

	@Test
	public void testGravarProduto() throws SerializadorException {
		assertEquals(true, implProduto.gravar(produtos, new File("produtos.dat")));
	}

	@Test
	public void testLerProduto() throws SerializadorException {
		assertNotNull(implProduto.ler(new File("produtos.dat")));
	}
	
	@Test
	public void testGravarVenda() throws SerializadorException {
		assertEquals(true, implVenda.gravar(vendas, new File("vendas.dat")));
	}

	@Test
	public void testLerVendas() throws SerializadorException {
		assertNotNull(implVenda.ler(new File("vendas.dat")));
	}
}
