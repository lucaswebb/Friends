import java.util.ArrayList;
import java.util.ArrayList;

/**
 * Created by Samuel Noyes, Lucas Webb, and Michelle Ramiz on 1/23/15.
 */
public class Friend {
    private String name;
    private String location;
    private Friend leftFriend;
    private Friend rightFriend;
    private ArrayList<Friend> friends;
    Friend(String name, String location) {
        this.name = name;
        this.location = location;
        leftFriend = null;
        rightFriend = null;
        friends= new ArrayList<Friend>();
    }

    public void setRight(Friend f) {
        rightFriend = f;
    }

    public void setLeft(Friend f) {
        leftFriend = f;
    }

    public boolean isFriendsWith(Friend f) {
        return friends.contains(f) && f.friends.contains(this);
    }

    public void addFriend(Friend f) {//Add a friend to the ArrayList.  Should only be called on one Friend, as it
        friends.add(f);              //updates both
        f.addFriend(this);

    }

    public void removeFriend(Friend f) {
        friends.remove(f);
        f.friends.remove(this);
    }

    public String name() { return name; }

    public String location() { return location; }

    public void setLocation(String l) { location = l; }

    public Friend rightFriend() { return rightFriend; }

    public Friend leftFriend() { return leftFriend; }



}
