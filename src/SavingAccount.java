public class SavingAccount extends Account{
    public static final double rate = 0.05;
    public SavingAccount(String name, String dob, String gender, String phoneNumber, double balance) {
        super(name, dob, gender, phoneNumber, balance);
    }

    public SavingAccount() {

    }

    @Override
    public double deposit(double amount) {
        if(amount>0){
            balance+=amount>=200?amount*rate+amount:amount;
            return balance;
        }
        HELPER.printErrorMessage("Amount need to be bigger than 0");
        return 0;
    }

    @Override
    public double withdraw(double amount) {
        if(amount>0){
            if(amount>balance){
                HELPER.printErrorMessage("Insufficient amount");
                return balance;
            }else if(amount>balance*0.8){
                HELPER.printErrorMessage("Cannot withdraw "+HELPER.df.format(amount)+". At least "+HELPER.df.format(balance*0.2)+" must remain in the account.");
                return balance;
            }
            balance-=amount;
            return balance;
        }
        HELPER.printErrorMessage("Invalid amount");
        return balance;
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
