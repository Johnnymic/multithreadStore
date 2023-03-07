package com.multithreadedstore.service;

import com.multithreadedstore.models.Customer;
import com.multithreadedstore.models.Store;
import com.multithreadedstore.models.Product;

import java.util.List;

public class StoreImplementation implements StoreServices{


    Store store;

    public StoreImplementation(Store store) {
        this.store = store;
    }

    public synchronized void receiveOrders(Customer customer) {
        try {
            Thread.sleep(2000);
            System.out.println("Receiving order from " + customer.getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        double totalCostOfItem = 0.0;
        List<Product> cart = customer.getCart();
        for (Product item : cart) {
            totalCostOfItem += item.getProductPrice() * item.getProductQuantity();
        }
        boolean canAfford = customer.makePayment(totalCostOfItem);
        if (canAfford) {
            processOrders(cart);
            customer.setCustomerBalance(customer.getCustomerBalance() - totalCostOfItem);
            store.balance += totalCostOfItem;
            System.out.println( customer.getName() +" bought the following items " + customer.getCart());
            System.out.println(customer.getName() + " is done purchasing  and his balance is: " + customer.getCustomerBalance());
        } else {
            throw new RuntimeException("Sorry you don't have enough funds to buy these products.");
        }
    }
    public synchronized void processOrders(List<Product> cart) {
        try {
            Thread.sleep(500);
            System.out.println("Processing product fro the customer " );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (Product item : cart) {
            for (Product storeProduct : store.getProductList()) {
                if (item.getName().equals(storeProduct.getName())) {
                    storeProduct.setProductQuantity(storeProduct.getProductQuantity() - item.getProductQuantity());
                    break;
                }
            }
        }
        System.out.println("Processing order done: store balance is "+ store.balance);
    }
}
