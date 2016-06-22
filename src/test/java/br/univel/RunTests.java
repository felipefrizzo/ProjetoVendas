package br.univel;

import br.univel.model.cliente.dao.DAOTestDelete;
import br.univel.model.cliente.dao.DAOTestInsert;
import br.univel.model.cliente.dao.DAOTestUpdate;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({DAOTestInsert.class, DAOTestUpdate.class, DAOTestDelete.class})
public class RunTests {

}