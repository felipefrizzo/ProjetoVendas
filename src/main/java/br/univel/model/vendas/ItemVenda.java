package br.univel.model.vendas;

import br.univel.annotation.Column;
import br.univel.model.produto.Produto;

import java.math.BigDecimal;

/**
 * Created by felipefrizzo on 6/19/16.
 */
public class ItemVenda {
    @Column(pk = true)
    private int id;
    @Column()
    private Produto produto;
    @Column()
    private BigDecimal quantidade;
    @Column()
    private BigDecimal preco;

    public ItemVenda() {
    }

    public ItemVenda(int id, Produto produto, BigDecimal quantidade, BigDecimal preco) {
        this.id = id;
        this.produto = produto;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public ItemVenda(Produto produto, BigDecimal quantidade, BigDecimal preco) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
}
