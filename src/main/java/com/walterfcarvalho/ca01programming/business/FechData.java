package com.walterfcarvalho.ca01programming.business;

import com.walterfcarvalho.ca01programming.domain.*;
import com.walterfcarvalho.ca01programming.domain.enums.AnimalType;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class can read a File and will returns an ArrayList of AnimalsZoo
 *
 */
public class FechData {

    public FechData() {

    }

    public ArrayList<AnimalZoo> loadAnimalList() throws Exception {
        // list witch keep Animals list
        ArrayList<AnimalZoo> animals = new ArrayList<>();

        // this list keeps data read from file
        ArrayList<String[]> txtFields = this.readFile();
        
        for (String[] item : txtFields) {
            try {
                AnimalZoo animal = null;

                if (item[0].equals(AnimalType.BIRD.toString())) {
                    animal = new Bird(item[3], item[2], item[1], item[5], item[4], item[6]);

                } else if (item[0].equals(AnimalType.FISH.toString())) {
                    animal = new Fish(item[3], item[2], item[1], item[5], item[4], "tune");

                } else if (item[0].equals(AnimalType.MAMAL.toString())) {
                    animal = new Mammal(item[3], item[2], item[1], item[5], item[4], item[6]);

                } else if (item[0].equals(AnimalType.REPTILE.toString())) {
                    animal = new Reptile(item[3], item[2], item[1], item[5], item[4], item[6]);
                } else {
                    throw new Exception("Invalid Animal type:" + item[0]);
                }

                animals.add(animal);
            } catch (Exception e) {
                System.out.println("\nError during Animal database loading: " + e.getMessage() + " \nLines: " + item[7]);
            }
        }
        
        return animals;
    }

    @SuppressWarnings("resource")
    private ArrayList<String[]> readFile() throws Exception {
        int lineCount = 1;
        ArrayList<String[]> list = new ArrayList<>();

        try {
            // File file = new File("src/main/java/Animals.txt"); for netbeans
            File file = new File("ca01Programming/src/main/java/Animals.txt");

            Scanner scanner = new Scanner(file);

            while (scanner.hasNext()) {
                String[] fields = new String[8];

                for (int i = 0; i < 4; i++) {
                    if (scanner.hasNext()) {
                        String[] auxStrArr = scanner.nextLine().split(",");

                        switch (i) {
                            case 0 -> {
                                fields[0] = auxStrArr[0];
                                fields[1] = auxStrArr[1];
                                fields[2] = auxStrArr[2];
                            }
                            case 1 -> fields[3] = auxStrArr[0];
                            case 2 -> {
                                fields[4] = auxStrArr[0];
                                fields[5] = auxStrArr[1];
                            }
                            case 3 -> fields[6] = auxStrArr[0];
                            default -> {
                            }
                        }
                    } else
                        throw new Exception("Error reading file from line from " + lineCount + " to " + (lineCount + 3)
                                + " file does not have correct structure.");
                }
                // add line range, will useful to show errors reference
                fields[7] = lineCount + " to " + (lineCount + 3);

                list.add(fields);
                lineCount += 4;
            }
            scanner.close();
        } catch (Exception e) {
            throw e;
        }
        return list;
    }

}
