package com.view;

import java.util.Scanner;

public class welcome {

    public static void main(String[] args) {
        while(true) {
            
            System.out.print( "\n=    WELCOME TO OUR MOVIE RENT APP    =\n"
            + "1. Login Page\n"
            + "2. Create Account\n"
            + "0. Exit App\n"
            + "Input [1/2] :");
            
            Scanner input = new Scanner(System.in);
            String pilihan = input.nextLine();

            if ( pilihan.equalsIgnoreCase("0")) {
                System.out.println("Thank You!!");
                break;
            }

            switch (pilihan) {
                case "1":
                    login.ShowLoginPage();
                    break;
                case "2":
                    menuCreate.menuCreate();
                    break;
                case "0":
                    System.out.println("Exit App");
                    break;            
                default:
                    System.out.println("Wrong Choice!!!");
                    break;
            }
            
            // Close Scanner
            input.close();
        }
    }
    
}
