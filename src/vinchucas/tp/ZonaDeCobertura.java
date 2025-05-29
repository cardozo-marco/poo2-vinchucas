package vinchucas.tp;

//Clase creada solo para testear la calculadora de distancias.
public class ZonaDeCobertura {
	
	private Ubicacion epicentro;
	private Double radio;
	
	public ZonaDeCobertura(Ubicacion epicentro, Double radio) {
		this.setEpicentro(epicentro);
		this.setRadio(radio);
	}

	public Ubicacion getEpicentro() {
		return epicentro;
	}

	private void setEpicentro(Ubicacion epicentro) {
		this.epicentro = epicentro;
	}

	public Double getRadio() {
		return radio;
	}

	private void setRadio(Double radio) {
		this.radio = radio;
	}
}
