package modelo;

import java.util.ArrayList;

public class Jogador {
	
	private int pontos;
	private String nome;
	private ArrayList<Float> tempos;
	private int id;
	
	



	public Jogador(String nome,int id) {
		this.nome = nome;
		this.tempos= new ArrayList<>();
		this.pontos=0;
		this.id=id;
	}
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public ArrayList<Float> getTempos() {
		return tempos;
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
		this.pontos = pontos;
}


}
