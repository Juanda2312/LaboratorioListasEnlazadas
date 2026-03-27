package co.edu.uniquindio.panaderia;

public class Panaderia {
    SimpleLinkedList<String> orders = new SimpleLinkedList<>();

    public void addOrder(String order){
        orders.addLast(order);
    }

    public void attend(){
        orders.removeFirst();
    }

    public String next(){
        return orders.getfirst();
    }

    public void allOrders(){
        orders.printList();
    }
}
