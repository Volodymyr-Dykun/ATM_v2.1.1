package atm.service;

import atm.Main;
import atm.json.AbstractJson;
import atm.json.AdminJson;
import atm.json.UserJson;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AdminService {

    private AdminJson adminJSON;

    public AdminService() throws IOException {
        adminJSON = new AdminJson();
    }

    // Check admin login and password (with admin.atm.json).
    public void enter() throws IOException {
        try {
            System.out.println("Enter Admin Login:");
            String login = new Scanner(System.in).nextLine();
            if (adminJSON.admin.checkLogin(login)){
                System.out.println("Enter Password For Admin:");
                int password = new Scanner(System.in).nextInt();
                if (adminJSON.admin.checkPassword(password)) {
                    workMenu();

                } else { System.out.println("The password does not match the login Admin");
                    exit();
                }
            } else {
                System.out.println("This login does not have administrator rights!");
                System.out.println("You may login as a user, or try again.");
                System.out.println();
                exit();
            }
        }   catch (InputMismatchException e) {
            System.out.println("Don't enter latter!");
            System.out.println();
            enter();
        }
    }

    private void workMenu() throws IOException {
        System.out.println("You are logged as an admin, select the following action:");
        System.out.println("1) ATM balance " + "\n" + "2) Refill ATM balance " + "\n" + "3) Create user"  + "\n" + "4) Exit admin mode");
        try {
            switch (new Scanner(System.in).nextInt()) {
                case 1:
                    adminJSON.admin.balance();
                    endMenu();
                    break;
                case 2:
                    adminJSON.admin.refillAtmBalance();
                    endMenu();
                    break;
                case 3:
                    checkLoginAndCreateUser();
                    endMenu();
                    break;
                case 4:
                    exit();
                    break;
                default:
                    System.out.println("This option does not exist!");
                    System.out.println();
                    workMenu();
                    break;
            }
        }  catch (InputMismatchException e) {
            System.out.println("Don't enter latter!");
            System.out.println();
            workMenu();
        }
    }

    private void endMenu() throws IOException {
        System.out.println("You want to leave Admin mode:");
        System.out.println("1) NO" + "\n" + "2) YES");
        try {
            switch (new Scanner(System.in).nextInt()) {
                case 1:
                    workMenu();
                    break;
                case 2:
                    exit();
                    break;
                default:
                    System.out.println("This option does not exist!");
                    System.out.println();
                    endMenu();
                    break;
            }
        }   catch (InputMismatchException e) {
            System.out.println("Don't enter latter!");
            System.out.println();
            endMenu();
        }
    }
// exit admin mode and write admin.atm.json
    private void exit() throws IOException {
        AdminJson.writeJson(AdminJson.LINK, adminJSON.admin);
        System.out.println("Thank you for using admin mode!");
        System.out.println();
        Main.startMenu();
    }

    private void checkLoginAndCreateUser() throws IOException {
        UserJson userJSON = new UserJson();                             // open user.atm.json
        System.out.println("Enter user Login:");
        String login = new Scanner(System.in).nextLine();

        int a=0;
        for (int i=0;i<userJSON.getUsers().size();i++) {                     // check users by name
            if (userJSON.getUsers().get(i).getLogin().equals(login)) {       // if user with such a name already exists
                a++;                                                    // in user.atm.json - a++
                break;
            }
        }

        if (a==0) {                                                     // if the user with such a name is not available yet,
            userJSON.addUser(adminJSON.admin.createUser(login));                  // admin creates a user and add him in user.atm.json
            AbstractJson.writeJson(UserJson.LINK, UserJson.getUsers());
            System.out.println();
            } else {
                System.out.println("Such a user already exists !! Try again !!");
                System.out.println();
                checkLoginAndCreateUser();
                }
    }
}
