package com.walterfcarvalho.ca01programming.domain;

import com.walterfcarvalho.ca01programming.domain.enums.AnimalType;

public class Bird extends AnimalZoo {

    // defines if this bird migrates or not
    private boolean isImigrant;

    /**
     * DEfault constructor
     */
    public Bird(){
        super();
    }

    /**
     * Contructor for Animal Bird
     * See Animal.class for details 
     * @throws Exception
     */
    public Bird(String habitat, String name, String specie, String weight, String dateOfBird,
            String imigrant) throws Exception {
        super(habitat, name, specie, AnimalType.BIRD, weight, dateOfBird);

        // Validation for local fields.
        String errors = "";

        boolean isImigrant = false;
                
        try {
            isImigrant = Boolean.parseBoolean(imigrant);
        } catch (Exception e) {
            errors += "\nfield: Imigrant \nmessage: Value for imigrant field is invalid";
        }

        if (errors.length() > 0)
            throw new IllegalArgumentException("\nIlegal argument Object " + this.getClass().getName() + " constructor " + errors);

        setImigrant(isImigrant);
    }

    
    public boolean isImigrant() {
        return isImigrant;
    }

    public void setImigrant(boolean isImigrant) {
        this.isImigrant = isImigrant;
    }

    /*
     * This method will print to concole all data a Object bird has
     * See Animal contructor for details.
     */
    public String toString() {
        return super.toString() +
                "\n\nProperties from Bird only " +
                "\nMigrant bird : " + (isImigrant ? "Yes" : "No");
    }

}
