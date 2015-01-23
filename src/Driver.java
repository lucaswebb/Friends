import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by lucaswebb on 1/23/15.
 */
public class Driver{
    public static void main(String[] args) {
        Driver m = new Driver();
        //m.createFriends();
        m.test();
    }

    public void test() {
        Friend rt = new Friend("Frodo", "The Shire");
        addFriendToHierarchy(rt, new Friend("Sam", "The Shire"));
        addFriendToHierarchy(rt, new Friend("Aragorn", "Gondor"));
        addFriendToHierarchy(rt, new Friend("Sam", "Eliot"));
        addFriendToHierarchy(rt, new Friend("John", "Dover"));
        addFriendToHierarchy(rt, new Friend("Idiot", "Dumbville"));
        addFriendToHierarchy(rt, new Friend("Tim", "Timmyland"));
        System.out.println(FriendToString(rt));
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
    public String FriendToString(Friend rt) {//Convert subtree to String (In alphabetical order) 
        if (rt == null) { return ""; }

        return "" + FriendToString(rt.leftFriend()) + rt.name() + " of " + rt.location() + "\n" + FriendToString(rt.rightFriend());
    }
}