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

import modelo.Campeonato;
import modelo.Jogador;
import negocio.Genius;

public class CadastrarTorneioTab {
    private Genius genius = new Genius();
	private JTextField txtTituloTorneio;

	public void menu(JPanel panelTorneio,JComboBox comboBoxTorneiosRelatorios,JComboBox comboBoxTorneios) {
		JLabel lblNewLabel_1 = new JLabel("Titulo:");
		panelTorneio.setBounds(34, 38, 82, 14);
		panelTorneio.add(lblNewLabel_1);
		
		txtTituloTorneio = new JTextField();
		txtTituloTorneio.setBounds(86, 35, 301, 23);
		panelTorneio.add(txtTituloTorneio);
		txtTituloTorneio.setColumns(10);
		
		JButton btnAdd = new JButton("Adicionar");
		btnAdd.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			Component btnNewButton = null;
			if(txtTituloTorneio.getText().isBlank())JOptionPane.showMessageDialog(btnAdd, "O titulo é obrigatório");
			else {
			try {
				genius.adicionarCampeonato(new Campeonato(txtTituloTorneio.getText(),""));
				comboBoxTorneiosRelatorios.addItem(txtTituloTorneio.getText());
				comboBoxTorneios.addItem(txtTituloTorneio.getText());
				JOptionPane.showMessageDialog(btnNewButton, "Torneio cadastrado com sucesso!");
				}
			catch (Exception e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(btnNewButton, "Já existe um torneio com esse nome, cadastre outro!");
				}
			}
		}
	});
		
		btnAdd.setBounds(86, 92, 301, 55);
		panelTorneio.add(btnAdd);
		
		JLabel lblNewLabel_3 = new JLabel("Titulo:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_3.setBounds(10, 38, 46, 14);
		panelTorneio.add(lblNewLabel_3);
		
		JButton btnDeletar = new JButton("Deletar todos os torneios");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String option=JOptionPane.showInputDialog("Digite SIM para deletar todos os torneios");
				if(option.equalsIgnoreCase("SIM")) {
					try {
						genius.deletarTorneios();
						comboBoxTorneiosRelatorios.removeAllItems();
						comboBoxTorneios.removeAllItems();
						JOptionPane.showMessageDialog(btnAdd, "Torneios Apagados!");
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnDeletar.setBounds(86, 151, 301, 55);
		panelTorneio.add(btnDeletar);
	
}
	}
