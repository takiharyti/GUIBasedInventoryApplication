package main.tennyakihary_pa1;

import classes.Inventory;
import classes.Part;
import classes.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Product Modification Controller for the FXML file ProductMod.fxml.
 * Implements a way for modifying a selected Product from the MainView.
 *
 * Note: Since the Product Associated Parts are independent of
 * Inventory Parts, Associated Parts won't be deleted when parts
 * from the inventory are removed.
 *
 * @author Tenny Akihary
 */

public class ProductModController implements Initializable {

    /**
     * TextField for Product ID, uneditable.
     */
    @FXML
    private TextField productIDText;

    /**
     * TextField for Product Name.
     */
    @FXML
    private TextField productNameText;

    /**
     * TextField for Product Inventory Level.
     */
    @FXML
    private TextField productInvText;

    /**
     * TextField for Product Price.
     */
    @FXML
    private TextField productPriceText;

    /**
     * TextField for Product Maximum Inventory Level.
     */
    @FXML
    private TextField productMaxText;

    /**
     * TextField for Product Minimum Inventory Level.
     */
    @FXML
    private TextField productMinText;

    /**
     * Part ID column in DisplayPartsTableView.
     */
    @FXML
    private TableColumn<Part, Integer> displayPartsIDCol;

    /**
     * Part Name column in DisplayPartsTableView.
     */
    @FXML
    private TableColumn<Part, String> displayPartsNameCol;

    /**
     * Part Inventory Level column in DisplayPartsTableView.
     */
    @FXML
    private TableColumn<Part, Integer> displayPartsInvCol;

    /**
     * Part Price column in DisplayPartsTableView.
     */
    @FXML
    private TableColumn<Part, Double> displayPartsPriceCol;

    /**
     * TableView for Displaying all parts or searched parts.
     */
    @FXML
    private TableView<Part> displayPartsTableView;

    /**
     * Part ID column in assoPartsTableView.
     */
    @FXML
    private TableColumn<Part, Integer> assoPartsIDCol;

    /**
     * Part Name column in assoPartsTableView.
     */
    @FXML
    private TableColumn<Part, String> assoPartsNameCol;

    /**
     * Part Inventory Level column in assoPartsTableView.
     */
    @FXML
    private TableColumn<Part, Integer> assoPartsInvCol;

    /**
     * Part Price column in assoPartsTableView.
     */
    @FXML
    private TableColumn<Part, Double> assoPartsPriceCol;

    /**
     * TableView for Displaying all parts to be associated.
     */
    @FXML
    private TableView<Part> assoPartsTableView;

    /**
     * TextField for searching parts.
     */
    @FXML
    private TextField partSearchText;

    /**
     * Get product to be modified.
     */
    Product modProduct = MainViewController.getModProduct();

    /**
     * Observable list for holding all associated parts selected and displayed.
     */
    ObservableList<Part> assoPartsList = modProduct.getAllAssociatedParts();

    /**
     * Create an index for the product being modified.
     */
    int indexModifyProduct = Inventory.getAllProducts().indexOf(modProduct);

    /**
     * Method for adding a selected part on the event to the associated parts list and
     * displaying it on assoDisplayTableView.
     * @param event add button clicked.
     */
    @FXML
    void addButtonPressed(ActionEvent event){
        Part partToAdd = displayPartsTableView.getSelectionModel().getSelectedItem();
        if(partToAdd == null)
            alert(1);
        else if(assoPartsList.contains(partToAdd))
            alert(3);
        else {
            assoPartsList.add(partToAdd);
            assoPartsTableView.setItems(assoPartsList);
        }
    }

    /**
     * Method for removing selected associated part on event.
     * Since associated parts and inventory parts are independent
     * there may be parts still in a products associated
     * parts that are no longer in the parts inventory.
     * @param event Remove Associated Part button clicked.
     */
    @FXML
    void removeButtonPressed(ActionEvent event) {
        Part selected = assoPartsTableView.getSelectionModel().getSelectedItem();
        if(selected == null)
            alert(1);
        else {
            Alert warn = new Alert(Alert.AlertType.CONFIRMATION);
            warn.setTitle("Warning: Removing part.");
            warn.setHeaderText("Are you sure you want to delete this part?\n If the part" +
                    " is no longer in the Inventory it will be lost forever");
            warn.showAndWait();
            if (warn.getResult() == ButtonType.OK) {
                assoPartsList.remove(assoPartsTableView.getSelectionModel().getSelectedItem());
                warn.setAlertType(Alert.AlertType.INFORMATION);
                warn.setTitle("Successful.");
                warn.setHeaderText("Part was removed.");
                warn.showAndWait();
            }
        }
    }

    /**
     * Method for performing the search upon Search button being pressed.
     * @param event Search button clicked.
     */
    @FXML
    void searchButtonPressed(ActionEvent event){
        ObservableList<Part> matchingParts = FXCollections.observableArrayList();

        String searchText = partSearchText.getText().trim().toLowerCase();

        if(searchText.equals(""))
            displayPartsTableView.setItems(Inventory.getAllParts());
        else {
            for(Part p : Inventory.getAllParts()){
                if(String.valueOf(p.getId()).contains(searchText) || p.getName().toLowerCase().contains(searchText))
                    matchingParts.add(p);
            }
            if(matchingParts.isEmpty())
                alert(0);
            else
                displayPartsTableView.setItems(matchingParts);
        }
    }

    /**
     * Method that on event it will check inputs and save the Product to the inventory.
     * Then the program will redirect to the main form.
     * @param event Save Button clicked.
     */
    @FXML
    void saveButtonPressed(ActionEvent event){
        try {
            int max = Integer.parseInt(productMaxText.getText());
            int min = Integer.parseInt(productMinText.getText());
            double price = Double.parseDouble(productPriceText.getText());
            int inv = Integer.parseInt(productInvText.getText());
            String name = productNameText.getText();
            if (name.isEmpty() || (max <= min) || (min < 1) ||
                    (inv > max) || (inv < min) || (price < 0.01)) {
                alert(2);
            } else {
                Product newProduct = new Product(modProduct.getId(), name, price, inv, min, max);
                for(Part p : assoPartsList)
                    newProduct.addAssociatedPart(p);
                Inventory.updateProduct(indexModifyProduct, newProduct);
                backToMain(event);
            }
        } catch(NumberFormatException | IOException error) {
            alert(2);
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
    static void alert(int num){
        Alert error = new Alert(Alert.AlertType.ERROR);
        switch(num){
            case 0:
                error.setAlertType(Alert.AlertType.INFORMATION);
                error.setTitle("Information.");
                error.setHeaderText("Can't find Part!");
                error.showAndWait();
                break;
            case 1:
                error.setTitle("Error!");
                error.setHeaderText("A Part is NOT selected!");
                error.showAndWait();
                break;
            case 2:
                error.setTitle("ERROR: Incorrect Values!");
                error.setHeaderText("ERROR: Incorrect Values. Ensure your information is accurate then try again.");
                error.showAndWait();
                break;
            case 3:
                error.setTitle("ERROR: Duplicate Parts!");
                error.setHeaderText("ERROR: Duplicated parts. This part is already associated with the product.");
                error.showAndWait();
        }
    }

    /**
     * Override class for implementing the initialize method.
     * Pre-filled textfields with all the correct data.
     * initializes the columns with the tableview and populates the top, all parts, table
     * and the bottom, associated parts, table.
     * @param address Location.
     * @param resource Resource.
     */
    @Override
    public void initialize(URL address, ResourceBundle resource){

        productIDText.setPromptText(String.valueOf(modProduct.getId()));
        productNameText.setText(modProduct.getName());
        productInvText.setText(String.valueOf(modProduct.getStock()));
        productPriceText.setText(String.valueOf(modProduct.getPrice()));
        productMaxText.setText(String.valueOf(modProduct.getMax()));
        productMinText.setText(String.valueOf(modProduct.getMin()));

        displayPartsTableView.setItems(Inventory.getAllParts());
        displayPartsIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        displayPartsNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        displayPartsPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        displayPartsInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));

        assoPartsTableView.setItems(assoPartsList);
        assoPartsIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        assoPartsNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        assoPartsPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        assoPartsInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
    }
}
