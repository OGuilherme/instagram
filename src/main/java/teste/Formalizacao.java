package teste;

import java.util.List;

import com.google.gson.annotations.SerializedName;


public class Formalizacao {
	
	@SerializedName("tarefa")
	private List<String> tafera;
	private List<String> nomeLink;
	private List<String> texto;
	private List<String> uri;

	public List<String> getTafera() {
		return tafera;
	}

	public void setTafera(List<String> tafera) {
		this.tafera = tafera;
	}

	public List<String> getNomeLink() {
		return nomeLink;
	}

	public void setNomeLink(List<String> nomeLink) {
		this.nomeLink = nomeLink;
	}

	public List<String> getTexto() {
		return texto;
	}

	public void setTexto(List<String> texto) {
		this.texto = texto;
	}

	public List<String> getUri() {
		return uri;
	}

	public void setUri(List<String> uri) {
		this.uri = uri;
	}
}
