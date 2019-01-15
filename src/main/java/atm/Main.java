package atm;

import atm.service.AdminService;
import atm.service.UserService;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main{

    public static void main(String... args) throws IOException {
            startMenu();
    }

    public static void startMenu() throws IOException {
        try {



            System.out.println("Welcome to ATM. Make your choice:");
            System.out.println("1) Admin service " + "\n" + "2) Client service " + "\n" + "3) Exit ");
            switch (new Scanner(System.in).nextInt()) {
                case 1:
                    AdminService adminService = new AdminService();         // start work with admin mode
                    adminService.enter();
                    break;
                case 2:
                    UserService userService = new UserService();            // start work with user mode
                    userService.enter();
                    break;
                case 3:
                    exit();                                                 // exit and work program
                    break;
                default:
                    System.out.println("This option does not exist!");
                    System.out.println();
                    startMenu();
                    break;
            }

        } catch (InputMismatchException e) {
            System.out.println("Don't enter latter!");
            System.out.println();
            startMenu();
        }

    }

    private static void exit() {
        System.out.println("Thank you for using ATM");
    }
}

