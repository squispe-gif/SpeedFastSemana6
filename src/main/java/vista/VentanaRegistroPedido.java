package vista;

import modelo.ControladorSpeedFast;
import modelo.Pedido;
import modelo.TipoPedido;

import javax.swing.*;
import java.awt.*;

/**
 * Formulario para registrar un nuevo pedido.
 */
public class VentanaRegistroPedido extends JFrame {

    private final ControladorSpeedFast controlador;

    private JTextField txtId;
    private JTextField txtDireccion;
    private JComboBox<TipoPedido> comboTipo;

    public VentanaRegistroPedido(ControladorSpeedFast controlador) {
        this.controlador = controlador;

        setTitle("SpeedFast - Registrar Pedido");
        setSize(380, 260);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        add(construirFormulario(), BorderLayout.CENTER);
        add(construirPanelBoton(), BorderLayout.SOUTH);
    }

    private JPanel construirFormulario() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));

        txtId = new JTextField();
        txtDireccion = new JTextField();
        comboTipo = new JComboBox<>(TipoPedido.values());

        panel.add(new JLabel("ID del pedido:"));
        panel.add(txtId);
        panel.add(new JLabel("Dirección:"));
        panel.add(txtDireccion);
        panel.add(new JLabel("Tipo:"));
        panel.add(comboTipo);

        return panel;
    }

    private JPanel construirPanelBoton() {
        JPanel panel = new JPanel();
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> guardarPedido());
        panel.add(btnGuardar);
        panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        return panel;
    }

    private void guardarPedido() {
        String id = txtId.getText().trim();
        String direccion = txtDireccion.getText().trim();
        TipoPedido tipo = (TipoPedido) comboTipo.getSelectedItem();

        if (id.isEmpty() || direccion.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Debes completar el ID y la dirección del pedido.",
                    "Datos incompletos",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (controlador.existeId(id)) {
            JOptionPane.showMessageDialog(this,
                    "Ya existe un pedido registrado con ese ID.",
                    "ID duplicado",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        Pedido pedido = new Pedido(id, direccion, tipo);
        controlador.agregarPedido(pedido);

        JOptionPane.showMessageDialog(this,
                "Pedido " + id + " registrado correctamente.",
                "Pedido guardado",
                JOptionPane.INFORMATION_MESSAGE);

        limpiarFormulario();
    }

    private void limpiarFormulario() {
        txtId.setText("");
        txtDireccion.setText("");
        comboTipo.setSelectedIndex(0);
        txtId.requestFocus();
    }
}
