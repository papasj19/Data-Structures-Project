package Presentation;

import java.util.Scanner;

public class AdvertisingView {

    private Scanner scan;

    public AdvertisingView(){
        scan = new Scanner(System.in);
    }

    /**
     * Menu used when the user navigates the final option
     * @return the user selected optoion
     */
    public MenuOptions advertisingMenu(){
        System.out.println("""
                A. Add business
                B. Remove business
                C. Check business
                D. Weekly histogram
                
                E. Go back
                """);
        do{
            System.out.print("What functionality do you want to run? ");
            String input = scan.nextLine();
            if(!input.equals("A") && !input.equals("B") && !input.equals("C") && !input.equals("D") && !input.equals("E")){
                System.out.println("incorrect input choice");
            }else{
                switch(input){
                    case "A":
                        return MenuOptions.ADD_BUSINESS;
                    case "B":
                        return MenuOptions.REMOVE_BUSINESS;
                    case "C":
                        return MenuOptions.CHECK_BUSINESS;
                    case "D":
                        return MenuOptions.WEEKLY_HISTOGRAM;
                    case "E":
                        return MenuOptions.GO_BACK;

                }
            }
        }while(true);
    }

    /**
     * Used to obtain the name of the business from the user in option A
     * @return a string of the name
     */
    public String grabBusinessName(){
        System.out.print("Enter the name of the business to add: ");
        String input = scan.nextLine();
        return(input);
    }

    /**
     * Used to obtain the Day interested from the user in option A
     * @return a string of the name of the day of the week
     */
    public String grabDayInterested(){
        System.out.print("Enter the day of the week they are interested in: ");
        String input = scan.nextLine();
        return input;
    }

    /**
     * Used to obtain the price interested from the user in option A
     *  -if bad input, we will try again
     * @return a float that has been parsed
     */
    public int grabPrice() {
        System.out.print("Enter the price they are willing to pay, in euros: ");
        String input = scan.nextLine();
        do{
            try{
                int parsedInput = Integer.parseInt(input);
                return parsedInput;
            } catch(NumberFormatException e){
                System.out.println("The number has not been accepted. Please try again");
            }
        }while(true);
    }

    /**
     * Used to obtain the name of the business from the user in option B
     * @return a string of the name
     */
    public String grabBusinessNameDelete(){
        System.out.print("Enter the name of the business to delete: ");
        String input = scan.nextLine();
        return(input);
    }

    /**
     * Used to obtain the name of the business from the user in option C
     * @return a string of the name
     */
    public String grabBusinessNameQuery(){
        System.out.print("Enter the name of the business to query: ");
        String input = scan.nextLine();
        return input;
    }

    public void showMessage(Messages message){
        System.out.println(message);
    }

}
