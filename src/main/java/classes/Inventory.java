package classes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 * Representation of an Inventory for Parts and Products.
 *
 * @author Tenny Akihary
 */



public class Inventory {

    /**
     * Creating the ObservableList variable to hold all Parts.
     */
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();

    /**
     * Creating the ObservableList variable to hold all Products.
     */
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /**
     * Incremental ID for Parts.
     */
    private static int partID = 0;

    /**
     * Incremental ID for Products.
     */
    private static int productID = 1000;

    /**
     * Adds parts to the Inventory.
     * @param newPart Part to add.
     */
    public static void addPart(Part newPart){
        allParts.add(newPart);
    }

    /**
     * Adds products to the Inventory.
     * @param newProduct Product to add.
     */
    public static void addProduct(Product newProduct){
        allProducts.add(newProduct);
    }

    /**
     * Part search by ID.
     * @param partId ID to be searched.
     * @return Part that matches the ID.
     */
    public static Part lookupPart(int partId){
        Part tempPart = null;
        for(Part p : allParts){
            if(p.getId() == partId)
                tempPart = p;
        }
        return tempPart;
    }

    /**
     * Product search by ID.
     * @param productId ID to be searched.
     * @return Product that matches the ID.
     */
    public static Product lookupProduct(int productId){
        Product tempProduct = null;
        for(Product p : allProducts) {
            if (p.getId() == productId)
                tempProduct = p;
        }
        return tempProduct;
    }

    /**
     * Part search by Name.
     * @param partName Name to be searched.
     * @return Part that matches the Name.
     */
    public static ObservableList<Part> lookupPart(String partName){
        ObservableList<Part> tempPart = FXCollections.observableArrayList();
        for(Part p : allParts){
            if(p.getName().equals(partName))
                tempPart.add(p);
        }
        return tempPart;
    }

    /**
     * Product search by Name.
     * @param productName Name to be searched.
     * @return Product that matches the name.
     */
    public static ObservableList<Product> lookupProduct(String productName){
        ObservableList<Product> tempProduct = FXCollections.observableArrayList();
        for(Product p : allProducts){
            if(p.getName().equals(productName))
                tempProduct.add(p);
        }
        return tempProduct;
    }

    /**
     * Part update/replaced.
     * @param index Index for the update.
     * @param selectedPart Part to be placed in the inventory.
     */
    public static void updatePart(int index, Part selectedPart){
        allParts.set(index, selectedPart);
    }

    /**
     * Products update/replaced.
     * @param index Index for the update.
     * @param selectedProduct Product to be placed in the inventory.
     */
    public static void updateProduct(int index, Product selectedProduct){
        allProducts.set(index, selectedProduct);
    }

    /**
     * Deleting part from the inventory.
     * @param selectedPart Part that is to be removed.
     * @return Status of removal.
     */
    public static boolean deletePart(Part selectedPart){
        if(allParts.contains(selectedPart)) {
            allParts.remove(selectedPart);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Deleting product fromt he inventory.
     * @param selectedProduct Product that is to be removed.
     * @return Status of removal.
     */
    public static boolean deleteProduct(Product selectedProduct){
        if(allProducts.contains(selectedProduct)){
            allProducts.remove(selectedProduct);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method for returning all Parts.
     * @return ObservableList with inventory of all Parts.
     */
    public static ObservableList<Part> getAllParts(){
        return allParts;
    }

    /**
     * Method for returning all Products.
     * @return ObservableList with inventory of all Products.
     */
    public static ObservableList<Product> getAllProducts(){
        return allProducts;
    }

    /**
     * Class for unique ID returning.
     * @param type True for Part, false for Product.
     * @return Unique ID incrementing by 1.
     */
    public static int getID(boolean type){
        if(type)
            return partID++;
        else
            return productID++;
    }



}
