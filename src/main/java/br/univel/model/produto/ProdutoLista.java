package br.univel.model.produto;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class ProdutoLista {

	private List<Produto> produtos;

    @XmlElement(name = "produto")
    public List<Produto> getProdutos() {
        return produtos;
    }

	public void setProdutos(List<Produto> produtos) {
	    this.produtos = produtos;
	}
}
