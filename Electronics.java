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
public class Electronics {
   

    private final String productID;
    private final int year;
    private final float price;
    private final String name;
    private final String maker;
    
    Electronics(String id, int year, float price, String name, String maker) {
            
       this.productID = id;
       this.name = name;
       this.price = price;
       this.year = year;
       this.maker = maker;
    }
     
    /**
     * 
     * @return name of electronic (string)
     */
    public String getName() {
        return this.name;
    }

    /**
     *
     * @return productID of electronic (string)
     */
    public String getID() {
        return this.productID;
    }

    /**
     *
     * @return maker of electronic (string)
     */
    public String getMaker() {
        return this.maker;
    }

    /**
     *
     * @return year of electronic (integer)
     */
    public int getYear() {
        return this.year;
    }

    /**
     *
     * @return price of electronic (float)
     */
    public float getPrice() {
        return this.price;
    }
}
