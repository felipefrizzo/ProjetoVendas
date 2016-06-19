package br.univel.model.vendas;

import br.univel.annotation.Column;
import br.univel.model.cliente.Cliente;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by felipefrizzo on 6/19/16.
 */
public class Venda {
    @Column(pk=true)
    private int id;
    @Column()
    private Cliente cliente;
    @Column()
    private List<ItemVenda> itemVendaList;
    @Column()
    private BigDecimal valorTotal;
}
