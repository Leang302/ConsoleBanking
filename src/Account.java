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

    public Account(String name, String dob, String gender, String phoneNumber, double balance) {
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.balance = balance;
        //to be deleted
        this.isActive = true;
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
        String[] dates = dob.split("-");
        int day = Integer.valueOf(dates[0]);
        int month = Integer.valueOf(dates[1]);
        int year = Integer.valueOf(dates[2]);
        if (day > 31 || month > 12) {
            System.out.println(HELPER.RED + "Invalid date" + HELPER.RESET);
            return false;
        }
        this.dob = dob;
        return true;
    }

    public String getGender() {
        return gender;
    }

    public boolean setGender(String gender) {
        if (gender.equalsIgnoreCase(Gender.FEMALE.value) || gender.equalsIgnoreCase(Gender.MALE.value)) {
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

    abstract double deposit(double amount);

    abstract double withdraw(double amount);

    abstract double transfer(double amount, Account targetAcount);

    abstract double displayAccountInfo();

}
