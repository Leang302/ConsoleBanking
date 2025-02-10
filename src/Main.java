public class Main {

    public static void main(String[] args) {

//        Account checkingAccount = new CheckingAccount();
//        Account savingAccount = new SavingAccount();
        Account checkingAccount = new CheckingAccount("leang", "01-01-2023", "male", "012123123", 0);
        Account savingAccount = new SavingAccount("leang", "01-01-2023", "male", "012123123", 0);
        do {
            HELPER.printMenu();
            int choice = Integer.parseInt(HELPER.getInputAndValidate("➡️Choose an option: ", "Choice cannot be empty", "[1-7]", "Please enter a valid choice (1-7)"));
            switch (choice) {
                //create account
                case 1:
                    createAccount:
                    do {
                        System.out.println("\n" + ">".repeat(16) + HELPER.BLUE + " Creating account " + HELPER.RESET + ">".repeat(16));
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
                        System.out.println("\n" + ">".repeat(16) + HELPER.BLUE + " Deposit Money " + HELPER.RESET + ">".repeat(16));
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
                                HELPER.displayWithdrawOrDepositReceipt(amount, checkingAccount, "Checking account", "Received");
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
                                HELPER.displayWithdrawOrDepositReceipt(amount, savingAccount, "Saving account", "Recieved");
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
                        System.out.println("\n" + ">".repeat(16) + HELPER.RESET + " Withdraw Money " + HELPER.RESET + ">".repeat(16));
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
                                    if (totalAmount == oldBalance) {
                                        String continueDeposit = HELPER.getInputAndValidate("➡️ Do you want to continue? (y or n): ", "Cannot be empty", "[ynYN]", "Invalid choice! Please input (y/n)");
                                        if (continueDeposit.equalsIgnoreCase("n")) {
                                            continue withdraw;
                                        }
                                    }
                                } while (oldBalance == totalAmount);
                                HELPER.displayWithdrawOrDepositReceipt(amount, checkingAccount, "Checking account", "Withdraw");
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
                                HELPER.displayWithdrawOrDepositReceipt(amount, savingAccount, "Saving account", "Withdraw");
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
                        if (!checkingAccount.isActive() || !savingAccount.isActive()) {
                            HELPER.printErrorMessage("You must 2 account to access this feature!\n");
                            break transfer;
                        }
                        System.out.println("\n" + ">".repeat(16) +HELPER.BLUE+ " Transfer money " +HELPER.RESET+ ">".repeat(16));
                        String[] options = {"Checking account -> Saving account", "Saving Account -> Checking account", "Back"};
                        HELPER.printOptionList(options);
                        int transferChoice = Integer.parseInt(HELPER.getInputAndValidate("➡️ Choose an options: ", "Option cannot be empty", "[1-3]", "Please enter a valid choice (1-3)"));
                        double amount;
                        double oldAmount;
                        double totalAmountLeft;
                        switch (transferChoice) {
                            case 1:
                                if (checkingAccount.getBalance() == 0) {
                                    HELPER.printErrorMessage("Please deposit money first");
                                    break;
                                }
                                do {
                                    oldAmount = checkingAccount.getBalance();
                                    amount = Double.parseDouble(HELPER.getInputAndValidate("Enter money to transfer: ", "Amount cannot be empty", "\\d+.?\\d*", "Wrong input format"));
                                    totalAmountLeft = checkingAccount.transfer(amount, savingAccount);
                                } while (oldAmount == totalAmountLeft);
                                checkingAccount.transferReciept(savingAccount, amount);
                                HELPER.displayWithdrawOrDepositReceipt(amount, savingAccount, "Saving account", "Received");
                                HELPER.printSuccessMessage("\nTransfer successful!\n");
                                break;
                            case 2:
                                if (savingAccount.getBalance() == 0) {
                                    HELPER.printErrorMessage("Please deposit money first");
                                    break;
                                }
                                do {
                                    oldAmount = savingAccount.getBalance();
                                    amount = Double.parseDouble(HELPER.getInputAndValidate("Enter money to transfer: ", "Amount cannot be empty", "\\d+.?\\d*", "Wrong input format"));
                                    totalAmountLeft = savingAccount.transfer(amount, checkingAccount);
                                } while (oldAmount == totalAmountLeft);
                                savingAccount.transferReciept(checkingAccount, amount);
                                HELPER.displayWithdrawOrDepositReceipt(amount, checkingAccount, "Checking account", "Received");
                                HELPER.printSuccessMessage("\nTransfer successful!\n");
                                break;
                            case 3:
                                break transfer;
                        }

                    } while (true);
                    break;
                //display account information
                case 5:
                    checkingAccount.displayAccountInfo();
                    savingAccount.displayAccountInfo();
                    System.out.println(HELPER.YELLOW + "\nPress any key to go back to menu...." + HELPER.RESET);
                    HELPER.sc.nextLine();
                    break;
                //delete account
                case 6:
                    if (!savingAccount.isActive() || !checkingAccount.isActive()) {
                        HELPER.printErrorMessage("You only have one account. You cannot delete your account!");
                        break;
                    }
                    System.out.println("\n" + ">".repeat(16) + HELPER.BLUE + " Delete Account " + HELPER.RESET + ">".repeat(16));
                    String[] options = {"Checking account", "Saving account", "Back"};
                    HELPER.printOptionList(options);
                    int deleteChoice = Integer.parseInt(HELPER.getInputAndValidate("➡️ Choose an options: ", "Option cannot be empty", "[1-3]", "Please enter a valid choice (1-3)"));
                    String yesNo;
                    double balanceLeft;
                    //check deleted choice
                    switch (deleteChoice) {
                        case 1:
                            yesNo = HELPER.getInputAndValidate("Are you sure you want to delete this account (Y/N):", "Choice cannot be empty", "[ynYN]", "Invalid choice!");
                            if (yesNo.equalsIgnoreCase("n")) {
                                break;
                            }
                            balanceLeft = checkingAccount.getBalance();
                            checkingAccount.deleteAccount(checkingAccount, savingAccount);
                            checkingAccount.transferReciept(savingAccount, balanceLeft);
                            HELPER.displayWithdrawOrDepositReceipt(balanceLeft, savingAccount, "Saving account", "Received");
                            break;
                        case 2:
                            yesNo = HELPER.getInputAndValidate("Are you sure you want to delete this account (Y/N):", "Choice cannot be empty", "[ynYN]", "Invalid choice!");
                            if (yesNo.equalsIgnoreCase("n")) {
                                break;
                            }
                            balanceLeft = savingAccount.getBalance();
                            savingAccount.deleteAccount(savingAccount, checkingAccount);
                            savingAccount.transferReciept(checkingAccount, balanceLeft);
                            HELPER.displayWithdrawOrDepositReceipt(balanceLeft, checkingAccount, "Checking account", "Received");
                            break;
                        case 3:
                            break;
                    }
                    break;
                //exit
                case 7:
                    System.out.println("Thank you🙏🏻🙏🏻 Good bye😁😁");
                    return;

            }
        } while (true);
    }


}