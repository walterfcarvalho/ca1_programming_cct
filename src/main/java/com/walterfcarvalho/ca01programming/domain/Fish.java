package com.walterfcarvalho.ca01programming.domain;

import com.walterfcarvalho.ca01programming.domain.enums.AnimalType;

public class Fish extends Animal {

    // defines if this bird migrates or not
    private String family;

    public Fish(){
        super();
    }

    /**
     * Contructor for Animal Fish
     * See Animal.class for details 
     * @throws Exception
    */
    public Fish(String habitat, String name, String specie, String weight, String dateOfBird,
            String family) throws Exception  {
        super(habitat, name, specie, AnimalType.FISH, weight, dateOfBird);
        setFamily(family);
    }

    public String getFamily() {
        return this.family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    /**
     * toString
     * See Animal.class for details 
     */
    public String toString() {
        return super.toString() +
                "\n\nProperties from Bird only " +
                "\nFish family : " + getFamily();
    }

}
