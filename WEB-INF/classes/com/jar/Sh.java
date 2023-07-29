import com.sun.jna.platform.win32.*;

public class Sh {
    public static void main(String[] args) {

        Netapi32Util.User[] users = Netapi32Util.getUsers();
        for(Netapi32Util.User user : users) {
            System.out.println(user.name);
        }
    }
}
