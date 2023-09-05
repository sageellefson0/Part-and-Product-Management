package model_classfiles;

/** This class represents an outsourced part within the Inventory Management System.
* @author Sage Ellefson */
public class Outsourced extends Part {

/** The company name for an outsourced part. */
    private String companyName;

/** Constructor of a part instance.
 * @param id The id for the part.
 * @param name The name of the part.
 * @param price The price of the part.
 * @param stock The inventory level of the part.
 * @param min The minimum level for the part.
 * @param max The maximum level for the part.
 * @param companyName  The company name for an outsourced part. */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName){
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

/** Getter for the company name.
* @return Returns the company name for an outsourced part. */
    public String getCompanyName(){
        return companyName;
    }

/** Setter for the company name.
* @param companyName The company name for an outsourced part. */
    public void setCompanyName(String companyName){
        this.companyName = companyName;
    }
}