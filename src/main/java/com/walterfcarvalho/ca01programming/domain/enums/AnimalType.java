package com.walterfcarvalho.ca01programming.domain.enums;

public enum AnimalType {

    AMPHIBIAN,
    BIRD,
    FISH,
    MAMAL,
    REPTILE;

    /**
     * Returns a string representation of enum value 
     * @return string
    */
    public String toString() {
        switch (this) {
            case AMPHIBIAN:
                return "amphibian";
            case BIRD:
                return "bird";
            case FISH:
                return "fish";
            case MAMAL:
                return "mamal";
            case REPTILE:
                return "reptile";
        }
        return null;
    }
  
    /**
     * Returns all possibles values for enum
     * @return string
    */
    public String allValues() {
        String res = "";
        AnimalType[] types = this.getDeclaringClass().getEnumConstants();
        
        for ( AnimalType t : types)
            res += t.toString() + ", ";

        return res.substring(0, res.length()-2);
    }
}
