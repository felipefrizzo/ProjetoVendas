package br.univel;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import br.univel.model.cliente.ClienteParser;
import br.univel.readerArquivo.ReaderArquivo;

public class ClienteParserTest {

	
	ReaderArquivo reader;
	ClienteParser clienteP;
	
	@Before
	public void setUp() throws Exception {
		reader = new ReaderArquivo();
		clienteP = new ClienteParser();
	}

	@Test
	public void testGetCliente() {
		assertNotNull(clienteP.getCliente(reader.lerArquivo(new File("clientes.csv"))));
	}

}
