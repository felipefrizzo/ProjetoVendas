package br.univel.model.produto;

import br.univel.annotation.Column;
import br.univel.util.Serializador;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by felipefrizzo on 6/19/16.
 */
public class Produto implements Serializable {
	
	private static final long serialVersionUID = 3567500841012871230L;
	
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