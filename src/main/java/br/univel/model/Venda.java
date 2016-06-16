package br.univel.model;

import br.univel.annotation.Column;
import br.univel.annotation.Table;

import java.math.BigDecimal;
import java.util.ArrayList;

@Table("Venda")
public class Venda {
    @Column(pk = true)
	private int id;
    @Column(name = "valorTotal")
	private BigDecimal valorTotal;
    @Column(name = "cliente")
	private Cliente cliente;
    @Column(name = "produto")
	private ArrayList<Produto> produtos;
	
	
	public Venda(ArrayList<Produto> prodvenda){
		id++;
		this.produtos = produtos;
	}
	
	public Venda(Cliente cliente, ArrayList<Produto> produtos){
		id++;
		this.cliente = cliente;
		this.produtos = produtos;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public BigDecimal getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public ArrayList<Produto> getProdutos() {
		return produtos;
	}
	public void setProdutos(ArrayList<Produto> produtos) {
		this.produtos = produtos;
	}
	
}
