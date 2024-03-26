package classes;

/**
 * Represents an in-house part.
 *
 * @author Tenny Akihary
 */

/**
 * InHouse Class Inheriting the Part class.
 */
public class InHouse extends Part{

    /**
     * An integer variable for the machine ID of an In-House Part.
     */
    private int machineId;

    /**
     * Constructor method for an In-House Part.
     *
     * @param id Part ID.
     * @param name Part Name.
     * @param price Part Price.
     * @param stock Part Inventory Level.
     * @param min Part Minimum Inventory Level.
     * @param max Part Maximum Inventory Level.
     * @param machineId Part Machine ID for In-House Part.
     */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId){
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }

    /**
     * Method for setting the Machine ID of a part.
     * @param machineId Part's Machine ID.
     */
    public void setMachineId(int machineId){
        this.machineId = machineId;
    }

    /**
     * Method for obtaining the Machine ID of a part.
     *
     * @return Returning the Machine ID of the part.
     */
    public int getMachineId(){
        return machineId;
    }
}

