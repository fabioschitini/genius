package ui;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import modelo.Jogador;
import negocio.Genius;

public class RelatoriosTab {
    private Genius genius = new Genius();
	private JTextField txtNomeJogador;

	public void menu(JPanel panelRelatorios,JComboBox comboBoxTorneiosRelatorios) {
		JLabel lblNewLabel_2 = new JLabel("Torneios :");
		lblNewLabel_2.setBounds(31, 26, 75, 14);
		panelRelatorios.add(lblNewLabel_2);
		
		comboBoxTorneiosRelatorios.setBounds(101, 22, 280, 22);
		panelRelatorios.add(comboBoxTorneiosRelatorios);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(31, 129, 388, 159);
		panelRelatorios.add(textArea);
		
		JButton btnNewButton_4 = new JButton("Pesquisar");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String campeonato=(String)comboBoxTorneiosRelatorios.getSelectedItem();
				System.out.println(campeonato+"quando nao existe torneios registrados");
				Component btnNewButton = null;
				if(campeonato!=null) {
					try {
						if(genius.retornarRelatorio(campeonato).isBlank()) throw new SQLException ();
						textArea.setText(""+genius.retornarRelatorio(campeonato));
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(btnNewButton, "Relatorio esta vázio,va jogar esse torneio para preenche-ló!");
						//e1.printStackTrace();
						System.out.println("Relatorio esta vazio exception");
					}
				}
				else JOptionPane.showMessageDialog(btnNewButton, "Nao existe torneios cadastrados!");
			}
			
		});
		btnNewButton_4.setBounds(101, 70, 280, 22);
		panelRelatorios.add(btnNewButton_4);
		
	}
	
}
