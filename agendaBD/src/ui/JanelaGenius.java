package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import modelo.Jogador;
import negocio.Genius;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JCheckBox;

public class Janela {

	private JFrame frame;
	private JTextField txtNomeJogador;
    private Genius genius = new Genius();
    private JTextField textField;
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
		try {
			System.out.println("\nTotal de jogadores:"+genius.getCurrentJogId()+"\n");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panelCadastroJogador = new JPanel();
		tabbedPane.addTab("Cadastro", null, panelCadastroJogador, null);
		panelCadastroJogador.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nome Pesquisador: ");
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
				genius.adicionarJogador(new Jogador(txtNomeJogador.getText(),genius.getCurrentJogId()+1),genius.getCurrentJogId()+1);
				JOptionPane.showMessageDialog(btnNewButton, "Contato cadasttrado com sucesso!");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				//e1.printStackTrace();
				JOptionPane.showMessageDialog(btnNewButton, "Todos os campos são obrigatorios!");
			}
		}
	});
		btnNewButton.setBounds(154, 199, 161, 23);
		panelCadastroJogador.add(btnNewButton);
		
		JPanel panelCadastrarCampeonato = new JPanel();
		tabbedPane.addTab("Consultas", null, panelCadastrarCampeonato, null);
		panelCadastrarCampeonato.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Titulo:");
		lblNewLabel_2.setBounds(26, 38, 88, 14);
		panelCadastrarCampeonato.add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(100, 35, 177, 20);
		panelCadastrarCampeonato.add(textField);
		textField.setColumns(10);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Jogador1");
		chckbxNewCheckBox.setBounds(26, 90, 97, 23);
		panelCadastrarCampeonato.add(chckbxNewCheckBox);
		
	}
}
