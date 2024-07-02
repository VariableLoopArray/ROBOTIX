import java.util.ArrayList;
public class LoginController{
    public static boolean login(String username, String password,ArrayList<User> allUsers){
        for(int i = 0; i < allUsers.size(); i++){
            if(password.equals(allUsers.get(i).getPassword())){
                return true;
            }
        }
        return false;

    }

}