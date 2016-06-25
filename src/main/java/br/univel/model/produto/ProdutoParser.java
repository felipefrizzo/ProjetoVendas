package br.univel.model.produto;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProdutoParser{

	private NumberFormat format = NumberFormat.getInstance(new Locale("pt", "BR")); 

	public List<Produto> getProduto(List<String> listaStr) {
		List<Produto> listaPrd = new ArrayList<>();
													//  expressao regular
		Pattern p = Pattern.compile("[0-9]+.*"); // + uma ocorrencia ou mais.      
												 // Ponto significa qualquer caracter
												 // * significa 0 ou mais

		listaStr.forEach(e -> {

			if (!e.startsWith("----")) {   // verifica se a linha comeca com essa string, caso nao comece ele executa
				Matcher m = p.matcher(e);  
				if (m.matches()) {  // verifico se combinou o padrao com a string.
					listaPrd.add(getProduto(e));
				}
			}

		});

		return listaPrd;
	}

	private Produto getProduto(String str) { 

		// Se fosse CSV....
		// String[] itens = str.split(",");
		// int id = Integer.parseInt(itens[0]);
		// String descricao = itens[1];

		int indexPrimeiroEspaco = str.indexOf(' ');  // aqui retorna o indice onde se encontra o primeiro espaco em branco
		String subStringCodigo = str.substring(0, indexPrimeiroEspaco); // substing corta a string, criando outra string
		int id = Integer.parseInt(subStringCodigo);

		String strSemCodigo = str.substring(indexPrimeiroEspaco).trim();

		int indexDolar = strSemCodigo.indexOf("US$");

		String descricao = strSemCodigo.substring(0, indexDolar).trim();

		BigDecimal preco = null;
		String strPreco = null;

		try {
			// strPreco = strSemCodigo.substring(indexDolar +     forma gambiarrenta
			// 3).trim().replaceAll("\\.", "").replace(',', '.');
			// preco = new BigDecimal(strPreco);

			strPreco = strSemCodigo.substring(indexDolar + 3).trim();
			preco = new BigDecimal(format.parse(strPreco).doubleValue());
			preco.setScale(2, RoundingMode.HALF_EVEN);  // mesmo arredondamento do excel, metodo do banqueiro.

		} catch (NumberFormatException | ParseException e) {

			System.out.println(strPreco);

			e.printStackTrace();
		}

		Produto p = new Produto(id, descricao, preco);
		return p;
	}
	
}
