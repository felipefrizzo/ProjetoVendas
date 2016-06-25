package br.univel.views;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class PesqVendaView extends JFrame{
	private JTextField textPesquisa;
	private JTable tableVenda;
	public PesqVendaView() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel lblPesquisaDeVendas = new JLabel("Pesquisa de Vendas");
		lblPesquisaDeVendas.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPesquisaDeVendas.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblPesquisaDeVendas = new GridBagConstraints();
		gbc_lblPesquisaDeVendas.gridwidth = 7;
		gbc_lblPesquisaDeVendas.insets = new Insets(0, 0, 5, 5);
		gbc_lblPesquisaDeVendas.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblPesquisaDeVendas.gridx = 0;
		gbc_lblPesquisaDeVendas.gridy = 0;
		getContentPane().add(lblPesquisaDeVendas, gbc_lblPesquisaDeVendas);
		
		JButton btnEfetuar = new JButton("Efetuar");
		GridBagConstraints gbc_btnEfetuar = new GridBagConstraints();
		gbc_btnEfetuar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnEfetuar.gridwidth = 2;
		gbc_btnEfetuar.insets = new Insets(0, 0, 5, 0);
		gbc_btnEfetuar.gridx = 7;
		gbc_btnEfetuar.gridy = 0;
		getContentPane().add(btnEfetuar, gbc_btnEfetuar);
		
		JLabel lblPesquisa = new JLabel("  Pesquisa");
		GridBagConstraints gbc_lblPesquisa = new GridBagConstraints();
		gbc_lblPesquisa.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblPesquisa.insets = new Insets(0, 0, 5, 5);
		gbc_lblPesquisa.gridx = 0;
		gbc_lblPesquisa.gridy = 1;
		getContentPane().add(lblPesquisa, gbc_lblPesquisa);
		
		JButton btnAlterar = new JButton("Alterar");
		GridBagConstraints gbc_btnAlterar = new GridBagConstraints();
		gbc_btnAlterar.gridwidth = 2;
		gbc_btnAlterar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAlterar.insets = new Insets(0, 0, 5, 0);
		gbc_btnAlterar.gridx = 7;
		gbc_btnAlterar.gridy = 1;
		getContentPane().add(btnAlterar, gbc_btnAlterar);
		
		textPesquisa = new JTextField();
		GridBagConstraints gbc_textPesquisa = new GridBagConstraints();
		gbc_textPesquisa.gridwidth = 5;
		gbc_textPesquisa.insets = new Insets(0, 0, 5, 5);
		gbc_textPesquisa.fill = GridBagConstraints.HORIZONTAL;
		gbc_textPesquisa.gridx = 0;
		gbc_textPesquisa.gridy = 2;
		getContentPane().add(textPesquisa, gbc_textPesquisa);
		textPesquisa.setColumns(10);
		
		JButton btnExcluir = new JButton("Excluir");
		GridBagConstraints gbc_btnExcluir = new GridBagConstraints();
		gbc_btnExcluir.insets = new Insets(0, 0, 5, 0);
		gbc_btnExcluir.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnExcluir.gridwidth = 3;
		gbc_btnExcluir.gridx = 7;
		gbc_btnExcluir.gridy = 2;
		getContentPane().add(btnExcluir, gbc_btnExcluir);
		
		JButton btnNewButton = new JButton("Sair");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton.gridwidth = 4;
		gbc_btnNewButton.gridx = 7;
		gbc_btnNewButton.gridy = 3;
		getContentPane().add(btnNewButton, gbc_btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 3;
		gbc_scrollPane.gridwidth = 7;
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 3;
		getContentPane().add(scrollPane, gbc_scrollPane);
		
		tableVenda = new JTable();
		scrollPane.setViewportView(tableVenda);
	}

}
