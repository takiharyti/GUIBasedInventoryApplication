package main.tennyakihary_pa1;

import classes.InHouse;
import classes.Inventory;
import classes.Outsourced;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Part Add controller class uses methods to add data to the inventory.
 * Catching unacceptable inputs, such as blank spaces, mins > max, negative values,
 * and incorrect types for In-House and Outsourced Parts.
 *
 * @author Tenny Akihary
 */

public class PartAddController{

    /**
     * Flag for radio buttons and if only Numbers need to exist.
     */
    private boolean onlyNum = true;

    /**
     * Label variable to allow switching from In to Outsource.
     */
    @FXML
    private Label labelID;

    /**
     * TextField for the Name of the part.
     */
    @FXML
    private TextField partAddName;

    /**
     * TextField for the Part Inventory.
     */
    @FXML
    private TextField partAddInv;

    /**
     * TextField for the Part Price.
     */
    @FXML
    private TextField partAddPrice;

    /**
     * TextField for the part Maximum Inventory.
     */
    @FXML
    private TextField partAddMax;

    /**
     * TextField for the part Minimum Inventory.
     */
    @FXML
    private TextField partAddMin;

    /**
     * TextField for the ID Name. Machine ID or Company Name.
     */
    @FXML
    private TextField partAddIDName;

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
     * Method for saving the Part into the Inventory.
     * @throws IOException From FMLLoader.
     */
    @FXML
    void saveButtonPressed(ActionEvent event) throws IOException {
        if (onlyNum) {
            try {
                int machID = Integer.parseInt(partAddIDName.getText());
                int max = Integer.parseInt(partAddMax.getText());
                int min = Integer.parseInt(partAddMin.getText());
                double price = Double.parseDouble(partAddPrice.getText());
                int inv = Integer.parseInt(partAddInv.getText());
                String name = partAddName.getText();
                if (name.isEmpty() || (max <= min) || (min < 1) ||
                        (inv > max) || (inv < min) || (price < 0.01)) {
                    alert();
                    clearFields();
                } else {
                    InHouse newPart = new InHouse(Inventory.getID(true), name, price, inv, min, max, machID);
                    Inventory.addPart(newPart);
                    backToMain(event);
                }
            } catch (NumberFormatException error) {
                alert();
                clearFields();
            }
        } else {
            try {
                String companyName = partAddIDName.getText();
                int max = Integer.parseInt(partAddMax.getText());
                int min = Integer.parseInt(partAddMin.getText());
                double price = Double.parseDouble(partAddPrice.getText());
                int inv = Integer.parseInt(partAddInv.getText());
                String name = partAddName.getText();
                if (name.isEmpty() || companyName.isEmpty() || (max <= min) ||
                        (min < 1) || (inv > max) || (inv < min) || (price < 0.01)) {
                    alert();
                    clearFields();
                } else {
                    Outsourced newPart = new Outsourced(Inventory.getID(true), name, price, inv, min, max, companyName);
                    Inventory.addPart(newPart);
                    backToMain(event);
                }
            } catch (NumberFormatException error) {
                alert();
                clearFields();
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
     * Method for clearing all fields.
     */
    void clearFields(){
        partAddName.clear();
        partAddIDName.clear();
        partAddInv.clear();
        partAddMax.clear();
        partAddMin.clear();
        partAddPrice.clear();
    }
}
