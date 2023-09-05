package main.sellefson;

/* Imports */
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model_classfiles.InHouse;
import model_classfiles.Inventory;
import model_classfiles.Outsourced;
import model_classfiles.Product;
import java.io.IOException;
import static model_classfiles.Inventory.addProduct;

/** This class is the main class and launches the initial main screen.
* <b> THE JAVADOCS FOLDER IS LOCATED AT /Sellefson/src/Javadocs. </b>
* <p> This class begins the program titled Inventory Management System. </p>
* <p><b>
FUTURE ENHANCEMENT:
A future enhancement that could be implemented in the inventory management system would be the ability to view all fields of a part from modify or add product pages.
This would allow the user to select a part row and push a button, which would cause a pop out box containing the rest of the fields to appear (the minimum and maximum values and the company name or machine id).
The user could then close the part window and continue modifying or adding a product. </b></p>
* @author Sage Ellefson
*/
public class Main extends Application {


/** This start method opens the initial stage window.
* @param stage Initial stage.
* @throws IOException From FXMLLoader.*/
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/View/Main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 845, 600);
        stage.setTitle("Inventory Management System");
        stage.setScene(scene);
        stage.show();
    }


/** This main method includes the test data for the project and launches the project.
* @param args Args */
    public static void main(String[] args) {

        // DUMMY DATA
        int partID = Inventory.newPartID();
        InHouse I = new InHouse(partID, "Buttons", 15.22, 20, 1, 100, 78);
        Inventory.addPart(I);

        partID = Inventory.newPartID();
        Outsourced OutOne = new Outsourced(partID, "Cloth", 10.99, 1392, 100, 2000, "Sally Screws");
        Inventory.addPart(OutOne);

        partID = Inventory.newPartID();
        Outsourced OutTwo = new Outsourced(partID, "Thread", 89.99, 101, 100, 1000, "Cover the Bottom");
        Inventory.addPart(OutTwo);

        int prodID = Inventory.newProductID();
        Product P1 = new Product(prodID, "Hat", 100.99, 5, 4, 50);
        addProduct(P1);

        prodID = Inventory.newProductID();
        Product P2 = new Product(prodID, "Coat", 160.99, 11, 10, 80);
        addProduct(P2);

        prodID = Inventory.newProductID();
        Product P3 = new Product(prodID, "Gloves", 160.99, 17, 10, 90);
        addProduct(P3);


// Launch
        launch();
    }

}


