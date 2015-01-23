import java.util.ArrayList;

/**
 * Created by lucaswebb on 1/23/15.
 */
public class Friend {
    String name, location;
    ArrayList<Friend> friends;
    Friend leftFriend;
    Friend rightFriend;

    Friend(String n, String l){
        name = n;
        location =l;
    }
    public boolean isFriend(Friend f){
        return friends.contains(f) && f.friends.contains(this);
    }
    public void addFriend(Friend f){
        friends.add(f);
    }
    public void removeFriend(Friend f){
        friends.remove(f);
        f.friends.remove(this);
    }
    public String getName(){return name;}
    public String getLocation(){return location;}
}