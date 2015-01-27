import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by lucaswebb on 1/23/15.
 */
public class Driver{
    int cnt = 1;//Because there will always be one root plus the ones added with addFriendsToHierarchy, which increments cnt
    Friend rt;

    public static void main(String[] args) {
        Driver m = new Driver();
        m.createFriends();
        m.test();
        System.out.println(m.numFriends());
        System.out.println(m.friendExists("Sam", "The Shire", m.rt));
    }

    public void test() {
        rt = new Friend("Frodo", "The Shire");
        addFriendToHierarchy(rt, new Friend("Sam", "The Shire"));
        addFriendToHierarchy(rt, new Friend("Aragorn", "Gondor"));
        addFriendToHierarchy(rt, new Friend("Sam", "Eliot"));
        addFriendToHierarchy(rt, new Friend("John", "Dover"));
        addFriendToHierarchy(rt, new Friend("Idiot", "Dumbville"));
        addFriendToHierarchy(rt, new Friend("Tim", "Timmyland"));
        System.out.println(FriendTreeToString(rt));
    }

    public void createFriends(){
        //Convert file to String
        File friends = new File("Friends/src/friends.txt");
        try{
            Scanner scan = new Scanner(friends);
            String str = "";

            while(scan.hasNextLine()){
                String line = scan.nextLine();
                str = str + line + ",";
            }
            scan.close();
            System.out.println(str);
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    public Friend addFriendToHierarchy(Friend rt, Friend f) {//Add a friend f to the BST rt
        if (rt == null) {
            incrementCount();
            return f;
        }
        if (rt.toString().compareTo(f.toString())<=0) {
            rt.setRight(addFriendToHierarchy(rt.rightFriend(), f));
        }
        else {
            rt.setLeft(addFriendToHierarchy(rt.leftFriend(), f));
        }
        return rt;
    }

    public String FriendTreeToString(Friend rt) {//Convert subtree to String (In alphabetical order) 
        if (rt == null) { return ""; }

        return "" + FriendTreeToString(rt.leftFriend()) + rt.name() + " of " + rt.location() + "\n" + FriendTreeToString(rt.rightFriend());
    }

    public boolean friendExists(String name, String location, Friend rt) { //Given a subtree rt, check to see if there is already a Friend with the given name and location
        if (rt == null) return false;
        if (rt.name().equals(name) && rt.location().equals(location)) return true;
        return friendExists(name, location, rt.leftFriend()) || friendExists(name, location, rt.rightFriend());
    }

    public void incrementCount() {//Add one to the friend count
        cnt++;
    }

    public int numFriends() {//return the number of Friends in the BST
        return cnt;
    }
}