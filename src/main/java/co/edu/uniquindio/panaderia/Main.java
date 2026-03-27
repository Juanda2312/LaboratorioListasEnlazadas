package co.edu.uniquindio.panaderia;

public class Main {

    public static void main(String[] args) {

        Panaderia panaderia = new Panaderia();

        // Agregando pedidos
        System.out.println("=== Agregando pedidos ===");
        panaderia.addOrder("Croissant x2");
        panaderia.addOrder("Pan de queso x3");
        panaderia.addOrder("Mogolla x5");
        panaderia.addOrder("Almojábana x1");

        // Ver todos los pedidos
        System.out.println("\n=== Lista de pedidos ===");
        panaderia.allOrders();

        // Ver el siguiente pedido a atender
        System.out.println("\n=== Próximo pedido a atender ===");
        System.out.println(panaderia.next());

        // Atender el primer pedido
        System.out.println("\n=== Atendiendo primer pedido ===");
        panaderia.attend();

        // Ver cómo quedó la lista
        System.out.println("\n=== Lista tras atender el primero ===");
        panaderia.allOrders();

        // Verificar cuál es ahora el siguiente
        System.out.println("\n=== Próximo pedido ahora ===");
        System.out.println(panaderia.next());

        // Atender los restantes uno a uno
        System.out.println("\n=== Atendiendo todos los pedidos restantes ===");
        panaderia.attend();
        panaderia.attend();
        panaderia.attend();

        // Verificar que la lista quedó vacía
        System.out.println("\n=== Lista final (debería estar vacía) ===");
        panaderia.allOrders();
    }
}