package ui;

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

public class JanelaGenius {

	private JFrame frame;
	private JTextField txtNome;
	private JTextField txtNumero;
    private Genius genius = new Genius();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JanelaGenius window = new JanelaGenius();
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
	public JanelaGenius() {
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
				System.out.println(genius.getCurrentJogId());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	//	try {
	//		genius.adicionarJogador(new Jogador("Exemplo"),2);
	//	} catch (Exception e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		//}

		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("NOME:");
		lblNewLabel.setBounds(31, 43, 46, 14);
		frame.getContentPane().add(lblNewLabel);
			
		txtNome = new JTextField();
		txtNome.setBounds(87, 40, 282, 20);
		frame.getContentPane().add(txtNome);
		txtNome.setColumns(10);
		
		
		/*	JButton btnNewButton = new JButton("ADD");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					genius.addJogador(new Jogador(txtNome.getText()));
					JOptionPane.showMessageDialog(btnNewButton, "Contato cadasttrado com sucesso!");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
					JOptionPane.showMessageDialog(btnNewButton, "Todos os campos são obrigatorios!");
				}
			}
		});
		btnNewButton.setBounds(31, 171, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
			JButton btnBuscar = new JButton("BUSCAR");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nomeBusca=JOptionPane.showInputDialog("Digite a string de busca:");
				try {
					Contato contato=agenda.buscarContato(nomeBusca);
					txtNome.setText(contato.getNome());
					txtNumero.setText(contato.getTelefone());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(btnNewButton, "Nenhum contato encontrado!");
					e1.printStackTrace();
				}
			}
		});
		btnBuscar.setBounds(280, 171, 89, 23);
		frame.getContentPane().add(btnBuscar);
		
	/*	JButton btnNewButton_1 = new JButton("RESET AGENDA");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String option=JOptionPane.showInputDialog("Digite SIM para resetar sua agenda. TODOS OS DADOS SERÃO APAGADOS!");
				if(option.equalsIgnoreCase("SIM")) {
					try {
						agenda.resetAgenda();
						JOptionPane.showMessageDialog(btnNewButton, "AGENDA RESETADA!");
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnNewButton_1.setBounds(129, 227, 168, 23);
		frame.getContentPane().add(btnNewButton_1); */
	}
}




