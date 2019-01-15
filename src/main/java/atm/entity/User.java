package atm.entity;

public class User extends AbstractUser {

    @Override
    public void balance() {
        System.out.println("Your balance:"+ getBalance());
    }
}
