package vinchucas.tp;

public class Ubicacion {
    private double latitud;
    private double longitud;
	
    //Comprobar si latitud y longitud son valores validos
	public Ubicacion(Double latitud, Double longitud) {
		this.setLatitud(latitud);
		this.setLongitud(longitud);
	}

	public Double getLatitud() {
		return latitud;
	}

	private void setLatitud(Double latitud) {
		this.latitud = latitud;
	}

	public Double getLongitud() {
		return longitud;
	}

	private void setLongitud(Double longitud) {
		this.longitud = longitud;
	}

    public double distanciaA(Ubicacion otra) {
        // Implementación Tomas
        return 0;
    }
}
