package lsi.identificadores;

public class Identificador {

	private String nome;
	private long categoria;
	private int nivel;
	
	public Identificador(String nome, long categoria, int nivel){
		setNome(nome);
		setCategoria(categoria);
		setNivel(nivel);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public long getCategoria() {
		return categoria;
	}

	public void setCategoria(long categoria) {
		this.categoria = categoria;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

}
