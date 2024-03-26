package main.tennyakihary_pa1;

import classes.InHouse;
import classes.Inventory;
import classes.Outsourced;
import classes.Part;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Part Modification Controller for the FXML file PartMod.fxml.
 * Implements a way for modifying a selected Part from the MainView.
 *
 * @author Tenny Akihary
 */

public class PartModController implements Initializable {

    /**
     * Flag for radio buttons and if only Numbers need to exist.
     */
    private boolean onlyNum = true;

    /**
     * Label control to allow switching from In to Outsource.
     */
    @FXML
    private Label labelID;

    /**
     * Unique ID text field.
     */
    @FXML
    private TextField uniqueIdLabel;

    /**
     * Name text field.
     */
    @FXML
    private TextField partModName;

    /**
     * Inventory text field.
     */
    @FXML
    private TextField partModInv;

    /**
     * Price text field.
     */
    @FXML
    private TextField partModPrice;

    /**
     * Maximum text field.
     */
    @FXML
    private TextField partModMax;

    /**
     * MachineID or CompanyName text field.
     */
    @FXML
    private TextField partModIDName;

    /**
     * Minimum text field.
     */
    @FXML
    private TextField partModMin;

    /**
     * In-House Radio Button.
     */
    @FXML
    private RadioButton inRadio;

    /**
     * Outsourced Radio Button.
     */
    @FXML
    private RadioButton outRadio;

    /**
     * modPart will hold the part selected.
     */
    private Part modifyPart = MainViewController.getModPart();

    /**
     * Create an index for the part being modified.
     */
    int indexModifyPart = Inventory.getAllParts().indexOf(modifyPart);

    /**
     * Method for changing the text to better represent Machine ID for In-House Parts.
     * @param event In-House radio button clicked.
     */
    @FXML
    void inRadioAction(ActionEvent event){
        labelID.setText("Machine ID");
        onlyNum = true;
    }

    /**
     * Method for changing the text to better represent Company Name for Outsourced parts.
     * @param event Outsourced radio button clicked.
     */
    @FXML
    void outRadioAction(ActionEvent event){
        labelID.setText("Company Name");
        onlyNum = false;
    }

    /**
     * Method for saving the modified Part into memory, then returning back to the main menu.
     * @param event Save Button Clicked.
     * @throws IOException For FXMLLoader.
     */
    @FXML
    void onSavePressed(ActionEvent event) throws IOException {
        if (onlyNum) {
            try {
                int machID = Integer.parseInt(partModIDName.getText());
                int max = Integer.parseInt(partModMax.getText());
                int min = Integer.parseInt(partModMin.getText());
                double price = Double.parseDouble(partModPrice.getText());
                int inv = Integer.parseInt(partModInv.getText());
                String name = partModName.getText();
                if (name.isEmpty() || (max <= min) || (min < 1) ||
                        (inv > max) || (inv < min) || (price < 0.01)) {
                    alert();
                } else {
                    InHouse newPart = new InHouse(modifyPart.getId(), name, price, inv, min, max, machID);
                    Inventory.updatePart(indexModifyPart, newPart);
                    backToMain(event);
                }
            } catch (NumberFormatException error) {
                alert();
            }
        } else {
            try {
                String companyName = partModIDName.getText();
                int max = Integer.parseInt(partModMax.getText());
                int min = Integer.parseInt(partModMin.getText());
                double price = Double.parseDouble(partModPrice.getText());
                int inv = Integer.parseInt(partModInv.getText());
                String name = partModName.getText();
                if (name.isEmpty() || companyName.isEmpty() || (max <= min) ||
                        (min < 1) || (inv > max) || (inv < min) || (price < 0.01)) {
                    alert();
                } else {
                    Outsourced newPart = new Outsourced(modifyPart.getId(), name, price, inv, min, max, companyName);
                    Inventory.updatePart(indexModifyPart, newPart);
                    backToMain(event);
                }
            } catch (NumberFormatException error) {
                alert();
            }
        }
    }

    /**
     * Method for heading back to the main form.
     * @throws IOException From FMLLoader.
     */
    private void backToMain(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MainView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 400);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Main Form");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Method and loader for going back to the main view.
     * @param event On Cancel Button Clicked.
     * @throws IOException From FMLLoader.
     */
    @FXML
    void cancelButtonPressed(ActionEvent event) throws IOException {
        backToMain(event);
    }

    /**
     * Method to alert incorrect values/rules.
     */
    static void alert(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR: Incorrect Values!");
        alert.setHeaderText("ERROR: Incorrect Values. Ensure your information is accurate then try again.");
        alert.showAndWait();
    }

    /**
     * Initialize override to pre-fill TextFields and check the right radio button.
     * @param address Location.
     * @param resource Resource.
     */
    @Override
    public void initialize(URL address, ResourceBundle resource) {

        uniqueIdLabel.setPromptText(String.valueOf(modifyPart.getId()));
        partModName.setText(modifyPart.getName());
        partModInv.setText(String.valueOf(modifyPart.getStock()));
        partModPrice.setText(String.valueOf(modifyPart.getPrice()));
        partModMax.setText(String.valueOf(modifyPart.getMax()));
        partModMin.setText(String.valueOf(modifyPart.getMin()));

        if(modifyPart instanceof InHouse){
            labelID.setText("Machine ID");
            partModIDName.setText(String.valueOf(((InHouse) modifyPart).getMachineId()));
            inRadio.setSelected(true);
            onlyNum = true;
        }
        if(modifyPart instanceof Outsourced){
            labelID.setText("Company Name");
            partModIDName.setText(String.valueOf((((Outsourced) modifyPart).getCName())));
            outRadio.setSelected(true);
            onlyNum = false;
        }
    }
}
