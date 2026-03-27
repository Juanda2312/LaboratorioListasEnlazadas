package co.edu.uniquindio.juegoporturnos;

public class Main {

    public static void main(String[] args) {

        JuegoTurnos juego = new JuegoTurnos();

        // Agregando jugadores
        System.out.println("=== Agregando jugadores ===");
        juego.addPlayer("Alice");
        juego.addPlayer("Bob");
        juego.addPlayer("Carlos");
        juego.addPlayer("Diana");

        // Turnos hacia adelante
        System.out.println("\n=== Turnos hacia adelante ===");
        System.out.println("Turno: " + juego.next());
        System.out.println("Turno: " + juego.next());
        System.out.println("Turno: " + juego.next());

        // Turnos hacia atrás
        System.out.println("\n=== Turnos hacia atrás ===");
        System.out.println("Turno: " + juego.prev());
        System.out.println("Turno: " + juego.prev());

        // Eliminar jugador que NO es el actual
        System.out.println("\n=== Eliminando a 'Bob' ===");
        juego.removePlayer("Bob");

        System.out.println("\n=== Turnos tras eliminar Bob ===");
        System.out.println("Turno: " + juego.next());
        System.out.println("Turno: " + juego.next());
        System.out.println("Turno: " + juego.next());

        // Eliminar jugador que SÍ es el actual
        System.out.println("\n=== Eliminando al jugador actual ===");
        String actual = juego.next();
        System.out.println("Jugador actual antes de eliminar: " + actual);
        juego.removePlayer(actual);

        System.out.println("\n=== Turnos tras eliminar al jugador actual ===");
        System.out.println("Turno: " + juego.next());
        System.out.println("Turno: " + juego.next());

        // Probar la naturaleza circular
        System.out.println("\n=== Naturaleza circular (6 turnos con 2 jugadores) ===");
        for (int i = 1; i <= 6; i++) {
            System.out.println("Turno " + i + ": " + juego.next());
        }

        // Eliminar los jugadores que realmente quedan
        System.out.println("\n=== Eliminando todos los jugadores restantes ===");
        while (!juego.isEmpty()) {
            String jugador = juego.next();
            juego.removePlayer(jugador);
            System.out.println("Eliminado: " + jugador);
        }

        // Intentar jugar sin jugadores
        System.out.println("\n=== Intentando jugar sin jugadores ===");
        System.out.println(juego.next());
        System.out.println(juego.prev());
    }
}