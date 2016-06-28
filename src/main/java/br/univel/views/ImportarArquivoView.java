package br.univel.views;

import javax.swing.JFrame;
import javax.swing.JSeparator;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;

import br.univel.model.cliente.Cliente;
import br.univel.model.cliente.ClienteParser;
import br.univel.model.cliente.dao.ClienteDAO;
import br.univel.model.produto.Produto;
import br.univel.model.produto.ProdutoParser;
import br.univel.model.produto.dao.ProdutoDAO;
import br.univel.modelo.readerURL.ReaderURL;
import br.univel.readerArquivo.ReaderArquivo;

import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class ImportarArquivoView extends JFrame{
	JCheckBox checkBox_Produto;
	JCheckBox checkBox_Clientes;
	ProdutoDAO prodDao;
	ClienteDAO cliDao;
	public ImportarArquivoView() {
		getContentPane().setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(203, 45, 0, 3);
		getContentPane().add(separator);
		
		checkBox_Clientes = new JCheckBox("Clientes");
		checkBox_Clientes.setBounds(20, 73, 104, 23);
		getContentPane().add(checkBox_Clientes);
		
		checkBox_Produto = new JCheckBox("Produtos");
		checkBox_Produto.setBounds(20, 50, 89, 23);
		getContentPane().add(checkBox_Produto);
		
		JButton button = new JButton("Sair");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button.setBounds(209, 80, 91, 23);
		getContentPane().add(button);
		
		JButton btnImportar = new JButton("importar");
		btnImportar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				importarArquivo();
			}
		});
		btnImportar.setBounds(209, 22, 91, 23);
		getContentPane().add(btnImportar);
		
		JLabel label = new JLabel("Backup");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.BOLD, 18));
		label.setBounds(10, 11, 193, 28);
		getContentPane().add(label);
	}

	protected void importarArquivo() {
		String msg = "";
		ReaderURL readerUrl = new  ReaderURL();
		ProdutoParser pParser = new ProdutoParser();
		ClienteParser cParser = new ClienteParser();
		ReaderArquivo readerArq = new ReaderArquivo();
		prodDao = new ProdutoDAO();
		cliDao = new ClienteDAO();
		List<Produto> produtos = new ArrayList<>();
		List<Cliente> clientes = new ArrayList<>();
		
		if(checkBox_Produto.isSelected()){
			msg = msg + "=========\nProdutos \n";
			produtos = pParser.getProduto(readerUrl.lerUrl());
			for(Produto p : produtos){
				if(prodDao.search(p.getId()) == null){					
					prodDao.save(p);
					msg = msg + "Produto "+p.getId()+" inserido com sucesso.\n";
				}else{
					msg = msg + "Produto "+p.getId()+" já existe no BD.\n";
				}
			}
		}
		if(checkBox_Clientes.isSelected()){
			msg = msg + "=========\nClientes \n";
			clientes = cParser.getCliente(readerArq.lerArquivo(new File("clientes.csv")));
			for(Cliente c : clientes){
				if(cliDao.search(c.getId()) == null){					
					cliDao.save(c);
					msg = msg + "Cliente "+c.getId()+" inserido com sucesso.\n";
				}else{
					msg = msg + "Cliente "+c.getId()+" já existe no BD.\n";
				}
			}
		}
		
		JOptionPane.showMessageDialog(null, msg);
	}
}
