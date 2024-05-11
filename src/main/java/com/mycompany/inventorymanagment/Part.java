package com.mycompany.inventorymanagment;

/**Represents a Part that with id, names, price, stock, min and max
 *
 * @author Cristhian Garcia
 * FUTURE ENHANCEMENT add a simple printAll method that displays all the parts variable into a console to allow for easy bug testing
 */
public abstract class Part {
    
    //Part variables 
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;    
    
    
    
    
     /** Creates a Part with the specified id, price, stock, min, and max.
     * @param id The Part’s id.
     * @param name The Part's name.
     * @param price The Part’s price.
     * @param stock The amount of Parts in stock.
     * @param min The minimum amount of Parts in stock.
     * @param max  The maximum amount of Parts in stock.
     * RUNTIME ERROR: Did not add "this" statement when assigning values to the object. This caused the constructor to not actually assign values
     * 
    */
    public Part(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }
    
    /**
     * @return the stock
     */
    public int getStock() {
        return stock;
    }

    /**
     * @param stock the stock to set
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * @return the min
     */
    public int getMin() {
        return min;
    }

    /**
     * @param min the min to set
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * @return the max
     */
    public int getMax() {
        return max;
    }

    /**
     * @param max the max to set
     */
    public void setMax(int max) {
        this.max = max;
    }
    
}