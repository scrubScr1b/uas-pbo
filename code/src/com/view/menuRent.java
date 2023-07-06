package com.view;

import java.util.Scanner;

import com.models.cConfig;

public class menuRent {
    private static Scanner input = new Scanner(System.in);

    public static void menuRent() {
        while (true) {

            System.out.print("\n=    RENT    =\n"
                    + "1. Rent List \n"
                    + "2. Add Rent \n"
                    + "3. Return Rent \n"
                    + "4. Delete Data Rent \n"
                    + "0. Exit\n"
                    + "Input [1/2/3/4] :");

            Scanner input = new Scanner(System.in);
            String pilihan = input.next();

            if (pilihan.equalsIgnoreCase("0")) {
                break;
            }

            switch (pilihan) {
                case "1":
                    allRentList();
                    activeRentList();
                    break;
                case "2":
                    addRent();
                    break;
                case "3":
                    returnRent();
                    break;
                case "4":
                    delRent();
                    break;
                case "0":
                    System.out.println("Exit App");
                    break;
                default:
                    System.out.println("Wrong Choice!!!");
                    break;
            }
        }
    }

    public static void allRentList() {
        System.out.print("\n=    ALL RENT LIST   =\n");
        System.out.println(cConfig.dataRent());
    }

    public static void activeRentList() {
        System.out.print("\n=    ACTIVE RENT LIST   =\n");
        System.out.println(cConfig.activeRent());
    }

    public static void addRent() {
        System.out.print("\n=    ADD RENT   =\n");
        System.out.println(cConfig.dataMovie());
        System.out.print("enter the id of the movie you want to rent :");
        String movieId = input.nextLine();
        System.out.print("enter your movie rental ticket :");
        String ticketRent = input.nextLine();
        cConfig.addRent(movieId,ticketRent);
        System.out.println("SUCESS - film successfully rented");
    }

    public static void returnRent() {
        System.out.print("\n=    RETURN RENT   =\n");
        activeRentList();
        System.out.print("enter the id of the movie you want to return :");
        String movieId = input.nextLine();
        cConfig.returnRent(movieId);
        System.out.println("SUCESS - film successfully returned");
    }

    public static void delRent() {
        allRentList();
        System.out.print("enter the id you want to delete :");
        String rentId = input.nextLine();
        cConfig.delRent(rentId);
    }

}
