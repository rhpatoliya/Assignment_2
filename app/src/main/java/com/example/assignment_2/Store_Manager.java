package com.example.assignment_2;

import java.util.ArrayList;
import java.util.List;

public class Store_Manager {
    public ArrayList<Product> productList;

    public Store_Manager() {

        this.productList = new ArrayList<>(3);
        this.productList.add( new Product("Pante", 20.44, 10));
        this.productList.add( new Product("Shoes", 10.44, 100));
        this.productList.add( new Product("Hats", 5.9, 30));

    }

    public boolean checkAvailability(Product p, int requestedQ){
        return p.getProduct_quentity() >= requestedQ;
    }

}
