package br.unive.views;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
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

public class PrincipalView extends JFrame{
	public PrincipalView() {
		
		
		JLabel lblProjetoVendas = new JLabel("Projeto Vendas");
		lblProjetoVendas.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblProjetoVendas.setHorizontalAlignment(SwingConstants.CENTER);
		
		JSeparator separator = new JSeparator();
		
		JButton btnProdutos = new JButton("Produtos");
		btnProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PesqProdutosView pv = new PesqProdutosView();
				pv.setVisible(true);
				pv.setSize(566,285);
			}
		});
		
		JButton btnClientes = new JButton("Clientes");
		btnClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PesqClientesView pcv = new PesqClientesView();
				pcv.setVisible(true);
				pcv.setSize(574,304);
			}
		});
		
		JButton btnA = new JButton("Vendas");
		btnA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		JButton btnBackup = new JButton("Backup");
		btnBackup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BackupView bv = new BackupView();
				bv.setVisible(true);
				bv.setSize(309,141);
			}
		});
		
		JButton btnXmls = new JButton("Xmls");
		btnXmls.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				XmlsView  xv = new XmlsView();
				xv.setVisible(true);
				xv.setSize(323,145);
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(lblProjetoVendas, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
				.addComponent(separator, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(157)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnXmls, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
							.addGap(150))
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(btnBackup, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
								.addGap(150))
							.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(btnA, GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
									.addComponent(btnClientes, GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
									.addComponent(btnProdutos, GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE))
								.addGap(150)))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(lblProjetoVendas, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnProdutos)
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
	}
}