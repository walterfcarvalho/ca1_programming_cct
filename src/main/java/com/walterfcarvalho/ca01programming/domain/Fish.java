package com.walterfcarvalho.ca01programming.domain;

import com.walterfcarvalho.ca01programming.domain.enums.AnimalType;

public class Fish extends AnimalZoo {

    // defines if this bird migrates or not
    private String family;

    public Fish(){
        super();
    }

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

    /*
     * This method will print to concole all data a Object bird has
     */
    public String toString() {
        return super.toString() +
                "\n\nProperties from Bird only " +
                "\nFish family : " + getFamily();
    }

}
