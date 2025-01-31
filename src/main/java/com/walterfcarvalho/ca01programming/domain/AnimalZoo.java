package com.walterfcarvalho.ca01programming.domain;

import com.walterfcarvalho.ca01programming.domain.enums.AnimalType;

/**
 * Provide a concrete class for Animals
 *
 * @author Valter Carvalho
 * @since 1.0
 */
public class AnimalZoo extends Animal {

    public AnimalZoo(){
        super();
    }

    public AnimalZoo(String habitat, String name, String specie, AnimalType animalType, String weight, String dateOfBird) {
        super(habitat, name, specie, animalType, weight, dateOfBird);
    }

    public String toString(){
        return super.toString();
    }
}
