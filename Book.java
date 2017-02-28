/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a1nesbittr;

/**
 *
 * @author Ryan
 */
public class Book {
    
    private final String productID;
    private final int year;
    private final float price;
    private final String name;
    private final String author;
    private final String publisher;
    
    
    
    Book(String id, int year, float price, String name, String author, String publisher) {

        this.productID = id;
        this.year = year;
        this.price = price;
        this.name = name;
        this.author = author;
        this.publisher = publisher;
    }
    
    /**
     *
     * @return name of book (string)
     */
    public String getName() {
        return this.name;
    }

    /**
     *
     * @return product ID of book (string)
     */
    public String getID() {
        return this.productID;
    }

    /**
     *
     * @return author of book (string)
     */
    public String getAuthor() {
        return this.author;
    }

    /**
     *
     * @return publisher of book (string)
     */
    public String getPub() {
        return this.publisher;
    }

    /**
     *
     * @return year of book (integer)
     */
    public int getYear() {
        return this.year;
    }

    /**
     *
     * @return price of book (float)
     */
    public float getPrice() {
        return this.price;
    }
}
