package negocio;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
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
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import modelo.Campeonato;
import modelo.Jogador;

public class Jogo {
	
	 	private static final long serialVersionUID = 1L;
	    //private JPanel contentPane;
	    //private JLabel speedLabel;
	    //private JLabel diffLabel;
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
		private String relatorio;
		boolean repetiu=false;
		int indiceJogadoresEmpatados=0;
	    private int indiceJogador=0;
	    
	    public Jogo() {
	    	 buttons = new ArrayList<>();
	    }
	    
	    public void startMenu(JPanel contentPane,String[] arrayCampeonatos,Genius genius,JComboBox comboBoxTorneios) {
	    	//indiceJogador=1;
	    	jogadoresEmpatadosSize();
	        contentPane.removeAll();
	        contentPane.revalidate();
	        contentPane.repaint();
	        
	        JLabel lblNewLabel_3 = new JLabel("Torneio:");
			lblNewLabel_3.setBounds(129, 11, 46, 14);
			contentPane.add(lblNewLabel_3);
			
			JLabel lblNewLabel_4 = new JLabel("Dificuldade:");
			lblNewLabel_4.setBounds(10, 11, 76, 14);
			contentPane.add(lblNewLabel_4);
			
			JLabel lblNewLabel_5 = new JLabel("Velocidade:");
			lblNewLabel_5.setBounds(319, 11, 76, 14);
			contentPane.add(lblNewLabel_5);
			
			comboBoxTorneios.setBounds(129, 40, 143, 22);
			contentPane.add(comboBoxTorneios);
			
			JComboBox comboBoxDificuldade = new JComboBox();
			comboBoxDificuldade.setModel(new DefaultComboBoxModel(new String[] {"Fácil","Médio","Díficil"}));
			comboBoxDificuldade.setBounds(10, 40, 76, 22);
			contentPane.add(comboBoxDificuldade);
			
			JComboBox comboBoxVelocidade = new JComboBox();
			comboBoxVelocidade.setModel(new DefaultComboBoxModel(new String[] {"1","2","3"}));
			comboBoxVelocidade.setBounds(319, 40, 76, 22);
			contentPane.add(comboBoxVelocidade);
			
			JTextArea textArea_1 = new JTextArea();
			textArea_1.setBounds(42, 73, 341, 40);
			contentPane.add(textArea_1);
			
			 JButton startButton = new JButton("Começar");
		        startButton.setBackground(Color.GREEN);
		        startButton.setForeground(Color.WHITE);
			startButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String campeonato=(String)comboBoxTorneios.getSelectedItem();
					try {
						if(genius.getQuantidadeJogadores()<2) throw new Exception();
						genius.setTorneioAtual(new Campeonato(campeonato,"Relatorio teste"));
						genius.getTorneioAtual().setJogadores((ArrayList<Jogador>) genius.getJogadores());
						jogadores=genius.getTorneioAtual().getJogadores();
						indiceJogador=0;
			                resetGame(contentPane,arrayCampeonatos,genius,comboBoxTorneios);
			                startGame(contentPane,arrayCampeonatos,genius,comboBoxTorneios);
			                playSequence(contentPane,arrayCampeonatos,genius,comboBoxTorneios);	
						//genius.addRelatorio(campeonato,textArea_1.getText());
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(startButton, "Voce precisa ter pelo menos dois jogadores cadastrados para jogar!");
						e1.printStackTrace();
					}
					
				}
			});
			startButton.setBackground(Color.GREEN);
			startButton.setBounds(144, 125, 89, 23);
			contentPane.add(startButton);
	    }
  
	    private void startGame(JPanel contentPane,String[] arrayCampeonatos,Genius genius,JComboBox comboBoxTorneios) {
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
	    
	    private void createButton(String name, Color color, int x, int y, int width, int height,JPanel contentPane,String[] arrayCampeonatos,Genius genius,JComboBox comboBoxTorneios) {
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

	    private void resetGame(JPanel contentPane,String[] arrayCampeonatos,Genius genius,JComboBox comboBoxTorneios) {
	        sequence = null;
	        sequenceIndex = 0;
	        currentSequence = 0;
	        currentIndex = 0;
	        isPlayingSequence = false;
	    }

	    private void generateNextSequenceItem(JPanel contentPane,String[] arrayCampeonatos,Genius genius,JComboBox comboBoxTorneios) {
	        Random random = new Random();
	        int nextColor;
	        
	        for (int k = 0; k < difficulty; k++) {
	            nextColor = random.nextInt(4);
	            sequence.add(nextColor);
	        }
	        
	        System.out.print("Sequence:");
	        for (int i = 0; i <= currentSequence; i++) {
	            System.out.print(" " + getColorName(sequence.get(i),contentPane,arrayCampeonatos,genius,comboBoxTorneios));
	        }
	        System.out.println();
	    }

	    private void playSequence(JPanel contentPane,String[] arrayCampeonatos,Genius genius,JComboBox comboBoxTorneios) {
	        currentIndex = 0;
	        //System.out.println("Começando a bipar \n");
	        isPlayingSequence = true;
	        flashNextButton(contentPane,arrayCampeonatos,genius,comboBoxTorneios);
	    }

	    private void flashNextButton(JPanel contentPane,String[] arrayCampeonatos,Genius genius,JComboBox comboBoxTorneios) {
	        if (currentIndex < sequence.size()) {
	            int buttonIndex = sequence.get(currentIndex);
	            JButton button = buttons.get(buttonIndex);
	            flashButton(button,contentPane,arrayCampeonatos,genius,comboBoxTorneios);
	            currentIndex++;
	        } else {
	            isPlayingSequence = false;
		        //System.out.println("Terminando a bipar \n");
	        }
	    }

	    private void flashButton(JButton button,JPanel contentPane,String[] arrayCampeonatos,Genius genius,JComboBox comboBoxTorneios) {
	        final Color originalColor = button.getBackground();
	        button.setBackground(Color.WHITE);
	        //playBeepSound();

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

	    private void checkButton(JButton button,JPanel contentPane,String[] arrayCampeonatos,Genius genius,JComboBox comboBoxTorneios) {
	        if (isPlayingSequence) {
	            return;
	        }

	        int buttonIndex = buttons.indexOf(button);
	        if (buttonIndex == sequence.get(sequenceIndex)) {
	            sequenceIndex++;

	            if (sequenceIndex > currentSequence) {
	                if (currentSequence == 100 * difficulty) {
	                    JOptionPane.showMessageDialog(contentPane, "Você venceu!", "Simon Says", JOptionPane.INFORMATION_MESSAGE);
	                    startMenu(contentPane,arrayCampeonatos,genius,comboBoxTorneios);
	                } else {
	                	finish = System.currentTimeMillis();
	    		        System.out.println("Terminando o timer\n");
	    		        float timeElapsed = (float)(finish - start)/1000;
	    		        System.out.println("Tempo da jogada "+timeElapsed+"s\n");
	    		        if(repetiu) jogadoresEmpatados.get(indiceJogadoresEmpatados).addTempo(timeElapsed);
	    		        else jogadores.get(indiceJogador).addTempo(timeElapsed);
	    		        jogadores.get(indiceJogador).addTempo(timeElapsed);
	                    currentSequence += difficulty;
	                    generateNextSequenceItem(contentPane,arrayCampeonatos,genius,comboBoxTorneios);
	                    sequenceIndex = 0;
	                    playSequence(contentPane,arrayCampeonatos,genius,comboBoxTorneios);
	                }
	            }
	        } else {
	        	
	        		if(repetiu) {
	        			jogadoresEmpatados.get(indiceJogadoresEmpatados).setPontos(currentSequence);
	        			JOptionPane.showMessageDialog(contentPane, "Botão errado! Fim da rodada pra voce  "+jogadoresEmpatados.get(indiceJogadoresEmpatados).getNome()+"Sua pontuação da rodada é  "
					 			+currentSequence +"\n e a total é:"+jogadoresEmpatados.get(indiceJogadoresEmpatados).getPontos(), "Simon Says", JOptionPane.INFORMATION_MESSAGE);	
	        		}
	        		
	        		else{ 
	        			jogadores.get(indiceJogador).setPontos(currentSequence);
	        			miniRelatorio+=jogadores.get(indiceJogador).getNome()+" Pontos"+jogadores.get(indiceJogador).getPontos()+"\n";
	        			JOptionPane.showMessageDialog(contentPane, "Botão errado! Fim de jogo pra voce  "+jogadores.get(indiceJogador).getNome()+"Sua pontuação da rodada é  "
				 			+currentSequence +"\n e a total é:"+jogadores.get(indiceJogador).getPontos(), "Simon Says", JOptionPane.INFORMATION_MESSAGE);
	        		}
	            try {
					if(genius.getQuantidadeJogadores()-1>indiceJogador) {
						 indiceJogador++;
						 resetGame(contentPane,arrayCampeonatos,genius,comboBoxTorneios);
			             startGame(contentPane,arrayCampeonatos,genius,comboBoxTorneios);
			             playSequence(contentPane,arrayCampeonatos,genius,comboBoxTorneios);	
					}
			    	//System.out.println(jogadoresEmpatados.size()+"Devia ser o tamanho do jogadoresEmpatados serio");
					else if(jogadoresEmpatados.size()-1>indiceJogadoresEmpatados) {
				    	System.out.println(jogadoresEmpatados.size()+"Devia ser o tamanho do jogadoresEmpatados lol");
				    	indiceJogadoresEmpatados++;
						resetGame(contentPane,arrayCampeonatos,genius,comboBoxTorneios);
			            startGame(contentPane,arrayCampeonatos,genius,comboBoxTorneios);
			            playSequence(contentPane,arrayCampeonatos,genius,comboBoxTorneios);
					}
					else {
						
						if(houveEmpate(jogadores)) {
							repetiu=true;
							JOptionPane.showMessageDialog(contentPane, "Houve um empate entre os jogadores "+getJogadoresEmpatados()+"  todos estao com "+getPontosJogadoresEmpatados()+"/n"
									+ " pontos ao total,logo,esses jogadores irão jogar rodadas ate desempatarem"
					   				 ,"Simon Says", JOptionPane.INFORMATION_MESSAGE);
							miniRelatorio="";
							resetGame(contentPane,arrayCampeonatos,genius,comboBoxTorneios);
				            startGame(contentPane,arrayCampeonatos,genius,comboBoxTorneios);
				            playSequence(contentPane,arrayCampeonatos,genius,comboBoxTorneios);
							
						}
						//if(houveEmpate(jogadoresEmpatados)) {
							
						//}
						else {
							JOptionPane.showMessageDialog(contentPane, "Fim de jogo o vencedor é :"+acharVencedor().getNome()+" Pontuação :"+acharVencedor().getPontos()
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
	    }

	    private String getColorName(int colorIndex,JPanel contentPane,String[] arrayCampeonatos,Genius genius,JComboBox comboBoxTorneios) {
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
	    
	    private void gerarRelatorio(Genius genius) throws Exception  {
	    	String parteCampeonato="Título do campeonato: "+genius.getTorneioAtual().getTitulo()+
	    			"  Data do Campeonato"+genius.getTorneioAtual().getData();
	    	String parteJogadores=null;
	    	float tempoTotal=0;
	    	float melhorTempo=0;
	    	for (Jogador jogador : jogadores) {
	    		tempoTotal=jogador.getTempos().stream().reduce((x, y) -> x + y).get();
	    		melhorTempo= Collections.min(jogador.getTempos());
	    		parteJogadores+="Nome: "+jogador.getNome()+" Apelido: "+gerarApelido(jogador)+" Total de Pontos:"+jogador.getPontos()+
	    				"\n Tempo Total das Jogadas:"+tempoTotal+" Jogada Mais Rápida:"+melhorTempo+"\n";
	    		//
	    	}
	    	System.out.println(parteCampeonato+"\n"+parteJogadores);
	    	//return parteCampeonato+"\n"+parteJogadores;
	    	
	    }
	    
	    private String gerarApelido(Jogador jogador) {
	    	return ""+jogador.getNome().charAt(0)+jogador.getNome().charAt(1)+jogador.getNome().charAt(2);
	    }
	    
	    private int jogadoresEmpatadosSize() {
	    	System.out.println(jogadoresEmpatados.size());
	    	return jogadoresEmpatados.size();
	    }
	  /*  private void loadBeepSound() {
	        try {
	            beepSoundClip = AudioSystem.getClip();
	            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(beepSoundFile);
	            beepSoundClip.open(audioInputStream);"

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

	/*    private void createActionButton(String label, int x, int y, int width, int height, int changeValue, String buttonType) {
	        JButton button = new JButton(label);
	        switch (buttonType) {
	            case "diffMinus":
	                button.addActionListener(new ActionListener() {
	                    public void actionPerformed(ActionEvent e) {
	                        diffLabel.setText("Dificuldade: " + difficulty);
	                        if (difficulty > 1)
	                            difficulty--;
	                    }
	                });
	                break;
	            case "diffPlus":
	                button.addActionListener(new ActionListener() {
	                    public void actionPerformed(ActionEvent e) {
	                        diffLabel.setText("Dificuldade: " + difficulty);
	                        if (difficulty < 3)
	                            difficulty++;
	                    }
	                });
	                break;
	            case "speedMinus":
	                button.addActionListener(new ActionListener() {
	                    public void actionPerformed(ActionEvent e) {
	                        speedLabel.setText("Velocidade: " + speed);
	                        if (speed > 1)
	                            speed--;
	                    }
	                });
	                break;
	            case "speedPlus":
	                button.addActionListener(new ActionListener() {
	                    public void actionPerformed(ActionEvent e) {
	                        speedLabel.setText("Velocidade: " + speed);
	                        if (speed < 3)
	                            speed++;
	                    }
	                });
	                break;
	        }
	        button.setBounds(x, y, width, height);
	        contentPane.add(button);

}*/
	    
}
