
import java.util.Scanner;

public interface HELPER {
    String RESET = "\u001B[0m";
    String GREEN = "\u001B[32m";
    String BLUE = "\u001B[34m";
    String YELLOW = "\u001B[33m";
    String RED = "\u001B[31m";
    Scanner sc = new Scanner(System.in);
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
    static void printSuccessMessage(String msg){
        System.out.println(GREEN+msg+RESET);
    }
    static void printErrorMessage(String msg){
        System.out.println(RED+msg+RESET);
    }
    public static void printOptionList(String[] options) {
        System.out.println(HELPER.BLUE);
        for (int i = 0; i < options.length; i++) {
            System.out.println(i + 1 + ". " + options[i]);
        }
        System.out.println(HELPER.RESET);
        System.out.println("-".repeat(51));
    }
    static void addAccountInfo(Account account,String accountType){
        String name;
        String date;
        String gender;
        String phoneNumber;
        if (account.isActive){
            HELPER.printErrorMessage("Your "+accountType+" is already in use!");
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
        System.out.println("=".repeat(56));
        HELPER.printSuccessMessage("Your "+accountType+" has been created successfully");
    }
    static boolean accountIsCreated(Account account){
        if(!account.isActive){
            HELPER.printErrorMessage("Please create an account first");
            System.out.println("Press any key to go back to menu...");
            sc.nextLine();
            return false;
        }
        return true;
    }
}
