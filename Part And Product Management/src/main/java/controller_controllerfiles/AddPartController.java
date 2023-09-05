package controller_controllerfiles;

/* Imports */
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model_classfiles.InHouse;
import model_classfiles.Inventory;
import model_classfiles.Outsourced;
import java.io.IOException;

/** The controller class for the add part screen of the project.
* @author Sage Ellefson
*/
public class AddPartController {

//////////////////////// Text fields for add part page //////////////////////
/** This field is for the part ID text field on the add part page. */
    public TextField addPartIDText;
/** This field is for the part name text field on the add part page. */
    public TextField addPartNameText;
/** This field is for the part inventory text field on the add part page. */
    public TextField addPartInvText;
/** This field is for the part price text field on the add part page. */
    public TextField addPartPriceText;
/** This field is for the part max text field on the add part page. */
    public TextField addPartMaxText;
/** This field is for the part min text field on the add part page. */
    public TextField addPartMinText;
/** This field is for the variable text field on the add part page that can contain a company name or machine ID. */
    public TextField variableTextAdd;


////////////////////// Radio buttons and group for radio buttons /////////////////////////
/** This label is for the label on the variable text field for Machine ID and Company Name. */
    @FXML
    private Label variableBottomFieldLabel;
/** This field is for the Outsourced radio button. */
    @FXML
    private RadioButton OutsourcedRadioButton;
/** This field is for the InHouse radio button. */
    @FXML
    private RadioButton InHouseRadioButton;
/** This is for toggle group containing both radio buttons. */
    @FXML
    private ToggleGroup sourceToggleGroup;

/** This method is for changing the bottom label depending on which radio button is selected.
* @param event Radio buttons to determine a text label.*/
    public void getvariableBottomFieldLabel(ActionEvent event) {
        if (OutsourcedRadioButton.isSelected()) {
            variableBottomFieldLabel.setText("Company Name");
        } else if (InHouseRadioButton.isSelected()) {
            variableBottomFieldLabel.setText("Machine ID");
        }
    }


////////////// Minimum, maximum, and inventory validation ///////////////////
/** This method validates minimum text field on add part page.
* Checks if partMin is less than or equal to 0 or if partMin is greater than or equal to partMax.
* @param min The minimum value for the part.
* @param max The maximum value for the part.
* @return Returns boolean indicating if min is valid. */
    private boolean partMinValidation(int min, int max) {
        boolean isValid = true;
        if (min <= 0 || min >= max) {
            isValid = false;
            alertsAddPartOptions(1);
        }
        return isValid;
    }

/** This method validates maximum and inventory text fields on add part page.
* Checks if partStock is greater than partMax or less than partMin.
* @param min The minimum value for the part.
* @param max The maximum value for the part.
* @param stock The stock value for the part.
* @return Returns boolean indicating if inventory is valid. */
    private boolean partMaxValidation(int min, int max, int stock) {
        boolean isValid = true;
        if (stock > max || stock < min) {
            isValid = false;
            alertsAddPartOptions(2);
        }
        return isValid;
    }


///////////////////// Button press methods for the add part page //////////////////////
/** This method changes the scene back to the main screen scene when the cancel button is pressed.
* @param event Cancel button action on the add part screen.
* @throws IOException From FXMLLoader. */
    @FXML void onCancelAddPartScreen(ActionEvent event) throws IOException {
        Parent cancelAddPartScreenParent = FXMLLoader.load(getClass().getResource("/View/Main.fxml"));
        Scene cancelAddPartScreen = new Scene(cancelAddPartScreenParent);
        // This line gets stage info
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(cancelAddPartScreenParent.getScene());
        window.show();
    }

/////////////////// RUNTIME ERROR BELOW ///////////////////////
/** This method saves text entered to a new part and changes back to main scene - <b>RUNTIME ERROR HERE.</b>
* <p> <b>
RUNTIME ERROR: Caused by: java.lang.NumberFormatException. This error was corrected by adding try catch blocks to catch
and handle errors caused by invalid data being entered into the text fields. The catch statement pull various error
messages from the switch block "alertsAddPartOptions" to let the user know what needs to be corrected.
* </b></p>
* Creates a new part by getting the text entered into the text fields.
* Generates various error messages to ensure data is valid.
* Returns to the main scene if partAddSuccessful returns true.
* @param event Save button action on add part page.
* @throws IOException From FXMLLoader. */
    @FXML void saveAddPartAction(ActionEvent event) throws IOException {
        try {
            int id = Inventory.newPartID();
            String name = addPartNameText.getText();
            int stock = Integer.valueOf(addPartInvText.getText());
            double price = (Double.valueOf(addPartPriceText.getText()));
            int max = Integer.valueOf(addPartMaxText.getText());
            int min = Integer.valueOf(addPartMinText.getText());
            boolean partAddSuccessful = false;

            if (partMinValidation(min, max) && partMaxValidation(min, max, stock)) {
                if (InHouseRadioButton.isSelected()) {
                    try {
                        int machineId = Integer.valueOf(variableTextAdd.getText());
                        InHouse newInHousePart = new InHouse(id, name, price, stock, min, max, machineId);
                        Inventory.addPart(newInHousePart);
                        partAddSuccessful = true;
                    }
                catch (Exception e) { alertsAddPartOptions(3); }
            }

            if (OutsourcedRadioButton.isSelected()) {
                try {
                    String companyName = variableTextAdd.getText();
                    if (companyName.isEmpty()) {
                        throw new Exception("Company name is required.");
                    }
                    Outsourced newOutsourcedPart = new Outsourced(id, name, price, stock, min, max, companyName);
                    Inventory.addPart(newOutsourcedPart);
                    partAddSuccessful = true;
                } catch (Exception e) {
                    alertsAddPartOptions(4);
                }
            }
        }
                // Goes to main scene when save is select if true
                if (partAddSuccessful == true) {
                    Parent saveAddPartActionParent = FXMLLoader.load(getClass().getResource("/View/Main.fxml"));
                    Scene saveAddPartScreen = new Scene(saveAddPartActionParent);
                    // This line gets stage info
                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    window.setScene(saveAddPartActionParent.getScene());
                    window.show();
        }
    }  catch(Exception e){ alertsAddPartOptions(5); }
}


////////////// Alerts ////////////////
/** This method provides alerts and error messages.
* A switch statement with cases is used to contain alerts that can be called by numbers 1-5.
* @param alertsAddPart Alert message options. */
    private void alertsAddPartOptions(int alertsAddPart) {
        Alert alerterror = new Alert(Alert.AlertType.ERROR);
        switch (alertsAddPart) {
            case 1:
                alerterror.setTitle("Inventory Management System");
                alerterror.setHeaderText(null);
                alerterror.setContentText("Min must be more than 0 but no greater than Max.");
                alerterror.showAndWait();
                break;
            case 2:
                alerterror.setTitle("Inventory Management System");
                alerterror.setHeaderText(null);
                alerterror.setContentText("Inventory must be between minimum and maximum.");
                alerterror.showAndWait();
                break;
            case 3:
                alerterror.setTitle("Inventory Management System");
                alerterror.setHeaderText(null);
                alerterror.setContentText("Machine ID is required and must consist of only numerical values.");
                alerterror.showAndWait();
                break;
            case 4:
                alerterror.setTitle("Inventory Management System");
                alerterror.setHeaderText(null);
                alerterror.setContentText("Company name is required.");
                alerterror.showAndWait();
                break;
            case 5:
                alerterror.setTitle("Inventory Management System");
                alerterror.setHeaderText(null);
                alerterror.setContentText(
                "Invalid values or blank fields." +
                " Min, Max, and Inventory must be numerical." +
                " Price must be a decimal double number." +
                " Machine ID must be numerical if InHouse is selected." +
                " All fields must be filled out." );
                alerterror.showAndWait();
                break;
        }
    }

}


