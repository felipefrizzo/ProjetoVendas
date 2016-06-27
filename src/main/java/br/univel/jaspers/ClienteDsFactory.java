package br.univel.jaspers;

import java.util.List;

import br.univel.model.cliente.Cliente;
import br.univel.model.cliente.dao.ClienteDAO;
import net.sf.jasperreports.engine.JRDataSource;

public class ClienteDsFactory {

	public ClienteDsFactory(List<Cliente> lista) {
		// TODO Auto-generated constructor stub
	}

	public static JRDataSource criar(){
		ClienteDAO dao = new ClienteDAO();
		
		List<Cliente> lista = dao.listAll();
		
		ClienteJRDataSource ds = new ClienteJRDataSource(lista);
		
		return ds;
	}
}
