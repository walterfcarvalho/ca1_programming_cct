package com.walterfcarvalho.ca01programming.domain;

import com.walterfcarvalho.ca01programming.domain.enums.AnimalType;

/**
 * Provide a abstract class AnimalZoo witch can be extended for better
 * representation
 * 
 */
public class Reptile extends AnimalZoo {

    // This property defines is a animal either aquatico or not
    private boolean isLayEgg;

    public Reptile(){
        super();
    }

    /**
     * Contructor for Animal Reptile
     * See Animal.class for details 
    */
    public Reptile(String habitat, String name, String specie, 
        String weight, String dateOfBird, String immigrant) throws Exception {
        super( habitat, name, specie, AnimalType.REPTILE, weight, dateOfBird);

        // Validation for local fields.
        String errors = "";

        boolean isImigrant = false;
                
        try {
            isImigrant = Boolean.parseBoolean(immigrant);
        } catch (Exception e) {
            errors += "\nfield: Imigrant \nmessage: Value for imigrant field is invalid";
        }

        if (errors.length() > 0)
            throw new IllegalArgumentException("",
                    new Throwable("\nIlegal argument Object " + this.getClass().getName() + " constructor " + errors));

        setIsLand(isImigrant);
    }

    public boolean isLayEgg() {
        return isLayEgg;
    }

    public void setIsLand(boolean isLayEgg) {
        this.isLayEgg = isLayEgg;
    }

    /**
     * toString
     * See Animal.class for details 
    */
    public String toString() {
        return super.toString() +
                "\n\nProperties from Reptile only " +
                "\nCan lay eggs: " + (isLayEgg() ? "Yes" : "No");
    }

}
