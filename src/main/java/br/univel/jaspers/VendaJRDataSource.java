package br.univel.jaspers;

import br.univel.model.vendas.ItemVenda;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import java.util.Iterator;
import java.util.List;

/**
 * Created by felipefrizzo on 6/29/16.
 */
public class VendaJRDataSource implements JRDataSource{
    private List<ItemVenda> lista;

    private ItemVenda selecionado;
    private Iterator<ItemVenda> iterator;

    public VendaJRDataSource(List<ItemVenda> lista) {
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
            return selecionado.getVenda().getId();
        } else if ("valorTotal".equals(jrField.getName())) {
            return selecionado.getValorTotal();
        }
        return "Undefined!";
    }
}
