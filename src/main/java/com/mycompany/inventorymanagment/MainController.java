package com.mycompany.inventorymanagment;

import java.io.IOException;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;

/** Controller Used to Control the main Java FXML
 * 
 *
 * @author Cristhian Garcia
 */
public class MainController {
    
    //Main JavaFXML Components
    public Button partAddBtn;
    public Button partModifyBtn;
    public Button partDeleteBtn;
    public Button exitBtn;
    public TextField partsSearchText;
    public TextField productSearchText;
    
    
    //Part Table
    public TableView<Part> partsTable;
    public TableColumn <Part, Integer> partIdCol;
    public TableColumn <Part, String> partNameCol;
    public TableColumn <Part, Integer> InvCol;
    public TableColumn <Part, Double> partPriceCostCol;
    
    //Products Table
    public TableView<Product> productsTable;
    public TableColumn <Product, Integer> productIdCol;
    public TableColumn <Product, String> productNameCol;
    public TableColumn <Product, Integer> productInvCol;
    public TableColumn <Product, Double> prodcutPriceCostCol;

     //Intitlized Objects
    public Inventory inventory = new Inventory();
    
    
    /**
     *Adds all parts and products into the tables on the launch of the program
     */
    @FXML
    
     public void initialize(){
    
      
            
        loadTable();
            
        
        
    }
     
     
    @FXML
      /**
     * Sets all the columns in the part and product table and links it to the allParts and allProducts list
     * 
     */
     public void loadTable(){
     
        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        InvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCostCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        partsTable.setItems(inventory.getAllParts());

              

        productIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        prodcutPriceCostCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        productsTable.setItems(inventory.getAllProducts());
         
     
     }
   
    
    
    /**
     *Sends and Receives The Inventory From the Add Part Controller to the Main Controller
     * @param inv the object that is received from other controller
     */
    @FXML
    
    public void receiveInventoryObject(Inventory inv){
    
        System.out.println("---------------Received at main");
        inventory = inv;
        initialize();
    }
    
    
     /**
     *Shows Confirmation window and closes program if user selects yes
     * 
     */
     @FXML
    public void exitButtonClick(){
        
        int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure you want to Close This Program?","Warning",dialogButton);
            
            if(dialogResult == JOptionPane.YES_OPTION){

                Platform.exit();
       

            }
       
    }  
     
    /**
     * Sets The Scene To The Add Part Window and sends the inventory object over to the AddPartController
     */
    
    @FXML
    
    private void addPartButtonClick() throws IOException {
        
       
         App.sendInvToController("addPart", inventory);
        
    }
    
    
     
     /**
     * Sets The Scene To The Modify Part Window and sends the inventory object/ Current part id over to the ModifyPartController
     * RUNTIME ERROR: RUNTIME ERROR: Clicking the add button if no part was selected would crash the program. added an if statement that confirms a part was selected before trying to add the part.
     */
    
     @FXML
    private void modifyPartButtonClick() throws IOException {
        
        
        if(partsTable.getSelectionModel().getSelectedItem() != null){
        
            System.out.print("The ID is ");
            System.out.println(partsTable.getSelectionModel().getSelectedItem().getId());
            
            App.sendInvIdToController("modifyPart", inventory,partsTable.getSelectionModel().getSelectedItem().getId());
         
        }
       
    }
    
    /**
     * Displays Confirmation window and Deletes selected Part if user chooses yes
     * LOGIC ERROR: Orginally the part would not actually delete from the list after a user searched for the part and delete it. Fixed this by adding "Inventory.deletePart" instead of only removing the part from the table
     * 
     */
     @FXML
    private void deletePartButtonClick() throws IOException {
        
        int dialogButton = JOptionPane.YES_NO_OPTION;
       
        if(partsTable.getSelectionModel().getSelectedItem() != null){
            
            
           
            int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure you want to delete this Part?","Warning",dialogButton);
            if(dialogResult == JOptionPane.YES_OPTION){

                inventory.deletePart(partsTable.getSelectionModel().getSelectedItem());
                partsSearchText.setText("");
                productSearchText.setText("");
                loadTable();
                

            }
        }

        
    }
    
    
    @FXML
     /**
     * Sets The Scene To The Add Product Window and sends the inventory object over to the AddProductController
     */
    private void addProductButtonClick() throws IOException {
        
       
             
        App.sendInvToController("addProduct", inventory);
       
       
    }
    
    
     /**
     * Sets The Scene To The Modify Product Window and sends the inventory object/ Current product id over to the ModifyProductController
     * RUNTIME ERROR: RUNTIME ERROR: Clicking the add button if no product was selected would crash the program. added an if statement that confirms a product was selected before trying to add the product.
     */
     @FXML
    private void modifyProducttButtonClick() throws IOException {
        
         if(productsTable.getSelectionModel().getSelectedItem() != null){
                   
            App.sendInvIdToController("modifyProduct", inventory,productsTable.getSelectionModel().getSelectedItem().getId());
         
        }
       
    }
    
    /**
     * Displays Confirmation window and Deletes selected Product if user chooses yes
     * LOGIC ERROR: Orginally the product would not actually delete from the list after a user searched for the product and delete it. Fixed this by adding "Inventory.deleteProduct" instead of only removing the product from the table
     * 
     */
     @FXML
    private void deleteProductButtonClick() throws IOException {
        
       
         if(productsTable.getSelectionModel().getSelectedItem() != null){
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure you want to delete this Product?","Warning",dialogButton);
            if(dialogResult == JOptionPane.YES_OPTION){

                productsTable.getItems().removeAll(productsTable.getSelectionModel().getSelectedItem());

            }
         }
    }
    
     /**
     * Retrieves parts that match the String in the search Text field and sets the table to show found parts
     * 
     * 
     */ 
     @FXML
    private void partSearch() throws IOException {
        
        
        if(inventory.getAllParts() != null){
            ObservableList<Part> foundPartList = FXCollections.observableArrayList();
             String searchResult = partsSearchText.getText();
            boolean found = false;

            if(partsSearchText.getText().isBlank()){

                  partsTable.setItems(inventory.getAllParts());
            }

            else if(searchResult.matches("[0-9]*")){


              Part foundPart;
              foundPart = inventory.lookupPart(Integer.parseInt(searchResult));



              if(foundPart != null){
                  foundPartList.add(foundPart);
                  partsTable.setItems(foundPartList);
                  partsTable.getSelectionModel().selectFirst();
                  found = true;
              }

            }
             if(found == false){
                foundPartList = inventory.lookupPart(searchResult);
                if(foundPartList.isEmpty()){

                    Alert.AlertType type = Alert.AlertType.WARNING;
                    Alert alert = new Alert(type, "");
                    alert.setHeight(250.0);
                    alert.getDialogPane().setContentText("No results found!");
                    alert.showAndWait();


                    partsSearchText.setText("");
                    partsTable.setItems(inventory.getAllParts());

                }

                else{
                    partsTable.setItems(foundPartList);
                }
            }
        }
      
    }

    /**
     * Retrieves Products that match the String in the search Text field and sets the table to show found products
     * 
     */ 
     @FXML
     private void productSearch() throws IOException {
        ObservableList<Product> foundProducttList = FXCollections.observableArrayList();
        String searchResult = productSearchText.getText();
        boolean found = false;
        
        
        if(inventory.getAllProducts() != null){ 
            if(searchResult.isBlank()){

                  productsTable.setItems(inventory.getAllProducts());
            }

            else if(searchResult.matches("[0-9]*")){


              Product foundProduct;
              foundProduct = inventory.lookupProduct(Integer.parseInt(searchResult));



              if(foundProduct != null){
                  foundProducttList.add(foundProduct);
                  productsTable.setItems(foundProducttList);
                  productsTable.getSelectionModel().selectFirst();
                  found = true;
              }

            }
             if(found == false){
                foundProducttList = inventory.lookupProduct(searchResult);
                if(foundProducttList.isEmpty()){

                    Alert.AlertType type = Alert.AlertType.WARNING;
                    Alert alert = new Alert(type, "");
                    alert.setHeight(250.0);
                    alert.getDialogPane().setContentText("No results found!");
                    alert.showAndWait();


                    productSearchText.setText("");
                    productsTable.setItems(inventory.getAllProducts());

                }

                else{
                    productsTable.setItems(foundProducttList);
                }
            }
        }
      
    }

    
   
    
   
}

    

