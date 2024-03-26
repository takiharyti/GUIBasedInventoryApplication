package classes;

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

/**
 * Representation of a product.
 * May Contain Part Objects.
 *
 * @author Tenny Akihary
 */

public class Product {

    /**
     * Lists the associated parts.
     */
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    /**
     * Variable for Product ID.
     */
    private int id;

    /**
     * Variable for Product Name.
     */
    private String name;

    /**
     * Variable for Product Price.
     */
    private double price;

    /**
     * Variable for Product Inventory Level.
     */
    private int stock;

    /**
     * Variable for minimum Inventory Level.
     */
    private int min;

    /**
     * Variable for maximum Inventory Level.
     */
    private int max;

    /**
     * Constructor for a Product Object.
     *
     * @param id Product ID.
     * @param name Product Name.
     * @param price Product Price.
     * @param stock Product Intentory Level.
     * @param min Product Minimum Inventory Level.
     * @param max Product Maximum Inventory Level.
     */
    public Product(int id, String name, double price, int stock, int min, int max){
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**
     * Set Product ID.
     * @param id Product ID.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Set Product Name.
     * @param name Product Name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set Product Price.
     * @param price Product Price.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Set Inventory Level.
     * @param stock Product Inventory Level.
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Set Product Min Inventory Level.
     * @param min Product Minimum Inventory Level.
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * Set Product Max Inventory Level.
     * @param max Max Inventory Level.
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * Obtaining the Product ID.
     * @return Product ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Obtaining the Product Name.
     * @return Product Name.
     */
    public String getName() {
        return name;
    }

    /**
     * Obtaining the Product Price.
     * @return Product Price.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Obtaining the Product Inventory Level.
     * @return Product Inventory Level.
     */
    public int getStock() {
        return stock;
    }

    /**
     * Obtaining the Product Minimum Inventory Level.
     * @return Product Minimum Inventory Level.
     */
    public int getMin() {
        return min;
    }

    /**
     * Obtaining the Product Maximum Inventory Level.
     * @return Product Maximum Inventory Level.
     */
    public int getMax() {
        return max;
    }

    /**
     * Method for associating a part with a product.
     * @param part Part to be associated.
     */
    public void addAssociatedPart(Part part){
        associatedParts.add(part);
    }

    /**
     * Method for dissociating a part from the product.
     * @param selectedAssociatedPart Part to dissociate.
     * @return Boolean for whether the given Part was dissociated from the Product.
     */
    public boolean deleteAssociatedPart(Part selectedAssociatedPart){
        if(associatedParts.contains(selectedAssociatedPart)){
            associatedParts.remove(selectedAssociatedPart);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method for listing the associated Parts of the Product.
     * @return List of Parts.
     */
    public ObservableList<Part> getAllAssociatedParts(){
        return associatedParts;
    }

}
