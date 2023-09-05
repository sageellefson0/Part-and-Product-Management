package model_classfiles;

/* Imports */
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** This class represents a product within the Inventory Management System.
* @author Sage Ellefson */
public class Product {

/** The ID for the product. */
    private int id;
/** The name for the product. */
    private String name;
/** The price for the product. */
    private double price;
/** The stock/inventory for the product. */
    private int stock;
/** The min for the product. */
    private int min;
/** The max for the product. */
    private int max;

/** Constructor of a product instance.
* @param id The id for the product.
* @param name The name of the product.
* @param price The price of the product.
* @param stock The inventory level of the product.
* @param min The minimum level for the product.
* @param max The maximum level for the product. */
    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }


//////////// Methods - getters ////////////

/** Getter for the product ID.
* @return Returns id of the product */
    public int getId() {
        return id;
    }

/** Getter for the product name.
* @return Returns name of the product */
    public String getName() {
        return name;
    }

/** Getter for the product price.
* @return Returns price of the product */
    public double getPrice() {
        return price;
    }

/** Getter for the product stock.
* @return Returns stock of the product */
    public int getStock() {
        return stock;
    }

/** Getter for the product minimum.
* @return Returns minimum of the product */
    public int getMin() {
        return min;
    }

/** Getter for the product maximum.
* @return Returns maximum of the product */
    public int getMax() {
        return max;
    }

/////////////// Methods - setters ////////////////

/** Setter for the product ID.
* @param id The product ID. */
    public void setId(int id) {
        this.id = id;
    }

/** Setter for the product name.
* @param name The product name. */
    public void setName(String name) {
        this.name = name;
    }

/** Setter for the product price.
* @param price The product price. */
    public void setPrice(double price) {
        this.price = price;
    }

/** Setter for the product stock.
* @param stock The product stock. */
    public void setStock(int stock) {
        this.stock = stock;
    }

/** Setter for the product min.
* @param min The product minimum. */
    public void setMin(int min) {
        this.min = min;
    }

/** Setter for the product max.
* @param max The product maximum. */
    public void setMax(int max) {
        this.max = max;
    }

//////////// Associated parts methods and declaration /////////////
/** This is the list of associated parts for the product. */
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();

/** Adds a part as an associated part of a product.
* @param part The part to be added. */
    public void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }

/** Getter for the associated parts.
* @return Returns associated parts list*/
    public ObservableList<Part> getAllAssociatedParts() {
        return associatedParts;
    }

/** Deletes a part from the associated parts list of a specific product.
* @param selectedAssociatedPart The part to delete
* @return Returns a boolean value indicating true or false for part deletion */
    public boolean deleteAssociatedPart(Part selectedAssociatedPart) {
        if (associatedParts.contains(selectedAssociatedPart)) {
            associatedParts.remove(selectedAssociatedPart);
            return true;
        } else
            return false;
    }

}


