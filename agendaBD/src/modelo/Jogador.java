package modelo;

import java.util.ArrayList;

public class Jogador {
	
	private int pontos;
	private String nome;
	private ArrayList<Float> tempos;


	public Jogador(String nome) {
		this.nome = nome;
		this.tempos= new ArrayList<>();
		this.pontos=0;
	}
	
	public ArrayList<Float> getTempos() {
		return tempos;
}
	
	public void setTempos(ArrayList<Float> tempos) {
		this.tempos = tempos;
	}


	public void addTempo(float tempo) {
		this.getTempos().add(tempo);
}

	public String getNome() {
		return nome;
}
	public void setNome(String nome) {
		this.nome = nome;
}

	public int getPontos() {
		return pontos;
}
	public void setPontos(int pontos) {
		this.pontos =this.pontos+pontos;
}

}
