package co.edu.uniquindio.historialweb;

public class HistorialWeb {
    DoubleLinkedList<String> pages = new DoubleLinkedList<>();
    DoubleLinkedList<String>.DoubleListIterator it;

    public void visit(String url) {
        pages.addLast(url);

        it = pages.iterator();

        int i = 0;
        while (i < pages.getSize() - 1) {
            it.next();
            i++;
        }
    }

    public String current() {
        if (it == null || it.getCurrent() == null) return "No hay página actual";
        return it.getCurrent().getData();
    }

    public String next() {
        if (it == null || !it.hasNext()) return "No hay página siguiente";
        return it.next();
    }

    public String prev() {
        if (it == null || !it.hasPrev()) return "No hay página anterior";
        return it.prev();
    }
}