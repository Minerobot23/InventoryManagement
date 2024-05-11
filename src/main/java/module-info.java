/* doesn't work with source level 1.8:
module com.mycompany.inventorymanagment {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.inventorymanagment to javafx.fxml;
    exports com.mycompany.inventorymanagment;
}
*/
