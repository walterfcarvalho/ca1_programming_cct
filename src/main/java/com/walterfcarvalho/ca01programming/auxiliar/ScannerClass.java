

package com.walterfcarvalho.ca01programming.auxiliar;

import java.util.Scanner;


/**
 * This class will have responsible for data input through keyboard
 * @author valter
 */
public class ScannerClass {
    Scanner scanner;


    public ScannerClass(){
        this.scanner = new Scanner(System.in);
    }

    public String nextLine(){
        return scanner.nextLine();
    }


    /*
        This method will receive a int value.
        Will show a message in case of invalid int and return -1
        otherwize.
    */
    public int getInt(){
        int res = -1;
        try {
            res = this.scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid integer value.");
            res = -1;           
        }
        scanner.nextLine();            
        return res;
    }
        
}
