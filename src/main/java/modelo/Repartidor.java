package modelo;

/**
 * Representa un repartidor de SpeedFast.
 */
public class Repartidor {

    private final String id;
    private final String nombre;
    private boolean disponible;

    public Repartidor(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.disponible = true;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public String toString() {
        return id + " - " + nombre + (disponible ? " (disponible)" : " (en ruta)");
    }
}
