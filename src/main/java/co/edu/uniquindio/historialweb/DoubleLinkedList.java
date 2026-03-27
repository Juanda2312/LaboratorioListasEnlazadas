package co.edu.uniquindio.historialweb;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoubleLinkedList<T extends Comparable<T>> implements Iterable<T>{

    private Node<T> first;
    private int size;

    public DoubleLinkedList() {
        first = null;
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public void addFirst(T data){
        Node<T> newNode = new Node<>(data);
        if (!isEmpty()) {
            newNode.setNext(first);
            first.setPrev(newNode);
        }
        first = newNode;
        size++;
    }

    public void addLast(T data){
        Node<T> newNode = new Node<>(data);
        if (isEmpty()){
            first = newNode;
        }else {
            Node<T> aux = first;
            while (aux.getNext() != null) {
                aux = aux.getNext();
            }
            aux.setNext(newNode);
            newNode.setPrev(aux);
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
        aux.getNext().setPrev(newNode);
        newNode.setPrev(aux);
        aux.setNext(newNode);
        size++;
    }

    public void removeFirst(){
        if (isEmpty()) throw new RuntimeException("List is empty");
        first = first.getNext();
        if (first != null) {
            first.setPrev(null);
        }
        size--;
    }

    public void removeLast(){
        if (isEmpty()) throw new RuntimeException("List is empty");
        if (first.getNext() == null){
            first = null;
        }else{
            Node<T> aux = first;
            while (aux.getNext().getNext() != null){
                aux = aux.getNext();
            }
            aux.getNext().setPrev(null);
            aux.setNext(null);
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
        aux2.getNext().setPrev(aux);
        aux2.setNext(null);
        aux2.setPrev(null);

        size--;
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
        while(aux != null){
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

            while (current.getNext() != null){
                if (current.getData().compareTo(current.getNext().getData()) > 0){
                    T temp = current.getData();
                    current.setData(current.getNext().getData());
                    current.getNext().setData(temp);

                    swapped = true;
                }
                current = current.getNext();
            }
        } while (swapped);
    }

    public void removeAll(){
        first = null;
        size = 0;
    }

    public void reverse(){
        if (first == null || first.getNext() == null) return;
        first = reverse(first);
    }

    private Node<T> reverse(Node<T> current) {
        if (current.getNext() == null) return current;

        Node<T> next = current.getNext();
        Node<T> newHead = reverse(next);

        next.setNext(current);
        next.setPrev(null);
        current.setPrev(next);
        current.setNext(null);

        return newHead;
    }

    @Override
    public DoubleListIterator iterator() {
        return new DoubleListIterator();
    }

    public class DoubleListIterator implements Iterator<T>{

        private Node<T> current;

        public DoubleListIterator(){
            current = first;
        }


        public Node<T> getCurrent(){
            return current;
        }
        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            T data = current.getData();
            current = current.getNext();
            return data;
        }

        public boolean hasPrev(){
            return current != null && current.getPrev() != null;
        }

        public T prev(){
            if (!hasPrev()) throw new NoSuchElementException();
            T data = current.getData();
            current = current.getPrev();
            return data;
        }
    }
}