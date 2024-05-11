
package com.mycompany.inventorymanagment;

import java.io.IOException;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**Controller used to Control the Modify Part Java FXML
 *
 * @author Cristhian Garcia
 */
public class ModifyPartController {
    
    public boolean rangeVaild = false;
    public boolean inventoryVaild = false;
    
    
    //Part JavaFXML Components
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
    public RadioButton selectedType;
    public  RadioButton inHouseRBtn;
    public RadioButton outsourceRBtn;
    
   
    
    
    
    //Alert Window
    public Alert.AlertType type = Alert.AlertType.WARNING;
    public Alert alert = new Alert(type, "");
    
    //Objects

    public Part currentPart;
    Inventory inventory = new Inventory();
    
    
    /**
     *Receives The Inventory and selected parts ID From the Main Controller and sets the current part.
     * @param inv the object that is received from other controller.
     * @param id The selected parts ID
     * 
     * 
     */
    @FXML
    public void receiveInventoryPartObject(Inventory inv, int id){
    
        
        
        System.out.println("the Id is" + id);
        inventory = inv;
        currentPart = inv.lookupPart(id);
        boolean isInHouse = InHouse.class.isAssignableFrom(currentPart.getClass());
        
        idTF.setText(Integer.toString(id));
        System.out.println(currentPart.getName());
        nameTF.setText(currentPart.getName());
        invTF.setText(Integer.toString(currentPart.getStock()));
        priceCostTF.setText(Double.toString(currentPart.getPrice()));
        maxTF.setText(Integer.toString(currentPart.getMax()));
        minTF.setText(Integer.toString(currentPart.getMin()));
        
        if(isInHouse == true){
            
            int machineID = ((InHouse)currentPart).getMachineID();
            
            machineCompanyTF.setText(Integer.toString(machineID));         
            inHouseRBtn.setSelected(true);
            machineCompanyLbl.setText("Machine ID");
        }
        else{
        
            machineCompanyTF.setText(((Outsourced)currentPart).getCompanyName());
            outsourceRBtn.setSelected(true);
            machineCompanyLbl.setText("Company Name");
        }
        
    }
    
      
    /**
    *Returns to main Page and sends the inventory to the main controller
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
     *Adds new Part to the Inventory. Displays Error Popup If data is not properly entered
     * 
     * RUNTIME ERROR: Program would give a object type error as I Had the outsourced Product initializing as an in house part. Changed line 176 from creating an in-house product to an outsourced product.
     * LOGIC ERROR: Forgot to put - 1 for part id this caused for the wrong part to be updated
     */
     @FXML
    private void saveButtonClick() throws IOException {
        ;
        
        if(validate() == true){
           
           
           int id = currentPart.getId();
           String name = nameTF.getText();
           int stock = Integer.parseInt(invTF.getText());
           double priceCost = Double.parseDouble(priceCostTF.getText());
           int min = Integer.parseInt(minTF.getText());
           int max = Integer.parseInt(maxTF.getText());


        
            
        
            System.out.println("All data has been Entered");
            
            if(selectedType.getText().equals("In-House")){

              int machineID = Integer.parseInt(machineCompanyTF.getText());  
              InHouse part = new InHouse(id, name, priceCost, stock, min, max, machineID);
              inventory.updatePart(id -1, part);

            }
            else if(selectedType.getText().equals("Outsourced")){

                 String companyName = machineCompanyTF.getText(); 
                 Outsourced part = new Outsourced(id, name, priceCost, stock, min, max, companyName);  
                 inventory.updatePart(id - 1, part);
                 
               

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
    
    @FXML
    
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
        

        if(!maxTF.getText().isEmpty() && !minTF.getText().isEmpty() && !(numFieldText.contains("Min") || numFieldText.contains("Max") )){
            
            int maxStock = Integer.parseInt(maxTF.getText());
            int minStock = Integer.parseInt(minTF.getText());
            
            if(minStock > maxStock){


                rangeVaild = false;
            }
            
            else{


                rangeVaild = true;

            }
        }
        
        
        
        if(!maxTF.getText().isEmpty() && !invTF.getText().isEmpty() && !(numFieldText.contains("Max") && numFieldText.contains("Min") &&  numFieldText.contains("Inventory")) && !(emptyFieldText.contains("Max") || emptyFieldText.contains("Min") ||  emptyFieldText.contains("Inventory"))){
            
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
                   
              
         if(emptyFieldText.isBlank() && numFieldText.isBlank() && rangeVaild == true && inventoryVaild == true){
        
            
           return true; 
            
          
        }
        
        else{
            
            return false;
        }
        
                 
         
     

    }
    
}
