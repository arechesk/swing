package com.netcracker.library;


public class Book implements java.io.Serializable {
    private String name;
    private String author;
    private double price;
    private int qty=0;

    public Book(double price, String author, String name) {
        this.price = price;
        this.author = author;
        this.name = name;
    }

    public Book(String name,   String author,double price,int qty) {
        this.name = name;
        this.qty = qty;
        this.price = price;
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public int getQty() {
        return qty;
    }

    public double getPrice() {
        return price;
    }

    public String getAuthor() {
        return author;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", author=" + author +
                ", price=" + price +
                ", qty=" + qty +
                '}';
    }
}
