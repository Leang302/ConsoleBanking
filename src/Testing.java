import java.nio.charset.CharacterCodingException;

public class Testing {
    public static void main(String[] args) {
        Account[] allAccount = new Account[2];
        Account account = new CheckingAccount();
        allAccount[0]= account;
        System.out.println(account.id);
        System.out.println(allAccount[0]==null);
    }
}
