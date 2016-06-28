package br.univel.views;

import br.unive.tabelaModelos.ModeloItemVenda;
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
import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EfetuarVendaView extends JFrame{
	private JTextField txtQtdProduto;
	private JTextField txtDescricaoProduto;
	private JTextField txtCodCliente;
	private JTextField txtNomeCliente;
	private JLabel lblNomeCliente;
	private JComboBox ComboBoxCliente;
	ClienteDAO clienteDAO = new ClienteDAO();
	List<Cliente> clienteList = clienteDAO.listAll();
	ItemVendaDAO itemVendaDAO = new ItemVendaDAO();
	ProdutoDAO produtoDAO = new ProdutoDAO();
	List<Produto> produtoList = produtoDAO.listAll();
	List<ItemVenda> itensVenda ;
	private double valorTotalVenda;
	protected int idAtual;
	NewVenda venda; 
	private JTable tableProutos;
	private JTextField txtValorTotal;
	private JTextField txtTroco;
	
	public EfetuarVendaView() {
		idAtual = buscaUltimaIdVenda();
		venda = new NewVenda();
//		venda.setId(idAtual);
		itensVenda = new ArrayList<ItemVenda>();
		
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
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
		
		JCheckBox checkboxUtilizarCliente = new JCheckBox("Utilizar Cliente");
		checkboxUtilizarCliente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(checkboxUtilizarCliente.isSelected()){
					lblNomeCliente.setEnabled(true);
					ComboBoxCliente.setEnabled(true);
				}else{
					lblNomeCliente.setEnabled(false);
					ComboBoxCliente.setEnabled(false);
					ComboBoxCliente.setSelectedIndex(-1);
				}
			}
		});
		
		GridBagConstraints gbc_checkboxUtilizarCliente = new GridBagConstraints();
		gbc_checkboxUtilizarCliente.insets = new Insets(0, 0, 5, 5);
		gbc_checkboxUtilizarCliente.gridx = 0;
		gbc_checkboxUtilizarCliente.gridy = 0;
		panel_Cliente.add(checkboxUtilizarCliente, gbc_checkboxUtilizarCliente);

		lblNomeCliente = new JLabel("Nome Cliente");
		lblNomeCliente.setEnabled(false);
		lblNomeCliente.setHorizontalAlignment(SwingConstants.LEFT);

		GridBagConstraints gbc_lblNomeCliente = new GridBagConstraints();
		gbc_lblNomeCliente.insets = new Insets(0, 0, 0, 5);
		gbc_lblNomeCliente.gridx = 0;
		gbc_lblNomeCliente.gridy = 1;
		panel_Cliente.add(lblNomeCliente, gbc_lblNomeCliente);

		DefaultComboBoxModel clienteComboBox = new DefaultComboBoxModel();
		for (int i = 0; i < clienteList.size(); i++) {
			clienteComboBox.addElement(clienteList.get(i).getNome());
		}
		ComboBoxCliente = new JComboBox();
		ComboBoxCliente.setEnabled(false);
		ComboBoxCliente.setModel(clienteComboBox);
		ComboBoxCliente.setSelectedIndex(-1);

		GridBagConstraints gbc_ComboBoxCliente = new GridBagConstraints();
		gbc_ComboBoxCliente.fill = GridBagConstraints.HORIZONTAL;
		gbc_ComboBoxCliente.gridx = 1;
		gbc_ComboBoxCliente.gridy = 1;
		panel_Cliente.add(ComboBoxCliente, gbc_ComboBoxCliente);


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
		gbl_panel_Produtos.columnWidths = new int[]{377, 72, 75, 0};
		gbl_panel_Produtos.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panel_Produtos.columnWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_Produtos.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		panel_Produtos.setLayout(gbl_panel_Produtos);

		JLabel lblNewLabel_1 = new JLabel("Produtos");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 0;
		panel_Produtos.add(lblNewLabel_1, gbc_lblNewLabel_1);

		JLabel lblQtd = new JLabel("Qtd");
		GridBagConstraints gbc_lblQtd = new GridBagConstraints();
		gbc_lblQtd.insets = new Insets(0, 0, 5, 5);
		gbc_lblQtd.gridx = 1;
		gbc_lblQtd.gridy = 0;
		panel_Produtos.add(lblQtd, gbc_lblQtd);


		DefaultComboBoxModel produtoComboBox = new DefaultComboBoxModel();
		for (int i = 0; i < produtoList.size(); i++) {
			produtoComboBox.addElement(produtoList.get(i).getNome());
		}
		JComboBox produtojComboBox = new JComboBox();
		produtojComboBox.setModel(produtoComboBox);
		produtojComboBox.setSelectedIndex(-1);
		
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
		
		JButton btnAddProd = new JButton("add prod");
		btnAddProd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(idAtual);
				ItemVenda iv  = new ItemVenda();
				Produto p = new ProdutoDAO().search(produtoList.get(produtojComboBox.getSelectedIndex()).getId());
				iv.setProduto(p);
				iv.setQuantidade(new BigDecimal(txtQtdProduto.getText()));
				iv.setPreco(new BigDecimal(txtQtdProduto.getText()).multiply(p.getPreco()));
				iv.setVenda(venda);
				itensVenda.add(iv);
				valorTotalVenda = valorTotalVenda + iv.getPreco().doubleValue();
				txtValorTotal.setText(Double.toString(valorTotalVenda));
				atualiza();
				
				produtojComboBox.setSelectedIndex(-1);
				txtQtdProduto.setText("");
			}
		});
		GridBagConstraints gbc_btnAddProd = new GridBagConstraints();
		gbc_btnAddProd.insets = new Insets(0, 0, 5, 0);
		gbc_btnAddProd.gridx = 2;
		gbc_btnAddProd.gridy = 1;
		panel_Produtos.add(btnAddProd, gbc_btnAddProd);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 2;
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 2;
		panel_Produtos.add(scrollPane, gbc_scrollPane);
		
		tableProutos = new JTable();
		scrollPane.setColumnHeaderView(tableProutos);
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		JLabel lblValorTotal = new JLabel("Valor Total");
		GridBagConstraints gbc_lblValorTotal = new GridBagConstraints();
		gbc_lblValorTotal.insets = new Insets(0, 0, 5, 5);
		gbc_lblValorTotal.gridx = 0;
		gbc_lblValorTotal.gridy = 3;
		getContentPane().add(lblValorTotal, gbc_lblValorTotal);
		
		JLabel lblTroco = new JLabel("Troco");
		GridBagConstraints gbc_lblTroco = new GridBagConstraints();
		gbc_lblTroco.insets = new Insets(0, 0, 5, 0);
		gbc_lblTroco.gridx = 1;
		gbc_lblTroco.gridy = 3;
		getContentPane().add(lblTroco, gbc_lblTroco);
		
		txtValorTotal = new JTextField();
		txtValorTotal.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER){
					txtTroco.setText(Double.toString(Double.parseDouble(txtValorTotal.getText()) - valorTotalVenda));
				}
			}
		});
		GridBagConstraints gbc_txtValorTotal = new GridBagConstraints();
		gbc_txtValorTotal.insets = new Insets(0, 0, 5, 5);
		gbc_txtValorTotal.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtValorTotal.gridx = 0;
		gbc_txtValorTotal.gridy = 4;
		getContentPane().add(txtValorTotal, gbc_txtValorTotal);
		txtValorTotal.setColumns(10);
		
		txtTroco = new JTextField();
		GridBagConstraints gbc_txtTroco = new GridBagConstraints();
		gbc_txtTroco.anchor = GridBagConstraints.NORTH;
		gbc_txtTroco.insets = new Insets(0, 0, 5, 0);
		gbc_txtTroco.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTroco.gridx = 1;
		gbc_txtTroco.gridy = 4;
		getContentPane().add(txtTroco, gbc_txtTroco);
		txtTroco.setColumns(10);
		
		JButton btnEfetuarVenda = new JButton("Efetuar Venda");
		GridBagConstraints gbc_btnEfetuarVenda = new GridBagConstraints();
		gbc_btnEfetuarVenda.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnEfetuarVenda.insets = new Insets(0, 0, 0, 5);
		gbc_btnEfetuarVenda.gridx = 0;
		gbc_btnEfetuarVenda.gridy = 5;
		getContentPane().add(btnEfetuarVenda, gbc_btnEfetuarVenda);
		GridBagConstraints gbc_btnSair = new GridBagConstraints();
		gbc_btnSair.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSair.gridx = 1;
		gbc_btnSair.gridy = 5;
		getContentPane().add(btnSair, gbc_btnSair);

		
		btnEfetuarVenda.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Cliente c = null;
				if(checkboxUtilizarCliente.isSelected()){
					c = new ClienteDAO().search(clienteList.get(ComboBoxCliente.getSelectedIndex()).getId());					
				}

				NewVendaDAO vendaDAO = new NewVendaDAO();
				if(checkboxUtilizarCliente.isSelected()){
					venda.setCliente(c);
				}
				venda.setItemVendas(itensVenda);
				int id = 0;
				vendaDAO.save(venda);
				List<NewVenda> list = vendaDAO.listAll();
				
				for(NewVenda l: list) {
					id = l.getId();
				}
				NewVenda newVenda = vendaDAO.search(id);
				for(ItemVenda iv1 : itensVenda){
					iv1.setVenda(newVenda);
					itemVendaDAO.save(iv1);
				}
//				int id = 0;
//
//				List<NewVenda> nv = vendaDAO.listAll();
//				for (NewVenda n: nv) {
//					id = n.getId();
//				}

//				NewVenda newVendas = vendaDAO.search(idAtual);
//
//				ItemVendaDAO itemVendaDAO = new ItemVendaDAO();
//				ItemVenda itemVenda = new ItemVenda();
// 				itemVenda.setVenda(newVendas);
//				itemVenda.setProduto(p);
//				itemVenda.setQuantidade(new BigDecimal(txtQtdProduto.getText().replaceAll(",", ".")));
////				itemVenda.setPreco(new BigDecimal(txtprecoProd.getText().replaceAll(",", ".")));


			}
		});
		
	}
	protected void atualiza() {
		ModeloItemVenda miv = new ModeloItemVenda(itensVenda);
		tableProutos.setModel(miv);
	}
	public int buscaUltimaIdVenda(){
		int id = 0;
		NewVendaDAO nvd = new NewVendaDAO();
		List<NewVenda> nv = nvd.listAll();
		for (NewVenda n: nv) {
			id = n.getId();
		}
		id = id + 1;
		return id;
	}
}


