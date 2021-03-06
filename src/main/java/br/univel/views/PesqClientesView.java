package br.univel.views;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.PatternSyntaxException;

import javax.swing.SwingConstants;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import br.univel.tabelaModelos.ModeloCliente;
import br.univel.model.cliente.Cliente;
import br.univel.model.cliente.dao.ClienteDAO;

import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.RowFilter;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PesqClientesView extends JFrame{
	public static Cliente cliAlterar;
	private JTextField txtPesquisa;
	private JTable tableClientes;
	private List<Cliente> listaClientes = new ArrayList<Cliente>();
	public PesqClientesView() {
		// para testar, depois vou substituir pelos dados do banco
//		ClienteParser clip = new ClienteParser();
//		ReaderArquivo reader = new ReaderArquivo();
		ClienteDAO cliDao = new ClienteDAO();
		listaClientes = cliDao.listAll();
		
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
				int selecionada = tableClientes.getSelectedRow();
				
				int dialogButton = JOptionPane.YES_NO_OPTION;
	            JOptionPane.showConfirmDialog (null, "Tem Certeza que deseja excluir esse registro ?","CUIDADO", dialogButton);
	            if(dialogButton == JOptionPane.YES_OPTION) {
	            	if(selecionada != -1){
	            		Cliente cliAntigo = ((ModeloCliente) tableClientes.getModel()).getCliente(selecionada);
	            		cliDao.delete(cliAntigo.getId());
	            		((ModeloCliente) tableClientes.getModel()).fireTableDataChanged();
	            	}
	            }
			}
		});
		
		JButton btnInserir = new JButton("Inserir");
		btnInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClienteView cv = new ClienteView();
				cv.setVisible(true);
				cv.setSize(450,301);
				cv.inserindo = true;
				((ModeloCliente) tableClientes.getModel()).fireTableDataChanged();
				atualiza();
			}
		});
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int selecionada = tableClientes.getSelectedRow();
				if(selecionada != -1){
					
					Cliente clienteAntigo = ((ModeloCliente) tableClientes.getModel()).getCliente(selecionada);
					
					
					cliAlterar = new Cliente();
					cliAlterar.setId(clienteAntigo.getId());
					cliAlterar.setNome(clienteAntigo.getNome());
					cliAlterar.setEndereço(clienteAntigo.getEndereco());
					cliAlterar.setNumero(clienteAntigo.getNumero());
					cliAlterar.setComplemento(clienteAntigo.getComplemento());
					cliAlterar.setBairro(clienteAntigo.getBairro());
					cliAlterar.setCidade(clienteAntigo.getCidade());
					cliAlterar.setEstado(clienteAntigo.getEstado());
					cliAlterar.setCep(clienteAntigo.getCep());
					cliAlterar.setTelefone(clienteAntigo.getTelefone());
					cliAlterar.setCelular(clienteAntigo.getCelular());
					cliDao.update(cliAlterar);
					
				}	
				ClienteView cv = new ClienteView();
				cv.setVisible(true);
				cv.setSize(450,301);
				((ModeloCliente) tableClientes.getModel()).fireTableDataChanged();
				atualiza();
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
								.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
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
	
	protected void atualiza() {
		ModeloCliente mc = new ModeloCliente(listaClientes);
		tableClientes.setModel(mc);
		
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