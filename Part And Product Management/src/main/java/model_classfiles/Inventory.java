package model_classfiles;

/* Imports */
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** This class represents the inventory within the Inventory Management System.
* @author Sage Ellefson */
public class Inventory {

////////////// Observable lists for all parts and all products ////////////////
/** Observable list for all parts. */
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();

/** Observable list for all products. */
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();


//////////// Add part and add product methods ////////////
/** Adds a part to all parts.
* @param newPart The new part to add.*/
    public static void addPart(Part newPart) {
        allParts.add(newPart);}

/** Adds a part to all products.
* @param newProduct The new product to add.*/
    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);}


///////////// Getter methods for parts and products ////////////////
/** Getter for all parts.
* @return Returns all parts. */
    public static ObservableList<Part> getAllParts() {
        return allParts;}

/** Getter for all products.
* @return Returns all products. */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;}


///////////// Lookup for part name and product name //////////////
/** Searches parts list by name.
* @param partName The part name.
* @return Returns the list of parts found matching the entered name. */
    public static ObservableList<Part> lookupPart (String partName) {
        ObservableList<Part> searchedParts = FXCollections.observableArrayList();
        for (Part partNameSearch : allParts) {
            if (partNameSearch.getName().contains(partName)) {
                searchedParts.add(partNameSearch);
            }
        }
        return searchedParts;
    }

/** Searches product list by name.
* @param productName The product name.
* @return Returns the list of products found matching the entered name. */
    public static ObservableList<Product> lookupProduct (String productName) {
        ObservableList<Product> searchedProducts = FXCollections.observableArrayList();
        for (Product productNameSearch : allProducts) {
            if (productNameSearch.getName().contains(productName)) {
                searchedProducts.add(productNameSearch);
            }
        }
        return searchedProducts;
    }

/** Searches part list by ID.
* @param id  The part ID.
* @return Returns the list of parts found matching the entered ID. */
    public static Part lookupPart(int id){
        Part partFound = null;

        for (Part part : allParts) {
            if (part.getId() == id) {
                partFound = part;
            }
        }
        return partFound;
    }

/** Searches product list by ID.
* @param id  The product ID.
* @return Returns the list of products found matching the entered ID. */
    public static Product lookupProduct(int id){
        Product productFound = null;
        for (Product product : allProducts) {
            if (product.getId() == id) {
                productFound = product;
            }
        }
        return productFound;
    }


//////////////// Action for delete buttons ////////////////////
/** Removes a selected part.
* @param selectedPart The part selected.
* @return Returns a boolean value of true or false indicating if a part was removed. */
    public static boolean deletePart(Part selectedPart){
        if (allParts.contains(selectedPart)){
            allParts.remove(selectedPart);
            return true;
        }
        else {
            return false;
        }
    }

/** Removes a selected product.
* @param selectedProduct The product selected.
* @return Returns a boolean value of true or false indicating if a product was removed. */
    public static boolean deleteProduct(Product selectedProduct){
        if (allProducts.contains(selectedProduct)){
            allProducts.remove(selectedProduct);
            return true;
        }
        else {
            return false;
        }
    }


//////////// Unique part and product IDs /////////////
/** Assigns a value of 0 to the part ID. */
    private static int partID = 10;
/** Method that contains the new part ID.
* @return Returns the part ID incrementally increasing by using the "++" operator. */
    public static int newPartID()
    {
        return ++partID;
    }

/** Assigns a value of 0 to the product ID. */
    private static int prodID= 100;
/** Method that contains the new prod ID.
* @return Returns the product ID incrementally increasing by using the "++" operator. */
    public static int newProductID()
    {
        return ++prodID;
    }


////////////// Update part and product methods //////////////
/** Updates a part in the list of parts.
* @param index Index of the part to be updated.
* @param selectedPart The new part that is replacing the previous part. */
    public static void updatePart (int index, Part selectedPart){
        allParts.set(index, selectedPart);
    }

/** Updates a product in the list of products.
* @param index Index of the product to be updated.
* @param selectedProduct The new product that is replacing the previous product. */
    public static void updateProduct (int index, Product selectedProduct){
        allProducts.set(index, selectedProduct);
    }


}





