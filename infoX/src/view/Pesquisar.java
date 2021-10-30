package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Atxy2k.CustomTextField.RestrictedTextField;
import model.DAO;
import net.proteanit.sql.DbUtils;

import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JPasswordField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Pesquisar extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtPesquisar;
	private JTable table;
	private JTextField txtNome;
	private JTextField txtId;
	private JPasswordField txtSenha;
	private JTextField txtCep;
	private JTextField txtEndereco;
	private JTextField txtNumero;
	private JTextField txtComplemento;
	private JTextField txtCidade;
	private JTextField txtFone;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Pesquisar dialog = new Pesquisar();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Pesquisar() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Pesquisar.class.getResource("/img/pc.png")));
		setBounds(100, 100, 634, 473);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		txtPesquisar = new JTextField();
		txtPesquisar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				pesquisarCliente();
			}
		});
		txtPesquisar.setBounds(10, 20, 163, 20);
		contentPanel.add(txtPesquisar);
		txtPesquisar.setColumns(10);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Pesquisar.class.getResource("/img/pesquisar.png")));
		lblNewLabel.setBounds(192, 14, 32, 32);
		contentPanel.add(lblNewLabel);

		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(10, 62, 587, 99);
		contentPanel.add(desktopPane);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 587, 99);
		desktopPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setarCampos();
				// setarSenha();
			}
		});
		scrollPane.setViewportView(table);

		JLabel lblNewLabel_1 = new JLabel("Nome");
		lblNewLabel_1.setBounds(222, 187, 46, 14);
		contentPanel.add(lblNewLabel_1);

		txtNome = new JTextField();
		txtNome.setBounds(278, 181, 319, 20);
		contentPanel.add(txtNome);
		txtNome.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("ID");
		lblNewLabel_2.setBounds(10, 187, 32, 14);
		contentPanel.add(lblNewLabel_2);

		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setBounds(32, 184, 86, 20);
		contentPanel.add(txtId);
		txtId.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Senha");
		lblNewLabel_4.setBounds(239, 226, 46, 14);
		contentPanel.add(lblNewLabel_4);

		txtSenha = new JPasswordField();
		txtSenha.setBounds(278, 224, 115, 17);
		contentPanel.add(txtSenha);

		JLabel lblNewLabel_5 = new JLabel("CEP");
		lblNewLabel_5.setBounds(10, 265, 32, 14);
		contentPanel.add(lblNewLabel_5);

		txtCep = new JTextField();
		txtCep.setBounds(56, 262, 124, 20);
		contentPanel.add(txtCep);
		txtCep.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("Endere\u00E7o");
		lblNewLabel_6.setBounds(239, 268, 57, 14);
		contentPanel.add(lblNewLabel_6);

		txtEndereco = new JTextField();
		txtEndereco.setBounds(319, 265, 278, 20);
		contentPanel.add(txtEndereco);
		txtEndereco.setColumns(10);

		JLabel lblNewLabel_7 = new JLabel("Numero");
		lblNewLabel_7.setBounds(10, 304, 46, 14);
		contentPanel.add(lblNewLabel_7);

		txtNumero = new JTextField();
		txtNumero.setBounds(66, 301, 57, 20);
		contentPanel.add(txtNumero);
		txtNumero.setColumns(10);

		JLabel lblNewLabel_8 = new JLabel("Complemento");
		lblNewLabel_8.setBounds(157, 307, 86, 14);
		contentPanel.add(lblNewLabel_8);

		txtComplemento = new JTextField();
		txtComplemento.setBounds(249, 301, 86, 20);
		contentPanel.add(txtComplemento);
		txtComplemento.setColumns(10);

		JLabel lblNewLabel_9 = new JLabel("Cidade");
		lblNewLabel_9.setBounds(360, 307, 46, 14);
		contentPanel.add(lblNewLabel_9);

		txtCidade = new JTextField();
		txtCidade.setBounds(416, 301, 181, 20);
		contentPanel.add(txtCidade);
		txtCidade.setColumns(10);

		JLabel lblNewLabel_10 = new JLabel("Fone");
		lblNewLabel_10.setBounds(403, 226, 46, 14);
		contentPanel.add(lblNewLabel_10);

		txtFone = new JTextField();
		txtFone.setBounds(459, 223, 138, 20);
		contentPanel.add(txtFone);
		txtFone.setColumns(10);

		btnAdicionar = new JButton("");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarCliente();
			}
		});
		btnAdicionar.setIcon(new ImageIcon(Pesquisar.class.getResource("/img/create.png")));
		btnAdicionar.setBounds(93, 343, 80, 80);
		contentPanel.add(btnAdicionar);

		btnEditar = new JButton("");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editarCliente();
			}
		});
		btnEditar.setEnabled(false);
		btnEditar.setIcon(new ImageIcon(Pesquisar.class.getResource("/img/update.png")));
		btnEditar.setBounds(231, 343, 80, 80);
		contentPanel.add(btnEditar);

		btnExcluir = new JButton("");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirCliente();
			}
		});
		btnExcluir.setEnabled(false);
		btnExcluir.setIcon(new ImageIcon(Pesquisar.class.getResource("/img/delete.png")));
		btnExcluir.setBounds(360, 343, 80, 80);
		contentPanel.add(btnExcluir);

		scrollPane.setViewportView(table);

		// uso da biblioteca Atxy2k para validações
		RestrictedTextField nome = new RestrictedTextField(this.txtNome);
		nome.setLimit(50);
		RestrictedTextField senha = new RestrictedTextField(this.txtSenha);
		senha.setLimit(250);

	}// Fim do Construtor

	DAO dao = new DAO();
	private JButton btnAdicionar;
	private JButton btnEditar;
	private JButton btnExcluir;

	private void pesquisarCliente() {
		// ? -> parâmetro
		String read = "select * from clientes where nome like  ?";
		try {
			
			Connection con = dao.conectar();
			
			PreparedStatement pst = con.prepareStatement(read);
			
			
			pst.setString(1, txtPesquisar.getText() + "%");
			
			ResultSet rs = pst.executeQuery();
			
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			System.out.println(e);
		}
	} // Fim do Metodo Pesquisar >>>>>>>>>

	private void adicionarCliente() {
		// validação de campos obrigatórios
		if (txtNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o nome", "Atenção!", JOptionPane.WARNING_MESSAGE);
			txtNome.requestFocus();
		}  else if (txtCep.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o CEP", "Atenção!", JOptionPane.WARNING_MESSAGE);
			txtCep.requestFocus();

		} else if (txtEndereco.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Endereço", "Atenção!", JOptionPane.WARNING_MESSAGE);
			txtEndereco.requestFocus();

		} else if (txtNumero.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Numero da sua residencia ", "Atenção!",
					JOptionPane.WARNING_MESSAGE);
			txtNumero.requestFocus();

		} else if (txtComplemento.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Complemento", "Atenção!", JOptionPane.WARNING_MESSAGE);
			txtComplemento.requestFocus();

		} else if (txtCidade.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a Cidade", "Atenção!", JOptionPane.WARNING_MESSAGE);
			txtCidade.requestFocus();
		} else {
			// inserir o cliente no banco
			String create = "insert into clientes (nome,cep,endereço,numero,complemento,cidade,fone) where idcli=?";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(create);
				pst.setString(1, txtNome.getText());
				pst.setString(2, txtCep.getText());
				pst.setString(3, txtEndereco.getText());
				pst.setString(4, txtNumero.getText());
				pst.setString(5, txtComplemento.getText());
				pst.setString(6, txtCidade.getText());
				pst.setString(7, txtFone.getText());
				pst.setString(8, txtId.getText());;
				
				int confirma = pst.executeUpdate();
				if (confirma == 1) {
					JOptionPane.showMessageDialog(null, "Cliente adicionado com sucesso.", "Mensagem",
							JOptionPane.INFORMATION_MESSAGE);
				}
				con.close();
				// limpar();
				// o catch abaixo refere ao valor duplicado do email (unique)
			}

			catch (Exception e) {
				System.out.println(e);
			}
		}
	} // Fim do Metodo Adicionar Cliente

	private void setarCampos() {
	
		int setar = table.getSelectedRow();
		// setar os campos
		txtId.setText(table.getModel().getValueAt(setar, 0).toString());
		txtNome.setText(table.getModel().getValueAt(setar, 1).toString());
		txtCep.setText(table.getModel().getValueAt(setar, 2).toString());
		txtEndereco.setText(table.getModel().getValueAt(setar, 3).toString());
		txtNumero.setText(table.getModel().getValueAt(setar, 4).toString());
		txtComplemento.setText(table.getModel().getValueAt(setar, 5).toString());
		txtCidade.setText(table.getModel().getValueAt(setar, 7).toString());
		txtFone.setText(table.getModel().getValueAt(setar, 9).toString());
		// Gerenciar os botões
		btnAdicionar.setEnabled(false);
		btnEditar.setEnabled(true);
		btnExcluir.setEnabled(true);
	}

	
	private void excluirCliente() {

		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclusão deste usuário?", "Atenção!",
				JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {

			String delete = " delete from clientes where idcli=?";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(delete);
				pst.setString(1, txtId.getText());
				int excluir = pst.executeUpdate();
				if (excluir == 1) {
					limpar();
					JOptionPane.showMessageDialog(null, "Cliente excluido com sucesso", "Mensagem",
							JOptionPane.INFORMATION_MESSAGE);
				}

				con.close();
			} catch (java.sql.SQLIntegrityConstraintViolationException ex) {
				JOptionPane.showMessageDialog(null, "Exclusão não realizada.\nCliente possui pedido em aberto.",
						"Atenção!", JOptionPane.WARNING_MESSAGE);

			} catch (Exception e) {
				System.out.println(e);
			}
		}

	} // fim do Metodo

	private void editarCliente() {

		if (txtNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o nome", "Atenção!", JOptionPane.WARNING_MESSAGE);
			txtNome.requestFocus();

		} else if (txtCep.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o CEP", "Atenção!", JOptionPane.WARNING_MESSAGE);
			txtCep.requestFocus();

		} else if (txtEndereco.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Endereço", "Atenção!", JOptionPane.WARNING_MESSAGE);
			txtEndereco.requestFocus();

		} else if (txtNumero.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Numero da sua residencia ", "Atenção!",
					JOptionPane.WARNING_MESSAGE);
			txtNumero.requestFocus();

		} else if (txtComplemento.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o Complemento", "Atenção!", JOptionPane.WARNING_MESSAGE);
			txtComplemento.requestFocus();

		} else if (txtCidade.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a Cidade", "Atenção!", JOptionPane.WARNING_MESSAGE);
			txtCidade.requestFocus();

		} else {

			String update = "update clientes set nome=?,cep=?,endereço=?, numero=? complemento=?, cidade=?, fone=? where idcli=?";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(update);
				pst.setString(1, txtNome.getText());
				pst.setString(2, txtCep.getText());
				pst.setString(3, txtEndereco.getText());
				pst.setString(4, txtNumero.getText());
				pst.setString(5, txtComplemento.getText());
				pst.setString(6, txtCidade.getText());
				pst.setString(7, txtFone.getText());
				pst.setString(8, txtId.getText());
				
				int confirma = pst.executeUpdate();
				if (confirma == 1) {
					JOptionPane.showMessageDialog(null, "Dados do cliente atualizado com sucesso.", "Mensagem",
							JOptionPane.INFORMATION_MESSAGE);
				}
				con.close();
				limpar();

			}

			catch (Exception e) {
				System.out.println(e);
			}
		}
	} // Fim Do Metodo
	
	private void limpar() {

		txtPesquisar.setText(null);
		txtId.setText(null);
		txtNome.setText(null);
		
		
	
		btnAdicionar.setEnabled(true);
		btnEditar.setEnabled(false);
		btnExcluir.setEnabled(false);
	}
}
