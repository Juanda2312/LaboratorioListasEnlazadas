package co.edu.uniquindio.reproductor;

public class Reproductor {
    CircularSimpleLinkedList<String> songs = new CircularSimpleLinkedList<>();
    CircularSimpleLinkedList<String>.CircularListIterator it;

    public void addSong(String song) {
        songs.addLast(song);
        restoreIterator();
    }

    public void removeSong(String song) {
        String current = (it != null && it.getCurrent() != null)
                ? it.getCurrent().getData()
                : null;

        songs.remove(song);

        if (song.equals(current)) {
            it = songs.iterator();
        } else {
            restoreIterator(current);
        }
    }

    public String next() {
        if (it == null || !it.hasNext()) return "No songs to reproduce";
        return it.next();
    }

    public void secuence() {
        songs.printList();
    }


    private void restoreIterator() {
        if (it == null) {
            it = songs.iterator();
            return;
        }
        String current = it.getCurrent() != null ? it.getCurrent().getData() : null;
        restoreIterator(current);
    }


    private void restoreIterator(String target) {
        it = songs.iterator();
        if (target == null || songs.isEmpty()) return;

        CircularSimpleLinkedList<String>.CircularListIterator temp = songs.iterator();
        int index = 0;
        int found = -1;

        for (int i = 0; i < songs.getSize(); i++) {
            if (temp.next().equals(target)) {
                found = i;
                break;
            }
        }

        if (found != -1) {
            it = songs.iterator();
            for (int i = 0; i < found; i++) {
                it.next();
            }
        }
    }
}