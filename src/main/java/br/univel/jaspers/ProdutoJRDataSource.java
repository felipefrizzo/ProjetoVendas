package br.univel.jaspers;

import br.univel.model.produto.Produto;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import java.util.Iterator;
import java.util.List;

/**
 * Created by felipefrizzo on 6/29/16.
 */
public class ProdutoJRDataSource implements JRDataSource {
    private List<Produto> lista;

    private Produto selecionado;
    private Iterator<Produto> iterator;

    public ProdutoJRDataSource(List<Produto> lista) {
        this.lista = lista;
        this.iterator = lista.iterator();
    }

    @Override
    public boolean next() throws JRException {
        if(iterator.hasNext()){
            selecionado = iterator.next();
            return true;
        }

        return false;
    }

    @Override
    public Object getFieldValue(JRField jrField) throws JRException {
        if ("id".equals(jrField.getName())) {
            return selecionado.getId();
        } else if ("nome".equals(jrField.getName())) {
            return selecionado.getNome();
        } else if ("preco".equals(jrField.getName())) {
            return selecionado.getPreco();
        }
        return "Undefined!";
    }
}
