
package com.mycompany.inventorymanagment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**Represents a Product that with id, names, price, stock, min and max
 *
 * @author Cristhian Garcia
 *RUNTIME ERROR: Had accidentally set all the variables to static so when ever I created a new product it would duplicate at replace the existing products
 */

public class Product {
    
    
    //Product variables
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int id; 
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;
    
    

    /** Creates a Product with the specified id, price, stock, min, and max.
     * @param id The products’s id.
     * @param name The product's name.
     * @param price The product’s price.
     * @param stock The amount of products in stock.
     * @param min The minimum amount of products in stock.
     * @param max  The maximum amount of products in stock.
     * RUNTIME ERROR: Did not add "this" statement when assigning values to the object. This caused the constructor to not actually assign values
     * 
    */
     public Product(int id, String name, double price, int stock, int min, int max){

        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;

    }
     
     /** Creates a Blank Product with default Values
     * 
     * RUNTIME ERROR: Created this constructor because I was unable to set the associated 
     */
     public Product(){
         
        id = 0; 
        name = "";
        price = 0;
        stock = 0;
        min = 0;
        max = 0;

     
    }

    /** Sets the product id 
     * @param id The product’s id.
     */

    public void setId(int id){


        this.id = id;

    }

    /** Sets the product name 
     * @param name The product’s name.
     */
    public void setName(String name){


         this.name = name;


    }
    /** Sets the product price 
    * @param price The product’s price.
    */

    public void setPrice(double price){


         this.price = price;

    }

    
   /** Sets the product stock 
    * @param stock The product’s stock.
    */ 

    public void setStock(int stock){


         this.stock = stock;

    }
    
    /** Sets the minimum amount for stock
     * @param min The minimum amount of products in stock.
     */
    public void setMin(int min){

        this.min = min;

    }
    
    /** Sets the maximum amount for stock
     * @param max The maximum amount of products in stock.
     */
    public void setMax(int max){


        this.max = max;

    }

    /** Gets the product id 
     * @return Product ID
     */

    public int getId(){


        return id;

    }

    /** Gets the product name 
     * @return Product Name 
     */
    public String getName(){


        return name;

    }

    /** Gets the product price  
    * @return Product Price
    */

    public double getPrice(){


        return price;

    }
    
    /** Gets the product stock 
    * @return Product Stock
    */ 
    public int getStock(){


        return stock;

    }

     /** Gets the minimum amount for stock
     * @return Product Stock Minimum
     */
    public int getMin(){


        return min;

    }
    
    /** Gets the maximum amount for stock
     * @return Product Stock Maximum
     */

    public int getMax(){


        return max;

    }
    
    /** Adds a part object into the associatedParts List
     * @param part The part object that will be added
     */
    public void addAssociatedPart(Part part){

        
        associatedParts.add(part);
        

    }
    
    /** Removes a part object from the associatedParts List
     * @param selectedAssociatedPart The selected part that will be removed.
     * @return True if the Part has been deleted. Otherwise False
     */
    public boolean deleteAssociatedPart(Part selectedAssociatedPart){

    
       return associatedParts.remove(selectedAssociatedPart);
       

    }
    
    /** Gets all Associated Parts
     * @return associatedParts Observable List
     */
    public ObservableList<Part> getAllAssociatedParts(){


       return(associatedParts);
        
    }



    
}
