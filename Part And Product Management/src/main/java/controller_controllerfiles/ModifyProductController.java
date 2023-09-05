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
import model_classfiles.Inventory;
import model_classfiles.Part;
import model_classfiles.Product;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;


/** The controller class for the modify product screen of the project.
 * @author Sage Ellefson
 */
public class ModifyProductController implements Initializable {

/** This method is for the selected product out of a tableview. */
    Product selectedProduct;
/** Original list of parts associated with product. */
    private ObservableList<Part> associatedPartsTempOriginal = FXCollections.observableArrayList();
/** Temp list of parts associated with product. */
    private ObservableList<Part> associatedPartsTemp = FXCollections.observableArrayList();

/** This method initializes the controller for the modify product page.
* @param location Location used.
* @param resources Resources used. */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    selectedProduct = MainController.getProductToModify();
    associatedPartsTempOriginal = selectedProduct.getAllAssociatedParts();

/* Sets the items in the tables.*/
    topPartTableModifyProd.setItems(Inventory.getAllParts());
    bottomPartTableModifyProd.setItems(associatedPartsTempOriginal);
    associatedPartsTemp.addAll(associatedPartsTempOriginal);

/* Sets up the columns to contain part data for top parts table. */
    partIDTopModProdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
    partNameTopModProdCol.setCellValueFactory(new PropertyValueFactory<>("name"));
    partInvTopModProdCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
    partPriceTopModProdCol.setCellValueFactory(new PropertyValueFactory<>("price"));

/* Sets up the columns to contain part data for bottom parts table. */
    partIDBottomModProdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
    partNameBottomModProdCol.setCellValueFactory(new PropertyValueFactory<>("name"));
    partInvBottomModProdCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
    partPriceBottomModProdCol.setCellValueFactory(new PropertyValueFactory<>("price"));

    idTextModProd.setText(String.valueOf(selectedProduct.getId()));
    nameTextModProd.setText(selectedProduct.getName());
    invTextModProd.setText(String.valueOf(selectedProduct.getStock()));
    priceTextModProd.setText(String.valueOf(selectedProduct.getPrice()));
    maxTextModProd.setText(String.valueOf(selectedProduct.getMax()));
    minTextModProd.setText(String.valueOf(selectedProduct.getMin()));
}


//////////////// Table column methods for modify product ////////////////
// Top Table
/** This is for the part ID column in the top table on the modify product screen. */
    @FXML
    private TableColumn<Part, Integer> partIDTopModProdCol;
/** This is for the part name column in the top table on the modify product screen. */
    @FXML
    private TableColumn<Part, String> partNameTopModProdCol;
/** This is for the part inventory column in the top table on the modify product screen. */
    @FXML
    private TableColumn<Part, Integer> partInvTopModProdCol;
/** This is for the part price column in the top table on the modify product screen. */
    @FXML
    private TableColumn<Part, Double> partPriceTopModProdCol;

// Bottom table
/** This is for the part ID column in the bottom table on the modify product screen. */
    @FXML
    private TableColumn<Part, Integer> partIDBottomModProdCol;
/** This is for the part name column in the bottom table on the modify product screen. */
    @FXML
    private TableColumn<Part, String> partNameBottomModProdCol;
/** This is for the part inventory column in the bottom table on the modify product screen. */
    @FXML
    private TableColumn<Part, Integer> partInvBottomModProdCol;
/** This is for the part price column in the bottom table on the modify product screen. */
    @FXML
    private TableColumn<Part, Double> partPriceBottomModProdCol;


//////////////////////// Text fields for the modify product page //////////////////////
/** This field is for the product ID text field on the modify product page. */
    public TextField idTextModProd;
/** This field is for the product name text field on the modify product page. */
    public TextField nameTextModProd;
/** This field is for the product inventory text field on the modify product page. */
    public TextField invTextModProd;
/** This field is for the product price text field on the modify product page. */
    public TextField priceTextModProd;
/** This field is for the product max text field on the modify product page. */
    public TextField maxTextModProd;
/** This field is for the product min text field on the modify product page. */
    public TextField minTextModProd;


////////////// Table views for modify product page ///////////////////
/** This configures the table view for the top parts table on the modify product page. */
    @FXML
    private TableView<Part> topPartTableModifyProd;
/** This configures the table view for the bottom parts table on the modify product page. */
    @FXML
    private TableView<Part> bottomPartTableModifyProd;


////////////// Minimum, maximum, and inventory validation ///////////////////
/** This method validates minimum text field on modify product page.
* Checks if prodMin is less than or equal to 0 or if prodMin is greater than or equal to prodMax.
* @param min The minimum value for the part.
* @param max The maximum value for the part.
* @return Returns boolean indicating if min is valid. */
    private boolean prodMinValidation(int min, int max) {
        boolean isValid = true;
        if (min <= 0 || min >= max) {
            isValid = false;
            alertOptionsModProd(2);
        }
        return isValid;
    }

/** This method validates maximum and inventory text fields on modify product page.
* Checks if prodStock is greater than prodMax or less than prodMin.
* @param min The minimum value for the part.
* @param max The maximum value for the part.
* @param stock The stock value for the part.
* @return Returns boolean indicating if inventory is valid. */
    private boolean prodMaxValidation(int min, int max, int stock) {
        boolean isValid = true;
        if (stock > max || stock < min) {
            isValid = false;
            alertOptionsModProd(3);
        }
        return isValid;
    }


///////////////////// Button press methods for the modify product page //////////////////////
/** This method is the add button on the modify product page.
* @param event Adds a part to the associated parts list from the top parts table. */
    @FXML
    void addButtonModifyProdAction(ActionEvent event) {
        Part selectedPart = topPartTableModifyProd.getSelectionModel().getSelectedItem();
        if (selectedPart == null) {
            alertOptionsModProd(4);
        }
        else {
              associatedPartsTemp.add(selectedPart);
              bottomPartTableModifyProd.setItems(associatedPartsTemp);
             }
}

/** This method is for the save button on the modify product page.
* @param event Save button.
* @throws IOException From FXMLLoader. */
    @FXML
    void saveButtonModifyProduct(ActionEvent event) throws IOException {
        try {
            int id = selectedProduct.getId();
            String name = nameTextModProd.getText();
            int stock = Integer.parseInt(invTextModProd.getText());
            double price = (Double.parseDouble(priceTextModProd.getText()));
            int max = Integer.parseInt(maxTextModProd.getText());
            int min = Integer.parseInt(minTextModProd.getText());

            if (name.isEmpty()) {
                alertOptionsModProd(4);
            } else {
                if (prodMinValidation(min, max) && prodMaxValidation(min, max, stock)) {

                    Product newProd = new Product(id, name, price, stock, min, max);

                    for (Part part : associatedPartsTemp) {
                        associatedPartsTempOriginal.addAll(associatedPartsTemp);
                        newProd.addAssociatedPart(part);
                    }

                    Inventory.addProduct(newProd);
                    Inventory.deleteProduct(selectedProduct);
                    bottomPartTableModifyProd.setItems(associatedPartsTemp);

                    // changes scene to main screen
                    Parent cancelAddProductScreenParent = FXMLLoader.load(getClass().getResource("/View/Main.fxml"));
                    Scene cancelAddProductScreen = new Scene(cancelAddProductScreenParent);
                    // This line gets stage info
                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    window.setScene(cancelAddProductScreenParent.getScene());
                    window.show();
                }
            }
        } catch (Exception e) { alertOptionsModProd(5); }
    }


/** This method is for the remove associated part button on the modify product page.
* Displays an error message is no row is selected.
* @param event Remove button for the modify product screen. */
    @FXML void removeAssociatedPartModifyScene(ActionEvent event)  {
            Part selectedPart = bottomPartTableModifyProd.getSelectionModel().getSelectedItem();
            if (selectedPart == null) {
                alertOptionsModProd(4);
            } else {
                Alert alertconfirm = new Alert(Alert.AlertType.CONFIRMATION);
                alertconfirm.setTitle("Inventory Management System");
                alertconfirm.setHeaderText(null);
                alertconfirm.setContentText("Are you sure you would like to remove this part from the product?");
                Optional<ButtonType> result = alertconfirm.showAndWait();

                if (result.isPresent() && result.get() == ButtonType.OK) {
                    associatedPartsTemp.remove(selectedPart);
                    bottomPartTableModifyProd.setItems(associatedPartsTemp);
                }
            }
        }

/** This method takes you back to the main scene when the cancel button is clicked.
* @param event Cancel button that redirects back to the main page.
* @throws IOException From FXMLLoader.*/
    @FXML
    void onCancelModifyProductScreen(ActionEvent event) throws IOException {
                Parent cancelModifyProductScreenParent = FXMLLoader.load(getClass().getResource("/View/Main.fxml"));
                Scene cancelModifyProductScreen = new Scene(cancelModifyProductScreenParent);
                // This line gets stage info
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(cancelModifyProductScreenParent.getScene());
                window.show();
        }

/** This method is for the search box text field. */
    public TextField searchTextModProd;
/** This method is the search button for the top parts table on the modify products page.
* @param event Search button for the search field. */
    @FXML
     void searchButtonModifyProductAction(ActionEvent event) {
        String searchStringPartModProd = searchTextModProd.getText();
        ObservableList<Part> allParts = Inventory.getAllParts();
        ObservableList<Part> partsList = FXCollections.observableArrayList();
        for (Part part : allParts) {
            if (String.valueOf(part.getId()).contains(searchStringPartModProd) ||
                    part.getName().contains(searchStringPartModProd)) {
                partsList.add(part);
            }
        }
        topPartTableModifyProd.setItems(partsList);
        if (partsList.size() == 0) {
            alertOptionsModProd(1);
        }
    }


////////////// Alerts ////////////////
/** This method provides alerts and error messages.
* A switch statement with cases is used to contain alerts that can be called by numbers 1-4.
* @param alerts Alert message options.*/
    private void alertOptionsModProd(int alerts) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        switch (alerts) {
            case 1:
                alert.setTitle("Inventory Management System");
                alert.setHeaderText(null);
                alert.setContentText("Part not found.");
                alert.showAndWait();
                break;
            case 2:
                alert.setTitle("Inventory Management System");
                alert.setHeaderText(null);
                alert.setContentText("Min must be more than 0 but no greater than Max.");
                alert.showAndWait();
                break;
            case 3:
                alert.setTitle("Inventory Management System");
                alert.setHeaderText(null);
                alert.setContentText("Inventory must be between minimum and maximum.");
                alert.showAndWait();
                break;
            case 4:
                alert.setTitle("Inventory Management System");
                alert.setHeaderText(null);
                alert.setContentText("No part has been selected.");
                alert.showAndWait();
                break;
            case 5:
                alert.setTitle("Inventory Management System");
                alert.setHeaderText(null);
                alert.setContentText(
                "Invalid values or blank fields." +
                " Min, Max, and Inventory are required and must be numerical." +
                " Price must be a decimal double number." +
                " Machine ID must be numerical if InHouse is selected." +
                " All fields must be filled out.");
                alert.showAndWait();
                break;
        }
    }

}
