package com.mycompany.inventorymanagment;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;

/**Controller used to Control the Add Product Java FXML
 *
 * @author Cristhian Garcia
 */
public class AddProductController {
    
    
    int id;
      
   //AddProduct JavaFXML Components
    public Button saveBtn;
    public Button cancelBtn;
    public Button addBtn;
    public Button removeAPartBtn;
    public Button exitBtn;
    public TextField idTF;
    public TextField nameTF;
    public TextField invTF;
    public TextField priceTF;
    public TextField maxTF;
    public TextField minTF;
    public TextField searchTF;
    public String emptyFieldText;
    public String numFieldText;
    
     //Alert Window
    public Alert.AlertType type = Alert.AlertType.WARNING;
    public Alert alert = new Alert(type, "");
    
    public boolean rangeVaild = false;
    public boolean inventoryVaild = false;
    
    //Part Table
    public TableView<Part> partsTable;
    public TableColumn <Part, Integer> partIdCol;
    public TableColumn <Part, String> partNameCol;
    public TableColumn <Part, Integer> InvCol;
    public TableColumn <Part, Double> partPriceCostCol;
    
    //Associated Parts Table
    public TableView<Part> associatePartsTable;
    public TableColumn <Part, Integer> aPartIdCol;
    public TableColumn <Part, String> aPartNameCol;
    public TableColumn <Part, Integer> aInvCol;
    public TableColumn <Part, Double> aPartPriceCostCol;
    
    //Intitlized Objects
    public Inventory inventory = new Inventory();
    public Inventory oldInventory = new Inventory();
    
    
    public Product newProduct =  new Product();
    
  
    @FXML
    
    /**
     *Receives The Inventory From the Main Controller to the Add Product Controller
     * FUTURE ENHANCEMENT:
     * 
     * @param inv the object that is received from other controller
     */
    public void receiveInventoryObject(Inventory inv){
    
        oldInventory = inv;
        inventory = inv;
               
          
        
        if(inventory.getAllProducts().isEmpty()){
               
                 id = 1;
                 idTF.setText(Integer.toString(id));
                 
           }
        
        else{

             int lastId = inventory.getAllProducts().get(inventory.getAllProducts().size()- 1).getId();        
             id = lastId + 1;
             
             idTF.setText(Integer.toString(id));
        }
        
        if(!inventory.getAllParts().isEmpty()){
         
            loadPartTable();
   
        }
        
    }
    
    /**
     * Sets all the columns in the part table and links it to the allParts list
     * 
     */
    @FXML
     public void loadPartTable(){
      
               
        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        InvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCostCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        partsTable.setItems(inventory.getAllParts());
        
        
    }
     
    /**
     * Sets all the columns in the part table and links it to the allParts list
     * 
     */
    @FXML 
    public void loadAssociatedPartTable(){
      
               
        aPartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        aPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        aInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        aPartPriceCostCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        associatePartsTable.setItems(newProduct.getAllAssociatedParts());
           
    }
    
    /**
    *Returns to main Page and sends the original inventory to the main controller
    */
    @FXML
    private void cancelButtonClick() throws IOException {
        
        App.sendInvToController("main", oldInventory);
       
    }
    
    
    /**
    *Adds product to the New inventory and returns to the main Controller with the New inventory. Displays Alerts if data is not properly entered
    */
    @FXML
    private void saveButtonClick() throws IOException {
                
        if(validate() == true){
           
                   
           String name = nameTF.getText();
           int stock = Integer.parseInt(invTF.getText());
           double priceCost = Double.parseDouble(priceTF.getText());
           int min = Integer.parseInt(minTF.getText());
           int max = Integer.parseInt(maxTF.getText());
           
           if(inventory.getAllProducts().isEmpty()){
               
                 id = 1;
               
           }
           
           else{
           
                int lastId = inventory.getAllProducts().get(inventory.getAllProducts().size()- 1).getId();
                id = lastId + 1;

           }
           

            newProduct.setId(id);
            newProduct.setName(name);
            newProduct.setPrice(priceCost);
            newProduct.setStock(stock);
            newProduct.setMin(min);
            newProduct.setMax(max);
            
            
            System.out.println(newProduct.getAllAssociatedParts().size());
            inventory.addProduct(newProduct);
            
           

            
           System.out.println(inventory.getAllProducts().get(inventory.getAllProducts().size()- 1).getName());
           
           System.out.println("All data has been Entered");
           System.out.println("Assocaited Part " + inventory.getAllProducts().get(inventory.getAllProducts().size()- 1).getAllAssociatedParts().size());
            
       
           App.sendInvToController("main", inventory);
            
           
        }
        
        else {
        
            String emptyAlert = "The following information must be entered: " + emptyFieldText + ".";
            String numFieldAlert = "Please enter numbers only for fields: " + numFieldText + ".";
            String rangeAlert = "The Minimum number must be less than the Maximum.";
            String inventoryAlert = "The Inventory must not be more than the Maximum stock or less than the Minimun Stock";    
            
            
            if(inventoryVaild == false && rangeVaild == false && !emptyFieldText.isBlank() && !numFieldText.isBlank()){
                
                alert.setHeight(300.0);
            
                alert.getDialogPane().setContentText(emptyAlert + "\n \n" + numFieldAlert + "\n \n" + rangeAlert + "\n \n" + inventoryAlert);
                
            
            }
            
            
           else if(inventoryVaild == false && !emptyFieldText.isBlank() && !numFieldText.isBlank()){
                
                alert.setHeight(250.0);
            
                alert.getDialogPane().setContentText(emptyAlert + "\n \n" + numFieldAlert + "\n \n" + inventoryAlert);
                
            
            }
            
            else if (inventoryVaild == false && rangeVaild == false && !numFieldText.isBlank()){
            
                alert.setHeight(250.0);
                alert.getDialogPane().setContentText(numFieldAlert + "\n \n" + rangeAlert + "\n \n" + inventoryAlert );
            
            }
                
            else if (inventoryVaild == false && rangeVaild == false && !emptyFieldText.isBlank()){
            
                alert.setHeight(250.0);
                alert.getDialogPane().setContentText(emptyAlert + "\n \n" + rangeAlert + "\n \n" + inventoryAlert);

            }
            
                  
          
            else if(rangeVaild == false && !emptyFieldText.isBlank() && !numFieldText.isBlank()){
                
                alert.setHeight(300.0);
            
                alert.getDialogPane().setContentText(emptyAlert + "\n \n" + numFieldAlert + "\n \n" + rangeAlert);
                
            
            }
                      
            if(!emptyFieldText.isBlank() && !numFieldText.isBlank()){
                
                alert.setHeight(250.0);
            
                alert.getDialogPane().setContentText(emptyAlert + "\n \n" + numFieldAlert);
                
            
            }
      
            else if (rangeVaild == false && !emptyFieldText.isBlank()){
            
                alert.setHeight(250.0);
                alert.getDialogPane().setContentText(emptyAlert + "\n \n" + rangeAlert);

            }
        
            else if (rangeVaild == false && !numFieldText.isBlank()){
            
                alert.setHeight(250.0);
                alert.getDialogPane().setContentText(numFieldAlert + "\n \n" + rangeAlert);
            
            }
            
            else if (inventoryVaild == false && !numFieldText.isBlank()){
            
                alert.setHeight(250.0);
                alert.getDialogPane().setContentText(numFieldAlert + "\n \n" + inventoryAlert);
            
            }
            
            
             else if (inventoryVaild == false && !emptyFieldText.isBlank()){
            
                alert.setHeight(250.0);
                alert.getDialogPane().setContentText(emptyAlert + "\n \n" + inventoryAlert);
            
            }
             else if (inventoryVaild == false && rangeVaild == false){
            
                alert.setHeight(250.0);
                alert.getDialogPane().setContentText(inventoryAlert + "\n \n" + rangeAlert);
            
            }
            
            
             
            else if (inventoryVaild == false){
            
                alert.setHeight(220.0);
                alert.getDialogPane().setContentText(inventoryAlert);
            
            }
            
            else if (rangeVaild == false){
            
                alert.setHeight(220.0);
                alert.getDialogPane().setContentText(rangeAlert);
            
            }
            
            else if (!emptyFieldText.isBlank()){
            
                alert.setHeight(220.0);
                alert.getDialogPane().setContentText(emptyAlert);

            }
            
           
            else if (!numFieldText.isBlank()){
            
                alert.setHeight(220.0);
                alert.getDialogPane().setContentText(numFieldAlert);
            
            }
            
            
           
            
            
            alert.getDialogPane().setHeaderText("Please Enter Vaild Information");
            
            alert.showAndWait();
                     
        }
             
       
    }
    /**
     *Validates That All Information Has been Entered
     *@return True if all information has been added. False if data has not been entered.
     * 
     *RUNTIME ERROR: Was getting an error when numbers were entered with symbols. Changed regex from checking if "[a-zA-Z]+" was found to checking if was not found "[0-9.]*"
     */
    @FXML
    public Boolean validate(){
            
        emptyFieldText = "";
        numFieldText = "";
        
        
        //Check Info Is Not Blank
        if(nameTF.getText().isBlank()){
        
            if(emptyFieldText.isBlank()){
            
                emptyFieldText = emptyFieldText + "Name";
            
            }
            else{
            
                emptyFieldText = emptyFieldText +", Inventory"; 
            
            }
        
        }
        
        if(invTF.getText().isBlank()){
        
            if(emptyFieldText.isBlank()){
            
            emptyFieldText = emptyFieldText + "Inventory";
            
            }
            
            else{
            
                emptyFieldText = emptyFieldText +", Inventory"; 
            
            }
        
        }
        
        if(priceTF.getText().isBlank()){
        
            if(emptyFieldText.isBlank()){
            
            emptyFieldText = emptyFieldText + "Price/Cost";
            
            }
            else{
            
                emptyFieldText = emptyFieldText +", Price/Cost"; 
            
            }
        
        }
        if(maxTF.getText().isBlank()){
        
            if(emptyFieldText.isBlank()){
            
            emptyFieldText = emptyFieldText + "Max";
            
            }
            else{
            
                emptyFieldText = emptyFieldText +", Max"; 
            
            }
        
        }
        if(minTF.getText().isBlank()){
        
            if(emptyFieldText.isBlank()){
            
            emptyFieldText = emptyFieldText + "Min";
            
            }
            
            else{
            
                emptyFieldText = emptyFieldText +", Min"; 
            
            }
        
        }
        
        
        //Check if only numbers are entered
        
        if(!invTF.getText().matches("[0-9]*")){
        
            if(numFieldText.isBlank()){
            
                numFieldText = numFieldText + "Inventory";
            
            }
            
            else{
            
                numFieldText = numFieldText +", " + "Inventory"; 
            
            }
        
        }
        
        
        
        if(!priceTF.getText().matches("[0-9.]*")){
        
            if(numFieldText.isBlank()){
            
                numFieldText = numFieldText + "Price/Cost";
            
            }
            
            else{
            
                numFieldText = numFieldText +", " + "Price/Cost"; 
            
            }
        
        }
        
     
        
        if(!maxTF.getText().matches("[0-9]*")){
        
            if(numFieldText.isBlank()){
            
                numFieldText = numFieldText + "Max";
            
            }
            
            else{
            
                numFieldText = numFieldText +", " + "Max"; 
            
            }
        
        }
        
        
        
        if(!minTF.getText().matches("[0-9]*")){
        
            if(numFieldText.isBlank()){
            
                numFieldText = numFieldText + "Min";
            
            }
            
            else{
            
                numFieldText = numFieldText +", " + "Min"; 
            
            }
        
        }
        
         
          
        System.out.println(numFieldText);
        System.out.println(emptyFieldText);
        
         
        
        
        
        
        if(!maxTF.getText().isEmpty() && !minTF.getText().isEmpty() && !(numFieldText.contains("Min") || numFieldText.contains("Max")  )){
            
            int maxStock = Integer.parseInt(maxTF.getText());
            int minStock = Integer.parseInt(minTF.getText());
            
            if(minStock > maxStock){


                rangeVaild = false;
                

            }
            
            else{


                rangeVaild = true;

            }
        }
            
               
        if(!maxTF.getText().isEmpty() && !invTF.getText().isEmpty() && !(numFieldText.contains("Max") || numFieldText.contains("Min") ||  numFieldText.contains("Inventory")  )){
            
            int maxStock = Integer.parseInt(maxTF.getText());
            int minStock = Integer.parseInt(minTF.getText());
            int inv = Integer.parseInt(invTF.getText());

            if(inv > maxStock || inv < minStock){

               
                inventoryVaild = false;
               

            }
            
            else{


                inventoryVaild = true;

            }
        }
        
        System.out.println(rangeVaild);
              
         if(emptyFieldText.isBlank() && numFieldText.isBlank() && rangeVaild == true && inventoryVaild == true ){
        
            
           return true; 
            
          
        }
        
        else{
            
            return false;
        }
        
     

    }
    
    
     /**
     * Add Part to the associated Part List
     * 
     * RUNTIME ERROR: Clicking the add button if no part was selected would crash the program. added an if statement that confirms a part was selected before trying to add the part.
     */   
    @FXML
    public void addButtonClick(){
    
        if(partsTable.getSelectionModel().getSelectedItem() != null){
                  
            newProduct.addAssociatedPart(partsTable.getSelectionModel().getSelectedItem());
          
            loadAssociatedPartTable();
           
        }
    
    
    }
    
    /**
     * Remove Part From associated Part List
     * 
     * 
     * RUNTIME ERROR: Clicking the remove button if no part was selected would crash the program. added an if statement that confirms a part was selected before trying to remove the part.
     */
    @FXML
    public void removeAssociated(){
    
        
        if(associatePartsTable.getSelectionModel().getSelectedItem() != null){
             
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure you want to Remove this Associated Part?","Warning",dialogButton);
        
            if(dialogResult == JOptionPane.YES_OPTION){
                
            
                newProduct.deleteAssociatedPart(associatePartsTable.getSelectionModel().getSelectedItem());
                 loadAssociatedPartTable();
           

            }
        } 
    }
    
    /**
     * Retrieves parts that match the String in the search Text field and sets the table to show found parts
     * 
     */ 
    @FXML
     private void partSearch() throws IOException {
        
        
        if(inventory.getAllParts() != null){
            ObservableList<Part> foundPartList = FXCollections.observableArrayList();
            String searchResult = searchTF.getText();
            boolean found = false;

            if(searchTF.getText().isBlank()){

                  partsTable.setItems(inventory.getAllParts());
            }

            else if(searchResult.matches("[0-9]*")){


              Part foundPart;
              foundPart = inventory.lookupPart(Integer.parseInt(searchResult));



              if(foundPart != null){
                  foundPartList.add(foundPart);
                  partsTable.setItems(foundPartList);
                  associatePartsTable.getSelectionModel().selectFirst();
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


                    searchTF.setText("");
                    partsTable.setItems(inventory.getAllParts());

                }

                else{
                    partsTable.setItems(foundPartList);
                }
            }
        }
      
    }
    
}
