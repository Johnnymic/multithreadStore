package com.multithreadedstore.models;

import com.multithreadedstore.service.StoreImplementation;

import java.util.ArrayList;
import java.util.List;

public class Customer implements Runnable{
    private final String name;
    private final List<Product> cart;
    private double customerBalance;
    private StoreImplementation implementation;



    public Customer(String name, double customerBalance, StoreImplementation implemenation) {
        this.name = name;
        this.customerBalance = customerBalance;
        this.implementation = implemenation;
        this.cart = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addToCart(Product product, int quantity) {
        Product newProduct = new Product(product.getName(), product.getProductPrice(), quantity);
        cart.add(newProduct);
    }


    public List<Product> getCart() {
        return cart;
    }

    public double getCustomerBalance() {
        return customerBalance;
    }

    public void setCustomerBalance(double customerBalance) {
        this.customerBalance = customerBalance;
    }

    public boolean makePayment(double amount) {
        return customerBalance >= amount;
    }


    public void purchaseItems(){
        try {
            implementation.receiveOrders(this);
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        purchaseItems();
        String message = String.format("%s bought the following  ...", name );
        System.out.println(message + " ");
    }
}
