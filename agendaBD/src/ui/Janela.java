package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import modelo.Campeonato;
import modelo.Jogador;
import negocio.Genius;
import exceptions.JogadoresInsifucientesException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JCheckBox;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import java.awt.Color;

public class Janela {

	private JFrame frame;
	private JTextField txtNomeJogador;
    private Genius genius = new Genius();
    private JTextField textField;
    private JTextField textField_1;
   
	/**

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Janela window = new Janela();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Janela() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		try {
			genius.iniciarBD();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ArrayList<String> campeonatos=new ArrayList<>();
		try {
			campeonatos=genius.getCampeonatos();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] arrayCampeonatos = campeonatos.toArray(new String[campeonatos.size()]);

		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		
		JPanel panelCadastroJogador = new JPanel();
		tabbedPane.addTab("Cadastro de Jogadores", null, panelCadastroJogador, null);
		panelCadastroJogador.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nome Jogador: ");
		lblNewLabel.setBounds(21, 36, 116, 14);
		panelCadastroJogador.add(lblNewLabel);
		
		txtNomeJogador = new JTextField();
		txtNomeJogador.setBounds(154, 33, 161, 20);
		panelCadastroJogador.add(txtNomeJogador);
		txtNomeJogador.setColumns(10);
		
		JButton btnNewButton = new JButton("ADD");
		btnNewButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			try {
				//genius.checkIfJogadorRepete(txtNomeJogador.getText());
				if(txtNomeJogador.getText().isBlank())throw new Exception();
				genius.adicionarJogador(new Jogador(txtNomeJogador.getText()));
				JOptionPane.showMessageDialog(btnNewButton, "Jogador cadastrado com sucesso!");
			} 
			catch(Exception e1) {
				JOptionPane.showMessageDialog(btnNewButton, "O campo esta vazio ou esse torneio ja existe!");
			}
		}
	});
		btnNewButton.setBounds(154, 99, 161, 23);
		panelCadastroJogador.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Deletar Jogadores");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String option=JOptionPane.showInputDialog("Digite SIM para deletar seus jogadores. TODOS OS DADOS SERÃO APAGADOS!");
				if(option.equalsIgnoreCase("SIM")) {
					try {
						genius.deletarJogadores();;
						JOptionPane.showMessageDialog(btnNewButton, "Jogadores Apagados!");
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnNewButton_1.setBounds(154, 151, 161, 23);
		panelCadastroJogador.add(btnNewButton_1);
		
		
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Relatorios", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Torneios :");
		lblNewLabel_2.setBounds(31, 26, 75, 14);
		panel.add(lblNewLabel_2);
		
		JComboBox cmbTipo = new JComboBox(arrayCampeonatos);
		cmbTipo.setBounds(101, 22, 116, 22);
		panel.add(cmbTipo);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(31, 129, 388, 93);
		panel.add(textArea);
		
		JButton btnNewButton_4 = new JButton("Pesquisar");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String campeonato=(String)cmbTipo.getSelectedItem();
				try {
					if(genius.retornarRelatorio(campeonato).isBlank()) throw new SQLException ();
					textArea.setText(""+genius.retornarRelatorio(campeonato));
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(btnNewButton, "Relatorio esta vázio,va jogar esse torneio para preenche-ló!");
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
		});
		btnNewButton_4.setBounds(101, 85, 89, 23);
		panel.add(btnNewButton_4);
		
		//Jogar
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Jogar", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Torneio:");
		lblNewLabel_3.setBounds(129, 11, 46, 14);
		panel_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Dificuldade:");
		lblNewLabel_4.setBounds(10, 11, 76, 14);
		panel_1.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Velocidade:");
		lblNewLabel_5.setBounds(319, 11, 76, 14);
		panel_1.add(lblNewLabel_5);
		
		JComboBox comboBoxTorneios = new JComboBox(arrayCampeonatos);
		comboBoxTorneios.setBounds(129, 40, 143, 22);
		panel_1.add(comboBoxTorneios);
		
		JComboBox comboBoxDificuldade = new JComboBox();
		comboBoxDificuldade.setModel(new DefaultComboBoxModel(new String[] {"Fácil","Médio","Díficil"}));
		comboBoxDificuldade.setBounds(10, 40, 76, 22);
		panel_1.add(comboBoxDificuldade);
		
		JComboBox comboBoxVelocidade = new JComboBox();
		comboBoxVelocidade.setModel(new DefaultComboBoxModel(new String[] {"1","2","3"}));
		comboBoxVelocidade.setBounds(319, 40, 76, 22);
		panel_1.add(comboBoxVelocidade);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(42, 73, 341, 40);
		panel_1.add(textArea_1);
		
		JButton btnNewButton_5 = new JButton("Começar");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String campeonato=(String)comboBoxTorneios.getSelectedItem();
				try {
					if(genius.getQuantidadeJogadores()<2) throw new Exception();
					genius.setTorneioAtual(new Campeonato(textField_1.getText(),"Relatorio teste"));
					genius.addRelatorio(campeonato,textArea_1.getText());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(btnNewButton, "Voce precisa ter pelo menos dois jogadores cadastrados para jogar!");
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton_5.setBackground(Color.GREEN);
		btnNewButton_5.setBounds(144, 125, 89, 23);
		panel_1.add(btnNewButton_5);
		
		
		
		JPanel panelTorneio = new JPanel();
		tabbedPane.addTab("Cadastrar Torneio", null, panelTorneio, null);
		panelTorneio.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Titulo:");
		panelTorneio.setBounds(34, 38, 82, 14);
		panelTorneio.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(86, 35, 139, 20);
		panelTorneio.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton_2 = new JButton("Adicionar");
		btnNewButton_2.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			try {
				if(textField_1.getText().isBlank())throw new Exception();
				genius.adicionarCampeonato(new Campeonato(textField_1.getText(),""));
				cmbTipo.addItem(textField_1.getText());
				comboBoxTorneios.addItem(textField_1.getText());
				JOptionPane.showMessageDialog(btnNewButton, "Torneio cadastrado com sucesso!");
			}
			catch (Exception e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(btnNewButton, "Todos os campos são obrigatorios ou esse torneio já existe!");
			}
		}
	});
		btnNewButton_2.setBounds(86, 93, 82, 23);
		panelTorneio.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Teste");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					System.out.println(genius.getTorneioAtual().getTitulo());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_3.setBounds(239, 93, 89, 23);
		panelTorneio.add(btnNewButton_3);
		
		
		
		

		
	
		
	}
}
