package library_utils;

import models.Order;

import java.util.ArrayList;
import java.util.List;

public class MyPriorityList {
    private List<Order> list = new ArrayList<>();

    //Adds an order to the list based on the priority of the user and if the books
    //are similar while also maintaining the FIFO principle when necessary
    public boolean add(Order order){

        //prevents duplicate entry
        if(list.contains(order)){
            return false;
        }

        int level = order.getUser().getLevel();
        String bookId  = order.getBookCode();

        for (int i = 0; i < list.size(); i++) {
            if(bookId.equals(list.get(i).getBookCode())
                    && level > list.get(i).getUser().getLevel()){
                list.add(i, order);
                return true;
            }
        }
        list.add(order);
        return true;
    }

    public Order remove() {
        return list.remove(0);
    }

    public boolean isEmpty(){
        return list.isEmpty();
    }

    public int size(){
        return list.size();
    }


    public void clear(){
        list.clear();
    }

}
