import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Account checkingAccount = new CheckingAccount();
        Account savingAccount = new SavingAccount();
        do {
            printMenu();
            int choice = Integer.parseInt(HELPER.getInputAndValidate("➡️Choose an option: ", "Choice cannot be empty", "[1-7]", "Please enter a valid choice (1-7)"));
            switch (choice) {
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
                case 2:
                    deposit:
                    do {
                        System.out.println("\n" + ">".repeat(16) + " Deposit Money " + ">".repeat(16));
                        String[] options = {"Checking Account", "Saving Account", "Back"};
                        HELPER.printOptionList(options);
                        int depositChoice = Integer.parseInt(HELPER.getInputAndValidate("➡️ Choose an options: ", "Option cannot be empty", "[1-3]", "Please enter a valid choice (1-3)"));
                        double amount;
                        double oldBalance;
                        switch (depositChoice) {
                            case 1:
                                if (!HELPER.accountIsCreated(checkingAccount)) {
                                    break deposit;
                                }
                                do {
                                    oldBalance = checkingAccount.getBalance();
                                    amount = Double.valueOf(HELPER.getInputAndValidate("➡️ Enter Money to deposit: ", "Deposit Amount cannot be empty", "\\d+.?\\d*", "Wrong input format"));
                                    checkingAccount.deposit(amount);
                                } while (oldBalance == checkingAccount.getBalance());
                                System.out.println(" ".repeat(24)+"⬇️"+" ".repeat(24));
                                System.out.println(" ".repeat(24)+"Saving Account"+" ".repeat(24));
                                break;
                            case 2:
                                if (!HELPER.accountIsCreated(savingAccount)) {
                                    break deposit;
                                }
                                do {
                                    oldBalance = checkingAccount.getBalance();
                                    amount = Double.valueOf(HELPER.getInputAndValidate("➡️ Enter Money to deposit: ", "Deposit Amount cannot be empty", "\\d+.?\\d*", "Wrong input format"));
                                    checkingAccount.deposit(amount);
                                } while (oldBalance == checkingAccount.getBalance());
                                break;
                            case 3:
                                break deposit;
                        }
                    } while (true);
                    break;
                case 3:
                    System.out.println("\n" + ">".repeat(16) + " Deposit Money " + ">".repeat(16));
                    String[] options = {"Checking Account", "Saving Account", "Back"};
                    HELPER.printOptionList(options);
                    break;
                case 4:
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