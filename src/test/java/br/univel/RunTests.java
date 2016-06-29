package br.univel;

import br.univel.model.cliente.dao.DAOTestDeleteCliente;
import br.univel.model.cliente.dao.DAOTestInsertCliente;
import br.univel.model.cliente.dao.DAOTestUpdateCliente;
import br.univel.model.produto.dao.DAOTestDeleteProduto;
import br.univel.model.produto.dao.DAOTestInsertProduto;
import br.univel.model.produto.dao.DAOTestUpdateProduto;
import br.univel.model.venda.dao.DAOTestDeleteVenda;
import br.univel.model.venda.dao.DAOTestInsertVenda;
import br.univel.model.venda.dao.DAOTestUpdateVenda;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({DAOTestInsertCliente.class, DAOTestUpdateCliente.class, DAOTestDeleteCliente.class,
        DAOTestInsertProduto.class, DAOTestUpdateProduto.class, DAOTestDeleteProduto.class,
        DAOTestInsertVenda.class, DAOTestUpdateVenda.class, DAOTestDeleteVenda.class,
        ClienteParserTest.class, EIXmlTest.class, ImplSerializadorTest.class, ProdutoParserTest.class,
        ReaderURLTest.class})
public class RunTests {

}
