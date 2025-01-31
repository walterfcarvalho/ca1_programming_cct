package com.walterfcarvalho.ca01programming.domain;

import com.walterfcarvalho.ca01programming.domain.enums.AnimalType;
import com.walterfcarvalho.ca01programming.domain.enums.Habitat;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

/**
 * Provide a abstract class Animal witch contains all
 * structure to our application
 * 
 * @author Valter Carvalho
 * @since 1.0
 */
public abstract class Animal {

    static private int nextId;

    private int id;
    private String habitat;
    private String name;
    private String specie;
    private double weight;
    private Date dateOfBird;
    private AnimalType type;

    public Animal() {

    }

    public Animal(String habitat, String name, String specie,
            AnimalType type, String weight, String date) throws IllegalArgumentException {
        String errors = "";

        double nWeight = 0;
        SimpleDateFormat formatter = new SimpleDateFormat("yyy/MM/dd", Locale.ENGLISH);
        Date inicialDate = new Date();

        // perform Validations
        if (!specie.matches("[a-zA-Z ]+"))
            errors += "\nfield: Specie \nmessage: specie must be text only";

        if (!name.matches("[a-zA-Z1-9 ]+"))
            errors += "\nfield: Name \nSpecie must be text or numbers";

        if (!Habitat.validHabitat(habitat))
            errors += "\nfield: Habitat \nHabitat must be one those: " + Habitat.getAll();

        try {
            nWeight = Double.parseDouble(weight);
            if (nWeight <= 0)
                errors += "\nfield: weight \nWeight must be bigger than 0" + Habitat.getAll();
        } catch (Exception e) {
            errors += "\nfield: Weight \nweight invalid: " + weight;
        }

        try {
            inicialDate = formatter.parse(date);
        } catch (Exception e) {
            errors += "\nfield: Date of bird \ndate invalid: " + date;
        }

        if (errors.length() > 0)
            throw new IllegalArgumentException("\n" + this.getClass().getName() + " constructor(), errors: " + errors);

        // set all values
        Animal.nextId++;

        setHabitat(habitat);
        setName(name);
        setSpecie(specie);
        setWeight(nWeight);
        setDateOfBird(inicialDate);

        // some fields should not have set() method
        this.id = nextId;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getHabitat() {
        return habitat;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecie() {
        return specie;
    }

    public void setSpecie(String specie) {
        this.specie = specie;
    }

    public AnimalType getAnimalType() {
        return type;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Date getDateOfBird() {
        return dateOfBird;
    }

    public void setDateOfBird(Date dateOfBird) {
        this.dateOfBird = dateOfBird;
    }

    /*
     * This method will print to concole all data a Object animal has
     */
    @Override
    public String toString() {
        return "\n--------------------------------------------------------------------------" +
                "\nClass:   " + "[" + this.getClass().getSimpleName() + "]" +
                "\nId:      " + getId() +
                "\nSpecie:  " + getSpecie() +
                "\nName:    " + getName() +
                "\nHabitat: " + getHabitat() +
                "\nSpecie:  " + String.format("%.2f", getWeight()) +
                "\nDate of bird : " + new SimpleDateFormat("yyy/MM/dd").format(getDateOfBird()) +
                "\nType:    " + getAnimalType();
    }

    /**
     * 
     * This method will search inside object for a value
     * 
     */
    public boolean hasStringValue(String field, String value) {
        boolean result = false;

        HashMap<String, String> objectInfo = this.getAllFieldsAndValues();

        if (field.isEmpty()) {

            result = objectInfo.containsValue(value);

        } else {
            if (objectInfo.containsKey(field) && objectInfo.containsValue(value)) {
                result = true;
            }

        }
        return result;
    }

    /**
     * This method will return all fields from this Object
     */
    public ArrayList<Field> getFields() {

        ArrayList<Field> fieldList = new ArrayList<>();

        // Get the class of the object
        Class<?> objectClass = this.getClass();

        while (objectClass != null) {

            Field[] fields = objectClass.getDeclaredFields();

            for (Field field : fields) {
                // skip static fields
                if (java.lang.reflect.Modifier.isStatic(field.getModifiers()))
                    continue;

                fieldList.add(field);
            }

            // Now I go to the superclass
            objectClass = objectClass.getSuperclass();
        }
        return fieldList;
    }

    /**
     * This method will return a hasmap with all fields an values of an object
     */
    public HashMap<String, String> getAllFieldsAndValues() {
        HashMap<String, String> fieldValue = new HashMap<>();

        ArrayList<Field> fields = this.getFields();

        for (Field field : fields) {
            // Make the field accessible, just here
            field.setAccessible(true);

            if (java.lang.reflect.Modifier.isStatic(field.getModifiers()))
                continue;

            try {
                String name = field.getName();
                Object value = field.get(this);

                // will store values in a correct way to be found
                if (value.getClass().toString().contains("Boolean"))
                    fieldValue.put(name, ((Boolean) value ? "Yes" : "No"));

                else if (value.getClass().toString().contains("Date"))
                    fieldValue.put(name, (new SimpleDateFormat("yyy/MM/dd").format(getDateOfBird())));
                else
                    fieldValue.put(name, value.toString());

            } catch (IllegalAccessException e) {
                System.out.println("Error during fetching object data: " + e.getMessage());
            }
        }

        return fieldValue;
    }

}
