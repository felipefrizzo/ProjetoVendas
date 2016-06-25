package br.univel;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.univel.modelo.readerURL.ReaderURL;
import junit.framework.Assert;

public class ReaderURLTest {

	ReaderURL reader;
	@Before
	public void setUp() throws Exception {
		reader = new ReaderURL();
	}

	@Test
	public void testLerUrl() {
		assertEquals(true, !reader.lerUrl().isEmpty()); // para retorno do banco ps.executeUpdate() retorna numero de registros alterados
	}

}
