package co.edu.uniquindio.juegoporturnos;


public class JuegoTurnos {
    CircularDoubleLinkedList<String> players = new CircularDoubleLinkedList<>();
    CircularDoubleLinkedList<String>.CircularDoubleListIterator it;

    public void addPlayer(String player){
        players.addLast(player);
        restoreIterator();
    }

    public void removePlayer(String player) {
        String current = (it != null && it.getCurrent() != null)
                ? it.getCurrent().getData()
                : null;

        players.remove(player);

        if (player.equals(current)) {
            it = players.iterator();
        } else {
            restoreIterator(current);
        }
    }

    public String next() {
        if (it == null || !it.hasNext()) return "No player to play";
        return it.next();
    }

    public String prev() {
        if (it == null || !it.hasPrev()) return "No player to play";
        return it.prev();
    }

    private void restoreIterator() {
        if (it == null) {
            it = players.iterator();
            return;
        }
        String current = it.getCurrent() != null ? it.getCurrent().getData() : null;
        restoreIterator(current);
    }


    private void restoreIterator(String target) {
        it = players.iterator();
        if (target == null || players.isEmpty()) return;

        CircularDoubleLinkedList<String>.CircularDoubleListIterator temp = players.iterator();
        int index = 0;
        int found = -1;

        for (int i = 0; i < players.getSize(); i++) {
            if (temp.next().equals(target)) {
                found = i;
                break;
            }
        }

        if (found != -1) {
            it = players.iterator();
            for (int i = 0; i < found; i++) {
                it.next();
            }
        }
    }

    // En JuegoTurnos
    public boolean isEmpty() {
        return players.isEmpty();
    }
}
