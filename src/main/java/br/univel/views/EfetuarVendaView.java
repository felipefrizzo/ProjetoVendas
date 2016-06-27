package br.univel.views;

import br.univel.model.cliente.Cliente;
import br.univel.model.cliente.dao.ClienteDAO;
import br.univel.model.produto.Produto;
import br.univel.model.produto.dao.ProdutoDAO;
import br.univel.model.vendas.ItemVenda;
import br.univel.model.vendas.NewVenda;
import br.univel.model.vendas.Venda;
import br.univel.model.vendas.dao.ItemVendaDAO;
import br.univel.model.vendas.dao.NewVendaDAO;
import br.univel.model.vendas.dao.VendaDAO;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.math.BigDecimal;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EfetuarVendaView extends JFrame{
	private JTextField txtQtdProduto;
	private JTextField txtprecoProd;
	private JTextField txtDescricaoProduto;
	private JTable table_Produtos;
	private JTextField txtCodCliente;
	private JTextField txtNomeCliente;

	ClienteDAO clienteDAO = new ClienteDAO();
	List<Cliente> clienteList = clienteDAO.listAll();
	ProdutoDAO produtoDAO = new ProdutoDAO();
	List<Produto> produtoList = produtoDAO.listAll();

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

		JLabel lblNomeCliente = new JLabel("Nome Cliente");
		lblNomeCliente.setHorizontalAlignment(SwingConstants.LEFT);

		GridBagConstraints gbc_lblNomeCliente = new GridBagConstraints();
		gbc_lblNomeCliente.insets = new Insets(0, 0, 5, 5);
		gbc_lblNomeCliente.gridx = 0;
		gbc_lblNomeCliente.gridy = 0;
		panel_Cliente.add(lblNomeCliente, gbc_lblNomeCliente);

		DefaultComboBoxModel clienteComboBox = new DefaultComboBoxModel();
		for (int i = 0; i < clienteList.size(); i++) {
			clienteComboBox.addElement(clienteList.get(i).getNome());
		}
		JComboBox jComboBox = new JComboBox();
		jComboBox.setModel(clienteComboBox);

		GridBagConstraints gbc_txtNomeCliente = new GridBagConstraints();
		gbc_txtNomeCliente.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNomeCliente.gridx = 1;
		gbc_txtNomeCliente.gridy = 0;
		panel_Cliente.add(jComboBox, gbc_txtNomeCliente);


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

		JLabel lblNewLabel_1 = new JLabel("Descrição Produto");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 0;
		panel_Produtos.add(lblNewLabel_1, gbc_lblNewLabel_1);

		JLabel lblQtd = new JLabel("Qtd");
		GridBagConstraints gbc_lblQtd = new GridBagConstraints();
		gbc_lblQtd.insets = new Insets(0, 0, 5, 5);
		gbc_lblQtd.gridx = 1;
		gbc_lblQtd.gridy = 0;
		panel_Produtos.add(lblQtd, gbc_lblQtd);
		
		JLabel lblpreco = new JLabel("Preco");
		GridBagConstraints gbc_lblPreco = new GridBagConstraints();
		gbc_lblPreco.insets = new Insets(0, 0, 5, 5);
		gbc_lblPreco.gridx = 2;
		gbc_lblPreco.gridy = 0;
		panel_Produtos.add(lblpreco, gbc_lblPreco);


		DefaultComboBoxModel produtoComboBox = new DefaultComboBoxModel();
		for (int i = 0; i < produtoList.size(); i++) {
			produtoComboBox.addElement(produtoList.get(i).getNome());
		}
		JComboBox produtojComboBox = new JComboBox();
		produtojComboBox.setModel(produtoComboBox);

		GridBagConstraints gbc_txtDescricaoProduto = new GridBagConstraints();
		gbc_txtDescricaoProduto.insets = new Insets(0, 0, 5, 5);
		gbc_txtDescricaoProduto.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDescricaoProduto.gridx = 0;
		gbc_txtDescricaoProduto.gridy = 1;
		panel_Produtos.add(produtojComboBox, gbc_txtDescricaoProduto);

		txtQtdProduto = new JTextField();
		GridBagConstraints gbc_txtQtdProduto = new GridBagConstraints();
		gbc_txtQtdProduto.insets = new Insets(0, 0, 5, 5);
		gbc_txtQtdProduto.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtQtdProduto.gridx = 1;
		gbc_txtQtdProduto.gridy = 1;
		panel_Produtos.add(txtQtdProduto, gbc_txtQtdProduto);
		txtQtdProduto.setColumns(5);
		
		txtprecoProd = new JTextField();
		GridBagConstraints gbc_txtprecoProd = new GridBagConstraints();
		gbc_txtprecoProd.insets = new Insets(0, 0, 5, 5);
		gbc_txtprecoProd.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtprecoProd.gridx = 2;
		gbc_txtprecoProd.gridy = 1;
		panel_Produtos.add(txtprecoProd, gbc_txtprecoProd);
		txtprecoProd.setColumns(5);
		
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

		btnEfetuarVenda.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Produto p = new ProdutoDAO().search(produtoList.get(produtojComboBox.getSelectedIndex()).getId());
				Cliente c = new ClienteDAO().search(clienteList.get(jComboBox.getSelectedIndex()).getId());

				NewVendaDAO vendaDAO = new NewVendaDAO();
				NewVenda venda = new NewVenda();
				venda.setCliente(c);
				vendaDAO.save(venda);
				int id = 0;

				List<NewVenda> nv = vendaDAO.listAll();
				for (NewVenda n: nv) {
					id = n.getId();
				}

				NewVenda newVendas = vendaDAO.search(id);

				ItemVendaDAO itemVendaDAO = new ItemVendaDAO();
				ItemVenda itemVenda = new ItemVenda();
 				itemVenda.setVenda(newVendas);
				itemVenda.setProduto(p);
				itemVenda.setQuantidade(new BigDecimal(txtQtdProduto.getText().replaceAll(",", ".")));
				itemVenda.setPreco(new BigDecimal(txtprecoProd.getText().replaceAll(",", ".")));

				itemVendaDAO.save(itemVenda);

			}
		});
	}

}
