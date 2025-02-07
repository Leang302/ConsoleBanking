public class CheckingAccount extends Account{
    public static final double rate = 0.05;

    public CheckingAccount() {
        super();
    }

    public CheckingAccount(String name, String dob, String gender, String phoneNumber, double balance) {
        super(name, dob, gender, phoneNumber, balance);
    }

    @Override
    public double deposit(double amount) {

        if(amount>0){
            balance+=amount;
            return amount;
        }
        HELPER.printErrorMessage("Amount need to be bigger than 0");
        return 0;
    }

    @Override
    public double withdraw(double amount) {
        return 0;
    }

    @Override
    public double transfer(double amount, Account targetAcount) {
        return 0;
    }

    @Override
    public double displayAccountInfo() {
        return 0;
    }

}
