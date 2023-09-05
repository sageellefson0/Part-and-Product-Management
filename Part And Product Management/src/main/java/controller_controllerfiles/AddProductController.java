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

/** The controller class for the add product screen of the project.
 * @author Sage Ellefson
 */
public class AddProductController implements Initializable {

/** A list of parts associated with a product. */
    private ObservableList<Part> associatedPartsProd = FXCollections.observableArrayList();

/** This method initializes the add product controller.
* @param location Location used.
* @param resources Resources used. */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

/* Sets the items in the tables. */
    topPartTableAddProd.setItems(Inventory.getAllParts());

/* Sets up the columns to contain part data for top parts table. */
    partIDTopAddProdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
    partNameTopAddProdCol.setCellValueFactory(new PropertyValueFactory<>("name"));
    partInvTopAddProdCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
    partPriceTopAddProdCol.setCellValueFactory(new PropertyValueFactory<>("price"));
/* Sets up the columns to contain part data for bottom parts table. */
    partIDBottomAddProdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
    partNameBottomAddProdCol.setCellValueFactory(new PropertyValueFactory<>("name"));
    partInvBottomAddProdCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
    partPriceBottomAddProdCol.setCellValueFactory(new PropertyValueFactory<>("price"));
}

//////////////// Table column methods for add product ////////////////
// Top table
/** This is for the part ID column in the top table on the add product screen. */
    @FXML
    private TableColumn<Part, Integer> partIDTopAddProdCol;
/** This is for the part name column in the top table on the add product screen. */
    @FXML
    private TableColumn<Part, String> partNameTopAddProdCol;
/** This is for the part inventory column in the top table on the add product screen. */
    @FXML
    private TableColumn<Part, Integer> partInvTopAddProdCol;
/** This is for the part price column in the top table on the add product screen. */
    @FXML
    private TableColumn<Part, Double> partPriceTopAddProdCol;

// Bottom table
/** This is for the part ID column in the bottom table on the add product screen. */
    @FXML
    private TableColumn<Part, Integer> partIDBottomAddProdCol;
/** This is for the part name column in the bottom table on the add product screen. */
    @FXML
    private TableColumn<Part, String> partNameBottomAddProdCol;
/** This is for the part inventory column in the bottom table on the add product screen. */
    @FXML
    private TableColumn<Part, Integer> partInvBottomAddProdCol;
/** This is for the part price column in the bottom table on the add product screen. */
    @FXML
    private TableColumn<Part, Double> partPriceBottomAddProdCol;


//////////////////////// Text fields for the add product page //////////////////////
/** This field is for the product ID text field on the add product page. */
    public TextField idTextAddProd;
/** This field is for the product name text field on the add product page. */
    public TextField nameTextAddProd;
/** This field is for the product inventory text field on the add product page. */
    public TextField invTextAddProd;
/** This field is for the product price text field on the add product page. */
    public TextField priceTextAddProd;
/** This field is for the product max text field on the add product page. */
    public TextField maxTextAddProd;
/** This field is for the product min text field on the add product page. */
    public TextField minTextAddProd;


///////////////// Table views for the add product page /////////////////////
/** This configures the table view for the top parts table on the add product page. */
    @FXML
    private TableView<Part> topPartTableAddProd;
/** This configures the table view for the bottom parts table on the add product page. */
    @FXML
    private TableView<Part> bottomPartTableAddProd;


////////////// Minimum, maximum, and inventory validation ///////////////////
/** This method validates minimum text field on modify product page.
* Checks if prodMin is less than or equal to 0 or if prodMin is greater than or equal to prodMax.
* @param min The minimum value for the part.
* @param max The maximum value for the part.
* @return Returns boolean indicating if min is valid. */
    private boolean prodMinValidation(int min, int max){
        boolean isValid = true;
        if (min <= 0 || min >= max){
            isValid = false;
            alertOptionsAddProd(4);
        }
        return isValid;
    }

/** This method validates maximum and inventory text fields on modify product page.
* Checks if prodStock is greater than prodMax or less than prodMin.
* @param min The minimum value for the part.
* @param max The maximum value for the part.
* @param stock The stock value for the part.
* @return Returns boolean indicating if inventory is valid. */
    private boolean prodMaxValidation (int min, int max, int stock){
        boolean isValid = true;
        if (stock > max || stock < min){
            isValid = false;
            alertOptionsAddProd(3);
        }
        return isValid;
    }


///////////////////// Button press methods for the add product page //////////////////////
/** This method takes you back to the main scene when the cancel button is clicked.
* @param event Cancel button that redirects back to the main page.
* @throws IOException From FXMLLoader. */
    @FXML void onCancelAddProductScreen(ActionEvent event) throws IOException {
        Parent cancelAddProductScreenParent = FXMLLoader.load(getClass().getResource("/View/Main.fxml"));
        Scene cancelAddProductScreen = new Scene(cancelAddProductScreenParent);
        // This line gets stage info
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(cancelAddProductScreenParent.getScene());
        window.show();
    }

/** This method is for the search box text field. */
    public TextField addProductSearchField;
/** This method is the search button for the parts table on the add product page.
* @param event Search button for the search field. */
    @FXML void searchButtonAddProductAction(ActionEvent event) {
        String searchStringPartAddProd = addProductSearchField.getText();
        ObservableList<Part> allParts = Inventory.getAllParts();
        ObservableList<Part> partsList = FXCollections.observableArrayList();
        for (Part part : allParts) {
            if (String.valueOf(part.getId()).contains(searchStringPartAddProd) ||
                    part.getName().contains(searchStringPartAddProd)) {
                partsList.add(part);
            }
        }
        topPartTableAddProd.setItems(partsList);
        if (partsList.size() == 0) {
            alertOptionsAddProd(1);
        }
    }

/** This method is for the add button on the add product page.
* @param event Adds a part to the associated parts list from the top parts table. */
    @FXML void addButtonAddProdAction(ActionEvent event) {
        Part selectedPart = topPartTableAddProd.getSelectionModel().getSelectedItem();

        if (selectedPart == null){
            alertOptionsAddProd(2);
        } else {
            associatedPartsProd.add(selectedPart);
            bottomPartTableAddProd.setItems(associatedPartsProd);
        }
    }

/** This method is for the save button on the add product page.
* @param event Save button.
* @throws IOException From FXMLLoader. */
    @FXML void saveButtonAddProduct(ActionEvent event) throws IOException {
        try {
            int id = Inventory.newProductID();
            String name = nameTextAddProd.getText();
            int stock = Integer.parseInt(invTextAddProd.getText());
            double price = (Double.parseDouble(priceTextAddProd.getText()));
            int max = Integer.parseInt(maxTextAddProd.getText());
            int min = Integer.parseInt(minTextAddProd.getText());
            boolean prodSuccess = false;

            if (prodMinValidation(min, max) && prodMaxValidation(min, max, stock)) {

                Product product = new Product(id, name, price, stock, min, max);

                for (Part part : associatedPartsProd) {
                    product.addAssociatedPart(part);
                }
                Inventory.addProduct(product);
                prodSuccess = true;

            }

            if (prodSuccess == true) {
                Parent cancelAddProductScreenParent = FXMLLoader.load(getClass().getResource("/View/Main.fxml"));
                Scene cancelAddProductScreen = new Scene(cancelAddProductScreenParent);
                // This line gets stage info
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(cancelAddProductScreenParent.getScene());
                window.show();
                }
            }
        catch (Exception e) { alertOptionsAddProd(5); }
    }

/** This method is for the remove associated part button on the add product scene.
* Displays an error message is no row is selected.
* @param event Remove button for the add product screen. */
    @FXML void removeAssociatedPartAddScene(ActionEvent event) {
        Part selectedPart = bottomPartTableAddProd.getSelectionModel().getSelectedItem();
        if (selectedPart == null) {
            alertOptionsAddProd(2);
        } else {
            Alert alertconfirm = new Alert(Alert.AlertType.CONFIRMATION);
            alertconfirm.setTitle("Inventory Management System");
            alertconfirm.setHeaderText(null);
            alertconfirm.setContentText("Are you sure you would like to remove this part from the product?");
            Optional<ButtonType> result = alertconfirm.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                associatedPartsProd.remove(selectedPart);
                bottomPartTableAddProd.setItems(associatedPartsProd);
            }
        }
    }


////////////// Alerts ////////////////
/** This method provides alerts and error messages.
 * A switch statement with cases is used to contain alerts that can be called by numbers 1-5.
 * @param alerts Alert message options. */
    private void alertOptionsAddProd(int alerts) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
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
                alert.setContentText("No part has been selected.");
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
                alert.setContentText("Min must be more than 0 but no greater than Max.");
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
