package Presentation;

import java.util.Scanner;

public class CanvasView {

    private Scanner scan;

    public CanvasView(){
        scan = new Scanner(System.in);
    }

    public MenuOptions canvasMenu(){
        System.out.println("""
                A. Add Circle
                B. Remove Circle
                C. Visualize
                D. Search by Area
                E. Search by Special Factor
                
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
                        return MenuOptions.ADD_CIRCLE;
                    case "B":
                        return MenuOptions.REMOVE_CIRCLE;
                    case "C":
                        return MenuOptions.VISUALIZE_CIRCLE;
                    case "D":
                        return MenuOptions.SEARCH_AREA_CIRCLE;
                    case "E":
                        return MenuOptions.SEARCH_SPEC_CIRCLE;
                    case "F":
                        return MenuOptions.GO_BACK;

                }
            }
        }while(true);
    }


    // below can be used for functionality A and B

    /**
     * Used to obtain the x coordinate of a circle from the user
     * @return a parsed input of type float
     */
    public float grabXCoordinate(){
        System.out.print("Enter the X coordinate of the circle's center: ");
        String input = scan.nextLine();
        do {
            try {
                float parsedInput = Float.parseFloat(input);
                return parsedInput;
            } catch (NumberFormatException e) {
                System.out.println("Bad Input");
            }
        }while(true);
    }

    /**
     * Used to obtain the y coordinate of a circle from the user
     * @return a parsed input of type float
     */
    public float grabYCoordinate(){
        System.out.print("Enter the Y coordinate of the circle's center: ");
        String input = scan.nextLine();
        do {
            try {
                float parsedInput = Float.parseFloat(input);
                return parsedInput;
            } catch (NumberFormatException e) {
                System.out.println("Bad Input");
            }
        }while(true);
    }

    /**
     * Used to obtain the radius of a circle from the user
     * @return a parsed input of type float
     */
    public float grabCircleRadius(){
        System.out.print("Enter the the circle's radius: ");
        String input = scan.nextLine();
        do {
            try {
                float parsedInput = Float.parseFloat(input);
                return parsedInput;
            } catch (NumberFormatException e) {
                System.out.println("Bad Input");
            }
        }while(true);
    }

    /**
     * Used to obtain the color of a circle from the user
     * @return a string of the color format
     */
    public String grabCircleColor(){
        System.out.print("Enter the circle's color: ");
        String input = scan.nextLine();
        return input;
    }

    //below are for functionality D

    public float[] grabRectanglePoints(){
        System.out.print("Enter the rectangle's first point (X,Y): ");
        String input = scan.nextLine();

        System.out.print("Enter the rectangle's second point (X,Y): ");
        input = input + "," + scan.nextLine();
        String[] a = input.split(",");
        float[] imhavingfun = new float[4];
        int i = 0;
        for (String string:a){
            try {
                imhavingfun[i] = Float.parseFloat(string);
                i++;
            } catch (NumberFormatException e) {
                System.out.println("Bad Input");
            }
        }

        return imhavingfun;
    }





    //All below are for functionality E

    /**
     * Used to obtain the x coordinate of a circle from the user when searching
     * @return a parsed input of type float
     */
    public float grabXCoordinateSearch(){
        System.out.print("Enter the X coordinate of the search circle's center: ");
        String input = scan.nextLine();
        do {
            try {
                float parsedInput = Float.parseFloat(input);
                return parsedInput;
            } catch (NumberFormatException e) {
                System.out.println("Bad Input");
            }
        }while(true);
    }

    /**
     * Used to obtain the y coordinate of a circle from the user when searching
     * @return a parsed input of type float
     */
    public float grabYCoordinateSearch(){
        System.out.print("Enter the Y coordinate of the search circle's center: ");
        String input = scan.nextLine();
        do {
            try {
                float parsedInput = Float.parseFloat(input);
                return parsedInput;
            } catch (NumberFormatException e) {
                System.out.println("Bad Input");
            }
        }while(true);
    }

    /**
     * Used to obtain the radius of a circle from the user when searching
     * @return a parsed input of type float
     */
    public float grabCircleRadiusSearch(){
        System.out.print("Enter the the search circle's radius: ");
        String input = scan.nextLine();
        do {
            try {
                float parsedInput = Float.parseFloat(input);
                return parsedInput;
            } catch (NumberFormatException e) {
                System.out.println("Bad Input");
            }
        }while(true);
    }


    /**
     * Used to obtain the color of a circle from the user when searching
     * @return a string ofthe color
     */
    public String grabCircleColorSearch(){
        System.out.print("\nEnter the search circle's color: ");
        String input = scan.nextLine();
        return input;
    }

    public void showMessage (Messages message){
        System.out.println(message);
    }




}
