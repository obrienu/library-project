package library_utils;

import models.Order;

import java.util.ArrayList;
import java.util.List;

public class MyUniqueQueue {
    List<Order> list = new ArrayList<>();

    public boolean add(Order order){
        if(list.contains(order)){
            return false;
        }
        list.add(order);
        return true;
    }

    public Order remove(){
        return list.remove(0);
    }

    public int size(){
        return list.size();
    }

    public boolean isEmpty(){
        return list.isEmpty();
    }

    public void clear(){
        list.clear();
    }
}
