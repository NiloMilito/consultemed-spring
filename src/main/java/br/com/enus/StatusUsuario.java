package br.com.enus;

public enum StatusUsuario {
	INATIVO(0), ATIVO(1);
	
	private int valor;
	private String descricao;
	
	public int getValor() {
		return valor;
	}
	
	private StatusUsuario(int valor) {
		this.valor = valor;
	}
	
	public void setValor(int valor) {
		this.valor = valor;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}	

}
