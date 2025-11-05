package monopoly;



public class Carta {
    private int id;
    private String tipo; // "Suerte" o "Comunidad"
    private String descripcion;
    private String accion; // nombre de la acción a realizar (por ejemplo: "PAGAR", "COBRAR", "MOVER", "CARCEL")
    private float cantidad; // importe a pagar o cobrar
    private String destino; // nombre de la casilla destino (si aplica)

    public Carta(int id, String tipo, String descripcion, String accion, float cantidad, String destino) {
        this.id = id;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.accion = accion;
        this.cantidad = cantidad;
        this.destino = destino;
    }

    // getters
    public int getId() { return id; }
    public String getTipo() { return tipo; }
    public String getDescripcion() { return descripcion; }
    public String getAccion() { return accion; }
    public float getCantidad() { return cantidad; }
    public String getDestino() { return destino; }
}





