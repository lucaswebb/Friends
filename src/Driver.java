import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by lucaswebb on 1/23/15.  THIS IS SAM HEY LUCAS!!
 */
public class Driver {
    public static void main(String[] args) {
        createFriends();
    }
    public static void createFriends(){
        //Convert file to String
        File friends = new File("/Users/lucaswebb/IdeaProjects/CSC420/Friends/src/friends.txt");
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

        //Test Update
    }
}