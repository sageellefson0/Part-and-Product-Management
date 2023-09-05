package model_classfiles;

/** This class represents an InHouse part within the Inventory Management System.
* @author Sage Ellefson */
public class InHouse extends Part{

/** The machine ID for an in-house part. */
    private int machineId;

/** Constructor of a part instance.
 * @param id The id for the part.
 * @param name The name of the part.
 * @param price The price of the part.
 * @param stock The inventory level of the part.
 * @param min The minimum level for the part.
 * @param max The maximum level for the part.
 * @param machineId  The machine ID for an in-house part. */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }

/** Getter for the machine ID.
* @return Returns the machine ID for an in-house part. */
    public int getMachineId(){
        return machineId;
    }

/** Setter for the machine ID.
* @param machineId The machine ID for an in-house part.*/
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }
}
