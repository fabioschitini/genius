package modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Campeonato {
    private String titulo;
    private String dataDoCampeonato;
    private ArrayList<Jogador> jogadores;
	private String relatorio;
	
	public String getData() {
		LocalDateTime  datCam = LocalDateTime.now();
		DateTimeFormatter dataFormatada = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String formattedDate = datCam.format(dataFormatada);
		return formattedDate;
	}
	
	public Campeonato(String titulo,String relatorio) {
		this.titulo = titulo;
		this.relatorio=relatorio;
		this.dataDoCampeonato=this.getData();
		this.jogadores= new ArrayList<>();
	};
	
	public String getRelatorio() {
		return relatorio;
	}

	public void setRelatorio(String relatorio) {
		this.relatorio = relatorio;
	}

	public ArrayList<Jogador> getJogadores() {
		return jogadores;
	}
	public void setJogadores(ArrayList<Jogador> jogadores) {
		this.jogadores = jogadores;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDataDoCampeonato() {
		return dataDoCampeonato;
	}
	
}