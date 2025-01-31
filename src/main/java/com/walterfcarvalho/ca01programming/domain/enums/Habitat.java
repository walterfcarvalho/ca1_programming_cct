package com.walterfcarvalho.ca01programming.domain.enums;


/**
 * Provide one class for identify habitats a animal can be original for.
 *
 * @author Valter Carvalho
 * @since 1.0
 */
public class Habitat {

    private static String[] habitats = {
        "Artic sea",
        "Artic islands",
        "Caatinga",
        "Cave",
        "Desert",
        "Grassland",
        "Marine",
        "Montain",
        "Rainforest",
        "Forest",
        "Savannah",
        "Tropical sea",
    };

    /**
     * this method can test if a string can repesent
     * a habitat enviroment
     */
    public static boolean validHabitat(String habitat) {
        boolean res = false;

        for (String x : habitats) {
            if (x.toUpperCase().equals(habitat.toUpperCase())) {
                res = true;
                break;
            }
        }
        return res;
    }

    public static String getAll() {
        String res = "";

        for (String item : habitats) {
            res += item + ", ";
        }

        return res.substring(0, res.length() - 2);
    }
}
