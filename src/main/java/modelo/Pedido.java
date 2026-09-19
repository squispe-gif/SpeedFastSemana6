package modelo;

/**
 * Representa un pedido dentro del sistema SpeedFast.
 */
public class Pedido {

    private final String id;
    private final String direccion;
    private final TipoPedido tipo;
    private Repartidor repartidorAsignado;
    private EstadoPedido estado;

    public Pedido(String id, String direccion, TipoPedido tipo) {
        this.id = id;
        this.direccion = direccion;
        this.tipo = tipo;
        this.estado = EstadoPedido.REGISTRADO;
        this.repartidorAsignado = null;
    }

    public String getId() {
        return id;
    }

    public String getDireccion() {
        return direccion;
    }

    public TipoPedido getTipo() {
        return tipo;
    }

    public Repartidor getRepartidorAsignado() {
        return repartidorAsignado;
    }

    public void setRepartidorAsignado(Repartidor repartidorAsignado) {
        this.repartidorAsignado = repartidorAsignado;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    public String getNombreRepartidor() {
        return repartidorAsignado != null ? repartidorAsignado.getNombre() : "Sin asignar";
    }

    @Override
    public String toString() {
        return id + " - " + direccion + " (" + tipo + ")";
    }
}
