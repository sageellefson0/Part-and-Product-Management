package model_classfiles;

/** This class represents a part within the Inventory Management System.
* @author Sage Ellefson */
public abstract class Part {

/** The ID for the part. */
    private int id;
/** The name for the part. */
    private String name;
/** The price for the part. */
    private double price;
/** The stock/inventory for the part. */
    private int stock;
/** The min for the part. */
    private int min;
/** The max for the part. */
    private int max;

/** Constructor of a part instance.
* @param id The id for the part.
* @param name The name of the part.
* @param price The price of the part.
* @param stock The inventory level of the part.
* @param min The minimum level for the part.
* @param max The maximum level for the part. */
    public Part(int id, String name, double price, int stock, int min, int max){
    this.id = id;
    this.name = name;
    this.price = price;
    this.stock = stock;
    this.min = min;
    this.max = max;
    }

//////////// Methods - getters ////////////
/** Getter for the part ID.
* @return Returns id of the part */
    public int getId(){
        return id;}

/** Getter for the part name.
 * @return Returns name of the part */
    public String getName(){
        return name;}

/** Getter for the part price.
* @return Returns price of the part */
    public double getPrice(){
        return price;
    }
/** Getter for the part stock.
* @return Returns stock of the part */
    public int getStock(){
        return stock;
    }
/** Getter for the part minimum.
* @return Returns minimum of the part */
    public int getMin (){
        return min;
    }
/** Getter for the part maximum.
* @return Returns maximum of the part */
    public int getMax(){
        return max;
    }


//////////// Methods - setters /////////////
/** Setter for the part ID.
* @param id The part ID. */
    public void setId(int id){
        this.id = id;
    }
/** Setter for the part name.
* @param name The part name. */
    public void setName(String name){
        this.name = name;
    }
/** Setter for the part price.
* @param price The part name. */
    public void setPrice(double price){
        this.price = price;
    }
/** Setter for the part stock.
* @param stock The part stock. */
    public void setStock(int stock){
        this.stock = stock;
    }
/** Setter for the part min.
* @param min The part minimum. */
    public void setMin(int min){
        this.min = min;
    }
/** Setter for the part max.
* @param max The part maximum. */
    public void setMax(int max){
        this.max = max;
    }
}




