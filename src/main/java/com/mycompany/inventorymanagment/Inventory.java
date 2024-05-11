

package com.mycompany.inventorymanagment;

import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

/** Represents an inventory that holds parts and products.
 *
 * 
 * 
 * @author Cristhian Garcia
 * FUTURE ENHANCEMENT: Create a Delete all Product method that removes all products or all the products that match the selected product
 * 
 */
public class Inventory {
    
    //Inventory variables
    
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    
    
    
    
    
    /** Adds a new Part to the Inventory Part list
    * @param newPart The Part Object to be added to the Inventory
    */
    public static void addPart(Part newPart){
    
    
        allParts.add(newPart);
    
    }
    
    /** Adds a new Product to the Inventory Product list
    * @param newProduct The Product Object to be added to the Inventory
    */
    public static void addProduct(Product newProduct){
    
   
        allProducts.add(newProduct);
    
    }
    
    
    
    /** Searches Part Inventory by PartID
    * @param partID The Part ID to search for
    * @return Part Object
    */
    public static Part lookupPart(int partID){
    
        
        for(int i = 0; i < allParts.size(); i++){
        
        
            if(allParts.get(i).getId() == partID){
            
                return allParts.get(i);
            
            }
                
        }
   
        return null;
    
    
    }
    
    
    /** Searches Product Inventory by ProductID
    * @param productID The Product ID to search for
    * @return Product Object
    */
    public static Product lookupProduct(int productID){
    
        
        for(int i = 0; i < allProducts.size(); i++){
        
        
            if(allProducts.get(i).getId() == productID){
            
                return allProducts.get(i);
            
            }
                
        }
   
        return null;
    
        
    }


    /** Searches Part Inventory by PartName
    * @param partName The part Name to search for
    * @return ObservableList of Parts that match name
    * 
    *  LOGIC ERROR: No parts were found if the user types in a capital version of a letter. set the part name and current part name to uppercase when checking for a match
    */
    public static ObservableList<Part> lookupPart(String partName){
    
        FilteredList<Part> filteredParts = new FilteredList<>(allParts);
        Predicate<Part> nameSearch = i -> i.getName().toUpperCase().contains(partName.toUpperCase());
        
        filteredParts.setPredicate(nameSearch);
         
        return filteredParts;
    
    
    }
    
    /** Searches Product Inventory by productName
    * @param productName The Product Name to search for
    * @return ObservableList of Products that match name
    * 
    * LOGIC ERROR: No products were found if the user types in a capital version of a letter. set the product name and current products name to uppercase when checking for a match
    */
    public static ObservableList<Product> lookupProduct(String productName){
    
    
        FilteredList<Product> filteredProducts = new FilteredList<>(allProducts);
        Predicate<Product> nameSearch = i -> i.getName().toUpperCase().contains(productName.toUpperCase());

        filteredProducts.setPredicate(nameSearch);

        return filteredProducts;


        
        
    }
    
    
    /** Updates existing Part with a new Part
    * @param index the location of the existing Part
    * @param selectedPart The new Part that will replace the existing Part
    */
    public static void updatePart(int index, Part selectedPart){
    
    
        allParts.set(index, selectedPart);
    
    }
    
    /** Updates existing Product with a new Product
    * @param index the location of the existing Product
    * @param selectedProduct The new Product that will replace the existing Product
    */
    public void updateProduct(int index, Product selectedProduct){
    
    
        allProducts.set(index, selectedProduct);
    
    }
    
    
    /** Deletes Part from the Part Inventory 
    * @param selectedPart the Part that will be deleted
    * @return True if part was successfully deleted, Otherwise returns false.
    */
    public static boolean deletePart(Part selectedPart){
    
    
        return allParts.remove(selectedPart);
    
    }
    
    /** Deletes Product from the Product Inventory 
    * @param selectedProduct the Product that will be deleted
    * @return True if Product was successfully deleted, Otherwise returns false.
    */
    public static boolean deleteproduct(Product selectedProduct){
    
    
        return allProducts.remove(selectedProduct);
    
    }
    
     /** Receives All Parts from the Inventory 
    * @return allParts.
    */
    public static ObservableList<Part> getAllParts(){
        
        
        return allParts;
    
    }
    
    
     /** Receives All Products from the Inventory 
    * @return allProducts.
    */
    
    public static ObservableList<Product> getAllProducts(){
        
        
        return allProducts;
    
    }


    


}

 