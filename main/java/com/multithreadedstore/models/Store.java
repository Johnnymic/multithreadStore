package com.multithreadedstore.models;

import java.util.List;


public class Store {
    private  String storeName;
    private  List<Product> productList;
    public double balance;


    public Store(String name, List<Product> storeProducts, double storeBalance) {
        this.storeName = name;
        this.productList = storeProducts;
        this.balance = storeBalance;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public double getStoreBalance() {
        return balance;
    }

}
