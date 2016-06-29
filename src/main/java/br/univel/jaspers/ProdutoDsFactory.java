package br.univel.jaspers;

import br.univel.model.produto.Produto;
import br.univel.model.produto.dao.ProdutoDAO;
import net.sf.jasperreports.engine.JRDataSource;

import java.util.List;

/**
 * Created by felipefrizzo on 6/29/16.
 */
public class ProdutoDsFactory {
    public ProdutoDsFactory(List<Produto> lista) {
    }

    public static JRDataSource criar(){
        ProdutoDAO dao = new ProdutoDAO();
        List<Produto> lista = dao.listAll();
        ProdutoJRDataSource ds = new ProdutoJRDataSource(lista);
        return ds;
    }
}
