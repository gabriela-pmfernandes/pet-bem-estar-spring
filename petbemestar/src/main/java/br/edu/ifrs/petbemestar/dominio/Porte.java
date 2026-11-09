package br.edu.ifrs.petbemestar.dominio;

public enum Porte {
	PEQUENO("Pequeno"),
	MEDIO("Médio"),
	GRANDE("Grande");
	
	private final String nome;
	
	Porte(String nome){
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}

}
