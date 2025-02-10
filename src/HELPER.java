
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;

public interface HELPER {
    String RESET = "\u001B[0m";
    String GREEN = "\u001B[32m";
    String BLUE = "\u001B[34m";
    String YELLOW = "\u001B[33m";
    String RED = "\u001B[31m";
    Scanner sc = new Scanner(System.in);
    DecimalFormat df = new DecimalFormat("$#,##0.00");


    static String getInputAndValidate(String prompt, String emptyMsg, String regex, String errorRegex) {
        Scanner sc = new Scanner(System.in);
        String value;
        while (true) {
            System.out.print(prompt);
            value = sc.nextLine().trim();
            if (value.isEmpty()) {
                System.out.println(RED + emptyMsg + RESET);
                continue;
            } else if (!value.matches(regex)) {
                System.out.println(RED + errorRegex + RESET);
                continue;
            }
            break;
        }

        return value;
    }

    static String getInputAndValidate(String prompt, String emptyMsg) {
        Scanner sc = new Scanner(System.in);
        String value;
        while (true) {
            System.out.print(prompt);
            value = sc.nextLine().trim();
            if (value.isEmpty()) {
                System.out.println(RED + emptyMsg + RESET);
                continue;
            }
            break;
        }
        ;
        return value;
    }

    static void printSuccessMessage(String msg) {
        System.out.println(GREEN + msg + RESET);
    }

    static void printErrorMessage(String msg) {
        System.out.println(RED + msg + RESET);
    }

    //print option list for each menu
    static void printOptionList(String[] options) {
        for (int i = 0; i < options.length; i++) {
            System.out.println(YELLOW + "[" + (i + 1) + "]" + RESET + ". " + BLUE + options[i] + RESET);
        }
        System.out.println(HELPER.RESET);
        System.out.println("-".repeat(51));
    }

    //add account info for both checking and saving account
    static void addAccountInfo(Account account, String accountType) {
        String name;
        String date;
        String gender;
        String phoneNumber;
        if (account.isActive()) {
            HELPER.printErrorMessage("Your " + accountType + " is already in use!");
            return;
        }
        System.out.println("\n\n\n" + ">".repeat(16) + " Account Information " + ">".repeat(16));
        name = HELPER.getInputAndValidate("➡️Enter username: ", "Name cannot be empty", "([a-zA-Z]+\\s?)+", "Please enter a valid name!");
        account.setName(name);
        do {
            date = HELPER.getInputAndValidate("➡️Enter date of birth (dd-mm-yyyy): ", "Birthdate cannot be null", "[0-9]{2}-[0-9]{2}-[0-9]{4}", "Please enter a valid date format");
        } while (!account.setDob(date));
        do {
            gender = HELPER.getInputAndValidate("➡️Enter gender: ", "Gender cannot be null");
        } while (!account.setGender(gender));
        phoneNumber = HELPER.getInputAndValidate("➡️Enter phone number: ", "Phone number cannot be empty", "^0[1-9][0-9]{7,8}", "Please enter a valid phone number format(eg 012123123)");
        account.setPhoneNumber(phoneNumber);
        account.setActive(true);
        account.setId(HELPER.generateID());
        System.out.println("=".repeat(56));
        HELPER.printSuccessMessage("Your " + accountType + " has been created successfully");
    }

    //check if account is created
    static boolean checkIsAccountCreated(Account account) {
        if (!account.isActive) {
            HELPER.printErrorMessage("Please create account first");
            System.out.println("Press any key to go back to menu...");
            sc.nextLine();
            return false;
        }
        return true;
    }

    /*print withdraw or deposit receipt
    Operation
    -Deposit: received
    -Withdraw: withdraw
    * */
    static void displayWithdrawOrDepositReceipt(double amount, Account account, String accountType, String operation) {
        System.out.println(" ".repeat(24) + "⬇️" + " ".repeat(24));
        System.out.println(" ".repeat(16) + BLUE + accountType + RESET + " ".repeat(24) + "\n");
        System.out.println((BLUE + operation + RESET + " ".repeat(30) + HELPER.df.format(amount)));
        System.out.println(BLUE + "Total Amount:" + RESET + " ".repeat(25) + HELPER.df.format(account.getBalance()));
        System.out.println("=".repeat(50));
    }

    //print main menu
    static void printMenu() {
        Table t = new Table(1, BorderStyle.DESIGN_FORMAL, ShownBorders.SURROUND);
        CellStyle align = new CellStyle(CellStyle.HorizontalAlign.center);
        t.setColumnWidth(0, 50, 77);
        t.addCell(BLUE + "Online Banking System" + RESET, align);
        System.out.println(t.render());
        String[] options = {"Create Account", "Deposit Money", "Withdraw Money", "Transfer Money", "Display Account Information", "Delete Account", "Exit"};
        HELPER.printOptionList(options);
    }

    //generate id for account
    static int generateID() {
        return (int) ((Math.random() * 1000) + 10000);
    }
}
