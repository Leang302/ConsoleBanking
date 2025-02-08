import java.io.ByteArrayInputStream;

public class CheckingAccount extends Account {


    public CheckingAccount() {
        super();
    }

    public CheckingAccount(String name, String dob, String gender, String phoneNumber, double balance) {
        super(name, dob, gender, phoneNumber, balance);
    }

    @Override
    public double deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            return balance;
        }
        HELPER.printErrorMessage("Amount need to be bigger than 0");
        return 0;
    }

    @Override
    public double withdraw(double amount) {
        if (amount > 0) {
            if (amount > balance) {
                HELPER.printErrorMessage("Insufficient amount");
                return balance;
            }
            balance -= amount;
            return balance;
        }
        HELPER.printSuccessMessage("Invalid amount");
        return balance;
    }

    @Override
    public double transfer(double amount, Account targetAcount) {
        if (amount > 0) {
            if (amount > balance) {
                HELPER.printErrorMessage("Insufficient amount");
                return 0;
            }
            balance -= amount;
            System.out.println("\n" + ">".repeat(16) + " Saving Account " + "<".repeat(16));
            System.out.println("Transfered:" + HELPER.df.format(amount));
            System.out.println("From:" + "Checking Account with ID: " + id);
            System.out.println("To:" + "Saving Account with ID: " + targetAcount.getId());
            System.out.println("Total Remain: " + " ".repeat(25) + HELPER.df.format(balance));
            System.out.println("-".repeat(50));
            targetAcount.deposit(amount);
            return amount;
        }
        HELPER.printErrorMessage("Invalid amount");
        return 0;
    }

    @Override
    public double displayAccountInfo() {
        return 0;
    }

}
