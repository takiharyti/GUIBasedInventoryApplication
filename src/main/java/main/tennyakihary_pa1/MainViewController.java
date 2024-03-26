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
import javafx.scene.control.*;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Main class controller to handle the Main View and all its controls through MainView.fxml.
 * Implements a way of viewing inventory, giving options for switching to
 * other forms to add or modify parts and products in the inventory.
 *
 * ERROR: Initialize override method threw a Java Runtime Exception, IllegalAccessException,
 * while trying to fill each column cell using PropertyValueFactory.
 * Fix was to open the classes package to the java javafx.base in module-info.java.
 *
 * Fix on line 278-279 and in the alert(int num) method to include an error exception when a user
 * attempts to delete a product when it is linked with parts.
 *
 * @author Tenny AKihary
 */

public class MainViewController implements Initializable{

    /**
     * String input by user in the Part search box.
     */
    @FXML
    private TextField partSearchText;

    /**
     * String input by user in the Product search box.
     */
    @FXML
    private TextField productSearchText;

    /**
     * TableView for all the Parts in inventory.
     */
    @FXML
    private TableView<Part> partTableView;

    /**
     * Column in the Parts TableView for displaying ID.
     */
    @FXML
    private TableColumn<Part, Integer> partColumnID;

    /**
     * Column in the Parts TableView for displaying Name.
     */
    @FXML
    private TableColumn<Part, String> partColumnName;

    /**
     * Column in the Parts TableView for displaying Inventory Level.
     */
    @FXML
    private TableColumn<Part, Integer> partColumnInventoryLevel;

    /**
     * Column in the Parts TableView for displaying Price.
     */
    @FXML
    private TableColumn<Part, Double> partColumnPrice;

    /**
     * TableView for all the Products in inventory.
     */
    @FXML
    private TableView<Product> productTableView;

    /**
     * Column in the Products TableView for displaying ID.
     */
    @FXML
    private TableColumn<Product, Integer> productColumnID;

    /**
     * Column in the Products TableView for displaying Name.
     */
    @FXML
    private TableColumn<Product, String> productColumnName;

    /**
     * Column in the Products TableView for displaying Inventory Level.
     */
    @FXML
    private TableColumn<Product, Integer> productColumnInventoryLevel;

    /**
     * Column in the Products TableView for displaying Price.
     */
    @FXML
    private TableColumn<Product, Double> productColumnPrice;

    /**
     * Part object to be modified.
     */
    private static Part modPart;

    /**
     * Product to be modified.
     */
    private static Product modProduct;


    /**
     * Searches the Parts Inventory.
     * @param event On Parts Search Button Pressed.
     */
    @FXML
    void onPartSearchButtonPressed(ActionEvent event){
        ObservableList<Part> matchingParts = FXCollections.observableArrayList();

        String searchText = partSearchText.getText().trim().toLowerCase();

        if(searchText.equals(""))
            partTableView.setItems(Inventory.getAllParts());
        else {
            for(Part p : Inventory.getAllParts()){
                if(String.valueOf(p.getId()).contains(searchText) || p.getName().toLowerCase().contains(searchText))
                    matchingParts.add(p);
            }
            if(matchingParts.isEmpty())
                alert(0);
            else
                partTableView.setItems(matchingParts);
        }
    }

    /**
     * Searches the Products Inventory.
     * @param event On Products Search Button Pressed.
     */
    @FXML
    void onProductSearchButtonPressed(ActionEvent event){

        ObservableList<Product> matchingProducts = FXCollections.observableArrayList();

        String searchText = productSearchText.getText().trim().toLowerCase();

        if(searchText.equals(""))
            productTableView.setItems(Inventory.getAllProducts());
        else {
            for(Product p : Inventory.getAllProducts()){
                if(String.valueOf(p.getId()).contains(searchText) || p.getName().toLowerCase().contains(searchText))
                    matchingProducts.add(p);
            }
            if(matchingProducts.isEmpty())
                alert(0);
            else
                productTableView.setItems(matchingProducts);
        }

    }

    /**
     * Action for add button loads the PartAdd.fxml form.
     * @param event Add button under Parts is pressed.
     * @throws IOException Throws an exception fromFXMLLoader.
     */
    @FXML
    void addPartButtonPressed(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("PartAdd.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 400);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Add Part Form");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Action for add button loads the ProductAdd.fxml form.
     * @param event Add button under Products is pressed.
     * @throws IOException From FXMLLoader.
     */
    @FXML
    void addProductButtonPressed(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ProductAdd.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 400);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Add Product Form");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Action for modify button loads the PartMod.fxml form.
     * @param event Modify button under Parts is pressed.
     * @throws IOException Throws an exception fromFXMLLoader.
     */
    @FXML
    void modPartButtonPressed(ActionEvent event) throws IOException{

        modPart = partTableView.getSelectionModel().getSelectedItem();

        if(modPart == null){
            alert(0);
        } else {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("PartMod.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 400);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Modify Part Form");
            stage.setScene(scene);
            stage.show();
        }
    }

    /**
     * Action for modify button loads the ProductMod.fxml form.
     * @param event Modify button under Products is pressed.
     * @throws IOException Throws an exception fromFXMLLoader.
     */
    @FXML
    void modProductButtonPressed(ActionEvent event) throws IOException{

        modProduct = productTableView.getSelectionModel().getSelectedItem();

        if(modProduct == null){
            alert(0);
        } else {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ProductMod.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 400);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Modify Product Form");
            stage.setScene(scene);
            stage.show();
        }

    }

    /**
     * Method for deleting a selected Part using the Delete Button.
     * @param event Event of clicking the delete button under parts.
     */
    @FXML
    void deletePartButtonPressed(ActionEvent event){
        Part delPart = partTableView.getSelectionModel().getSelectedItem();
        if(delPart == null){
            alert(1);
        } else {
            Alert warn = new Alert(Alert.AlertType.CONFIRMATION);
            warn.setTitle("Warning: Deleting Part/Product.");
            warn.setHeaderText("Are you sure you want to delete this part/product?");
            warn.showAndWait();
            if(warn.getResult() == ButtonType.OK) {
                Inventory.deletePart(delPart);
                warn.setAlertType(Alert.AlertType.INFORMATION);
                warn.setTitle("Successful.");
                warn.setHeaderText("Part was deleted.");
                warn.showAndWait();
            }
        }
    }

    /**
     * Method for deleting a selected Product using the Delete Button.
     * @param event Event of clicking the delete button under products.
     */
    @FXML
    void deleteProductButtonPressed(ActionEvent event){
        Product delProduct = productTableView.getSelectionModel().getSelectedItem();
        if(delProduct == null){
            alert(1);

        //UPDATE: Added an else if statement to catch a product with associated parts.
        } else if(!delProduct.getAllAssociatedParts().isEmpty()) {
            alert(2);
        } else {
            Alert warn = new Alert(Alert.AlertType.CONFIRMATION);
            warn.setTitle("Warning: Deleting Part/Product.");
            warn.setHeaderText("Are you sure you want to delete this part/product?");
            warn.showAndWait();
            if (warn.getResult() == ButtonType.OK) {
                Inventory.deleteProduct(delProduct);
                warn.setAlertType(Alert.AlertType.INFORMATION);
                warn.setTitle("Successful.");
                warn.setHeaderText("Product was deleted.");
                warn.showAndWait();
            }
        }
    }

    /**
     * Alert system to inform the user of different types of alerts when using the system.
     * @param num Integer for the different types of alerts.
     */
    private void alert(int num){
        Alert error = new Alert(Alert.AlertType.ERROR);
        switch(num){
            case 0:
                error.setAlertType(Alert.AlertType.INFORMATION);
                error.setTitle("Information.");
                error.setHeaderText("Can't find Part/Product!");
                error.showAndWait();
                break;
            case 1:
                error.setTitle("Error!");
                error.setHeaderText("A Part/Product is NOT selected!");
                error.showAndWait();
                break;

            //UPDATE: Added an alert in the case a product has an associated part.
            case 2:
                error.setTitle("Error!");
                error.setHeaderText("The product has associated parts and cannot be deleted!");
                error.showAndWait();
                break;
            case 3:
                error.setAlertType(Alert.AlertType.CONFIRMATION);
                error.setTitle("Warning: Exiting System.");
                error.setHeaderText("Would you like to exit?");
                error.showAndWait();
                if(error.getResult() == ButtonType.OK)
                    System.exit(0);
                else
                    error.close();
                break;
        }
    }

    /**
     * Action Event for the program to close upon being clicked.
     * @param event Event object of button being clicked.
     */
    @FXML
    void exitButtonPressed(ActionEvent event){
        alert(3);
    }

    /**
     * Get method for the Part being modified.
     * @return the part selected.
     */
    public static Part getModPart(){
        return modPart;
    }

    /**
     * Get method for the Product being modified.
     * @return the product selected.
     */
    public static Product getModProduct(){
        return modProduct;
    }


    /**
     * Initialization for the controller.
     * Input for table views and columns.
     * @param address Location.
     * @param resource Resources.
     */
    @Override
    public void initialize(URL address, ResourceBundle resource){

        partTableView.setItems(Inventory.getAllParts());
        partColumnID.setCellValueFactory(new PropertyValueFactory<>("id"));
        partColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        partColumnPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        partColumnInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));

        productTableView.setItems(Inventory.getAllProducts());
        productColumnID.setCellValueFactory(new PropertyValueFactory<>("id"));
        productColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        productColumnPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        productColumnInventoryLevel.setCellValueFactory(new PropertyValueFactory<>("stock"));
    }

}