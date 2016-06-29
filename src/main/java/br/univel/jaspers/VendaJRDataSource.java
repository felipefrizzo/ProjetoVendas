package br.univel.jaspers;

import br.univel.model.vendas.ItemVenda;
import br.univel.model.vendas.NewVenda;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import java.util.Iterator;
import java.util.List;

/**
 * Created by felipefrizzo on 6/29/16.
 */
public class VendaJRDataSource implements JRDataSource{
    private List<NewVenda> lista;

    private NewVenda selecionado;
    private Iterator<NewVenda> iterator;

    public VendaJRDataSource(List<NewVenda> lista) {
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
        } else if ("cliente".equals(jrField.getName())) {
            return selecionado.getCliente().getNome();
        } else if ("vlttotal".equals(jrField.getName())){
        	return selecionado.getValorTotal();
        }
        return "Undefined!";
    }
}
