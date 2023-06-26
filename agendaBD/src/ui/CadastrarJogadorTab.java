package ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import modelo.Jogador;
import negocio.Genius;

public class CadastrarJogadorTab {
    private Genius genius = new Genius();
	private JTextField txtNomeJogador;

	public void menu(JPanel panelCadastroJogador) {
		JLabel lblNewLabel = new JLabel("Nome Jogador: ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(21, 36, 116, 14);
		panelCadastroJogador.add(lblNewLabel);
		
		txtNomeJogador = new JTextField();
		txtNomeJogador.setBounds(154, 33, 294, 23);
		panelCadastroJogador.add(txtNomeJogador);
		txtNomeJogador.setColumns(10);
		
		JButton btnAdd = new JButton("ADD");
		btnAdd.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(txtNomeJogador.getText().isBlank()) JOptionPane.showMessageDialog(btnAdd, "O nome é obrigatório");
			else {try {
				genius.adicionarJogador(new Jogador(txtNomeJogador.getText()));
				JOptionPane.showMessageDialog(btnAdd, "Jogador cadastrado com sucesso!");
				} 
			catch(Exception e1) {
				JOptionPane.showMessageDialog(btnAdd, "Já existe um jogador com esse nome,coloque outro!");
				}
			}
		}
	});
		btnAdd.setBounds(154, 81, 294, 59);
		panelCadastroJogador.add(btnAdd);
		
		JButton btnDeletar = new JButton("Deletar Jogadores");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String option=JOptionPane.showInputDialog("Digite SIM para deletar seus jogadores. TODOS OS DADOS SERÃO APAGADOS!");
				if(option.equalsIgnoreCase("SIM")) {
					try {
						genius.deletarJogadores();;
						JOptionPane.showMessageDialog(btnAdd, "Jogadores Apagados!");
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnDeletar.setBounds(154, 151, 294, 68);
		panelCadastroJogador.add(btnDeletar);
		
	}
	
}
