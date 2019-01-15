package atm.entity;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Admin extends AbstractUser {

    @Override
    public void balance() {
        System.out.println("ATM balance is: "+ getBalance());
    }

    public void refillAtmBalance() {
        try {
            System.out.println("Enter the amount of replenishment of the balance: ");
            int ball = new Scanner(System.in).nextInt();
            if (ball>0) {
                int atmBalance = getBalance() + ball;
                setBalance(atmBalance);
                System.out.println("New balance is: " + atmBalance);
            } else {
                System.out.println("The amount entered cannot be less than 0");
                System.out.println();
                refillAtmBalance();
            }
        } catch (InputMismatchException e) {
            System.out.println("The amount should consist only of digits.");
            System.out.println();
            refillAtmBalance();
        }
    }

    public User createUser(String login) {
        User user = new User();
        try {
            user.setLogin(login);
            System.out.println("Enter the password for user Login:");
            user.setPassword(new Scanner(System.in).nextInt());
            user.setBalance(0);
            System.out.println("User is create!");
            return user;
        } catch (InputMismatchException e) {
            System.out.println("The password must consist of digits.");
            System.out.println("User not created. Start from the beginning.");
            System.out.println();
            return createUser(login);
        }
    }
}
