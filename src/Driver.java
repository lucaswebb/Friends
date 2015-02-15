import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Samuel Noyes, Lucas Webb, and Michelle Ramiz on 1/23/15.
 *
 *
 */

public class Driver{
    int cnt = 1;//Set it to one because there will always be one root plus the ones added with addFriendsToHierarchy, which increments cnt
    Friend rt = null;//Will be set later on

    public static void main(String[] args) {
        Driver m = new Driver();
        m.createFriends();
        System.out.println(m.FriendTreeToString(m.rt));
    }

    public void createFriends(){//Reads file friends.txt and creates friend object if they don't already exist.  Then makes the friendship
        //Convert file to String
        File friends = new File("Friends/src/friends.txt");
        String str = "";
        try{
            Scanner scan = new Scanner(friends);

            while(scan.hasNextLine()){
                String line = scan.nextLine();
                str = str + line + ",";
            }
            scan.close();
        } catch (FileNotFoundException e){//Catches FileNotFoundException
            e.printStackTrace();
        }
        //Create friends
        int num = Character.getNumericValue(str.charAt(0));
        int commas = 0;
        int i = 1;
        //Parsing of string begins here
        while(commas != num){//Commas work to keep track of new lines
            i++;
            String friend = "";
            String location = "";
            String friend2 = "";
            String location2 = "";
            while(str.charAt(i) != ','){
                while(str.charAt(i) != '['){
                    friend += str.charAt(i);
                    i++;
                }
                i++;
                while(str.charAt(i) != ']'){
                    location += str.charAt(i);
                    i++;
                }
                i++;
                i++;
                while(str.charAt(i) != '['){
                    friend2 += str.charAt(i);
                    i++;
                }
                i++;
                while(str.charAt(i) != ']'){
                    location2 += str.charAt(i);
                    i++;
                }
                i++;
            }
            if(!(friendExists(friend))){//Checks to make sure friend object hasn't already been created
                addFriendToHierarchy((new Friend(friend, location)));//
            }
            if(!(friendExists(friend2))){
                addFriendToHierarchy((new Friend(friend2, location2)));
            }
            makeFriends(getFriendWithName(friend), getFriendWithName(friend2));//Sets friendship
            commas++;//Moves on to next pair
        }
    }

    public Friend addFriendToHierarchy(Friend f) {// serves as a buffer - if the name has already been added, returns null - also so we don't have to specify the rt, automatically uses the already-created rt
        if (rt == null) {
            rt = f;
            return f;
        }
        else if (!friendExists(f.name())) {
            return addFriendToHierarchy(rt, f);
        }
        return null;
    }

    private Friend addFriendToHierarchy(Friend rt, Friend f) {//Add a friend f to the BST rt - updates count as well - private because should only be called by the method above so that repeating names are never added.
            if (rt == null) {
                incrementCount();
                return f;
            }
            if (rt.toString().compareToIgnoreCase(f.toString()) <= 0) {
                rt.setRight(addFriendToHierarchy(rt.rightFriend(), f));
            } else {
                rt.setLeft(addFriendToHierarchy(rt.leftFriend(), f));
            }
            return rt;
    }

    public String FriendTreeToString(Friend rt) {//Convert subtree to String (In alphabetical order) 
        if (rt == null) { return ""; }
        return "" + FriendTreeToString(rt.leftFriend()) + rt.toString() + "\n" + FriendTreeToString(rt.rightFriend());
    }

    public boolean friendExists(String name) {//So that we don't have to pass in the rt pointer - automatically checks under rt.
        return friendExists(name, rt);
    }

    private boolean friendExists(String name, Friend rt) { //Given a subtree rt, check to see if there is already a Friend with the given name and location
        if (rt == null) return false;
        if (rt.name().equals(name)) return true;
        if (rt.name().compareToIgnoreCase(name)<=0) return friendExists(name, rt.rightFriend());
        return friendExists(name, rt.leftFriend());
    }

    public void incrementCount() {//Add one to the friend count
        cnt++;
    }

    public int numFriends() {//return the number of Friends in the BST
        return cnt;
    }

    public ArrayList<String> friendsOfFriends(String name){// There is a friends of friends method in the class Friend, but we are supposed to take a name and return the list of friends, so this is somewhat of an extension for that method.
        return getFriendWithName(name).friendsOfFriends();
    }

    public Friend getFriendWithName(String name) {//So that we don't have to pass in rt, automatically searches under already-created rt.
        return getFriendWithName(name, rt);
    }

    private Friend getFriendWithName(String name, Friend rt) {//Returns a Friend object with the given name or null if not found
        if (rt == null) return null;
        if (rt.name().equals(name)) {
            return rt;
        }
        Friend fr;
        if (rt.name().compareToIgnoreCase(name)<=0) {
            fr = getFriendWithName(name, rt.rightFriend());
        }
        else {
            fr = getFriendWithName(name, rt.leftFriend());
        }
        if (fr != null) {
            return fr;
        }
        return null;
    }

    public void makeFriends(Friend f, Friend f2) {//Make a pair of friends - add each to each other's lists
        if (f != null && f2 != null) {
            f.addFriend(f2);
            f2.addFriend(f);
        }
    }

    public void unfriend(Friend f, Friend f2) {//unfriend two people - remove each from each other's lists
        f.removeFriend(f2);
        f2.removeFriend(f);
    }
}