package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import negocio.Genius;
import java.sql.SQLException;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.util.ArrayList;
import javax.swing.JComboBox;

public class Janela {

	private JFrame frame;
    private Genius genius = new Genius();
    private Jogo jogo=new Jogo();
    private CadastrarJogadorTab cadastrarJogador=new CadastrarJogadorTab();
    private RelatoriosTab relatorios=new  RelatoriosTab();
    private CadastrarTorneioTab cadastrarTorneio=new CadastrarTorneioTab();
   
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

	private void initBD() {
		try {
			genius.iniciarBD();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private ArrayList<String> getCampeonatosBD() {
		try {
			return genius.getCampeonatos();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	private void initialize() {
		
		initBD();

		ArrayList<String> campeonatos=new ArrayList<>();
		campeonatos=getCampeonatosBD();
		String[] arrayCampeonatos = campeonatos.toArray(new String[campeonatos.size()]);
		JComboBox comboBoxTorneios = new JComboBox(arrayCampeonatos);
		JComboBox comboBoxTorneiosRelatorios = new JComboBox(arrayCampeonatos);
		
		frame = new JFrame();
		frame.setBounds(100, 100, 531, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		//Tab de cadastrar jogadores
		JPanel panelCadastroJogador = new JPanel();
		tabbedPane.addTab("Cadastro de Jogadores", null, panelCadastroJogador, null);
		panelCadastroJogador.setLayout(null);
		cadastrarJogador.menu(panelCadastroJogador);
		//Tab de cadastrar torneios
		JPanel panelTorneio = new JPanel();
		tabbedPane.addTab("Cadastrar Torneio", null, panelTorneio, null);
		panelTorneio.setLayout(null);
		cadastrarTorneio.menu(panelTorneio, comboBoxTorneiosRelatorios, comboBoxTorneios);
		//tab de jogar		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Jogar", null, panel_1, null);
		panel_1.setLayout(null);
		jogo.startMenu(panel_1,arrayCampeonatos,genius,comboBoxTorneios);
		//Tab de pegar os relatorios
		JPanel panelRelatorios = new JPanel();
		tabbedPane.addTab("Relatorios", null, panelRelatorios, null);
		panelRelatorios.setLayout(null);
		relatorios.menu(panelRelatorios, comboBoxTorneiosRelatorios);
	}
}
