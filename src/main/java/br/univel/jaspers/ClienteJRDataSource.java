package br.univel.jaspers;

import java.util.Iterator;
import java.util.List;

import br.univel.annotation.Column;
import br.univel.model.cliente.Cliente;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class ClienteJRDataSource implements JRDataSource {

	private List<Cliente> lista;
	
	private Cliente selecionado;
	private Iterator<Cliente> iterator;
	
	public ClienteJRDataSource(List<Cliente> lista) {
		this.lista = lista;
		this.iterator = iterator;
	}

//	private int id;
//	private String nome;
//	private String endereco;
//	private int numero;
//	private String complemento;
//	private String bairro;
//	private String cidade;
//	private String estado;
//	private String cep;
//	private String telefone;
//	private String celular;
	
	@Override
	public Object getFieldValue(JRField arg0) throws JRException {
		if("id".equals(arg0.getName())){
			return selecionado.getId();
		}else{
			if("nome".equals(arg0.getName())){
				return selecionado.getNome();
			}else {
				if (arg0.getName().equals("telefone")) {
					return selecionado.getTelefone();
				}else {
					if (arg0.getName().equals("endereco")) {
						return selecionado.getEndereco();
					}else {
						if (arg0.getName().equals("numero")) {
							return selecionado.getNumero();
						}else {
							if (arg0.getName().equals("complemento")) {
								return selecionado.getComplemento();
							}else {
								if (arg0.getName().equals("bairro")) {
									return selecionado.getBairro();
								}else {
									if (arg0.getName().equals("cidade")) {
										return selecionado.getCidade();
									}else {
										if (arg0.getName().equals("estado")) {
											return selecionado.getEstado();
										}else {
											if (arg0.getName().equals("cep")) {
												return selecionado.getCep();
											}else {
												if (arg0.getName().equals("celular")) {
													return selecionado.getCelular();
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		
		return "Undefined!";
	}

	@Override
	public boolean next() throws JRException {
		if(iterator.hasNext()){
			selecionado = iterator.next();
			return true;
		}
		
		return false;
	}

}

