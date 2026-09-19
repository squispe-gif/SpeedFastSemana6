package vista;

import modelo.ControladorSpeedFast;

import javax.swing.*;
import java.awt.*;

/**
 * Ventana principal del sistema SpeedFast.
 * Desde aquí se navega hacia el registro de pedidos, el listado de pedidos
 * y la asignación de repartidores / inicio de entregas.
 */
public class VentanaPrincipal extends JFrame {

    private final ControladorSpeedFast controlador;

    public VentanaPrincipal() {
        this.controlador = new ControladorSpeedFast();

        setTitle("SpeedFast - Gestión de Entregas");
        setSize(420, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JLabel titulo = new JLabel("SpeedFast - Panel Principal", SwingConstants.CENTER);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 18));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        add(titulo, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel(new GridLayout(3, 1, 15, 15));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(20, 60, 20, 60));

        JButton btnRegistrar = new JButton("Registrar pedido");
        JButton btnListar = new JButton("Listar pedidos");
        JButton btnAsignar = new JButton("Asignar repartidor / Iniciar entrega");

        btnRegistrar.addActionListener(e ->
                new VentanaRegistroPedido(controlador).setVisible(true));

        btnListar.addActionListener(e ->
                new VentanaListaPedidos(controlador).setVisible(true));

        btnAsignar.addActionListener(e ->
                new VentanaAsignarRepartidor(controlador).setVisible(true));

        panelBotones.add(btnRegistrar);
        panelBotones.add(btnListar);
        panelBotones.add(btnAsignar);

        add(panelBotones, BorderLayout.CENTER);
    }
}
