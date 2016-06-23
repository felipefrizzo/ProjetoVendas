package br.univel.views;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.PatternSyntaxException;
import javax.swing.table.DefaultTableModel;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import br.unive.tabelaModelos.ModeloCliente;
import br.univel.model.cliente.Cliente;
import br.univel.model.cliente.ClienteParser;
import br.univel.model.produto.ProdutoParser;
import br.univel.modelo.readerURL.ReaderURL;
import br.univel.readerArquivo.ReaderArquivo;

import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.RowFilter;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class PesqClientesView extends JFrame{
	public static Cliente cliAlterar;
	private JTextField txtPesquisa;
	private JTable tableClientes;
	private List<Cliente> listaClientes = new ArrayList<Cliente>();
	public PesqClientesView() {
		// para testar, depois vou substituir pelos dados do banco
		ClienteParser clip = new ClienteParser();
		ReaderArquivo reader = new ReaderArquivo();
		listaClientes = clip.getCliente(reader.lerArquivo(new File("clientes.csv")));
		
		JLabel lblClientes = new JLabel("Clientes");
		lblClientes.setHorizontalAlignment(SwingConstants.CENTER);
		lblClientes.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JSeparator separator = new JSeparator();
		
		txtPesquisa = new JTextField();
		txtPesquisa.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				txtPesquisaKeyPressed(arg0);
			}
		});
		txtPesquisa.setColumns(10);
		
		JLabel label_1 = new JLabel("Pesquisa");
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				Terminar a exclusao
//				int selecionada = tableClientes.getSelectedRow();
//				DefaultTableModel dtm = (DefaultTableModel) tableClientes.getModel();
//				if(selecionada != -1){
//					dtm.removeRow(selecionada);
//				}
			}
		});
		
		JButton btnInserir = new JButton("Inserir");
		btnInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClienteView cv = new ClienteView();
				cv.setVisible(true);
				cv.setSize(450,301);
			}
		});
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int selecionada = tableClientes.getSelectedRow();
				if(selecionada != -1){
					cliAlterar = new Cliente();
					cliAlterar.setId(Integer.parseInt(tableClientes.getValueAt(selecionada, 0).toString()));
					cliAlterar.setNome(tableClientes.getValueAt(selecionada, 1).toString());
					cliAlterar.setEndereço(tableClientes.getValueAt(selecionada, 2).toString());
					cliAlterar.setNumero(Integer.parseInt(tableClientes.getValueAt(selecionada, 3).toString()));
					cliAlterar.setComplemento(tableClientes.getValueAt(selecionada, 4).toString());
					cliAlterar.setBairro(tableClientes.getValueAt(selecionada, 5).toString());
					cliAlterar.setCidade(tableClientes.getValueAt(selecionada, 6).toString());
					cliAlterar.setEstado(tableClientes.getValueAt(selecionada, 7).toString());
					cliAlterar.setCep(tableClientes.getValueAt(selecionada, 8).toString());
					cliAlterar.setTelefone(tableClientes.getValueAt(selecionada, 9).toString());
					cliAlterar.setCelular(tableClientes.getValueAt(selecionada, 10).toString());
				}	
				ClienteView cv = new ClienteView();
				cv.setVisible(true);
				cv.setSize(450,301);
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addComponent(txtPesquisa, GroupLayout.PREFERRED_SIZE, 328, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblClientes, GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
								.addComponent(separator, GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE))))
					.addPreferredGap(ComponentPlacement.RELATED, 10, GroupLayout.PREFERRED_SIZE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnInserir, GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
						.addComponent(btnAlterar, GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
						.addComponent(btnExcluir, GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
						.addComponent(btnSair, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(14)
							.addComponent(btnInserir)
							.addGap(6)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnAlterar)
									.addGap(5)
									.addComponent(btnExcluir))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(label_1)
									.addGap(7)
									.addComponent(txtPesquisa, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblClientes, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
						.addComponent(btnSair))
					.addContainerGap())
		);
		
		
		
		tableClientes = new JTable();
//		tableClientes.addFocusListener(new FocusAdapter() {
//			@Override
//			public void focusGained(FocusEvent arg0) {
//				if(!btnAlterar.isEnabled() || !btnExcluir.isEnabled()){
//					btnAlterar.setEnabled(true);
//					btnExcluir.setEnabled(true);
//				}
//			}
//			@Override
//			public void focusLost(FocusEvent e) {
//				if(btnAlterar.isEnabled() || btnExcluir.isEnabled()){
//					btnAlterar.setEnabled(false);
//					btnExcluir.setEnabled(false);
//				}
//			}
//		});
		scrollPane.setViewportView(tableClientes);
		getContentPane().setLayout(groupLayout);
		Consultar();
	}
	
	public void Consultar(){
		ModeloCliente model = new ModeloCliente(getListaClientes());
		tableClientes.setModel(model);		
	}

	private void txtPesquisaKeyPressed(java.awt.event.KeyEvent evt){                                         
        ModeloCliente tabela_clientes =  (ModeloCliente) tableClientes.getModel();
        final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tabela_clientes);
        tableClientes.setRowSorter(sorter);
        String text = txtPesquisa.getText().toUpperCase();
        if (text.length() == 0)
        {
             sorter.setRowFilter(null);
        }
        else
        {
             try
             {
                   sorter.setRowFilter(
                   RowFilter.regexFilter(text));
             }
             catch (PatternSyntaxException pse)
             {
                   System.err.println("Erro");
             }
        }
    }
	
	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}
}