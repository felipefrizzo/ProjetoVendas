package br.univel.model;

import br.univel.annotation.Column;
import br.univel.annotation.Table;

import java.math.BigDecimal;

/**
 * Created by felipefrizzo on 6/15/16.
 */
@Table("Produto")
public class Produto {
    @Column(pk = true)
    private int id;
    @Column(name = "nome", size = 255)
    private String nome;
    @Column(name = "preco")
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
