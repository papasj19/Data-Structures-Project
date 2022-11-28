package Presentation;

import java.util.Scanner;

/**
 * A class to be used by the UIController to display information about the main menu to the console
 */

public class MainView {
    private Scanner scan;

    /**
     * Constructor used to initialize the scanner
     */
    public MainView(){
        scan = new Scanner(System.in);
    }


    /**
     * The main menu of the program
     * The users input is first recieved by a string that is then attempted to be parsed to an int
     *      There are two outcomes
     *          1. The variable is not acceptable and the program attempts to recieve the input again
     *          2. The input is correct and the option is returned for use in the UIController
     * @return the integer of the choice the user input if it is correctly parsed
     */
    public MenuOptions mainMenu () {
        int option;

        System.out.println("""
                .*LinkedTree*.
                                
                1. Followers (Graphs)
                2. Feed (Trees)
                3. Canvas (R-Trees)
                4. Advertising (Tables)
                                
                5. EXIT
                """);

        while (true) {
            System.out.print("Choose an option: ");
            String input = scan.nextLine();
            try {
                option = Integer.parseInt(input);
                if (option != 1 && option != 2 && option != 3 && option !=4 && option != 5)
                    System.out.println("Bad input");
                else {
                    switch (option) {
                        case 1:
                            System.out.println("\nBeing redirected to followers menu...\n");
                            return MenuOptions.FOLLOWERS;
                        case 2:
                            System.out.println("\nBeing redirected to feed menu...\n");
                            return MenuOptions.FEED;
                        case 3:
                            System.out.println("\nBeing redirected to canvas menu...\n");
                            return MenuOptions.CANVAS;
                        case 4:
                            System.out.println("\nBeing redirected to advertising menu");
                            return MenuOptions.ADVERTISING;
                        case 5:
                            return MenuOptions.EXIT;
                    }
                }
            } catch(NumberFormatException e) {
                System.out.println("Bad input");
            }
        }
    }

    public void showMessage (Messages message){
        System.out.println(message);
    }
}
