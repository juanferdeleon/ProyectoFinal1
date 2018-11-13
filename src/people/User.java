package people;

public class User extends Person{
    private Double accountBalance;

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    @Override
    public String toString() {
        String user = "";
        user += " " + this.getPersonId();
        user += " " + this.getPersonFirstName();
        user += " " + this.getAccountBalance();
        return user;
    }
}
