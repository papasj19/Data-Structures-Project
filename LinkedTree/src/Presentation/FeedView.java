package Presentation;

import java.awt.*;
import java.util.Scanner;

public class FeedView {

    private Scanner scan;

    /**
     * Constructor used to initialize scanner
     */
    public FeedView(){
        scan = new Scanner(System.in);
    }


    /**
     * Used to show the user the menu for trees
     *      -error is managed
     * @return a menuoption of the users choice
     */
    public MenuOptions feedMenu(){
        System.out.println("""
                A. Add Algorithm
                B. Remove Algorithm
                C. List Algorithms
                D. Search by Timestamp (exact)
                E. Search by Timestamp (range)
                
                F. Go back
                """);
        do{
            System.out.print("What functionality do you want to run? ");
            String input = scan.nextLine();
            if(!input.equals("A") && !input.equals("B") && !input.equals("C") && !input.equals("D") && !input.equals("E") && !input.equals("F")){
                System.out.println("incorrect input choice bro");
            }else{
                switch(input){
                    case "A":
                        return MenuOptions.ADD_ALGORITHM;
                    case "B":
                        return MenuOptions.REMOVE_ALGORITHM;
                    case "C":
                        return MenuOptions.LIST_ALGORITHMS;
                    case "D":
                        return MenuOptions.SEARCH_TIMESTAMP_EXACT;
                    case "E":
                        return MenuOptions.SEARCH_TIMESTAMP_RANGE;
                    case "F":
                        return MenuOptions.GO_BACK;

                }
            }
        }while(true);
    }


    /**
     * OBtains an id for an algorithm from the user
     * @return an int
     */
    public int grabAlgorithmIdentifier(){
        int userid = -1;
        System.out.print("\nEnter the algorithm's identifier: ");
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
     * Obtains the name of the algorithm from the user
     * @return a string with the name
     */
    public String grabAlgorithmName(){
        System.out.print("\nEnter the algorithm's name: ");
        String input = scan.nextLine();
        return input;
    }

    /**
     * Obtains the language of the algorithm from the user
     * @return a String of the language
     */
    public String grabAlgorithmLanguage(){
        System.out.print("\nEnter the algorithm's language: ");
        String input = scan.nextLine();
        return input;
    }

    /**
     * Obtains the cost of the algorithm from the user
     * @return a String of the cost
     */
    public String grabAlgorithmCost(){
        System.out.print("\nEnter the algorithm's cost: ");
        String input = scan.nextLine();
        return input;
    }

    //for now im saving it as a string until the group decides how they want to save this input
    //this extends into the method below used in option D searching for timestamps
    public String grabAlgorithmTS(){
        System.out.print("\nEnter the algorithm's timestamp: ");
        String input = scan.nextLine();
        return input;
    }

    /**
     * In relation to feed menu option D
     * @return a string of the TS to be searched
     */
    public String askAlgorithmTSSearchExact(){
        System.out.print("\nEnter the timestamp to search for: ");
        String input = scan.nextLine();
        return input;
    }


    /**
     * In relation to feed menu option E
     *      -Not managed bc string
     * Obtains the minimum TS of the algorithm from the user to search
     * @return a String of the entered TS
     */
    public String askAlgorithmTSSearchRangeMinimum(){
        System.out.print("\nEnter the minimum timestamp to search for: ");
        String input = scan.nextLine();
        return input;
    }

    /**
     * In relation to feed menu option E
     *      -Not managed bc string
     * Obtains the maximum TS of the algorithm from the user to search
     * @return a String of the entered TS
     */
    public String askAlgorithmTSSearchRangeMaximum(){
        System.out.print("\nEnter the maximum timestamp to search for: ");
        String input = scan.nextLine();
        return input;
    }












}
