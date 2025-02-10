public class SavingAccount extends Account {
    public static final double rate = 0.05;

    public SavingAccount(String name, String dob, String gender, String phoneNumber, double balance) {
        super(name, dob, gender, phoneNumber, balance);
    }

    public SavingAccount() {
    }

    @Override
    public double deposit(double amount) {
        if (amount > 0) {
            balance += amount >= 200 ? amount * rate + amount : amount;
            return balance;
        }
        HELPER.printErrorMessage("Amount need to be bigger than 0");
        return balance;
    }

    @Override
    public double withdraw(double amount) {
        if (amount > 0) {
            if (amount > balance) {
                HELPER.printErrorMessage("Insufficient amount");
                return balance;
            } else if (amount > balance * 0.8) {
                HELPER.printErrorMessage("Cannot withdraw " + HELPER.df.format(amount) + ". At least " + HELPER.df.format(balance * 0.2) + " must remain in the account.");
                return balance;
            }
            balance -= amount;
            return balance;
        }
        HELPER.printErrorMessage("Invalid amount");
        return balance;
    }

    @Override
    public double transfer(double amount, Account targetAcount) {
        if (amount > 0) {
            if (amount > balance) {
                HELPER.printErrorMessage("Insufficient amount");
                return balance;
            } else if (amount > balance * 0.8) {
                HELPER.printErrorMessage("Cannot transfer " + HELPER.df.format(amount) + ". At least " + HELPER.df.format(balance * 0.2) + " must remain in the account.");
                return balance;
            }
            balance -= amount;
            targetAcount.setBalance(targetAcount.getBalance() + amount);
            return balance;
        }
        HELPER.printErrorMessage("Invalid amount");
        return balance;
    }

    @Override
    public void displayAccountInfo() {
        if (isActive()) {
            System.out.println("\n\n" + ">".repeat(16) + HELPER.BLUE + "Saving account" + HELPER.RESET + ">".repeat(16));
            System.out.println(HELPER.BLUE + "Account type:" + HELPER.RESET + " Saving Account");
            System.out.println(HELPER.BLUE + "Account Number: " + HELPER.RESET + id);
            System.out.println(HELPER.BLUE + "User name: " + HELPER.RESET + name);
            System.out.println(HELPER.BLUE + "Date of Birth: " + HELPER.RESET + dob);
            System.out.println(HELPER.BLUE + "Gender: " + HELPER.RESET + gender);
            System.out.println(HELPER.BLUE + "Phone Number: " + HELPER.RESET + phoneNumber);
            System.out.println(HELPER.BLUE + "Balance: " + HELPER.RESET + HELPER.df.format(balance));
        }
    }

    @Override
    void transferReciept(Account receiver, double amount) {
        System.out.println("\n\n\n" + ">".repeat(16) + HELPER.RESET + "Saving Account" + HELPER.RESET + ">".repeat(16));
        System.out.println(HELPER.BLUE + "Transfered:" + HELPER.RESET + " ".repeat(30) + HELPER.df.format(amount));
        System.out.println(HELPER.BLUE + "From:" + HELPER.RESET + " ".repeat(12) + "Saving Account with ID: " + id);
        System.out.println(HELPER.BLUE + "To:" + HELPER.RESET + " ".repeat(12) + "Checking Account with ID: " + receiver.getId());
        System.out.println(HELPER.BLUE + "Total remain: " + HELPER.RESET + " ".repeat(27) + HELPER.df.format(balance));
        System.out.println(HELPER.BLUE + "-".repeat(48));
    }
}
