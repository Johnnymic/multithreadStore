package com.multithreadedstore.models;

public class Product {
    private String productName;
    private double productPrice;
    private int productQuantity;


    public Product(String name, double productPrice, int productQuantity) {
        this.productName = name;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
    }

    public String getName() {
        return productName;
    }


    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + productName + '\'' +
                ", ProductQuantity=" + productQuantity +
                '}';
    }
}
