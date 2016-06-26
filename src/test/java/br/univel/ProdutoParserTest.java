package br.univel;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import br.univel.model.produto.ProdutoParser;
import br.univel.modelo.readerURL.ReaderURL;

public class ProdutoParserTest {

	ReaderURL reader;
	ProdutoParser prodp;
	
	@Before
	public void setUp() throws Exception {
		reader = new ReaderURL();
		prodp = new ProdutoParser();
	}

	@Test
	public void testGetProduto() {  // falta terminar
//		assertEquals(prodp != null, prodp.getProduto(reader.lerUrl()));
		assertNotNull(prodp.getProduto(reader.lerUrl()));
	}

}
