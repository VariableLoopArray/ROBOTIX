import java.util.ArrayList;

public class UserController {
    public User model;
    public UserView view;

    public UserController (User user, UserView userView){
        this.model = user;
        this.view = userView;
    }


    public void manageFollowers (User user){

    }
    
    public void manageInterests (User user){

    }

    public void manageAccount (User user){

    }

    public void manageFollowing (User user){

    }

    public void follow(User user){
        model.addFollower(user);
    }

    public void unfollow(User user){
        model.unfollowFollower(user);
    }

    public void removeFollower(User user){
        model.removeFollower(user);
    }

    public void deleteInterest(String interest){
        model.deleteInterest(interest);
    }

    public void subscribeInterest(String interest){
        model.addInterest(interest);
    }

    public void removeActivity(Activity activity){
        model.removeActivity(activity);
    }

    public void modifyActivity(Activity activity){

    }

    public void registerActivity(Activity activity){

    }

    public void addActivity(Activity activity){

    }

    public void verifyActivity(Activity activity){

    }

    public void changePassword(String oldPass, String newPass){
        model.changePassword(oldPass,newPass);
    }
}
