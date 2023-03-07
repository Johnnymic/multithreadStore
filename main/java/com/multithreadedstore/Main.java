package com.multithreadedstore;

import com.multithreadedstore.models.Customer;
import com.multithreadedstore.models.Product;
import com.multithreadedstore.models.Store;
import com.multithreadedstore.service.StoreImplementation;

import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {

        Product p1 = new Product("rice", 12000, 10);
        Product p2 = new Product("bean", 10000, 10);
        Product p3 = new Product("fish", 12000, 10);
        Product p4 = new Product("bread", 1000, 10);
        Product p5 = new Product("onion", 2000, 10);
        List<Product> products = new ArrayList<>(List.of(p1, p2, p3, p4, p5));

        Store store = new Store("BestBUY", products, 20000);
       StoreImplementation storeImplementation =  new StoreImplementation(store);


        Customer tony = new Customer("Tony", 1_200, storeImplementation);
        Customer john = new Customer("John", 200_000, storeImplementation);
        Customer isaac = new Customer("Isaac", 500_000, storeImplementation);
        Customer peter = new Customer("peter", 100_000, storeImplementation);

        tony.addToCart(p1, 2);
        tony.addToCart(p2, 2);
        tony.addToCart(p3, 1);

        john.addToCart(p1, 4);

        isaac.addToCart(p4, 3);

        peter.addToCart(p3, 5);
        peter.addToCart(p5, 2);

        Thread t1 = new Thread(tony, tony.getName());
        Thread t2 = new Thread(john, john.getName());
        Thread t3 = new Thread(isaac, isaac.getName());
        Thread t4 = new Thread(peter, peter.getName());

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Store products: " + store.getProductList());
        System.out.println("Store balance: #" + store.getStoreBalance());


    }
}