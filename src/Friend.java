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

    public void addFriend(Friend f) {//Add a friend to the ArrayList
        friends.add(f);

    }

    public void removeFriend(Friend f) {
        friends.remove(f);
    }

    public String name() { return name; }

    public String location() { return location; }

    public void setLocation(String l) { location = l; }

    public Friend rightFriend() { return rightFriend; }

    public Friend leftFriend() { return leftFriend; }

    public String toString() {
        return "" + name() + " of " + location();
    }

    public int friends() {
        return friends.size();
    }

    public ArrayList<String> friendsOfFriends() {//returns ArrayList of names
        ArrayList<String> friendsOfFriendsList = new ArrayList<String>();
        for (Friend f:friends) {
            for (Friend FoF: f.friends) {
                if (!friendsOfFriendsList.contains(FoF.name()) && FoF != this) {
                    friendsOfFriendsList.add(FoF.name());
                }
            }
        }
        return friendsOfFriendsList;
    }
}