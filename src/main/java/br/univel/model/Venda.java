package br.univel.model;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Venda {
	
	private int id;
	private BigDecimal valorTotal;
	private Cliente cliente;
	private ArrayList<Produto> produtos = new ArrayList<>();
	
	
	public Venda(ArrayList<Produto> produtos){
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
