package classes;

/**
 * Represents an Outsourced Part.
 *
 * @author Tenny Akihary
 */

/**
 * Outsourced class inheriting the Part class.
 */
public class Outsourced extends Part {

    /**
     * String variable for the Company Name of an Outsourced Part.
     */
    private String companyName;

    /**
     * Constructor method for an Outsourced Part.
     *
     * @param id          Part ID.
     * @param name        Part Name.
     * @param price       Part Price.
     * @param stock       Part Inventory Level.
     * @param min         Part Minimum Inventory Level.
     * @param max         Part Maximum Inventory Level.
     * @param companyName Part Company Name for Outsourced Part.
     */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /**
     * Method for setting the Company Name of a part.
     *
     * @param companyName Part's Company Name.
     */
    public void setCName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * Method for obtaining the Company Name of a part.
     *
     * @return Company Name of the part.
     */
    public String getCName() {
        return companyName;
    }

}