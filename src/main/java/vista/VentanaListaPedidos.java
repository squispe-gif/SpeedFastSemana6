package vista;

import modelo.ControladorSpeedFast;
import modelo.Pedido;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Ventana que muestra en una tabla todos los pedidos registrados.
 */
public class VentanaListaPedidos extends JFrame {

    private final ControladorSpeedFast controlador;
    private DefaultTableModel modeloTabla;
    private JTable tabla;

    private static final String[] COLUMNAS = {"ID", "Dirección", "Tipo", "Repartidor", "Estado"};

    public VentanaListaPedidos(ControladorSpeedFast controlador) {
        this.controlador = controlador;

        setTitle("SpeedFast - Listado de Pedidos");
        setSize(560, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        modeloTabla = new DefaultTableModel(COLUMNAS, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabla = new JTable(modeloTabla);

        add(new JScrollPane(tabla), BorderLayout.CENTER);
        add(construirPanelInferior(), BorderLayout.SOUTH);

        cargarPedidos();
    }

    private JPanel construirPanelInferior() {
        JPanel panel = new JPanel();
        JButton btnActualizar = new JButton("Actualizar listado");
        btnActualizar.addActionListener(e -> cargarPedidos());
        panel.add(btnActualizar);
        return panel;
    }

    /**
     * Refresca la tabla leyendo el estado actual de la lista en memoria
     * del controlador compartido.
     */
    public void cargarPedidos() {
        modeloTabla.setRowCount(0);
        for (Pedido pedido : controlador.getPedidos()) {
            modeloTabla.addRow(new Object[]{
                    pedido.getId(),
                    pedido.getDireccion(),
                    pedido.getTipo(),
                    pedido.getNombreRepartidor(),
                    pedido.getEstado()
            });
        }
    }
}
