import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by lucaswebb on 1/23/15.
 *
 *
 * Already done:
 * Friends of Friends list
 * Add Friend
 * Remove Friend
 * There is already a method to check if a friend with the given name already exists - no need to do that again.
 * DONE: From assignment doc: "Display all relationships as shown initially in the first assignment.  That is, one line per name with the associated friends of that person following."
 *     - This is a little unclear but it doesn't mean Aragorn[Gondor], it means like this:
 *          -  Frodo of the Shire is friends with Sam, Aragorn, and Gandalf
 *
 * TO-DO's:
 *  The method that reads from the text file and turns them into a tree is yet to be implemented.
 */
public class Driver{
    int cnt = 1;//Set it to one because there will always be one root plus the ones added with addFriendsToHierarchy, which increments cnt
    Friend rt;

    public static void main(String[] args) {
        Driver m = new Driver();
        m.createFriends();
        //m.test();
        //System.out.println(m.numFriends());
        //System.out.println(m.friendExists("Sam", m.rt));
        //System.out.println(m.friendsOfFriends("Tim").toString());
        //m.toString();
    }

    public void test() {//Just creates some relationships as a test
        rt = new Friend("Frodo", "The Shire");
        addFriendToHierarchy(new Friend("Sam", "The Shire"));
        addFriendToHierarchy(new Friend("Aragorn", "Gondor"));
        addFriendToHierarchy(new Friend("John", "Dover"));
        addFriendToHierarchy(new Friend("Idiot", "Dumbville"));
        addFriendToHierarchy(new Friend("Tim", "Timmyland"));
        makeFriends(getFriendWithName("Tim", rt), getFriendWithName("John", rt));
        makeFriends(getFriendWithName("Tim", rt), getFriendWithName("Idiot", rt));
        makeFriends(getFriendWithName("John", rt), getFriendWithName("Aragorn", rt));
        makeFriends(getFriendWithName("Idiot", rt), getFriendWithName("Aragorn", rt));
        System.out.println(FriendTreeToString(rt));
    }

    public void createFriends(){
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
            System.out.println(str);
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
        //Create friends
        int num = Character.getNumericValue(str.charAt(0));
        int commas = 0;
        int i = 1;
        boolean first = true;
        while(commas != num){
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
            //System.out.println(friend + " " + location);
            //System.out.println(friend2 + " " + location2);
            if(first){
                rt = new Friend(friend, location);
                first = false;
            }
            if(!(friendExists(friend, rt))){
                addFriendToHierarchy((new Friend(friend, location)));//
            }
            if(!(friendExists(friend2, rt))){
                addFriendToHierarchy((new Friend(friend2, location2)));
            }
            System.out.println("Making friends between " + friend + " and " + friend2 + ".");
            System.out.println(FriendTreeToString(rt));
            System.out.println("rt = " + rt.name());
            System.out.println(getFriendWithName(friend, rt).name());
            makeFriends(getFriendWithName(friend, rt), getFriendWithName(friend2, rt));
            commas++;
        }
    }

    public Friend addFriendToHierarchy(Friend f) {// serves as a buffer - if the name has already been added, returns null
        if (!friendExists(f.name(), rt)) {
            return addFriendToHierarchy(rt, f);
        }
        return null;
    }

    private Friend addFriendToHierarchy(Friend rt, Friend f) {//Add a friend f to the BST rt - updates count as well - private because should only be called by the method above so that repeating names are never added.
            if (rt == null) {
                incrementCount();
                return f;
            }
            if (rt.toString().compareTo(f.toString()) <= 0) {
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

    public boolean friendExists(String name, Friend rt) { //Given a subtree rt, check to see if there is already a Friend with the given name and location
        if (rt == null) return false;
        if (rt.name().equals(name)) return true;
        return friendExists(name, rt.leftFriend()) || friendExists(name, rt.rightFriend());
    }

    public void incrementCount() {//Add one to the friend count
        cnt++;
    }

    public int numFriends() {//return the number of Friends in the BST
        return cnt;
    }

    public ArrayList<String> friendsOfFriends(String name){// There is a friends of friends method in the class Friend, but we are supposed to take a name and return the list of friends, so this is somewhat of an extension for that method.
        return getFriendWithName(name, rt).friendsOfFriends();
    }

    public Friend getFriendWithName(String name, Friend rt) {//Returns a Friend object with the given name or null if not found
        if (rt == null) return null;
        if (rt.name() == name) {
            return rt;
        }
        Friend fr = getFriendWithName(name, rt.rightFriend());
        Friend fl = getFriendWithName(name, rt.leftFriend());
        if (fr != null) {
            return fr;
        }
        if (fl != null) {
            return fl;
        }
        return null;
    }

    public void makeFriends(Friend f, Friend f2) {//Make a pair of friends - add each to each other's lists
        if (f == null) System.out.println("f is null. Program will crash");
        if (f2 == null) System.out.println("f2 is null. Program will crash");
        f.addFriend(f2);
        f2.addFriend(f);
    }

    public void unfriend(Friend f, Friend f2) {//unfriend two people - remove each from each other's lists
        f.removeFriend(f2);
        f2.removeFriend(f);
    }
}