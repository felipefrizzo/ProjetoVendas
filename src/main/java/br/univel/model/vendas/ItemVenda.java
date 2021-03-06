package br.univel.model.vendas;

import br.univel.annotation.Column;
import br.univel.annotation.SerialUID;
import br.univel.annotation.Table;
import br.univel.model.produto.Produto;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by felipefrizzo on 6/25/16.
 */
@Table("ItemVenda")
@XmlRootElement
public class ItemVenda implements Serializable {
	
	@SerialUID()
	private static final long serialVersionUID = 3567500841012871230L;

    @Column(name="id", pk=true)
    private int id;
    @Column(name="quantidade")
    private BigDecimal quantidade;
    @Column(name="preco")
    private BigDecimal preco;
    @Column(name="produto", fk = true)
    private Produto produto;
    @Column(name="venda", fk = true)
    private NewVenda venda;
    @Column(skip = true)
    private BigDecimal valorTotal;

    public ItemVenda() {
    }

    public ItemVenda(BigDecimal quantidade, BigDecimal preco, Produto produto, NewVenda venda) {
        this.quantidade = quantidade;
        this.preco = preco;
        this.produto = produto;
        this.venda = venda;
        this.valorTotal = new BigDecimal(getValorTotal());
    }

    public ItemVenda(int id, BigDecimal quantidade, BigDecimal preco, Produto produto, NewVenda venda) {
        this.id = id;
        this.quantidade = quantidade;
        this.preco = preco;
        this.produto = produto;
        this.venda = venda;
        this.valorTotal = new BigDecimal(getValorTotal());
    }

    public double getValorTotal() {
        double valor = (getQuantidade().doubleValue() * getPreco().doubleValue());
        return valor;
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

    public NewVenda getVenda() {
        return venda;
    }

    public void setVenda(NewVenda venda) {
        this.venda = venda;
    }
}
