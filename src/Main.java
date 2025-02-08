import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;


public class Main {

    public static void main(String[] args) {

//        Account checkingAccount = new CheckingAccount();
//        Account savingAccount = new SavingAccount();
        Account checkingAccount = new CheckingAccount("leang", "01-01-2023", "male", "012123123", 0);
        Account savingAccount = new SavingAccount("leang", "01-01-2023", "male", "012123123", 0);
        do {
            printMenu();
            int choice = Integer.parseInt(HELPER.getInputAndValidate("➡️Choose an option: ", "Choice cannot be empty", "[1-7]", "Please enter a valid choice (1-7)"));
            switch (choice) {
                //create account
                case 1:
                    createAccount:
                    do {
                        System.out.println("\n" + ">".repeat(16) + " Creating account " + ">".repeat(16));
                        String[] options = {"Checking Account", "Saving Account", "Back"};
                        HELPER.printOptionList(options);
                        int typeOfAccountChoice = Integer.parseInt(HELPER.getInputAndValidate("What type of account do you want to create ? ", "Option cannot be empty", "[1-3]", "Please enter a valid choice (1-3)"));

                        switch (typeOfAccountChoice) {
                            case 1:
                                HELPER.addAccountInfo(checkingAccount, "checking account");
                                break;
                            case 2:
                                HELPER.addAccountInfo(savingAccount, "saving account");
                                break;

                            case 3:
                                break createAccount;
                        }
                    } while (true);
                    break;
                //deposit money
                case 2:
                    deposit:
                    do {
                        System.out.println("\n" + ">".repeat(16) + " Deposit Money " + ">".repeat(16));
                        String[] options = {"Checking Account", "Saving Account", "Back"};
                        HELPER.printOptionList(options);
                        int depositChoice = Integer.parseInt(HELPER.getInputAndValidate("➡️ Choose an options: ", "Option cannot be empty", "[1-3]", "Please enter a valid choice (1-3)"));
                        double amount;
                        double oldBalance;
                        double totalAmount;
                        switch (depositChoice) {
                            case 1:
                                if (!HELPER.checkIsAccountCreated(checkingAccount)) {
                                    break deposit;
                                }
                                do {
                                    oldBalance = checkingAccount.getBalance();
                                    amount = Double.parseDouble(HELPER.getInputAndValidate("➡️ Enter Money to deposit: ", "Deposit Amount cannot be empty", "\\d+.?\\d*", "Wrong input format"));
                                    totalAmount = checkingAccount.deposit(amount);
                                } while (oldBalance == totalAmount);
                                HELPER.displayWithdrawOrDepositReceipt(amount, totalAmount, "Checking account","Recieved");
                                break;
                            case 2:
                                if (!HELPER.checkIsAccountCreated(savingAccount)) {
                                    break deposit;
                                }
                                do {
                                    oldBalance = savingAccount.getBalance();
                                    amount = Double.parseDouble(HELPER.getInputAndValidate("➡️ Enter Money to deposit: ", "Deposit Amount cannot be empty", "\\d+.?\\d*", "Wrong input format"));
                                    totalAmount = savingAccount.deposit(amount);
                                } while (oldBalance == totalAmount);
                                HELPER.displayWithdrawOrDepositReceipt(amount, totalAmount, "Saving account","Recieved");
                                break;
                            case 3:
                                break deposit;
                        }
                        HELPER.printSuccessMessage("\nDeposit successful!\n");
                    } while (true);
                    break;
                //withdraw money
                case 3:
                    withdraw:
                    do {
                        System.out.println("\n" + ">".repeat(16) + " Withdraw Money " + ">".repeat(16));
                        String[] options = {"Checking Account", "Saving Account", "Back"};
                        HELPER.printOptionList(options);
                        int withdrawChoice = Integer.parseInt(HELPER.getInputAndValidate("➡️ Choose an options: ", "Option cannot be empty", "[1-3]", "Please enter a valid choice (1-3)"));
                        double amount;
                        double oldBalance;
                        double totalAmount;
                        switch (withdrawChoice) {
                            case 1:
                                if (!HELPER.checkIsAccountCreated(checkingAccount)) {
                                    break withdraw;
                                } else if (checkingAccount.getBalance() == 0) {
                                    HELPER.printErrorMessage("Please deposit your money first!");
                                    System.out.println("Press any key to go back to menu...");
                                    HELPER.sc.nextLine();
                                    break withdraw;
                                }
                                do {
                                    oldBalance = checkingAccount.getBalance();
                                    amount = Double.parseDouble(HELPER.getInputAndValidate("➡️ Enter Money to withdraw: ", "Withdraw Amount cannot be empty", "\\d+.?\\d*", "Wrong input format"));
                                    totalAmount = checkingAccount.withdraw(amount);
                                } while (oldBalance == totalAmount);
                                HELPER.displayWithdrawOrDepositReceipt(amount, totalAmount, "Checking account","Withdraw");
                                break;
                            case 2:
                                if (!HELPER.checkIsAccountCreated(savingAccount)) {
                                    break withdraw;
                                } else if (savingAccount.getBalance() == 0) {
                                    HELPER.printErrorMessage("Please deposit your money first!");
                                    System.out.println("Press any key to go back to menu...");
                                    HELPER.sc.nextLine();
                                    break withdraw;
                                }
                                do {
                                    oldBalance = savingAccount.getBalance();
                                    amount = Double.parseDouble(HELPER.getInputAndValidate("➡️ Enter Money to withdraw: ", "Withdraw Amount cannot be empty", "\\d+.?\\d*", "Wrong input format"));
                                    totalAmount = savingAccount.withdraw(amount);
                                    if (totalAmount == oldBalance) {
                                        String continueDeposit = HELPER.getInputAndValidate("➡️ Do you want to continue? (y or n): ", "Cannot be empty", "[ynYN]", "Invalid choice! Please input (y/n)");
                                        if (continueDeposit.equalsIgnoreCase("n")) {
                                            continue withdraw;
                                        }
                                    }
                                } while (oldBalance == totalAmount);
                                HELPER.displayWithdrawOrDepositReceipt(amount, totalAmount, "Saving account","Withdraw");
                                break;
                            case 3:
                                break withdraw;
                        }
                        HELPER.printSuccessMessage("\nWithdraw successful!\n");
                    } while (true);
                    break;
                //transfer money
                case 4:
                    transfer:
                    do {
                        if (!HELPER.checkIsAccountCreated(checkingAccount) || !HELPER.checkIsAccountCreated(savingAccount)) {
                            break transfer;
                        }
                        System.out.println("\n" + ">".repeat(16) + " Transfer money " + ">".repeat(16));
                        String[] options = {"Checking account -> Saving account", "Saving Account -> Checking account", "Back"};
                        HELPER.printOptionList(options);
                        int transferChoice = Integer.parseInt(HELPER.getInputAndValidate("➡️ Choose an options: ", "Option cannot be empty", "[1-3]", "Please enter a valid choice (1-3)"));
                        double amount;
                        switch (transferChoice) {
                            case 1:
                                amount = Double.parseDouble(HELPER.getInputAndValidate("Enter money to transfer: ", "Amount cannot be empty", "\\d+.?\\d*", "Wrong input format"));
                                checkingAccount.transfer(amount, savingAccount);
                                break;
                            case 2:
                                break;
                            case 3:
                                break transfer;
                        }
                    } while (true);
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    System.out.println("Thank you🙏🏻🙏🏻 Good bye😁😁");
                    break;

            }
        } while (true);
    }


    public static void printMenu() {
        Table t = new Table(1, BorderStyle.DESIGN_FORMAL, ShownBorders.SURROUND);
        CellStyle align = new CellStyle(CellStyle.HorizontalAlign.center);
        t.setColumnWidth(0, 50, 77);
        t.addCell("Online Banking System", align);
        System.out.println(t.render());
        String[] options = {"Create Account", "Deposit Money", "Withdraw Money", "Transfer Money", "Display Account Information", "Delete Account", "Exit"};
        HELPER.printOptionList(options);
    }


}