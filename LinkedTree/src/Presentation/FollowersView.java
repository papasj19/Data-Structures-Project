package Presentation;

import java.util.Scanner;

//testing for pol

/**
 * A class used to display the menu relating to the followers option from the main menu
 * This will be implemented by the UIcontrolloer Follower
 */

public class FollowersView {

    private Scanner scan;

    /**
     * Constructor to initialize the scanner for the user input
     */
    public FollowersView() {scan = new Scanner(System.in);}

    /**
     * The followers submenu demonstrated in snippet 3 from project statement
     * @return the menu option relating to the character the user input
     */
    public MenuOptions followersMenu(){
        System.out.println("""
                A. Explore the network
                B. Suggest users
                C. Contextualize drama
                D. Networking
                
                E. Go back
                """);
        do{
            System.out.print("What functionality do you want to run? ");
            String input = scan.nextLine();
            if(!input.equals("A") && !input.equals("B") && !input.equals("C") && !input.equals("D") && !input.equals("E")){
                System.out.println("incorrect input choice bro");
            }else{
                switch(input){
                    case "A":
                        return MenuOptions.EXPLORE_NETWORK;
                    case "B":
                        return MenuOptions.SUGGEST_USERS;
                    case "C":
                        return MenuOptions.CONTXT_DRAM;
                    case "D":
                        return MenuOptions.NETWORKING;
                    case "E":
                        return MenuOptions.GO_BACK;
                }
            }
        }while(true);
    }

    /**
     * Used to obtain input from the user
     * @return parsed input of type int containing hte id of the user.
     */
    public int grabIdentifier(){
        int userid = -1;
        System.out.print("\nEnter your identifier: ");
        String input = scan.nextLine();
        do {
            try {
                userid = Integer.parseInt(input);
                return userid;
            } catch (NumberFormatException e) {
                System.out.println("Bad input");
            }
        }while(true);
    }

    /**
     * When needing to determine paths connecting users it is necessary to grab the id of the other user
     * @return a parsed input of type int containing the other users identifier
     */
    public int grabOtherIdentifier(){
        int userid = -1;
        System.out.print("Enter the other user's identifier: ");
        String input = scan.nextLine();
        System.out.println();
        do {
            try {
                userid = Integer.parseInt(input);
                return userid;
            } catch (NumberFormatException e) {
                System.out.println("Bad input");
            }
        }while(true);
    }

    public void showMessage (Messages message){
        System.out.println(message);
    }



}
