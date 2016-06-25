package br.univel.model.vendas;

import br.univel.annotation.Column;
import br.univel.annotation.SerialUID;
import br.univel.annotation.Table;
import br.univel.model.produto.Produto;

import java.math.BigDecimal;

/**
 * Created by felipefrizzo on 6/25/16.
 */
@Table("ItemVenda")
public class ItemVenda {
	
	@SerialUID()
	private static final long serialVersionUID = 3567500841012871230L;

    @Column(name="id", pk=true)
    private int id;
    @Column(name="id")
    private BigDecimal quantidade;
    @Column(name="id")
    private BigDecimal preco;
    @Column(name="id")
    private Produto produto;

    public ItemVenda() {
    }

    public ItemVenda(BigDecimal quantidade, BigDecimal preco, Produto produto) {
        this.quantidade = quantidade;
        this.preco = preco;
        this.produto = produto;
    }

    public ItemVenda(int id, BigDecimal quantidade, BigDecimal preco, Produto produto) {
        this.id = id;
        this.quantidade = quantidade;
        this.preco = preco;
        this.produto = produto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}