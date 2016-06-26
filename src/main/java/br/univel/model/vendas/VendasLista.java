package br.univel.model.vendas;



import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class VendasLista {

	private List<NewVenda> vendas;
    private List<ItemVenda> itemVendas;

    @XmlElement(name = "venda")
    public List<NewVenda> getVendas() {
        return vendas;
    }

	public void setVendas(List<NewVenda> vendas) {
	    this.vendas = vendas;
	}

    @XmlElement(name = "itemVenda")
    public List<ItemVenda> getItemVendas() {
        return itemVendas;
    }

    public void setItemVendas(List<ItemVenda> itemVendas) {
        this.itemVendas = itemVendas;
    }
}
