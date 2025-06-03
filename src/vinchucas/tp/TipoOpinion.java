package vinchucas.tp;

public enum TipoOpinion {
	VINCHUCA_INFESTANS("Vinchuca Infestans"),
    VINCHUCA_SORDIDA("Vinchuca Sordida"),
    VINCHUCA_GUASAYANA("Vinchuca Guasayana"),
    CHINCHE_FOLIADA("Chinche Foliada"),
    PHTIA_CHINCHE("Phtia Chinche"),
    NINGUNA("Ninguna"),
    POCO_CLARA("Imagen poco clara");

    private final String descripcion;

    TipoOpinion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
