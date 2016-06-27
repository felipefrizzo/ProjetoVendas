package br.univel.views;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;

import br.univel.Principal;
import br.univel.database.ConnectionDB;
import br.univel.jaspers.ClienteJRDataSource;
import br.univel.model.cliente.Cliente;
import br.univel.model.cliente.dao.ClienteDAO;
import br.univel.model.produto.Produto;
import br.univel.model.vendas.ItemVenda;
import br.univel.model.vendas.NewVenda;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Button;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PrincipalView extends JFrame{
	ConnectionDB conn ;
	
	public PrincipalView() {
		conn = new ConnectionDB();
		conn.open();
//		Principal p = new Principal();
//		p.createTable(new Produto());
//		p.createTable(new Cliente());
//		p.createTable(new NewVenda());
//		p.createTable(new ItemVenda());
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				conn.close();
			}
		});
		
		JLabel lblProjetoVendas = new JLabel("Projeto Vendas");
		lblProjetoVendas.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblProjetoVendas.setHorizontalAlignment(SwingConstants.CENTER);
		
		JSeparator separator = new JSeparator();
		
		JButton btnProdutos = new JButton("Produtos");
		btnProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PesqProdutosView pv = new PesqProdutosView();
				pv.setSize(566,285);
				pv.setLocationRelativeTo(null);
				pv.setVisible(true);
			}
		});
		
		JButton btnClientes = new JButton("Clientes");
		btnClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PesqClientesView pcv = new PesqClientesView();
				pcv.setSize(574,304);
				pcv.setLocationRelativeTo(null);
				pcv.setVisible(true);
			}
		});
		
		JButton btnA = new JButton("Vendas");
		btnA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VendaView vv = new VendaView();
				vv.setSize(700,384);
				vv.setLocationRelativeTo(null);
				vv.setVisible(true);
			}
		});
		
		JButton btnBackup = new JButton("Backup");
		btnBackup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BackupView bv = new BackupView();
				bv.setSize(309,141);
				bv.setLocationRelativeTo(null);
				bv.setVisible(true);
			}
		});
		
		JButton btnXmls = new JButton("Xmls");
		btnXmls.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				XmlsView  xv = new XmlsView();
				xv.setSize(323,145);
				xv.setLocationRelativeTo(null);
				xv.setVisible(true);
			}
		});
		
		JButton btnX = new JButton("X");
		btnX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(lblProjetoVendas, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE)
				.addComponent(separator, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(157)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnXmls, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
						.addComponent(btnBackup, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
						.addComponent(btnA, GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
						.addComponent(btnClientes, GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
						.addComponent(btnProdutos, GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE))
					.addGap(51)
					.addComponent(btnX)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(lblProjetoVendas, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnProdutos)
						.addComponent(btnX))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnClientes)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnA)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnBackup)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnXmls)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Relatórios");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmRelatrios = new JMenuItem("Clientes");
		mntmRelatrios.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				try {
					imprimir();
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		});
		mntmRelatrios.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					imprimir();
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			}
		});
		mnNewMenu.add(mntmRelatrios);
		
		JMenuItem mntmProdutos = new JMenuItem("Produtos");
		mnNewMenu.add(mntmProdutos);
		
		JMenuItem mntmVendas = new JMenuItem("Vendas");
		mnNewMenu.add(mntmVendas);
	}

	protected void imprimir() throws JRException {
		String arq = "RelCliente.jrxml";
		
		ClienteDAO dao = new ClienteDAO();
		ClienteJRDataSource ds = new ClienteJRDataSource(dao.listAll());
		
		JasperPrint jp = JasperFillManager.fillReport(arq, null, ds);

		JasperViewer jasperViewer = new JasperViewer(jp);

		jasperViewer.setBounds(50, 50, 320, 240);
		jasperViewer.setLocationRelativeTo(null);
		jasperViewer.setExtendedState(JFrame.MAXIMIZED_BOTH);

		jasperViewer.setVisible(true);
	}
}
