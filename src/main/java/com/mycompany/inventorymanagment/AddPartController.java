package com.mycompany.inventorymanagment;

import java.io.IOException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Modality;
import com.mycompany.inventorymanagment.Inventory;
import com.mycompany.inventorymanagment.Outsourced;
import com.mycompany.inventorymanagment.MainController;
import javafx.collections.ObservableList;

/**Controller used to Control the Add Part Java FXML
 *
 * @author Cristhian Garcia
 */
public class AddPartController {
    
    public boolean rangeVaild = false;
    public boolean inventoryVaild = false;
    public int id;
    
    //AddPart JavaFXML Components
    public RadioButton typeRBtn;
    public ToggleGroup typeGroup;
    public Button saveBtn;
    public Button cancelBtn;
    public Button partDeleteBtn;
    public Button exitBtn;
    public TextField idTF;
    public TextField nameTF;
    public TextField invTF;
    public TextField priceCostTF;
    public TextField maxTF;
    public TextField minTF;
    public TextField machineCompanyTF;
    public Label machineCompanyLbl;
    public String emptyFieldText;
    public String numFieldText;
    RadioButton selectedType;
    
  
    //Alert Window
    public AlertType type = AlertType.WARNING;
    public Alert alert = new Alert(type, "");
    
    //Intitlized Objects
    
    Inventory inventory = new Inventory();
    
    
    
    
    /**
     *Receives The Inventory From the Main Controller to the Add Part Controller
     * @param inv the object that is received from other controller
     * 
     * FUTURE ENHANCEMENT: Creating a Text box for Company name that automatically completes the Companies name if it's been used before
     * RUNTIME ERROR: Would get out of index error and crash program when trying to set the last ID integer. Added if statement to check if part list is empty.
     */
    public void receiveInventoryObject(Inventory inv){
    
        inventory = inv;
        
        
        
         if(inventory.getAllParts().isEmpty()){
               
                 id = 1;
                 idTF.setText(Integer.toString(id));
           }
           else{
           
                int lastId = inventory.getAllParts().get(inventory.getAllParts().size()- 1).getId();
                
                
                System.out.println(lastId);
                id = lastId + 1;
                
                 System.out.println(id);
                idTF.setText(Integer.toString(id));
           }
    }
    
    
    /**
    *Returns to main Page and sends the original inventory to the main controller
    */
     @FXML
    private void cancelButtonClick() throws IOException {
        
         App.sendInvToController("main", inventory);
       
    }  
    
    
     /**
     *Changes The ID/Company Text For the Appropriate Part type
     */
     @FXML
    private void typeButtonClick() throws IOException {
        
        
        selectedType = (RadioButton) typeGroup.getSelectedToggle();

        if(selectedType.getText().equals("In-House")){


           machineCompanyLbl.setText("Machine ID");

        }
        else if(selectedType.getText().equals("Outsourced")){


           machineCompanyLbl.setText("Company Name");

        }
       
       
    }  
    
    
     /**
     *Adds product to the New inventory and returns to the main Controller with the New inventory. Displays Alerts if data is not properly entered
     *
     *RUNTIME ERROR: Had the Outsourced product initializing as an in-house part this caused the program to crash. Added if/else statements that check which type of part is about to be added and assigns the type of part
     *RUNTIME ERROR: Typing letters into min or max fields crashed program. added !(numFieldText.contains("Min") || numFieldText.contains("Max") to line 540.
     */
     @FXML
    private void saveButtonClick() throws IOException {
        
        int id;
        
        if(validate() == true){
           
           if(inventory.getAllParts().isEmpty()){
               
                 id = 1;
               
           }
           else{
           
                int lastId = inventory.getAllParts().get(inventory.getAllParts().size()- 1).getId();
                id = lastId + 1;

           }
          
           String name = nameTF.getText();
           int stock = Integer.parseInt(invTF.getText());
           double priceCost = Double.parseDouble(priceCostTF.getText());
           int min = Integer.parseInt(minTF.getText());
           int max = Integer.parseInt(maxTF.getText());


        
            
        
            System.out.println("All data has been Entered");
            
            if(selectedType.getText().equals("In-House")){

              int machineID = Integer.parseInt(machineCompanyTF.getText());  
              InHouse part = new InHouse(id, name, priceCost, stock, min, max, machineID);
              inventory.addPart(part);

            }
            else if(selectedType.getText().equals("Outsourced")){

                 String companyName = machineCompanyTF.getText(); 
                 Outsourced part = new Outsourced(id, name, priceCost, stock, min, max, companyName);  
                 inventory.addPart(part);
                 
                machineCompanyLbl.setText("Company Name");

            }
       
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
     * @return True if all information has been added. False if data has not been entered.
     * 
     *RUNTIME ERROR: Was getting an error when numbers were entered with symbols. Changed regex from checking if "[a-zA-Z]+" was found to checking if was not found "[0-9.]*"
     */
    public Boolean validate(){
    
        
        emptyFieldText = "";
        numFieldText = "";
        selectedType = (RadioButton) typeGroup.getSelectedToggle();
        
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
        
        if(priceCostTF.getText().isBlank()){
        
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
        if(machineCompanyTF.getText().isBlank()){
        
            if(emptyFieldText.isBlank()){
            
                if(selectedType.getText().equals("In-House"))
                
                    emptyFieldText = emptyFieldText + "Machine ID";
                
                else if (selectedType.getText().equals("Outsourced")){
                
                    emptyFieldText = emptyFieldText + "Company Name";
                
                }
            
            }
            
            else{
            
                                
                if(selectedType.getText().equals("In-House"))
                
                    emptyFieldText = emptyFieldText + ", Machine ID";
                
                else if (selectedType.getText().equals("Outsourced")){
                
                    emptyFieldText = emptyFieldText + ", Company Name";
                
                }
            
            }
        
        }
       
        
        if(!invTF.getText().matches("[0-9]*")){
        
            if(numFieldText.isBlank()){
            
                numFieldText = numFieldText + "Inventory";
            
            }
            
            else{
            
                numFieldText = numFieldText +", " + "Inventory"; 
            
            }
        
        }
        
        
        
        if(!priceCostTF.getText().matches("[0-9.]*")){
        
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
        
        
        
        if(!machineCompanyTF.getText().matches("[0-9]*")){
        
            if(numFieldText.isBlank()){
            
                if(selectedType.getText().equals("In-House")){
                
                    numFieldText = numFieldText + "Machine ID";
                
                
                
                }
            
            }
            
            else{
            
                                
                if(selectedType.getText().equals("Outsourced")){
                
                    numFieldText = numFieldText + ", Company Name";
                
               
            
                 }
        
            }
        
            
        }
        
 
       
         
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
              
         if(emptyFieldText.isBlank() && numFieldText.isBlank() && rangeVaild == true && inventoryVaild == true){
        
            
           return true; 
            
          
        }
        
        else{
            
            return false;
        }
        
    }
    
}
