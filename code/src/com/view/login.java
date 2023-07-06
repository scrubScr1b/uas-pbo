package com.view;
import java.util.Scanner;

import com.entity.userEntity;
import com.models.cConfig;

public class login {

    public static void ShowLoginPage() {
        
        Scanner inputScanner = new Scanner(System.in);

        System.out.print("\n=    LOGIN    =\n");

        System.out.print("name :");
        String userid = inputScanner.nextLine();

        System.out.print("password :");
        String pass = inputScanner.nextLine();


        cConfig LoginUsecase = new cConfig();
        String sValidate = LoginUsecase.LoginValidate(userid, pass);

        if (sValidate.equals("")) {
            System.out.println();
            System.out.println("LOGIN SUCCESS !!!");
            System.out.println(cConfig.ticketRent(userid));
            menu.menuAwal();
            
        } else {
            System.out.println();
            System.out.println("LOGIN FAILED - " +sValidate);
            welcome.main(null);
        }
        // close scanner tp kayanya gausah
        // inputScanner.close();

    }
    
}