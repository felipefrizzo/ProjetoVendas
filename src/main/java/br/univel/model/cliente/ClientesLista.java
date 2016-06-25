package br.univel.model.cliente;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class ClientesLista {

	
	private List<Cliente> clientes;

    @XmlElement(name = "cliente")
    public List<Cliente> getClientes() {
        return clientes;
    }

	public void setClientes(List<Cliente> clientes) {
	    this.clientes = clientes;
	}
}
