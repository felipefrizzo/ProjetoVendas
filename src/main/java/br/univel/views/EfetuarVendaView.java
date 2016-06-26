package br.univel.views;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class EfetuarVendaView extends JFrame{
	private JTextField txtQtdProduto;
	private JTextField txtCodigoProd;
	private JTextField txtDescricaoProduto;
	private JTable table_Produtos;
	private JTextField txtCodCliente;
	private JTextField txtNomeCliente;
	public EfetuarVendaView() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel lblNewLabel = new JLabel("Venda");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 2;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		
		JPanel panel_Cliente = new JPanel();
		panel_Cliente.setBorder(new TitledBorder(null, "Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_Cliente = new GridBagConstraints();
		gbc_panel_Cliente.gridwidth = 2;
		gbc_panel_Cliente.fill = GridBagConstraints.BOTH;
		gbc_panel_Cliente.insets = new Insets(0, 0, 5, 0);
		gbc_panel_Cliente.gridx = 0;
		gbc_panel_Cliente.gridy = 1;
		getContentPane().add(panel_Cliente, gbc_panel_Cliente);
		GridBagLayout gbl_panel_Cliente = new GridBagLayout();
		gbl_panel_Cliente.columnWidths = new int[]{0, 0, 0};
		gbl_panel_Cliente.rowHeights = new int[]{0, 0, 0};
		gbl_panel_Cliente.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_Cliente.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_Cliente.setLayout(gbl_panel_Cliente);
		
		JLabel lblCdigo = new JLabel("Código");
		GridBagConstraints gbc_lblCdigo = new GridBagConstraints();
		gbc_lblCdigo.insets = new Insets(0, 0, 5, 5);
		gbc_lblCdigo.gridx = 0;
		gbc_lblCdigo.gridy = 0;
		panel_Cliente.add(lblCdigo, gbc_lblCdigo);
		
		JLabel lblNomeCliente = new JLabel("Nome Cliente");
		lblNomeCliente.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblNomeCliente = new GridBagConstraints();
		gbc_lblNomeCliente.insets = new Insets(0, 0, 5, 0);
		gbc_lblNomeCliente.gridx = 1;
		gbc_lblNomeCliente.gridy = 0;
		panel_Cliente.add(lblNomeCliente, gbc_lblNomeCliente);
		
		txtCodCliente = new JTextField();
		GridBagConstraints gbc_txtCodCliente = new GridBagConstraints();
		gbc_txtCodCliente.insets = new Insets(0, 0, 0, 5);
		gbc_txtCodCliente.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCodCliente.gridx = 0;
		gbc_txtCodCliente.gridy = 1;
		panel_Cliente.add(txtCodCliente, gbc_txtCodCliente);
		txtCodCliente.setColumns(10);
		
		txtNomeCliente = new JTextField();
		GridBagConstraints gbc_txtNomeCliente = new GridBagConstraints();
		gbc_txtNomeCliente.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNomeCliente.gridx = 1;
		gbc_txtNomeCliente.gridy = 1;
		panel_Cliente.add(txtNomeCliente, gbc_txtNomeCliente);
		txtNomeCliente.setColumns(10);
		
		JPanel panel_Produtos = new JPanel();
		panel_Produtos.setBorder(new TitledBorder(null, "Produtos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_Produtos = new GridBagConstraints();
		gbc_panel_Produtos.gridwidth = 2;
		gbc_panel_Produtos.insets = new Insets(0, 0, 5, 0);
		gbc_panel_Produtos.fill = GridBagConstraints.BOTH;
		gbc_panel_Produtos.gridx = 0;
		gbc_panel_Produtos.gridy = 2;
		getContentPane().add(panel_Produtos, gbc_panel_Produtos);
		GridBagLayout gbl_panel_Produtos = new GridBagLayout();
		gbl_panel_Produtos.columnWidths = new int[]{72, 87, 0, 0};
		gbl_panel_Produtos.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panel_Produtos.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_Produtos.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel_Produtos.setLayout(gbl_panel_Produtos);
		
		JLabel lblQtd = new JLabel("Qtd");
		GridBagConstraints gbc_lblQtd = new GridBagConstraints();
		gbc_lblQtd.insets = new Insets(0, 0, 5, 5);
		gbc_lblQtd.gridx = 0;
		gbc_lblQtd.gridy = 0;
		panel_Produtos.add(lblQtd, gbc_lblQtd);
		
		JLabel lblCodigo = new JLabel("Codigo");
		GridBagConstraints gbc_lblCodigo = new GridBagConstraints();
		gbc_lblCodigo.insets = new Insets(0, 0, 5, 5);
		gbc_lblCodigo.gridx = 1;
		gbc_lblCodigo.gridy = 0;
		panel_Produtos.add(lblCodigo, gbc_lblCodigo);
		
		JLabel lblNewLabel_1 = new JLabel("Descrição Produto");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.gridx = 2;
		gbc_lblNewLabel_1.gridy = 0;
		panel_Produtos.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		txtQtdProduto = new JTextField();
		GridBagConstraints gbc_txtQtdProduto = new GridBagConstraints();
		gbc_txtQtdProduto.insets = new Insets(0, 0, 5, 5);
		gbc_txtQtdProduto.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtQtdProduto.gridx = 0;
		gbc_txtQtdProduto.gridy = 1;
		panel_Produtos.add(txtQtdProduto, gbc_txtQtdProduto);
		txtQtdProduto.setColumns(10);
		
		txtCodigoProd = new JTextField();
		GridBagConstraints gbc_txtCodigoProd = new GridBagConstraints();
		gbc_txtCodigoProd.insets = new Insets(0, 0, 5, 5);
		gbc_txtCodigoProd.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCodigoProd.gridx = 1;
		gbc_txtCodigoProd.gridy = 1;
		panel_Produtos.add(txtCodigoProd, gbc_txtCodigoProd);
		txtCodigoProd.setColumns(10);
		
		txtDescricaoProduto = new JTextField();
		txtDescricaoProduto.setEditable(false);
		GridBagConstraints gbc_txtDescricaoProduto = new GridBagConstraints();
		gbc_txtDescricaoProduto.insets = new Insets(0, 0, 5, 0);
		gbc_txtDescricaoProduto.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDescricaoProduto.gridx = 2;
		gbc_txtDescricaoProduto.gridy = 1;
		panel_Produtos.add(txtDescricaoProduto, gbc_txtDescricaoProduto);
		txtDescricaoProduto.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 2;
		panel_Produtos.add(scrollPane, gbc_scrollPane);
		
		table_Produtos = new JTable();
		scrollPane.setColumnHeaderView(table_Produtos);
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		JButton btnEfetuarVenda = new JButton("Efetuar Venda");
		GridBagConstraints gbc_btnEfetuarVenda = new GridBagConstraints();
		gbc_btnEfetuarVenda.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnEfetuarVenda.insets = new Insets(0, 0, 0, 5);
		gbc_btnEfetuarVenda.gridx = 0;
		gbc_btnEfetuarVenda.gridy = 3;
		getContentPane().add(btnEfetuarVenda, gbc_btnEfetuarVenda);
		GridBagConstraints gbc_btnSair = new GridBagConstraints();
		gbc_btnSair.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSair.gridx = 1;
		gbc_btnSair.gridy = 3;
		getContentPane().add(btnSair, gbc_btnSair);
	}

}
