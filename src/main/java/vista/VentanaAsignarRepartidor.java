package vista;

import modelo.ControladorSpeedFast;
import modelo.EstadoPedido;
import modelo.Pedido;
import modelo.Repartidor;

import javax.swing.*;
import java.awt.*;

/**
 * Permite asignar un repartidor disponible a un pedido registrado y simular
 * el inicio de la entrega (usa el ExecutorService del controlador).
 */
public class VentanaAsignarRepartidor extends JFrame {

    private final ControladorSpeedFast controlador;

    private JComboBox<Pedido> comboPedidos;
    private JComboBox<Repartidor> comboRepartidores;
    private JLabel lblEstado;

    public VentanaAsignarRepartidor(ControladorSpeedFast controlador) {
        this.controlador = controlador;

        setTitle("SpeedFast - Asignar Repartidor / Iniciar Entrega");
        setSize(420, 260);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        add(construirFormulario(), BorderLayout.CENTER);
        add(construirPanelBoton(), BorderLayout.SOUTH);

        cargarCombos();
    }

    private JPanel construirFormulario() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        comboPedidos = new JComboBox<>();
        comboRepartidores = new JComboBox<>();
        lblEstado = new JLabel(" ");

        panel.add(new JLabel("Pedido:"));
        panel.add(comboPedidos);
        panel.add(new JLabel("Repartidor:"));
        panel.add(comboRepartidores);
        panel.add(new JLabel("Estado:"));
        panel.add(lblEstado);

        return panel;
    }

    private JPanel construirPanelBoton() {
        JPanel panel = new JPanel();
        JButton btnAsignar = new JButton("Asignar e iniciar entrega");
        btnAsignar.addActionListener(e -> asignarEIniciar());
        panel.add(btnAsignar);
        panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        return panel;
    }

    private void cargarCombos() {
        comboPedidos.removeAllItems();
        for (Pedido p : controlador.getPedidos()) {
            if (p.getEstado() == EstadoPedido.REGISTRADO) {
                comboPedidos.addItem(p);
            }
        }

        comboRepartidores.removeAllItems();
        for (Repartidor r : controlador.getRepartidores()) {
            if (r.isDisponible()) {
                comboRepartidores.addItem(r);
            }
        }
    }

    private void asignarEIniciar() {
        Pedido pedido = (Pedido) comboPedidos.getSelectedItem();
        Repartidor repartidor = (Repartidor) comboRepartidores.getSelectedItem();

        if (pedido == null || repartidor == null) {
            JOptionPane.showMessageDialog(this,
                    "No hay pedidos pendientes o repartidores disponibles.",
                    "Sin datos",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        lblEstado.setText("En ruta...");

        controlador.asignarYIniciarEntrega(pedido, repartidor, () -> {
            lblEstado.setText("Entregado");
            JOptionPane.showMessageDialog(this,
                    "El pedido " + pedido.getId() + " fue entregado por " + repartidor.getNombre() + ".",
                    "Entrega finalizada",
                    JOptionPane.INFORMATION_MESSAGE);
            cargarCombos();
        });

        JOptionPane.showMessageDialog(this,
                "Se asignó el pedido " + pedido.getId() + " a " + repartidor.getNombre()
                        + ". La entrega se está simulando en segundo plano.",
                "Entrega iniciada",
                JOptionPane.INFORMATION_MESSAGE);

        cargarCombos();
    }
}
