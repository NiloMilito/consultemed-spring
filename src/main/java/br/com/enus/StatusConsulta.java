package br.com.enus;

public enum StatusConsulta {
	AGENDADA(0), REALIZADA(1), CANCELADA(2);
	
	private int valor;
	private String descricao;
	
	private StatusConsulta(int valor) {
		this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}	

}
