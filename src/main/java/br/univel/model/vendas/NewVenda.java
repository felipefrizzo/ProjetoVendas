package br.univel.model.vendas;

import br.univel.annotation.Column;
import br.univel.annotation.SerialUID;
import br.univel.annotation.Table;
import br.univel.model.cliente.Cliente;

import java.util.ArrayList;

/**
 * Created by felipefrizzo on 6/25/16.
 */
@Table("Venda")
public class NewVenda {
    @SerialUID()
    private static final long serialVersionUID = 3567500841012871230L;
    @Column(pk = true)
    private int id;
    @Column(name = "Cliente", fk = true)
    private Cliente cliente;
    @Column(name = "ItemVenda", skip = true)
    private ArrayList<ItemVenda> itemVendas;

    public NewVenda() {
    }

    public NewVenda(ArrayList<ItemVenda> itemVendas, Cliente cliente) {
        this.itemVendas = itemVendas;
        this.cliente = cliente;
    }

    public NewVenda(int id, ArrayList<ItemVenda> itemVendas, Cliente cliente) {
        this.id = id;
        this.itemVendas = itemVendas;
        this.cliente = cliente;
    }

    public NewVenda(int id, Cliente cliente) {
        this.id = id;
        this.cliente = cliente;
    }

    public NewVenda(Cliente cliente) {
        this.cliente = cliente;
    }

    public double getValorTotal() {
        double retorno = 0;
        for (int i = 0; i < this.itemVendas.size(); i++) {
            double valor = (this.itemVendas.get(i).getQuantidade().doubleValue() * this.itemVendas.get(i).getPreco().doubleValue());
            retorno = retorno + valor;
        }
        return retorno;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<ItemVenda> getItemVendas() {
        return itemVendas;
    }

    public void setItemVendas(ArrayList<ItemVenda> itemVendas) {
        this.itemVendas = itemVendas;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
