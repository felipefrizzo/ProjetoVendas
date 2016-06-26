package br.univel.views;

import javax.swing.JFrame;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VendaView extends JFrame {
	private JTable table_Venda;
	private JTable table_Itens;
	public VendaView() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel lblNewLabel = new JLabel("Vendas");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		
		JButton btnNewButton = new JButton("Inserir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EfetuarVendaView ev = new EfetuarVendaView();
				ev.setSize(626,367);
				ev.setLocationRelativeTo(null);
				ev.setVisible(true);
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 0;
		getContentPane().add(btnNewButton, gbc_btnNewButton);
		
		JScrollPane scrollPane_Venda = new JScrollPane();
		GridBagConstraints gbc_scrollPane_Venda = new GridBagConstraints();
		gbc_scrollPane_Venda.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane_Venda.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_Venda.gridx = 0;
		gbc_scrollPane_Venda.gridy = 1;
		getContentPane().add(scrollPane_Venda, gbc_scrollPane_Venda);
		
		table_Venda = new JTable();
		scrollPane_Venda.setColumnHeaderView(table_Venda);
		
		JLabel lblItensDaVenda = new JLabel("Itens da Venda");
		lblItensDaVenda.setFont(new Font("Tahoma", Font.BOLD, 18));
		GridBagConstraints gbc_lblItensDaVenda = new GridBagConstraints();
		gbc_lblItensDaVenda.insets = new Insets(0, 0, 5, 5);
		gbc_lblItensDaVenda.gridx = 0;
		gbc_lblItensDaVenda.gridy = 2;
		getContentPane().add(lblItensDaVenda, gbc_lblItensDaVenda);
		
		JScrollPane scrollPane_ItemVenda = new JScrollPane();
		GridBagConstraints gbc_scrollPane_ItemVenda = new GridBagConstraints();
		gbc_scrollPane_ItemVenda.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane_ItemVenda.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_ItemVenda.gridx = 0;
		gbc_scrollPane_ItemVenda.gridy = 3;
		getContentPane().add(scrollPane_ItemVenda, gbc_scrollPane_ItemVenda);
		
		table_Itens = new JTable();
		scrollPane_ItemVenda.setColumnHeaderView(table_Itens);
	}
}
