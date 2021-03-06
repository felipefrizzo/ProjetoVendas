package br.univel.views;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.List;
import java.util.regex.PatternSyntaxException;

import javax.swing.SwingConstants;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import br.univel.tabelaModelos.ModelProduto;
import br.univel.model.produto.Produto;
import br.univel.model.produto.dao.ProdutoDAO;

import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PesqProdutosView extends JFrame{
	private JTextField txtPesquisa;
	public static JTable tableProdutos;
	private List<Produto> listaProdutos;
	public static Produto prodAlterar;
	ProdutoDAO prodDAO ;
	
	
	PesqProdutosView() {
		// para testar depois vou substituir pelos dados do banco
//		ProdutoParser prodp = new ProdutoParser();
//		ReaderURL reader = new ReaderURL();
//		listaProdutos = prodp.getProduto(reader.lerUrl());
		
		
//		for(Produto p : listaProdutos){
//			prodDAO.save(p);
//		}
		prodDAO = new ProdutoDAO();
		listaProdutos = prodDAO.listAll();
		
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 89, 438, 150);
		getContentPane().add(scrollPane);
		
		tableProdutos = new JTable();
		scrollPane.setViewportView(tableProdutos);
		
		txtPesquisa = new JTextField();
		txtPesquisa.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				txtPesquisaKeyPressed(arg0);
			}
		});
		txtPesquisa.setColumns(10);
		txtPesquisa.setBounds(10, 63, 328, 20);
		getContentPane().add(txtPesquisa);
		
		JLabel lblProdutos = new JLabel("Produtos");
		lblProdutos.setHorizontalAlignment(SwingConstants.CENTER);
		lblProdutos.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblProdutos.setBounds(0, 0, 448, 28);
		getContentPane().add(lblProdutos);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 32, 438, 4);
		getContentPane().add(separator);
		
		JLabel label_1 = new JLabel("Pesquisa");
		label_1.setBounds(10, 42, 91, 14);
		getContentPane().add(label_1);
		
		JButton btnInserir = new JButton("Inserir");
		btnInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProdutoView pv = new ProdutoView();
				pv.setVisible(true);
				pv.setSize(463,142);
				pv.inserindo = true;
				((ModelProduto) tableProdutos.getModel()).fireTableDataChanged();
			}
		});
		btnInserir.setBounds(458, 3, 91, 23);
		getContentPane().add(btnInserir);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				int selecionada = tableProdutos.getSelectedRow();
				if(selecionada != -1){
					Produto prodAntigo = ((ModelProduto) tableProdutos.getModel()).getProduto(selecionada); 
					
					prodAlterar = new Produto();
					prodAlterar.setId(prodAntigo.getId());
					prodAlterar.setNome(prodAntigo.getNome());
					prodAlterar.setPreco(prodAntigo.getPreco());
				}
				
				ProdutoView pv = new ProdutoView();
				pv.setVisible(true);
				pv.setSize(463,142);
				((ModelProduto) tableProdutos.getModel()).fireTableDataChanged();
			}
		});
		btnAlterar.setBounds(458, 32, 91, 23);
		getContentPane().add(btnAlterar);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int selecionada = tableProdutos.getSelectedRow();
				
				int dialogButton = JOptionPane.YES_NO_OPTION;
	            JOptionPane.showConfirmDialog (null, "Tem Certeza que deseja excluir esse registro ?","CUIDADO", dialogButton);
	            if(dialogButton == JOptionPane.YES_OPTION) {
	            	if(selecionada != -1){
	            		Produto prodAntigo = ((ModelProduto) tableProdutos.getModel()).getProduto(selecionada);
	            		prodDAO.delete(prodAntigo.getId());
	            		((ModelProduto) tableProdutos.getModel()).fireTableDataChanged();
	            	}
	            }
			}
		});
		btnExcluir.setBounds(458, 60, 91, 23);
		getContentPane().add(btnExcluir);
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSair.setBounds(458, 89, 89, 23);
		getContentPane().add(btnSair);
		consulta();
	}
	
	protected void txtPesquisaKeyPressed(java.awt.event.KeyEvent evt) {
		ModelProduto tabela_produto =  (ModelProduto) tableProdutos.getModel();
        final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tabela_produto);
        tableProdutos.setRowSorter(sorter);
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

	public void consulta(){
		ModelProduto model = new ModelProduto(getListaProdutos());
		tableProdutos.setModel(model);
	}

	public List<Produto> getListaProdutos() {
		return listaProdutos;
	}

	public void setListaProdutos(List<Produto> listaProdutos) {
		this.listaProdutos = listaProdutos;
	}
}
