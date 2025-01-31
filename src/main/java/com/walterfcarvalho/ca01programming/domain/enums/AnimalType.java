package com.walterfcarvalho.ca01programming.domain.enums;

public enum AnimalType {

    AMPHIBIAN,
    BIRD,
    FISH,
    MAMAL,
    REPTILE;

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
  

    public String allValues() {
        String res = "";
        AnimalType[] types = this.getDeclaringClass().getEnumConstants();
        
        for ( AnimalType t : types)
            res += t.toString() + ", ";

        return res.substring(0, res.length()-2);
    }
}
