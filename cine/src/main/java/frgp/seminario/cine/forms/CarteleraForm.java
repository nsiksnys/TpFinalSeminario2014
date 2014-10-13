package frgp.seminario.cine.forms;

public class CarteleraForm {
	private int pelicula;
	private String version;
	private boolean subtitulos;
	private String inicio;
	private String fin;
	public int getPelicula() {
		return pelicula;
	}
	public void setPelicula(int pelicula) {
		this.pelicula = pelicula;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public boolean isSubtitulos() {
		return subtitulos;
	}
	public void setSubtitulos(boolean subtitulos) {
		this.subtitulos = subtitulos;
	}
	public String getInicio() {
		return inicio;
	}
	public void setInicio(String inicio) {
		this.inicio = inicio;
	}
	public String getFin() {
		return fin;
	}
	public void setFin(String fin) {
		this.fin = fin;
	}
}
