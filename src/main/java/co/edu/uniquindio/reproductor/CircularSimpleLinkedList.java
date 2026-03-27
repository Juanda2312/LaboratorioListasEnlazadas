package co.edu.uniquindio.reproductor;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CircularSimpleLinkedList<T extends Comparable<T>> implements Iterable<T>{

    private Node<T> first;
    private Node<T> last;
    private int size;

    public CircularSimpleLinkedList() {
        first = null;
        last = null;
        size = 0;
    }

    public void addFirst(T data){
        Node<T> newNode = new Node<>(data);
        if (!isEmpty()) {
            newNode.setNext(first);
            last.setNext(newNode);
        }else{
            last = newNode;
            newNode.setNext(newNode);
        }
        first = newNode;
        size++;
    }

    public void addLast(T data){
        Node<T> newNode = new Node<>(data);
        if (isEmpty()){
            first = newNode;
            last = newNode;
            newNode.setNext(newNode);
        }else {
            last.setNext(newNode);
            newNode.setNext(first);
            last = newNode;
        }
        size++;
    }

    public void add(T data, int i){
        if (i < 0 || i > size) throw new IndexOutOfBoundsException("Invalid index");
        if (isEmpty() || i == 0) {
            addFirst(data);
            return;
        } else if (i == size) {
            addLast(data);
            return;
        }

        Node<T> aux = first;
        int j = 0;
        while (j < i - 1){
            j++;
            aux = aux.getNext();
        }
        Node<T> newNode = new Node<>(data);
        newNode.setNext(aux.getNext());
        aux.setNext(newNode);
        size++;
    }

    public void removeFirst(){
        if (isEmpty()) throw new RuntimeException("List is empty");
        if (size == 1){
            first = null;
            last = null;
        } else {
            first = first.getNext();
            last.setNext(first);
        }
        size--;
    }

    public void removeLast(){
        if (isEmpty()) throw new RuntimeException("List is empty");
        if (size == 1){
            first = null;
            last = null;
        }else{
            Node<T> aux = first;
            while (aux.getNext() != last){
                aux = aux.getNext();
            }
            aux.setNext(first);
            last = aux;
        }
        size--;
    }

    public void remove(int i){
        if (isEmpty()) throw new RuntimeException("List is empty");
        if (i < 0 || i >= size) throw new IndexOutOfBoundsException("Invalid index");
        if (i == 0) {
            removeFirst();
            return;
        } else if (i == size - 1) {
            removeLast();
            return;
        }

        int j = 0;
        Node<T> aux = first;
        while (j < i - 1){
            j++;
            aux = aux.getNext();
        }
        Node<T> aux2 = aux.getNext();
        aux.setNext(aux2.getNext());
        aux2.setNext(null);

        size--;
    }

    public void remove(T data) {
        if (isEmpty()) throw new RuntimeException("List is empty");

        if (first.getData().equals(data)) {
            if (first == last) {
                first = null;
                last = null;
            } else {
                first = first.getNext();
                last.setNext(first);
            }
            size--;
            return;
        }

        Node<T> aux = first;
        while (aux.getNext() != first) {
            if (aux.getNext().getData().equals(data)) {
                if (aux.getNext() == last) {
                    last = aux;
                }
                aux.setNext(aux.getNext().getNext());
                size--;
                return;
            }
            aux = aux.getNext();
        }

        throw new NoSuchElementException("No se encontró: " + data);
    }

    public T get(int i){
        return getNode(i).getData();
    }

    public Node<T> getNode(int i){
        if (isEmpty()) throw new RuntimeException("List is empty");
        if (i < 0 || i >= size) throw new IndexOutOfBoundsException("Invalid index");

        Node<T> aux = first;
        for (int j = 0; j < i; j++) {
            aux = aux.getNext();
        }
        return aux;
    }

    public int getIndex(T value){
        Node<T> aux = first;
        for (int i = 0; i < size; i++) {
            if (aux.getData().equals(value)) return i;
            aux = aux.getNext();
        }
        return -1;
    }

    public void update(T newData, int i){
        Node<T> aux = getNode(i);
        aux.setData(newData);
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void printList(){
        if (isEmpty()) return;
        StringBuilder s = new StringBuilder();
        Node<T> aux = first;
        for (int i = 0; i < size; i++){
            s.append(aux.toString());
            aux = aux.getNext();
        }
        System.out.println(s);
    }

    public void sort(){
        if (isEmpty() || size == 1) return;

        boolean swapped;

        do {
            swapped = false;
            Node<T> current = first;

            int count = 0;
            while (count < size - 1){
                if (current.getData().compareTo(current.getNext().getData()) > 0){
                    T temp = current.getData();
                    current.setData(current.getNext().getData());
                    current.getNext().setData(temp);

                    swapped = true;
                }
                current = current.getNext();
                count++;
            }
        } while (swapped);
    }

    public void removeAll(){
        first = null;
        last = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public void reverse(){
        if (isEmpty() || size == 1) return;
        last.setNext(null);
        last = first;
        first = reverse(first);
        last.setNext(first);
    }

    private Node<T> reverse(Node<T> current) {
        if (current.getNext() == null){
            return current;
        }
        Node<T> aux = reverse(current.getNext());

        current.getNext().setNext(current);
        current.setNext(null);

        return aux;
    }

    @Override
    public CircularListIterator iterator() {
        return new CircularListIterator();
    }

    public class CircularListIterator implements Iterator<T>{

        private Node<T> current;

        public CircularListIterator(){
            current = first;
        }

        public Node<T> getCurrent() {
            return current;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            T data = current.getData();
            current = current.getNext();
            return data;
        }
    }
}