# SpeedFast - Semana 6 (PRY2203)
Interfaz gráfica de escritorio (Java Swing) para la gestión de entregas de SpeedFast.

## Estructura de paquetes
- `modelo`: Pedido, Repartidor, TipoPedido, EstadoPedido y ControladorSpeedFast
  (listas en memoria + ExecutorService para simular la entrega).
- `vista`: VentanaPrincipal, VentanaRegistroPedido, VentanaListaPedidos,
  VentanaAsignarRepartidor.
- `main`: Main (punto de entrada, llama a `new VentanaPrincipal()`).

## Cómo abrir en IntelliJ IDEA
1. File > Open... y selecciona esta carpeta (SpeedFast-S6).
2. Marca `src` como "Sources Root" si IntelliJ no lo detecta automáticamente
   (clic derecho sobre `src` > Mark Directory as > Sources Root).
3. Ejecuta la clase `main.Main`.

## Cómo compilar y ejecutar por consola

javac -d out $(find src -name "*.java")
java -cp out main.Main

**
## Funcionalidad implementada
- Registrar nuevos pedidos (ID, dirección, tipo) con validación y confirmación.
- Listar pedidos en una JTable (DefaultTableModel), con botón para refrescar.
- Asignar repartidor a un pedido pendiente e iniciar una simulación de entrega
  concurrente (ExecutorService), actualizando el estado del pedido.
- Navegación completa entre ventanas desde VentanaPrincipal.
- Almacenamiento en listas en memoria (sin conexión a base de datos, según lo
  solicitado en el enunciado de esta semana).**
