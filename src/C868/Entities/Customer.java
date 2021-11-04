package C868.Entities;

/**
 * This is the class for the Customer object
 * @author  Patrick Denney
 */
public class Customer {
    private final int customer_ID;
    private String customer_Name;
    private String address;
    private String postalCode;
    private String phone;
    private String createdDate;
    private String createdBy;
    private String lastUpdate;
    private String lastUpdatedBy;
    private int divisionID;//fk

    /**
     * This is the constructor method for the Customer object
     * @param customer_ID ID number generated by the database
     * @param customer_Name customer's name
     * @param address customer's street address
     * @param postalCode postal code for customer's address
     * @param phone customer's phone number
     * @param createdDate date and time that customer's record was created in the database
     * @param createdBy user who created the customer's record
     * @param lastUpdate date and time that customer's record was last updated in the database
     * @param lastUpdatedBy last user to update the customer's record
     * @param divisionID the first level division ID for the customers country
     */
    public Customer(int customer_ID, String customer_Name, String address, String postalCode, String phone, String createdDate, String createdBy, String lastUpdate, String lastUpdatedBy, int divisionID) {
        this.customer_ID = customer_ID;
        this.customer_Name = customer_Name;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.divisionID = divisionID;
    }

    /**
     *
     * @return Returns the customer's ID number
     */
    public int getCustomer_ID() {
        return customer_ID;
    }

    /**
     * Sets the customer's ID number
     * @return
     */
    public String getCustomer_Name() {
        return customer_Name;
    }
    /**
     * Sets the customer's name
     */
    public void setCustomer_Name(String customer_Name) {
        this.customer_Name = customer_Name;
    }

    /**
     * Returns the customer's street address
     * @return
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the customer's street address
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     *
     * @return Returns the customer's postal code
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Sets the customer's postal code
     * @param postalCode
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     *
     * @return Returns the customer's phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the customer's phone number
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     *
     * @return Returns the created date
     */
    public String getCreatedDate() {
        return createdDate;
    }

    /**
     * Sets the created date
     * @param createdDate
     */
    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    /**
     *
     * @return Returns the user that created the record
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Sets the user who created the record
     * @param createdBy
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     *
     * @return Returns the date and time that the record was last updated
     */
    public String getLastUpdate() {
        return lastUpdate;
    }

    /**
     * Sets the date and time that the record was last updated
     * @param lastUpdate
     */
    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     *
     * @return Returns the user who last updated the record
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * Sets the user who last updated the record
     * @param lastUpdatedBy
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * Returns the first level division ID for the customer's address
     * @return
     */
    public int getDivisionID() {
        return divisionID;
    }

    /**
     * Sets the first level division ID for the customer's address
     * @param divisionID
     */
    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }
}