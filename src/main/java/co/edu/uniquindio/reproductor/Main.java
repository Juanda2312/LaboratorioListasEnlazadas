package co.edu.uniquindio.reproductor;

public class Main {

    public static void main(String[] args) {

        Reproductor reproductor = new Reproductor();

        // Agregando canciones
        System.out.println("=== Agregando canciones ===");
        reproductor.addSong("Bohemian Rhapsody - Queen");
        reproductor.addSong("Stairway to Heaven - Led Zeppelin");
        reproductor.addSong("Hotel California - Eagles");
        reproductor.addSong("Smells Like Teen Spirit - Nirvana");
        reproductor.addSong("Billie Jean - Michael Jackson");

        // Ver la secuencia completa
        System.out.println("\n=== Secuencia de canciones ===");
        reproductor.secuence();

        // Reproducir canciones una por una
        System.out.println("\n=== Reproduciendo ===");
        System.out.println("♪ " + reproductor.next());
        System.out.println("♪ " + reproductor.next());
        System.out.println("♪ " + reproductor.next());

        // Eliminar una canción
        System.out.println("\n=== Eliminando 'Hotel California - Eagles' ===");
        reproductor.removeSong("Hotel California - Eagles");

        // Ver secuencia actualizada
        System.out.println("\n=== Secuencia actualizada ===");
        reproductor.secuence();

        // Seguir reproduciendo
        System.out.println("\n=== Continuando reproducción ===");
        System.out.println("♪ " + reproductor.next());
        System.out.println("♪ " + reproductor.next());
        System.out.println("♪ " + reproductor.next());
        System.out.println("♪ " + reproductor.next());
        System.out.println("♪ " + reproductor.next());

        // Eliminar todas las canciones
        System.out.println("\n=== Eliminando todas las canciones ===");
        reproductor.removeSong("Bohemian Rhapsody - Queen");
        reproductor.removeSong("Stairway to Heaven - Led Zeppelin");
        reproductor.removeSong("Smells Like Teen Spirit - Nirvana");
        reproductor.removeSong("Billie Jean - Michael Jackson");

        // Intentar reproducir con lista vacía
        System.out.println("\n=== Intentando reproducir sin canciones ===");
        System.out.println(reproductor.next());
    }
}