package Controllers;
import java.util.ArrayList;

import Models.User;
public class LoginController{
    public static int login(String username, String password,ArrayList<User> allUsers){
        for(int i = 0; i < allUsers.size(); i++){
            if(username.equals(allUsers.get(i).getUserName()) && password.equals(allUsers.get(i).getPassword())){
                return i;
            }
        }
        return -1;

    }

}