package com.walterfcarvalho.ca01programming.domain;

import com.walterfcarvalho.ca01programming.domain.enums.AnimalType;

/**
 * Provide a class to Animal type mammals.
 * structure to our application
 * 
 * @author Valter Carvalho
 * @since 1.0
 */

public class Mammal extends AnimalZoo {

    // determine how many mamaml glands a mamal has
    private short mamaryGlands;

    public Mammal(){
        super();
    }
    public Mammal(String habitat, String name, String specie, String weight, String dateOfBird,
            String mamaryGlands)  throws Exception {
        super(habitat, name, specie, AnimalType.MAMAL, weight, dateOfBird);

        // Validation for local fields.
        String errors = "";

        short glands = 0;
                
        try {
            glands = Short.parseShort(mamaryGlands);
        } catch (Exception e) {
            errors += "\nfield: Mamamry glands \nmessage: Value for mamamry gland is invalid";
        }

        if (errors.length() > 0)
            throw new IllegalArgumentException("",
                    new Throwable("\nIlegal argument Object " + this.getClass().getName() + " constructor " + errors));

        setMamaryGlands(glands);
    }

    public short getMamaryGlands() {
        return mamaryGlands;
    }

    public void setMamaryGlands(short mamaryGlands) {
        this.mamaryGlands = mamaryGlands;
    }

    /*
     * This method will print to concole all data a Object mammmal has
     */
    public String toString() {
        return super.toString() +
                "\n\nProperties from Mammal only " +
                "\nMamary Glands: " + getMamaryGlands() +
                " ";

    }
}
