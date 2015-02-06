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
    //Setters for right and left nodes of BST
    public void setRight(Friend f) {
        rightFriend = f;
    }

    public void setLeft(Friend f) {
        leftFriend = f;
    }
    //Returns true if they are friends
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
    //ToString method summarizes all friends of one person
    public String toString() {
        String friendsString = "";
        for (int i = 0; i<friends.size(); i++) {
            Friend fr = friends.get(i);
            if (i == friends.size()-2 && friends.size() ==2) {
                friendsString += fr.name() + " of " + fr.location() + " and ";
            }
            else if (i == friends.size()-2) {
                friendsString += fr.name() + " of " + fr.location() + ", and ";
            }
            else if (i<friends.size() -2) {
                friendsString += fr.name() + " of " + fr.location() + ", ";
            }
            else friendsString += fr.name() + " of " + fr.location() + ".";
        }
        if (friendsString.equals("")) {
            return "" + name() + " of " + location() + " has no friends.";
        }
        return "" + name() + " of " + location() + " is friends with " + friendsString;
    }

    public int friends() {
        return friends.size();
    }
    //Returns an AList of friends of friends

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
