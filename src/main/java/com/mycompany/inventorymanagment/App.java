package com.mycompany.inventorymanagment;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import com.mycompany.inventorymanagment.Inventory;

/** Sets up the Scene, Stage, Window and sends Inventory Objects to controllers
 *
 * @author Cristhian Garcia
 */
public class App extends Application {

    private static Scene scene;
    

    /**
    *Creates the Main FXML Scene and sets and shows the provided stage
    * 
    * @param stage the stage that will be used for this program 
    */
    @Override
    public void start(Stage stage) throws IOException {

       
        scene = new Scene(loadFXML("main"));
        stage.setScene(scene);
        
        stage.show();
    }
    
    
    /**
    *Sends the current inventory from one controller to another
    * 
    * @param fxml the FXML that will be loaded and transferred to 
    * @param inv the Inventory object to send to the controller
    */
    static void sendInvToController(String fxml, Inventory inv) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource( "/fxml/"+ fxml + ".fxml"));
        fxmlLoader.load();
        
        if(fxml.equals("addPart")){
        
            AddPartController Controller = fxmlLoader.getController();
            Controller.receiveInventoryObject(inv);
        }
        if(fxml.equals("main")){
        
            MainController Controller = fxmlLoader.getController();
            
            System.out.println("---------------Received at App");
            Controller.receiveInventoryObject(inv);
            System.out.println("---------------Sent to main");
        }
         if(fxml.equals("addProduct")){
        
            AddProductController Controller = fxmlLoader.getController();
            
            System.out.println("---------------Received at App");
            Controller.receiveInventoryObject(inv);
            System.out.println("---------------Sent to main");
        }
        scene.setRoot(fxmlLoader.getRoot());
        scene.getWindow().sizeToScene();
        
        
    }
    
    /**
    *Sends the current inventory and Product/Part ID from one controller to another
    * 
    * @param fxml the FXML that will be loaded and transferred to 
    * @param inv the Inventory object to send to the controller
    * @param id the ID for the part or product that will be modified 
    */
    
    static void sendInvIdToController(String fxml, Inventory inv, int id) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource( "/fxml/"+ fxml + ".fxml"));
        fxmlLoader.load();
        
        if(fxml.equals("modifyPart")){
        
            ModifyPartController Controller = fxmlLoader.getController();
            System.out.println("the Id is " + id);
            Controller.receiveInventoryPartObject(inv, id);
        }
        if(fxml.equals("modifyProduct")){
            ModifyProductController Controller = fxmlLoader.getController();
            System.out.println("the Id is " + id);
            Controller.receiveInventoryProductObject(inv, id);
        }
        if(fxml.equals("main")){
        
            MainController Controller = fxmlLoader.getController();
            
            System.out.println("---------------Received at App");
            Controller.receiveInventoryObject(inv);
            System.out.println("---------------Sent to main");
        }
        scene.setRoot(fxmlLoader.getRoot());
        scene.getWindow().sizeToScene();
        
        
    }
     /**
    *Sets the fxmlLoader to the the passed fxml String and loads it
    * 
    * @param fxml the FXML that will be loaded
    * 
    * 
    * RUNTIME ERROR: Received Location not set Error. Fixed by adding "/fxml/" + to the beginning of the getResource method call
    */
// 
    static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource( "/fxml/"+ fxml + ".fxml"));
        return fxmlLoader.load();
    }
    
   

    public static void main(String[] args) {
        launch();
    }

}