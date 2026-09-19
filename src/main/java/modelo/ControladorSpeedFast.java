package modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Controlador central de SpeedFast.
 * Mantiene en memoria la lista de pedidos y repartidores, y reutiliza el
 * ExecutorService desarrollado en semanas anteriores para simular el inicio
 * de una entrega de forma concurrente.
 */
public class ControladorSpeedFast {

    private final List<Pedido> pedidos = new ArrayList<>();
    private final List<Repartidor> repartidores = new ArrayList<>();
    private final ExecutorService executor = Executors.newCachedThreadPool();

    public ControladorSpeedFast() {
        // Repartidores de ejemplo para poder probar la asignación sin
        // necesidad de una pantalla adicional de registro.
        repartidores.add(new Repartidor("R1", "Juan Pérez"));
        repartidores.add(new Repartidor("R2", "María López"));
        repartidores.add(new Repartidor("R3", "Carlos Soto"));
    }

    public void agregarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public List<Repartidor> getRepartidores() {
        return repartidores;
    }

    public boolean existeId(String id) {
        return pedidos.stream().anyMatch(p -> p.getId().equalsIgnoreCase(id));
    }

    /**
     * Asigna un repartidor a un pedido y simula, en un hilo aparte, el
     * inicio y término de la entrega. Al terminar, ejecuta onFinish en el
     * hilo de Swing (Event Dispatch Thread) para refrescar la interfaz.
     */
    public void asignarYIniciarEntrega(Pedido pedido, Repartidor repartidor, Runnable onFinish) {
        pedido.setRepartidorAsignado(repartidor);
        pedido.setEstado(EstadoPedido.ASIGNADO);
        repartidor.setDisponible(false);

        executor.submit(() -> {
            try {
                pedido.setEstado(EstadoPedido.EN_RUTA);
                Thread.sleep(3000); // Simula el tiempo de traslado de la entrega
                pedido.setEstado(EstadoPedido.ENTREGADO);
                repartidor.setDisponible(true);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                if (onFinish != null) {
                    javax.swing.SwingUtilities.invokeLater(onFinish);
                }
            }
        });
    }

    public void cerrar() {
        executor.shutdown();
    }
}
