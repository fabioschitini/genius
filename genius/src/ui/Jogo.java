package ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.Timer;

import modelo.Campeonato;
import modelo.Jogador;
import negocio.Genius;

public class Jogo {
	
	 	@SuppressWarnings("unused")
		private static final long serialVersionUID = 1L;
	    private JLabel speedLabel;
	    private JLabel diffLabel;
	    private List<JButton> buttons;
	    private List<Integer> sequence;
	    private int sequenceIndex;
	    private int currentSequence;
	    private int currentIndex;
	    private int difficulty = 1;
	    private int speed = 1;
	    private boolean isPlayingSequence;
	    private ArrayList<Jogador> jogadores=new ArrayList<>();
	    private ArrayList<Jogador> jogadoresEmpatados= new ArrayList<>();;
		private JTextArea textArea_2 = new JTextArea();
		private String miniRelatorio="";
		private long start;
		private long finish;
		boolean repetiu=false;
		int indiceJogadoresEmpatados=0;
	    private int indiceJogador=0;
	    private File beepSoundFile;
	    private Clip beepSoundClip;
	    
	    public Jogo() {
	    	 buttons = new ArrayList<>();
	         beepSoundFile = new File("beep.wav");
	         System.out.println(beepSoundFile.getAbsolutePath());
	         loadBeepSound();
	    }
	    
	    public void startMenu(JPanel contentPane,String[] arrayCampeonatos,Genius genius,@SuppressWarnings("rawtypes") JComboBox comboBoxTorneios) {
	    	//Iniciando a tab jogar
	    	
	        contentPane.removeAll();
	        contentPane.revalidate();
	        contentPane.repaint();
	        
	       JLabel lblNewLabel_3 = new JLabel("Torneio:");
			lblNewLabel_3.setBounds(149, 11, 46, 14);
			contentPane.add(lblNewLabel_3);
			comboBoxTorneios.setBounds(149, 40, 143, 30);
			contentPane.add(comboBoxTorneios);
			
	        speedLabel = new JLabel("Velocidade: " + speed);
	        speedLabel.setBounds(319, 11, 76, 14);
	        diffLabel = new JLabel("Dificuldade: " + difficulty);
	        diffLabel.setBounds(10, 11, 76, 14);
			
		    createActionButton("Dificuldade++", 10, 40, 120, 30, 1, "diffPlus",contentPane);
		    createActionButton("Velocidade++", 319, 40, 120, 30, 50, "speedPlus",contentPane);
	        contentPane.add(speedLabel);
	        contentPane.add(diffLabel);
			//Funcao com botao de comecar o jogo
			JButton startButton = new JButton("Começar");
		    startButton.setBackground(Color.GREEN);
		    startButton.setForeground(Color.WHITE);
			startButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String campeonato=(String)comboBoxTorneios.getSelectedItem();
					if(campeonato!=null) {
						try {
							if(genius.getQuantidadeJogadores()<2)JOptionPane.showMessageDialog(startButton, "Voce precisa ter pelo menos dois jogadores cadastrados para jogar!");
							else {
								genius.setTorneioAtual(new Campeonato(campeonato,"Relatorio teste"));
								genius.getTorneioAtual().setJogadores((ArrayList<Jogador>) genius.getJogadores());
								jogadores=genius.getTorneioAtual().getJogadores();
								indiceJogador=0;
								resetGame(contentPane,arrayCampeonatos,genius,comboBoxTorneios);
								startGame(contentPane,arrayCampeonatos,genius,comboBoxTorneios);
								playSequence(contentPane,arrayCampeonatos,genius,comboBoxTorneios);	
						}}
						catch (Exception e1) {
							e1.printStackTrace();
							}	
					}
					else JOptionPane.showMessageDialog(startButton, "Nao existe torneios cadastrados!");
				}
			});
			startButton.setBackground(Color.GREEN);
			startButton.setBounds(80, 125, 300, 150);
			contentPane.add(startButton);
	    }
  
	    private void startGame(JPanel contentPane,String[] arrayCampeonatos,Genius genius,@SuppressWarnings("rawtypes") JComboBox comboBoxTorneios) {
	       //Apagar a tab de jogar e coloca os botes do jogo em seu lugar,alem de iniciar as variaveis usadas no jogo
	    	contentPane.removeAll();
	        contentPane.revalidate();
	        contentPane.repaint();
	        buttons = new ArrayList<>();

	        createButton("RED", Color.RED, 27, 22, 182, 115,contentPane,arrayCampeonatos,genius,comboBoxTorneios);
	        createButton("BLUE", Color.BLUE, 241, 22, 182, 115,contentPane,arrayCampeonatos,genius,comboBoxTorneios);
	        createButton("GREEN", Color.GREEN, 27, 149, 182, 115,contentPane,arrayCampeonatos,genius,comboBoxTorneios);
	        createButton("YELLOW", Color.YELLOW, 241, 149, 182, 115,contentPane,arrayCampeonatos,genius,comboBoxTorneios);
	        
		
			textArea_2.setBounds(30, 300, 341, 80);
			contentPane.add(textArea_2);
			if(repetiu) textArea_2.setText("Vez de "+jogadoresEmpatados.get(indiceJogadoresEmpatados).getNome()+"\n"+miniRelatorio);
			else textArea_2.setText("Vez de "+jogadores.get(indiceJogador).getNome()+"\n"+miniRelatorio);
			
	        sequence = new ArrayList<>();
	        sequenceIndex = 0;
	        currentSequence = 0;
	        
	        generateNextSequenceItem(contentPane,arrayCampeonatos,genius,comboBoxTorneios);
	    }
	    
	    private void createButton(String name, Color color, int x, int y, int width, int height,JPanel contentPane,String[] arrayCampeonatos,Genius genius,@SuppressWarnings("rawtypes") JComboBox comboBoxTorneios) {
	        JButton button = new JButton("");
	        button.setName(name);
	        button.setBackground(color);
	        button.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                checkButton(button,contentPane,arrayCampeonatos,genius,comboBoxTorneios);
	            }
	        });
	        button.setBounds(x, y, width, height);
	        contentPane.add(button);
	        buttons.add(button);
	    }

	    private void resetGame(JPanel contentPane,String[] arrayCampeonatos,Genius genius,@SuppressWarnings("rawtypes") JComboBox comboBoxTorneios) {
	        sequence = null;
	        sequenceIndex = 0;
	        currentSequence = 0;
	        currentIndex = 0;
	        isPlayingSequence = false;
	    }

	    private void generateNextSequenceItem(JPanel contentPane,String[] arrayCampeonatos,Genius genius,@SuppressWarnings("rawtypes") JComboBox comboBoxTorneios) {
	        Random random = new Random();
	        int nextColor;
	        
	        for (int k = 0; k < difficulty; k++) {
	            nextColor = random.nextInt(4);
	            sequence.add(nextColor);
	        }
	        for (int i = 0; i <= currentSequence+difficulty-1; i++) {
	            System.out.print(" " + getColorName(sequence.get(i),contentPane,arrayCampeonatos,genius,comboBoxTorneios));
	        }
	        System.out.println();
	    }

	    private void playSequence(JPanel contentPane,String[] arrayCampeonatos,Genius genius,@SuppressWarnings("rawtypes") JComboBox comboBoxTorneios) {
	        currentIndex = 0;
	        isPlayingSequence = true;
	        flashNextButton(contentPane,arrayCampeonatos,genius,comboBoxTorneios);
	    }

	    private void flashNextButton(JPanel contentPane,String[] arrayCampeonatos,Genius genius,@SuppressWarnings("rawtypes") JComboBox comboBoxTorneios) {
	    	System.out.println("FlashNextButton \n");
	        if (currentIndex < sequence.size()) {
	            int buttonIndex = sequence.get(currentIndex);
	            JButton button = buttons.get(buttonIndex);
	            flashButton(button,contentPane,arrayCampeonatos,genius,comboBoxTorneios);
	            currentIndex++;
	        } else {
	            isPlayingSequence = false;
	        }
	    }

	    private void flashButton(JButton button,JPanel contentPane,String[] arrayCampeonatos,Genius genius,@SuppressWarnings("rawtypes") JComboBox comboBoxTorneios) {
	    	System.out.println("FlashButton \n");
	        final Color originalColor = button.getBackground();
	        button.setBackground(Color.WHITE);
	        playBeepSound();

	        Timer timer = new Timer(500 / speed, new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                button.setBackground(originalColor);
	                ((Timer) e.getSource()).stop();
	                if (currentIndex < sequence.size()) {
	                    int nextButtonIndex = sequence.get(currentIndex);
	                    if (nextButtonIndex == sequence.get(currentIndex - 1)) {
	                        Timer delayTimer = new Timer(50, new ActionListener() {
	                            public void actionPerformed(ActionEvent e) {
	                                flashNextButton(contentPane,arrayCampeonatos,genius,comboBoxTorneios);
	                            }
	                        });
	                        delayTimer.setRepeats(false);
	                        delayTimer.start();
	                    } else {
	                        flashNextButton(contentPane,arrayCampeonatos,genius,comboBoxTorneios);
	                    }
	                } else {
	                    isPlayingSequence = false;
	    		        System.out.println("Comecando o timer \n");
	    		        start = System.currentTimeMillis();
	                }
	            }
	        });
	        timer.setRepeats(false);
	        timer.start();
	    }

	    private void checkButton(JButton button,JPanel contentPane,String[] arrayCampeonatos,Genius genius,@SuppressWarnings("rawtypes") JComboBox comboBoxTorneios) {
	        if (isPlayingSequence) {
	            return;
	        }
	        //Checa o botão e ve acertou a sequencia ou nao
	        int buttonIndex = buttons.indexOf(button);
	        //acertou
	        if (buttonIndex == sequence.get(sequenceIndex)) {
	            sequenceIndex++;
	            System.out.println("Check button \n");
	            if (sequenceIndex > currentSequence+difficulty-1) {
		            System.out.println("Acertei \n");
	    		        if(repetiu) addTempoJogada(jogadoresEmpatados,indiceJogadoresEmpatados);
	    		        else addTempoJogada(jogadores,indiceJogador);
	                    currentSequence += difficulty;
	                    generateNextSequenceItem(contentPane,arrayCampeonatos,genius,comboBoxTorneios);
	                    sequenceIndex = 0;
	                    playSequence(contentPane,arrayCampeonatos,genius,comboBoxTorneios);
	            }
	        }
	        //errou
	        	else {
		            System.out.println("Errei \n");
	        		//verifica se esse é o ultimo jogador e faz as mudancas necessarias
	        		if(repetiu) {
	        			fimDoTurno(contentPane,jogadoresEmpatados, indiceJogadoresEmpatados);
	        		}
	        		else{
	        			fimDoTurno(contentPane,jogadores, indiceJogador);
	        		}
	        		
	        		fimDaRodada( contentPane,arrayCampeonatos, genius, comboBoxTorneios);
	        }
	    }

	    private String getColorName(int colorIndex,JPanel contentPane,String[] arrayCampeonatos,Genius genius,@SuppressWarnings("rawtypes") JComboBox comboBoxTorneios) {
	        switch (colorIndex) {
	            case 0:
	                return "RED";
	            case 1:
	                return "BLUE";
	            case 2:
	                return "GREEN";
	            case 3:
	                return "YELLOW";
	            default:
	                return "";
	        }
	    }

	    private Jogador acharVencedor() {
	    	int maior=0;
	    	for (Jogador jogador : jogadores) {
	    		if(jogador.getPontos()>maior)maior=jogador.getPontos();
	    		//for(Float tempo: jogador.getTempos())System.out.println(tempo);
	    		//System.out.println()
	    	}
	    	for (Jogador jogador : jogadores) {
	    		if(jogador.getPontos()==maior) return jogador;
	    	}
	    	
	    	return null;
	    }
	    
	    private boolean houveEmpate(ArrayList<Jogador> jogadores) {
	    	int maior=0;
	    	int indice = 0;
	    	clonarJogadores();
	    	indiceJogadoresEmpatados=0; 
	    	jogadoresEmpatados.clear();
	    	for(Jogador jogador : jogadores) {
	    		if(jogador.getPontos()>maior)maior=jogador.getPontos();
	    	}
	    	for(Jogador jogador : jogadores) {
	    		if(jogador.getPontos()==maior) {
					//System.out.println("O aumento do indice de repeticao,so deveria ter um por agora \n");
	    			indice++;
	    			jogadoresEmpatados.add(jogador);
	    		}
	    	}
	    	if(indice>1) return true;
	    	return false;
	    	
	    }
	    
	    private void clonarJogadores() {
	    	for(Jogador jogador : jogadores) {
		    	for(Jogador jogadorEmpatado : jogadoresEmpatados) {
		    		if(jogador.getNome()==jogadorEmpatado.getNome()) {
		    			System.out.println("\n Quantas vezes isso ocorre?  "+jogadorEmpatado.getPontos());
			    		jogador.setPontos(jogadorEmpatado.getPontos());
			    		jogador.setTempos(jogadorEmpatado.getTempos());
		    			}
		    		}
		    		
		    	}	
	    }
	    
	    private String getJogadoresEmpatados() {
	    	String jogadores="";
	    	int indice=1;
	    	for(Jogador jogador : jogadoresEmpatados) {
	    		if(jogadoresEmpatados.size()==indice)jogadores+=jogador.getNome();
	    		else {
			    	jogadores+=jogador.getNome()+",";
		    		indice++;	
	    		}
		    	}	
	    	return jogadores;
	    }
	    
	    private int getPontosJogadoresEmpatados() {
	    	return jogadoresEmpatados.get(0).getPontos();
	    }
	    
	    private String gerarRelatorio(Genius genius) throws Exception  {
	    	String parteCampeonato="Título do campeonato: "+genius.getTorneioAtual().getTitulo()+
	    			",  Data do Campeonato "+genius.getTorneioAtual().getData();
	    	String parteJogadores="";
	    	float tempoTotal=0;
	    	float melhorTempo=0;
	    	for (Jogador jogador : jogadores) {
	    		tempoTotal=jogador.getTempos().stream().reduce((x, y) -> x + y).get();
	    		melhorTempo= Collections.min(jogador.getTempos());
	    		parteJogadores+="Nome: "+jogador.getNome()+", Apelido: "+gerarApelido(jogador)+", Total de Pontos:"+jogador.getPontos()+
	    				"\n Tempo Total das Jogadas:"+tempoTotal+"s, Jogada Mais Rápida:"+melhorTempo+"s \n\n";
	    	}
	    	return parteCampeonato+"\n\n"+parteJogadores;
	    }
	    
	    private String gerarApelido(Jogador jogador) {
	    	String apelido;
	    	apelido=""+jogador.getNome().charAt(0)+jogador.getNome().charAt(1)+jogador.getNome().charAt(2);
	    	return apelido.toUpperCase();
	    }
	    
	    private void addTempoJogada(ArrayList<Jogador> jogadores,int indiceJogador) {
	    	finish = System.currentTimeMillis();
	        float timeElapsed = (float)(finish - start)/1000;
	        System.out.println("\n Tempo da jogada=="+timeElapsed);
	       jogadores.get(indiceJogador).addTempo(timeElapsed);
	    }
	    
	    private void fimDoTurno(JPanel contentPane,ArrayList<Jogador> jogadores,int indiceJogador) {
	    	//adiciona o tempo da jogada,mostra mensagem que perdeu e adicion info para o mini relatorio
    		addTempoJogada(jogadores,indiceJogador);
    		jogadores.get(indiceJogador).addPontos(currentSequence/difficulty);
    		miniRelatorio+="Pontos "+jogadores.get(indiceJogador).getPontos()+" de "+jogadores.get(indiceJogador).getNome()+"\n";
    		JOptionPane.showMessageDialog(contentPane, "Botão errado! Fim de jogo pra voce "+jogadores.get(indiceJogador)
    		.getNome()+". Sua pontuação da rodada é "+currentSequence/difficulty +"\n e a total é "+jogadores
    		.get(indiceJogador).getPontos(), "Simon Says", JOptionPane.INFORMATION_MESSAGE);
	    }
	    
	    private void fimDaRodada(JPanel contentPane,String[] arrayCampeonatos,Genius genius,@SuppressWarnings("rawtypes") JComboBox comboBoxTorneios) {
	    	
            try {
				if(genius.getQuantidadeJogadores()-1>indiceJogador) {
					 indiceJogador++;
					 resetGame(contentPane,arrayCampeonatos,genius,comboBoxTorneios);
		             startGame(contentPane,arrayCampeonatos,genius,comboBoxTorneios);
		             playSequence(contentPane,arrayCampeonatos,genius,comboBoxTorneios);	
				}
		    	//System.out.println(jogadoresEmpatados.size()+"Devia ser o tamanho do jogadoresEmpatados serio");
				else if(jogadoresEmpatados.size()-1>indiceJogadoresEmpatados) {
			    	indiceJogadoresEmpatados++;
					resetGame(contentPane,arrayCampeonatos,genius,comboBoxTorneios);
		            startGame(contentPane,arrayCampeonatos,genius,comboBoxTorneios);
		            playSequence(contentPane,arrayCampeonatos,genius,comboBoxTorneios);
				}
				else {
					//Ve se houve empate,se houve reinicia o jogo com os jogadores empatados,se nao terminar o jogo e gera o relatorio
					//se houve reinicia o jogo com os jogadores empatados
					if(houveEmpate(jogadores)) {
						repetiu=true;
						JOptionPane.showMessageDialog(contentPane, "Houve um empate entre os jogadores "+getJogadoresEmpatados()+"  todos estao com "
						+getPontosJogadoresEmpatados()+"\n"
								+ " pontos ao total,logo,esses jogadores irão jogar rodadas ate desempatarem"
				   				 ,"Simon Says", JOptionPane.INFORMATION_MESSAGE);
						miniRelatorio="";
						resetGame(contentPane,arrayCampeonatos,genius,comboBoxTorneios);
			            startGame(contentPane,arrayCampeonatos,genius,comboBoxTorneios);
			            playSequence(contentPane,arrayCampeonatos,genius,comboBoxTorneios);
						
					}
					//se nao terminar o jogo e gera o relatorio
					else {
						jogadoresEmpatados.clear();
						miniRelatorio="";
						repetiu=false;
						genius.addRelatorio(genius.getTorneioAtual().getTitulo(),gerarRelatorio(genius));
						JOptionPane.showMessageDialog(contentPane, "Fim de jogo. O vencedor é "+acharVencedor()
						.getNome()+" com "+acharVencedor().getPontos()+" pontos"
			   				 ,"Simon Says", JOptionPane.INFORMATION_MESSAGE);
			            gerarRelatorio(genius);
			            resetGame(contentPane,arrayCampeonatos,genius,comboBoxTorneios);
			            startMenu(contentPane,arrayCampeonatos,genius,comboBoxTorneios);
					} 
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Fudeu no try cathc");
			}
	    }
	    
	    private void loadBeepSound() {
	        try {
	            beepSoundClip = AudioSystem.getClip();
	            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(beepSoundFile);
	            beepSoundClip.open(audioInputStream);

	            FloatControl gainControl = (FloatControl) beepSoundClip.getControl(FloatControl.Type.MASTER_GAIN);
	            gainControl.setValue(-20.0f);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    private void playBeepSound() {
	        try {
	            Clip clip = AudioSystem.getClip();
	            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(beepSoundFile);
	            clip.open(audioInputStream);
	            clip.start();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    private void createActionButton(String label, int x, int y, int width, int height, int changeValue, String buttonType,JPanel contentPane) {
	        JButton button = new JButton(label);
	        switch (buttonType) {
	            case "diffPlus":
	                button.addActionListener(new ActionListener() {
	                    public void actionPerformed(ActionEvent e) {
	                        if (difficulty == 3)difficulty=1;
	                        else difficulty++;
	                        diffLabel.setText("Dificuldade: " + difficulty);
	                    }
	                });
	                break; 
	            case "speedPlus":
	                button.addActionListener(new ActionListener() {
	                    public void actionPerformed(ActionEvent e) {
	                        if (speed == 3)speed=1;
	                        else speed++;
	                        speedLabel.setText("Velocidade: " + speed);
	                    }
	                });
	                break;
	        }
	        button.setBounds(x, y, width, height);
	        contentPane.add(button);
	    }   
}
