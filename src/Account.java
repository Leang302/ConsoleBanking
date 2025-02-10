import java.time.LocalDate;
import java.util.Random;

public abstract class Account {
    protected int id;
    protected String name;
    protected String dob;
    protected String gender;
    protected String phoneNumber;
    protected double balance;
    protected boolean isActive;

    public Account() {
        Random random = new Random();
        id = random.nextInt(10000);
    }

    public int getId() {
        return id;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public boolean setDob(String dob) {
        int currentYear = LocalDate.now().getYear();
        String[] dates = dob.split("-");
        int day = Integer.parseInt(dates[0]);
        int month = Integer.parseInt(dates[1]);
        int year = Integer.parseInt(dates[2]);
        if (year > currentYear - 150 && year <= currentYear - 16) {
            if (month >= 1 && month <= 12) {
                //leap year validation
                int daysInMonth = LocalDate.of(year, month, 1).lengthOfMonth();
                if (day >= 1 && day <= daysInMonth) {
                    this.dob = dob;
                    return true;
                }
            }
            HELPER.printErrorMessage("Invalid date.");
            return false;
        }
        HELPER.printErrorMessage("Age must be between 16-150 years.");
        return false;
    }

    public String getGender() {
        return gender;
    }

    public boolean setGender(String gender) {
        if (gender.equalsIgnoreCase("male") || gender.equalsIgnoreCase("female")) {
            this.gender = gender;
            return true;
        }
        System.out.println(HELPER.RED + "Invalid gender" + HELPER.RESET);
        return false;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void deleteAccount(Account accToDelete, Account receiver) {
        double totalAmount = receiver.getBalance() + accToDelete.getBalance();
        //send money to receiver
        receiver.setBalance(totalAmount);
        //reset the account to be deleted
        accToDelete.setBalance(0);
        accToDelete.setActive(false);

    }

    //abstract method

    abstract void transferReciept(Account receiver, double amount);

    abstract double deposit(double amount);

    abstract double withdraw(double amount);

    abstract double transfer(double amount, Account targetAcount);

    abstract void displayAccountInfo();

}
