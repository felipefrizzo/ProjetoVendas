package br.univel.model.cliente;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.univel.model.produto.Produto;

public class ClienteParser {
	
	public List<Cliente> getCliente(List<String> listaStr) {
		List<Cliente> listaCli = new ArrayList<>();
													//  expressao regular
		Pattern p = Pattern.compile("[0-9]+.*"); // + uma ocorrencia ou mais.      
												 // Ponto significa qualquer caracter
												 // * significa 0 ou mais
		listaStr.forEach(e -> {
			Matcher m = p.matcher(e);  

			if (m.matches()) {  // verifico se combinou o padrao com a string.
				listaCli.add(getCliente(e));
			}

		});

		return listaCli;
	}

	private Cliente getCliente(String str) { 

		String[] itens = str.split(",");
		int id = Integer.parseInt(itens[0]);
		String descricao = itens[1];
		String endereco  = itens[2];
		int numero = Integer.parseInt(itens[3]);
		String complemento = itens[4];
		String bairro = itens[5];
		String cidade = itens[6];
		String estado = itens[7];
		String cep = itens[8];
		String telefone = itens[9];
		String celular = itens[10];

		Cliente c = new Cliente(id, descricao, endereco, numero, complemento, bairro, cidade, estado, cep, telefone, celular);
		
		return c;
		
	}
}
