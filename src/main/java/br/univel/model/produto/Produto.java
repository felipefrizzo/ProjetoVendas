package br.univel.model.produto;

import br.univel.annotation.Column;

import java.math.BigDecimal;

/**
 * Created by felipefrizzo on 6/19/16.
 */
public class Produto {
    @Column(pk = true)
    private int id;
    @Column()
    private String nome;
    @Column()
    private BigDecimal preco;

    public Produto() {
    }

    public Produto(int id, String nome, BigDecimal preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
}
