package controller_controllerfiles;

/* Imports */
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import model_classfiles.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/** The controller class for the modify part screen of the project.
 * @author Sage Ellefson
 */
public class ModifyPartController implements Initializable {


/** This method is for the selected part out of a tableview. */
    private Part selectedPart;

/** This method populates text fields in the scene and initializes controller.
* @param location Location used.
* @param resources Resources used. */
    @Override
    public void initialize(URL location, ResourceBundle resources){

        selectedPart = MainController.getPartToModify();

        if (selectedPart instanceof InHouse){
            InHouseRadioButton.setSelected(true);
            variableBottomFieldLabel.setText("Machine ID");
            variableText.setText(String.valueOf(((InHouse) selectedPart).getMachineId()));
        }
        if (selectedPart instanceof Outsourced){
            OutsourcedRadioButton.setSelected(true);
            variableBottomFieldLabel.setText("Company Name");
            variableText.setText(((Outsourced) selectedPart).getCompanyName());
        }
        partIDModifyText.setText(String.valueOf(selectedPart.getId()));
        partNameText.setText(selectedPart.getName());
        partInvText.setText(String.valueOf(selectedPart.getStock()));
        partPriceText.setText(String.valueOf(selectedPart.getPrice()));
        partMaxText.setText(String.valueOf(selectedPart.getMax()));
        partMinText.setText(String.valueOf(selectedPart.getMin()));
    }


//////////////////////// Text fields for modify part page //////////////////////
/** This field is for the part ID text field on the modify part page. */
    public TextField partIDModifyText;
/** This field is for the part name text field on the modify part page. */
    public TextField partNameText;
/** This field is for the part inventory text field on the modify part page. */
    public TextField partInvText;
/** This field is for the part price text field on the modify part page. */
    public TextField partPriceText;
/** This field is for the part max text field on the modify part page. */
    public TextField partMaxText;
/** This field is for the part min text field on the modify part page. */
    public TextField partMinText;
/** This field is for the variable text field on the modify part page that can contain a company name or machine ID. */
    public TextField variableText;


////////////////////// Labels for the modify part page ////////////////////////
/** This label is for the part ID label on the modify part page. */
    public Label modifyPartID;
/** This label is for the part name label on the modify part page. */
    public Label modifyPartName;
/** This label is for the part inventory label on the modify part page. */
    public Label modifyPartInv;
/** This label is for the part price label on the modify part page. */
    public Label modifyPartPrice;
/** This label is for the part min label on the modify part page. */
    public Label modifyPartMin;
/** This label is for the part max label on the modify part page. */
    public Label modifyPartMax;


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

/** This method is for changing the bottom label when the InHouse radio button is selected.
* @param event Radio button to determine a text label. */
    @FXML void inHouseRadioButtonAction (ActionEvent event){
        variableBottomFieldLabel.setText("Machine ID");
    }
/** This method is for changing the bottom label when the Outsourced radio button is selected.
* @param event Radio button to determine a text label.  */
    @FXML void outsourcedRadioButtonAction(ActionEvent event) {
        variableBottomFieldLabel.setText("Company Name");
    }


////////////// Minimum, maximum, and inventory validation ///////////////////
/** This method validates minimum text field on modify part page.
* Checks if partMin is less than or equal to 0 or if partMin is greater than or equal to partMax.
* @param min The minimum value for the part.
* @param max The maximum value for the part.
* @return Returns boolean indicating if min is valid. */
    private boolean partMinValidation(int min, int max){
        boolean isValid = true;
        if (min <= 0 || min >= max){
            isValid = false;
            alertsModPartOptions(1);
        }
        return isValid;
    }

/** This method validates maximum and inventory text fields on modify part page.
* Checks if partStock is greater than partMax or less than partMin.
* @param min The minimum value for the part.
* @param max The maximum value for the part.
* @param stock The stock value for the part.
* @return Returns boolean indicating if inventory is valid. */
    private boolean partMaxValidation (int min, int max, int stock){
        boolean isValid = true;
        if (stock > max || stock < min){
            isValid = false;
            alertsModPartOptions(2);
        }
        return isValid;
    }


///////////////////// Button press methods for the modify part page  //////////////////////
/** This method changes the scene back to the main screen scene when the cancel button is pressed.
* @param event Cancel button action on the modify part screen.
* @throws IOException From FXMLLoader. */
    @FXML void onCancelModifyPartScreen(ActionEvent event) throws IOException {
        Parent cancelModifyPartScreenParent = FXMLLoader.load(getClass().getResource("/View/Main.fxml"));
        Scene cancelModifyPartScreen = new Scene(cancelModifyPartScreenParent);
        // This line gets stage info
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(cancelModifyPartScreenParent.getScene());
        window.show();
    }

/** This method gets text from a selected row in the table, updates it and returns to the main scene when save button is clicked.
* Modifies a part by retrieving the data in a table row then uses text fields to allow the user to update fields.
* Generates various error messages to ensure data is valid.
* Returns to the main scene if partAddSuccessful returns true.
* @param event Save button action on modify part page.
* @throws IOException From FXMLLoader. */
    @FXML void saveModifyPartAction(ActionEvent event) throws IOException {
        try {
            int id = Integer.parseInt(partIDModifyText.getText());
            String name = partNameText.getText();
            int stock = Integer.parseInt(partInvText.getText());
            double price = (Double.parseDouble(partPriceText.getText()));
            int max = Integer.parseInt(partMaxText.getText());
            int min = Integer.parseInt(partMinText.getText());
            boolean partSuccessful = false;

            if (partMinValidation(min, max) && partMaxValidation(min, max, stock)) {
                if (InHouseRadioButton.isSelected()) {
                    try {
                        int machineId = Integer.parseInt(variableText.getText());
                        InHouse newInHousePart = new InHouse(id, name, price, stock, min, max, machineId);
                        Inventory.addPart(newInHousePart);
                        partSuccessful = true;
                    } catch (Exception e) { alertsModPartOptions(3); }
                }
                if (OutsourcedRadioButton.isSelected()) {
                    try {
                        String companyName = variableText.getText();
                        if (companyName.isEmpty()) {
                            throw new Exception("Company name is required.");
                        }
                        Outsourced newOutsourcedPart = new Outsourced(id, name, price, stock, min, max, companyName);
                        Inventory.addPart(newOutsourcedPart);
                        partSuccessful = true;
                    } catch (Exception e){ alertsModPartOptions(4); }
                }
            }
                // Goes to main scene when save is select if true
            if (partSuccessful == true) {
                Inventory.deletePart(selectedPart);
                Parent saveModifyPartScreenParent = FXMLLoader.load(getClass().getResource("/View/Main.fxml"));
                Scene saveModifyPartScreen = new Scene(saveModifyPartScreenParent);
                // This line gets stage info
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(saveModifyPartScreenParent.getScene());
                window.show();
            }
        } catch(Exception e){ alertsModPartOptions(5); }
    }


////////////// Alerts ////////////////
/** This method provides alerts and error messages.
* A switch statement with cases is used to contain alerts that can be called by numbers 1-5.
* @param alertsModPart Alert message options. */
    private void alertsModPartOptions (int alertsModPart) {
        Alert alerterror = new Alert(Alert.AlertType.ERROR);
        switch (alertsModPart){
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
                alerterror.setContentText("Machine ID may only contain numbers.");
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
                " Min, Max, and Inventory are required and must be numerical." +
                " Price must be a decimal double number." +
                " Machine ID must be numerical if InHouse is selected." +
                " All fields must be filled out.");
                alerterror.showAndWait();
                break;
        }
    }

}



