package br.univel.model.vendas;



import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class VendasLista {

	private List<Venda> vendas;

    @XmlElement(name = "venda")
    public List<Venda> getVendas() {
        return vendas;
    }

	public void setVendas(List<Venda> vendas) {
	    this.vendas = vendas;
	}
}
