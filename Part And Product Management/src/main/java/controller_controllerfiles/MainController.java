package controller_controllerfiles;

/* Imports */
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model_classfiles.*;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;
import model_classfiles.Inventory;

/** The controller class for the main page screen of the project.
 * @author Sage Ellefson
 */
public class MainController implements Initializable {

/** This method initializes the main controller.
* @param location Location used.
* @param resources Resources used.*/
    @Override
    public void initialize(URL location, ResourceBundle resources) {
/* Gets Products and Parts for productTableMain and partTableMain from Inventory*/
    productTableMain.setItems(Inventory.getAllProducts());
    partTableMain.setItems(Inventory.getAllParts());

/* Sets up the columns to contain data for PARTS table */
    mainPartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
    mainPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
    mainPartInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
    mainPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

/* Sets up the columns to contain data for PRODUCTS table */
    mainProductIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
    mainProductNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
    mainProductInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
    mainProductPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
}


////////////// Table views for part and product tables ///////////////
/** This configures the table view for the parts table on the main scene. */
    public TableView<Part> partTableMain;
/** This configures the table view for the products table on the main scene. */
    public TableView<Product> productTableMain;


//////////////// Table column methods - configures columns for parts ////////////////
/** This is for the part ID column in the part table on the main scene. */
    @FXML
    private TableColumn<Part, Integer> mainPartIDCol;
/** This is for the part name column in the part table on the main scene. */
    @FXML
    private TableColumn<Part, String> mainPartNameCol;
/** This is for the part inventory column in the part table on the main scene. */
    @FXML
    private TableColumn<Part, Integer> mainPartInventoryCol;
/** This is for the part price column in the part table on the main scene. */
    @FXML
    private TableColumn<Part, Double> mainPartPriceCol;


//////////////// Table column methods - configures columns for products ////////////////
/** This is for the product ID column in the product table on the main scene. */
    @FXML
    private TableColumn<Product, Integer> mainProductIDCol;
/** This is for the product name column in the product table on the main scene. */
    @FXML
    private TableColumn<Product, String> mainProductNameCol;
/** This is for the product inventory column in the product table on the main scene. */
    @FXML
    private TableColumn<Product, Integer> mainProductInventoryCol;
/** This is for the product price column in the product table on the main scene. */
    @FXML
    private TableColumn<Product, Double> mainProductPriceCol;


//////////////////////// Buttons for part table /////////////////////////
/** This method controls the add button on the part table.
* On action, this button takes you to the add part scene.
* @param event Add button on part table.
* @throws IOException From FXMLLoader.*/
    @FXML void onAddPartMainScreen(ActionEvent event) throws IOException {
        Parent addPartScreenParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/AddPart.fxml")));
        Scene addPartScreen = new Scene(addPartScreenParent);
// This line gets stage info
        Stage windowAddPartMain = (Stage) ((Node) event.getSource()).getScene().getWindow();
        windowAddPartMain.setScene(addPartScreenParent.getScene());
        windowAddPartMain.show();
    }

/** This method contains what part is to be modified. */
    private static Part partToModify;
/** This method gets what part is to be modified.
* @return Returns a part. */
    @FXML static Part getPartToModify() {
        return partToModify;}

/** This method controls the modify button on the part table.
* On action, this button takes you to the modify part scene and loads the selected row into the text fields.
* @param event Modify button on the part table.
* @throws IOException From FXMLLoader. */
    @FXML void onModifyPartMainScreen(ActionEvent event) throws IOException {
     partToModify = partTableMain.getSelectionModel().getSelectedItem();
     if (partToModify == null) {
         alertOptions(1);
     } else {
         Parent modifyPartScreenParent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/ModifyPart.fxml")));
         Scene modifyPartScreen = new Scene(modifyPartScreenParent);
         Stage windowModifyPartMain = (Stage) ((Node) event.getSource()).getScene().getWindow();
         windowModifyPartMain.setScene(modifyPartScreenParent.getScene());
         windowModifyPartMain.show();
     }
}

/** This method controls the delete button for the part table on the main scene.
* If no row is selected, an error message from alertOptions will be called.
* @param event Delete button on the part table. */
    @FXML void deleteButtonPushedPart(ActionEvent event) {
        Part selectedPart = partTableMain.getSelectionModel().getSelectedItem();
        if (selectedPart == null) {
            alertOptions(1);
        } else {
            Alert alertconfirm = new Alert(Alert.AlertType.CONFIRMATION);
            alertconfirm.setTitle("Inventory Management System");
            alertconfirm.setHeaderText(null);
            alertconfirm.setContentText("Are you sure you would like to delete the selected part?");
            Optional<ButtonType> result = alertconfirm.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                Inventory.deletePart(selectedPart);
            }
        }
    }


//////////////////////// Buttons for product table /////////////////////////
/** This method controls the add button on the product table.
* On action, this button takes you to the add product scene.
* @param event Add button on product table.
* @throws IOException From FXMLLoader. */
    @FXML void onAddProductMainScreen(ActionEvent event) throws IOException {
        Parent addProductScreenParent = FXMLLoader.load(getClass().getResource("/View/AddProduct.fxml"));
        Scene addProductScreen = new Scene(addProductScreenParent);
// This line gets stage info
        Stage windowAddProductMain = (Stage) ((Node) event.getSource()).getScene().getWindow();
        windowAddProductMain.setScene(addProductScreenParent.getScene());
        windowAddProductMain.show();
    }

/** This method contains what product is to be modified. */
    private static Product productToModify;
/** This method gets what part is to be modified.
* @return productToModify Returns a product. */
    public static Product getProductToModify() {
        return productToModify;}

/** This method controls the modify button on the product table.
* On action, this button takes you to the modify product scene and loads the selected row into the text fields.
* @param event Modify button on the product table.
* @throws IOException From FXMLLoader. */
    @FXML void onModifyProductMainScreen(ActionEvent event) throws IOException {
    productToModify = productTableMain.getSelectionModel().getSelectedItem();

        if (productToModify == null) {
        alertOptions(2);
        } else {
        Parent modifyProductScreenParent = FXMLLoader.load(getClass().getResource("/View/ModifyProduct.fxml"));
        Scene modifyProductScreen = new Scene(modifyProductScreenParent);
// This line gets stage info
        Stage windowModifyProductMain = (Stage) ((Node) event.getSource()).getScene().getWindow();
        windowModifyProductMain.setScene(modifyProductScreenParent.getScene());
        windowModifyProductMain.show();
        }
    }

/** This method controls the delete button for the product table on the main scene.
* If no row is selected, an error message from alertOptions will be called.
* @param event Delete button on the product table. */
    @FXML
    void deleteButtonPushedProduct(ActionEvent event) {
        Product selectedProduct = productTableMain.getSelectionModel().getSelectedItem();
        if (selectedProduct == null) {
            alertOptions(2);
        } else {
            ObservableList<Part> associatedParts = selectedProduct.getAllAssociatedParts();
            if (associatedParts.size() >= 1) {
                alertOptions(5);
            }
                else {
                Alert alertconfirm = new Alert(Alert.AlertType.CONFIRMATION);
                alertconfirm.setTitle("Inventory Management System");
                alertconfirm.setHeaderText(null);
                alertconfirm.setContentText("Are you sure you would like to delete the selected product?");
                Optional<ButtonType> result = alertconfirm.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    Inventory.deleteProduct(selectedProduct);
                }
            }
        }
    }

/////////////// Text fields for search bars and search buttons for both tables /////////////////
/** This method contains the text field for the part search bar.*/
    public TextField mainPagePartSearch;
/** This method contains the text field for the product search bar.*/
    public TextField mainPageProductSearch;

/** This method is used for the search button on the part table of the main scene.
* @param event Search button on the part table of the main scene. */
    @FXML void searchPartTable(ActionEvent event) {
        String searchStringPart = mainPagePartSearch.getText();
        ObservableList<Part> allParts = Inventory.getAllParts();
        ObservableList<Part> partsList = FXCollections.observableArrayList();
        for (Part part : allParts) {
            if (String.valueOf(part.getId()).contains(searchStringPart) ||
                    part.getName().contains(searchStringPart)) {
                partsList.add(part);
            }
        }
        partTableMain.setItems(partsList);
        if (partsList.size() == 0) {
            alertOptions(3);
        }
    }

/** This method is used for the search button on the product table of the main scene.
* @param event Search button on the product table of the main scene. */
    @FXML void searchProductTable(ActionEvent event) {
        String searchStringProduct = mainPageProductSearch.getText();
        ObservableList<Product> allProducts = Inventory.getAllProducts();
        ObservableList<Product> prodList = FXCollections.observableArrayList();
        for (Product product : allProducts) {
            if (String.valueOf(product.getId()).contains(searchStringProduct) ||
                    product.getName().contains(searchStringProduct)) {
                    prodList.add(product);
            }
        }
        productTableMain.setItems(prodList);
        if (prodList.size() == 0) {
            alertOptions(4);
        }
    }


////////////// Exit Button for main page ////////////////
/** This method is for the exit button on the main scene.
* This method closes the application when clicked.
* @param event Exit button action. */
    @FXML void onMainExitButton (ActionEvent event){
        System.exit(0);
    }


////////////// Alerts ////////////////
/** This method provides alerts and error messages.
* A switch statement with cases is used to contain alerts that can be called by numbers 2-6.
* @param alerts Alert message options. */
    private void alertOptions(int alerts) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        switch (alerts) {
            case 1:
                alert.setTitle("Inventory Management System");
                alert.setHeaderText(null);
                alert.setContentText("No part has been selected.");
                alert.showAndWait();
                break;
            case 2:
                alert.setTitle("Inventory Management System");
                alert.setHeaderText(null);
                alert.setContentText("No product has been selected.");
                alert.showAndWait();
                break;
            case 3:
                alert.setTitle("Inventory Management System");
                alert.setHeaderText(null);
                alert.setContentText("Part not found.");
                alert.showAndWait();
                break;
            case 4:
                alert.setTitle("Inventory Management System");
                alert.setHeaderText(null);
                alert.setContentText("Product not found.");
                alert.showAndWait();
                break;
            case 5:
                alert.setTitle("Inventory Management System");
                alert.setHeaderText(null);
                alert.setContentText("You cannot delete a product with a part associated to it.");
                alert.showAndWait();
        }
    }

}
