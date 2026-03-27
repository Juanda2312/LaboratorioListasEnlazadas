package co.edu.uniquindio.historialweb;

public class Node<T>{

    private Node<T> next;
    private Node<T> prev;
    private T data;


    public Node(T data) {
        this.data = data;
        next = null;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node<T> getPrev() {
        return prev;
    }

    public void setPrev(Node<T> prev) {
        this.prev = prev;
    }

    @Override
    public String toString() {
        if (next != null){
            return data +  "--> ";
        }
            return data+ "";
    }
}
