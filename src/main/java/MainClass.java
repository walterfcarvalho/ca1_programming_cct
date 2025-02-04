
import com.walterfcarvalho.ca01programming.auxiliar.AppMessages;
import com.walterfcarvalho.ca01programming.auxiliar.ScannerClass;
import com.walterfcarvalho.ca01programming.business.FechData;
import com.walterfcarvalho.ca01programming.domain.Animal;
import com.walterfcarvalho.ca01programming.domain.Bird;
import com.walterfcarvalho.ca01programming.domain.Fish;
import com.walterfcarvalho.ca01programming.domain.Mammal;
import com.walterfcarvalho.ca01programming.domain.Reptile;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

public class MainClass {

    static ArrayList<Animal> appAnimals = new ArrayList<>();

    static AppMessages appMessages = new AppMessages("eng");
    static ScannerClass appScanner = new ScannerClass();

    public static void main(String[] args) throws Exception {
        Integer menuOption = 0;

        do {
            appMessages.clearConsole();
            appMessages.showMessage(10, 14);

            menuOption = appScanner.getInt();
            switch (menuOption) {
                case 1:
                    loadDatabase();
                    break;
                case 2:
                    manageAnimals();
                    break;
            }
        } while (menuOption != 0);

        appMessages.showMessage(15, 15);
    }

    /*
     * This method will show menu for manage animals menu
     */
    private static void manageAnimals() {

        if (appAnimals.isEmpty()) {
            appMessages.clearConsole();
            appMessages.showMessage(21); // Please run first the option 'Import Animal database from...
            appScanner.nextLine();
            return;
        }

        short menuOption = 0;
        do {
            appMessages.clearConsole();
            appMessages.showMessage(12); // Choose one option bellow, or 0 to leave this menu
            appMessages.showMessage(19, 19); // 1 - List all animals \n2 - Find animals...");

            menuOption = (short) appScanner.getInt();
            switch (menuOption) {
                case 1:
                    printAllRecords(); // go to print all records feature
                    break;
                case 2:
                    findPrinBySpecificField(); // go to find and print by specific field
                    break;
                case 3:
                    findAnyField(); // go to find and print by any field
                    break;
            }
        } while (menuOption != 0);
    }

    /*
     * This method will serach in record by a value in a specific field.
     */
    private static void findPrinBySpecificField() {
        // Get all fields possibilities
        ArrayList<String> classesFields = getAllFieldsDomainClasses();

        String strField = "";
        String strTerm = "";

        // mount list of fields to show in screen
        String options = "";
        for (int i = 0; i < classesFields.size(); i++) {

            String strOpt = (i < 9) ? "\n " : "\n";

            options += strOpt + (i + 1) + " - " + classesFields.get(i);
        }

        // show options
        short menuOption = -1;
        do {
            appMessages.clearConsole();
            appMessages.showMessage(29, options);


            menuOption = (short) appScanner.getInt();

            if (menuOption < 1 | menuOption > classesFields.size()) {
                appMessages.showMessage(30, ("" + classesFields.size())); // \nInvalid option. Should be between %s
                                                                          // and...
                appScanner.nextLine();
            }
        } while (menuOption < 1 | menuOption > classesFields.size());

        appMessages.showMessage(31, classesFields.get(menuOption - 1)); // Inform the value you wish should be search in field 
        appMessages.showMessage(32); // (note, for date format ...)

        strTerm = appScanner.nextLine();

        strField = classesFields.get(menuOption - 1).split(" \\(")[0];

        ArrayList<Animal> result = new ArrayList<>();

        for (Animal animal : appAnimals) {

            HashMap<String, String> hashAnimal = animal.getAllFieldsAndValues();

            // check is object has properties we are loking for regardless case.
            if (hashAnimal.containsKey(strField) && hashAnimal.get(strField).toUpperCase().contains(strTerm.toUpperCase()))
                result.add(animal);
        }

        // when no record matches search
        if (result.isEmpty()) {
            appMessages.showMessage(27, strTerm); // No Record found with term \"%s\"...
            appScanner.nextLine();
            return;
        }

        // when some record matches search
        appMessages.showMessage(28, ("" + result.size()), strTerm); // Found \"%s\" record(s) with term \"%s\". (Press
                                                                                // ENTER to continue)");
        appScanner.nextLine();
        printFoundRecords(result);
        appScanner.nextLine();
    }

    /**
     * This method will return a HashMap, with all fields used by domain classes in
     * your application
     */
    private static ArrayList<String> getAllFieldsDomainClasses() {

        ArrayList<String> fields = new ArrayList<>();

        ArrayList<Animal> listAnimals = new ArrayList<>();
        listAnimals.add(new Bird());
        listAnimals.add(new Fish());
        listAnimals.add(new Mammal());
        listAnimals.add(new Reptile());

        for (Animal animal : listAnimals) {
            ArrayList<Field> objFields = animal.getFields();

            for (Field field : objFields) {

                // get datatype from field
                // when type is some.some.some.Type we filter to return only "type"
                int lastIndex = field.getType().getName().lastIndexOf(".");
                String fieldName = "";
                if (lastIndex == -1)
                    fieldName = field.getName() + " (" + field.getType().getName() + ")";
                else {
                    fieldName =  "" + field.getType();
                    fieldName = field.getName() +" ( " + fieldName.substring(fieldName.lastIndexOf(".") +1,  fieldName.length()) + " )";
                }

                // check for repeated field
                if (!fields.contains(fieldName))
                    fields.add(fieldName);
            }
        }
        // sort arraylist
        fields.sort((a, b) -> -1 * b.compareTo(a));
        return fields;
    }

    /**
     * This method will perform search in all record and match for any field that
     * have
     * value matching with a string passed by argument.
     */
    private static void findAnyField() {
        appMessages.clearConsole();
        appMessages.showMessage(26); // This feature will print all records...

        String term = appScanner.nextLine();

        ArrayList<Animal> animalsFounds = new ArrayList<>();

        for (Animal animal : appAnimals) {
            if (animal.hasStringValue("", term)) {
                animalsFounds.add(animal);
            }
        }

        // if no record matches search
        if (animalsFounds.isEmpty()) {
            appMessages.showMessage(27, term); // No Record found with this term: %...
            appScanner.nextLine();
            return;
        }

        appMessages.showMessage(28, ("" + animalsFounds.size()), term); // Found %s records with this term...
        
        appScanner.nextLine();
        printFoundRecords(animalsFounds);
        appScanner.nextLine();
    }

    /**
     * This method will print all record on the screen
     */
    private static void printAllRecords() {
        appMessages.clearConsole();
        appMessages.showMessage(24); // This feature will print all records...
        appScanner.nextLine();

        printFoundRecords(appAnimals);

        appMessages.showMessage(25); // This feature will print all records...
        appScanner.nextLine();
    }

    /**
     * This method will print all record on the screen
     * 
     * @param animals ArrayList<Animal> with animals shoud be printed
     */
    private static void printFoundRecords(ArrayList<Animal> animals) {

        for (Animal item : animals)
            System.out.println(item.toString());
    }

    /*
     * This method will perform a databaseload by reading file animals.txt and
     * returing a ArrayList of animals. All validations are perform on the domain
     * classes
     */
    private static void loadDatabase() {

        appMessages.clearConsole();

        if (appAnimals.size() > 0) {
            appMessages.showMessage(23); // databaseLoad is already complete...
            appScanner.nextLine();
            return;
        }

        FechData fetchData = new FechData();

        appMessages.showMessage(16, 17); // 16 This feature will inicialize animals database by importing
        appScanner.nextLine();

        try {
            appAnimals = fetchData.loadAnimalList();

            appMessages.showMessage(18, "" + appAnimals.size()); // Database load is complete.imported...
            appScanner.nextLine();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            appScanner.nextLine();
        }
    }
}
