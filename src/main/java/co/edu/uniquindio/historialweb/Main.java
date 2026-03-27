package co.edu.uniquindio.historialweb;

public class Main {

    public static void main(String[] args) {

        HistorialWeb historial = new HistorialWeb();

        // Visitando páginas
        System.out.println("=== Visitando páginas ===");
        historial.visit("google.com");
        historial.visit("github.com");
        historial.visit("stackoverflow.com");
        historial.visit("docs.oracle.com");

        // Página actual (la última visitada)
        System.out.println("\n=== Página actual ===");
        System.out.println(historial.current());

        // Retroceder
        System.out.println("\n=== Retrocediendo ===");
        System.out.println(historial.prev());
        System.out.println(historial.prev());
        System.out.println(historial.prev());

        // Intentar retroceder más allá del inicio
        System.out.println("\n=== Intentando retroceder más allá del inicio ===");
        System.out.println(historial.prev());

        // Avanzar
        System.out.println("\n=== Avanzando ===");
        System.out.println(historial.next());
        System.out.println(historial.next());
    }
}