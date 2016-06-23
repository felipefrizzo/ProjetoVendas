package br.univel.views;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JCheckBox;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class XmlsView extends JFrame{
	public XmlsView() {
		
		JCheckBox checkBox = new JCheckBox("Produtos");
		checkBox.setBounds(10, 44, 69, 23);
		
		JCheckBox checkBox_1 = new JCheckBox("Clientes");
		checkBox_1.setBounds(10, 67, 69, 23);
		
		JCheckBox checkBox_2 = new JCheckBox("Vendas");
		checkBox_2.setBounds(81, 44, 69, 23);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 35, 203, 10);
		
		JLabel lblXmls = new JLabel("Xmls");
		lblXmls.setBounds(10, 1, 193, 28);
		lblXmls.setHorizontalAlignment(SwingConstants.CENTER);
		lblXmls.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JButton btnGerar = new JButton("Gerar");
		btnGerar.setBounds(213, 7, 91, 23);
		
		JButton btnImportar = new JButton("Importar");
		btnImportar.setBounds(213, 35, 91, 23);
		getContentPane().setLayout(null);
		getContentPane().add(lblXmls);
		getContentPane().add(checkBox);
		getContentPane().add(checkBox_1);
		getContentPane().add(checkBox_2);
		getContentPane().add(btnImportar);
		getContentPane().add(btnGerar);
		getContentPane().add(separator);
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSair.setBounds(215, 67, 89, 23);
		getContentPane().add(btnSair);
	}

}
