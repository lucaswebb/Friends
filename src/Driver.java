import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by lucaswebb on 1/23/15.
 */
public class Driver {
    public static void main(String[] args) {
        createFriends();
    }
    public static void createFriends(){
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
        if (rt.name().compareTo(f.name())<=0) {
            rt.setRight(addFriendToHierarchy(rt.rightFriend(), f));
        }
        else {
            rt.setRight(addFriendToHierarchy(rt.leftFriend(), f));
        }
        return rt;
    }
}