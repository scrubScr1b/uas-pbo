package com.view;

import java.util.Scanner;

import com.models.cConfig;

public class menuMovie {

    private static Scanner input = new Scanner(System.in);

    public static void menuMovie() {
        while (true) {

            System.out.print("\n=    MOVIE    =\n"
                    + "1. Movie List \n"
                    + "2. Add Movie \n"
                    + "3. Delete Movie \n"
                    + "0. Exit\n"
                    + "Input [1/2/3] :");

            Scanner input = new Scanner(System.in);
            String pilihan = input.next();

            if (pilihan.equalsIgnoreCase("0")) {
                break;
            }

            switch (pilihan) {
                case "1":
                    movieList();
                    break;
                case "2":
                    addMovie();
                    break;
                case "3":
                    delMovie();
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

    public static void movieList() {
        System.out.print("\n=    MOVIE LIST   =\n");
        System.out.println(cConfig.dataMovie());
    }

    public static void addMovie() {
        System.out.print("\n=    ADD MOVIE   =\n");
        System.out.print("Title :");
        String title = input.nextLine();
        System.out.print("Genre :");
        String genre = input.nextLine();
        cConfig.addMovie(title, genre);
        System.out.println("SUCESS - Movie has been added");
    }

    public static void delMovie() {
        movieList();
        System.out.print("enter the id you want to delete :");
        String movieId = input.nextLine();
        cConfig.delMovie(movieId);
    }
}
