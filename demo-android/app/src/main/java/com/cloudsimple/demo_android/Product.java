package com.cloudsimple.demo_android;

/**
 * Created by tomek on 3/17/16.
 */
public class Product {
    public final int id;
    public final String name;
    public final String category;
    public final String price;
    public final String description;

    public Product(int id, String name, String category, String price, String description) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.description = description;
    }
}
