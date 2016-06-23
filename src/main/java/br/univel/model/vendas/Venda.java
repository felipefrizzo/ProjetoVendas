package br.univel.model.vendas;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import br.univel.annotation.Column;
import br.univel.annotation.SerialUID;
import br.univel.annotation.Table;
import br.univel.model.cliente.Cliente;
import br.univel.model.produto.Produto;

import java.math.BigDecimal;
import java.util.ArrayList;

@Table("Venda")
@XmlRootElement
public class Venda implements Serializable{
	@SerialUID
	private static final long serialVersionUID = 3567500841012871230L;
	
    @Column(pk = true)
	private int id;
    @Column(name = "valorTotal")
	private BigDecimal valorTotal;
    @Column(name = "cliente")
	private Cliente cliente;
    @Column(name = "produto")
	private List<Produto> produtos;
	
    public Venda(){
    	
    }
	
	public Venda(List<Produto> prodvenda){
		id++;
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
	
	@XmlElement(name = "produto")
	public List<Produto> getProdutos() {
		return produtos;
	}
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	
}
