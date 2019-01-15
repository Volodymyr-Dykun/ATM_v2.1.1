package atm.entity;

public abstract class AbstractUser {

    private String login;
    private Integer password;
    private Integer balance;

    public Integer getPassword() {
        return password;
    }

    public void setPassword(Integer password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public boolean checkLogin(String login) {
        return login.equals(getLogin());
    }

    public boolean checkPassword(Integer password) {
        return  password.equals(getPassword());
    }

    public abstract void balance();
}
