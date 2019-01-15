package atm.service;

import atm.Main;
import atm.entity.User;
import atm.json.AbstractJson;
import atm.json.AdminJson;
import atm.json.UserJson;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UserService {

    private AdminJson adminJSON;
    private UserJson userJSON;
    private Integer atmBalance;                                 // take a balance ATM with admin.atm.json
    private User user;                                          // variable for future use

    public UserService() throws IOException {
         adminJSON = new AdminJson();
         userJSON = new UserJson();
         atmBalance = adminJSON.openAtmBalansceForUser();          // take a balance ATM with admin.atm.json
    }


    // Check login and password. Remove user with List users in atm.json.UserJson.class
    public void enter() throws IOException {
        int a = 0;                                                // variable for user search

        System.out.println("Enter User Login:");
        String login = new Scanner(System.in).nextLine();

        for (int i = 0; i < userJSON.getUsers().size(); i++) {
            User userLocal = userJSON.getUsers().get(i);
            if (userLocal.getLogin().equals(login)) {
                a++;                                                // means that the user with such a name is in the database
                System.out.println("Enter Password For this User:");
                try {
                    Integer password = new Scanner(System.in).nextInt();
                    if (userLocal.getPassword().equals(password)) {
                        user = userJSON.getUsers().get(i);             // assign the variable found to the user
                        userJSON.getUsers().remove(i);               // remove user from Users List in userJSON.class
                        workMenu();
                        break;
                    } else {
                        System.out.println("The password does not match the login " + login);
                        exit();
                    }
                } catch (InputMismatchException e) {          // you can enter only numbers
                    System.out.println("Don't enter latter!");
                    System.out.println();
                    enter();
                }
            }
        }
//if a = 0, then such user is not in the base
        if (a == 0) {
            System.out.println("This user is not in the database.");
            System.out.println("Contact the bank and become our client");
            System.out.println();
            Main.startMenu();
        }


    }

    // start users workMenu after check login and password. user = user with user.atm.json
    private void workMenu() throws IOException {
        try {
            System.out.println();
            System.out.println("You are logged in as an " + user.getLogin() + ", select the following action:");
            System.out.println("1) You balance " + "\n" + "2) Put Cash " + "\n" + "3) Take Cash" + "\n" + "4) Exit user mode");
            switch (new Scanner(System.in).nextInt()) {
                case 1:
                    user.balance();
                    endMenu();
                    break;
                case 2:
                    putCash();
                    endMenu();
                    break;
                case 3:
                    takeCash();
                    endMenu();
                    break;
                case 4:
                    exit();
                    break;
                default:
                    System.out.println("This option does not exist!");
                    break;
            }
        } catch (InputMismatchException e) {                   // you can enter only numbers
            System.out.println("Don't enter latter!");
            System.out.println();
            workMenu();
        }
    }

    private void putCash() {
        try {
            System.out.println("Specify the amount you want to put: ");
            int cash = new Scanner(System.in).nextInt();
            if (cash > 0) {                                       // entered number should be greater than 0
                user.setBalance(user.getBalance() + cash);
                atmBalance = atmBalance + cash;
                user.balance();
            } else {
                System.out.println("The entered amount can not be less than 0");
                System.out.println();
                putCash();
            }
        } catch (InputMismatchException e) {                    // you can enter only numbers
            System.out.println("Don't enter latter!");
            System.out.println();
            putCash();
        }

    }

    private void takeCash() {
        try {
            System.out.println("Specify the amount you want to take: ");
            int cash = new Scanner(System.in).nextInt();
            if (cash > 0) {                                       // entered number should be greater than 0
                if (user.getBalance() > cash) {                   // checking for the presence of the amount in the user
                    if (atmBalance > cash) {                      // checking for the presence of the amount in the user
                        user.setBalance(user.getBalance() - cash);// we reduce the balance of the user and write it down
                        atmBalance = atmBalance - cash;             // we reduce the balance of the ATM
                        user.balance();
                    } else {
                        System.out.println("ATM is not enough cash.");
                        System.out.println("At the ATM account there is: " + atmBalance);
                        System.out.println("The nearest ATM is located on the street Zelena, 150");
                        System.out.println();
                    }
                } else {
                    System.out.println("You do not have enough funds in your account!");
                    System.out.println("Please, refill your account!");
                    System.out.println();
                }
            } else {
                System.out.println("The entered amount can not be less than 0");
                System.out.println();
                takeCash();
            }
        } catch (InputMismatchException e) {                    // you can enter only numbers
            System.out.println("Don't enter latter!");
            System.out.println();
            takeCash();
        }
    }

    private void endMenu() throws IOException {
        try {
            System.out.println("You want to leave User mode:");
            System.out.println("1) NO" + "\n" + "2) YES");
            switch (new Scanner(System.in).nextInt()) {
                case 1:
                    workMenu();
                    break;
                case 2:
                    exit();
                    break;
                default:
                    System.out.println("This option does not exist!");
                    endMenu();
                    break;
            }
        } catch (InputMismatchException e) {                    // you can enter only numbers
            System.out.println("Don't enter latter!");
            System.out.println();
            endMenu();
        }
    }

    // put user after changes in List users in atm.json.UserJson.class and exit with start users workMenu.
    private void exit() throws IOException {
        adminJSON.admin.setBalance(atmBalance);                 // write ATM balance for atm.entity.Admin
        AdminJson.writeJson(AdminJson.LINK, adminJSON.admin);              // write atm.entity.Admin for admin.atm.json
        userJSON.addUser(user);                                 // add user to Users List in userJSON.class
        // re-write user.atm.json
        AbstractJson.writeJson(UserJson.LINK, UserJson.getUsers());
        System.out.println("Thank you for using ATM");
        System.out.println();
        Main.startMenu();
    }
}
