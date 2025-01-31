package com.walterfcarvalho.ca01programming.auxiliar;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AppMessages {
    private HashMap<Integer, String> messages = new HashMap<Integer, String>();

    public AppMessages() {

        messages.put(10, "------------------------------------------");
        messages.put(11, "Welcome to the Dublin zoo Animal managment.");
        messages.put(12, "Choose either 1 or2 option, or 0 to finish application");
        messages.put(13, "1 - Import Animal database from file. ");
        messages.put(14, "2 - Animal managment");
        messages.put(15, "You have choosed close application. Good Bye ;)");

        messages.put(16, "This feature will inicialize animals database by importing");
        messages.put(17, "file animals.txt. Press ENTER to continue");
        messages.put(18, "Database load is complete.imported %s register(s). Press ENTER to return");

        messages.put(19, "1 - List all animals \n2 - Find by specific field \n3 - Find by any field");
        messages.put(20, "");
        messages.put(21, "Please run first the option 'Import Animal database from file'(Press ENTER to continue)' ");
        messages.put(22, "Total recors imported: ");
        messages.put(23, "Database load is already complete. (Press ENTER to continue)");
        messages.put(24, "This feature will print all records \n(Press ENTER to continue)");
        messages.put(25, "Proccess finished. Press ENTER to continue.");
        messages.put(26, "This feature will search all records: \nType the string you wish to be search for (Press ENTER to continue):");
        messages.put(27, "\nNo Record found with term \"%s\". (Press ENTER to continue)");
        messages.put(28, "\nFound \"%s\" record(s) with term \"%s\". (Press ENTER to continue)");
        messages.put(29, "\nChoose the field the search should search for: \n%s");
        messages.put(30, "\nInvalid option. Should be between 1 and %s (Press ENTER to continue)" );
        messages.put(31, "\nInform the value you wish should be search in field \"%s\": and Press ENTER." );


    }

    /**
     * Show a message in console
     * Params:
     * idMessage: Id to be located in messages hasmap
     * param1: string that will be interpolated
     * param2: string that will be interpolated
     */
    public void showMessage(Integer idMessage, String param1, String param2) {

        String msg = messages.get(idMessage);
        System.out.println( String.format(msg, param1, param2));
    }
    /**
     * Show a message in console
     * Params:
     * idMessage: Id to be located in messages hashmap
     * @param idMessage message Id on messages hashmap
     * @param param string that will be interpolated
     */
    public void showMessage(Integer idMessage, String param) {

        String msg = messages.get(idMessage);
        System.out.println( String.format(msg, param));
    }

    /*
     * A method to clean console
     */
    public void clearConsole() {
        try {
            System.out.print("*********************************************************\n");
            Runtime.getRuntime().exec(new String[]{"clear"});
            System.out.print("\033[H\033[2J");
            System.out.flush();
        } catch (IOException ex) {
            System.out.println("Error on attempt clear console");
        }
    }

    /**
     * Prints a message to console
     * @param idMessage Hashmap id for message
     */
    public void showMessage(Integer idMessage) {
        this.showMessage(idMessage, idMessage);
    }

    public void showMessage(Integer idStart, Integer idEnd) {
        Integer[] ids = new Integer[idEnd - idStart + 1];

        for (int i = 0; i <= (idEnd - idStart); i++)
            ids[i] = i + idStart;

        this.showMessage(ids);
    }

    public void showMessage(Integer[] messages) {

        for (Integer msg : messages) {
            System.out.println(this.messages.get(msg));
        }
    }
}
