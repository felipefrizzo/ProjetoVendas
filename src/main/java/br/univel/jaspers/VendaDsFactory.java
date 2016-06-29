package br.univel.jaspers;

import br.univel.model.vendas.ItemVenda;
import br.univel.model.vendas.dao.ItemVendaDAO;
import net.sf.jasperreports.engine.JRDataSource;

import java.util.List;

/**
 * Created by felipefrizzo on 6/29/16.
 */
public class VendaDsFactory {
    public VendaDsFactory(List<ItemVenda> lista) {
    }

    public static JRDataSource criar(){
        ItemVendaDAO dao = new ItemVendaDAO();
        List<ItemVenda> lista = dao.listAll();
        VendaJRDataSource ds = new VendaJRDataSource(lista);
        return ds;
    }
}
