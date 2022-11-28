package Business.Graph;

import java.util.Arrays;

/**
 * A class that implements the class node and represents a user within our program
 */

public class User{


    private final int id;
    private boolean visited;

    private String name;
    private String alias;
    private String[] interests;

    public String buildInterestString(){
        String builtString = "";
        int quickFlag = 0;
        for(String interest: interests){
            if(quickFlag == 0){
                builtString = builtString + interest;
                quickFlag++;
            }
            builtString = builtString +" & " + interest;
        }
        return builtString;
    }


    public String buildSuggestUserString(){
        String formattedInterest = buildInterestString();
        String formatted4Printing = id + " - " + name +"(" + alias + ")\nInterests: " + formattedInterest + "\n\n";
        return formatted4Printing;

    }

    public String getName() {
        return name;
    }


    /**
     * Constructor to be used when creating a user
     * @param id the identity of the user
     * @param name a string with the users name
     * @param alias the users username that they selected
     * @param interests an array of strings containing the interests of the user
     */
    public User (int id, String name, String alias, String[] interests) {
        this.id = id;
        this.name = name;
        this.alias = alias;
        this.interests = interests;
    }

    /**
     * Used as a constructor when a user has no interests
     * @param id the identity of the user
     * @param name a string with the users name
     * @param alias the users username that they selected
     */
    public User (int id, String name, String alias) {
        this.id = id;
        this.name = name;
        this.alias = alias;
        this.interests = new String[0];
    }

    public String getAlias() {
        return alias;
    }

    /**
     * OBtains attribute data about the User for printing to the console
     * @return a String containing all the information
     */
    public String getPrintData (){
        if(interests.length == 1)return "\t"+getId()+" - "+name+" ("+alias+")\n\tInterests: "+interests[0];
        if (interests.length > 0) return "\t"+getId()+" - "+name+" ("+alias+")\n\tInterests: "+String.join(", ", Arrays.copyOfRange(interests,0,interests.length-1))+" & "+interests[interests.length-1];
        else return "\t"+getId()+" - "+name+" ("+alias+")\n\tThis user has no interests";
    }

    /**
     * A getter for the id
     * @return an int of the id of the user
     */
    public int getId () {
        return id;
    }

    /**
     * A setter to mark as visited
     */
    public void setVisited () {
        this.visited = true;
    }
    /**
     * A setter to mark as NOT visited
     */
    public void unsetVisited () {
        this.visited = false;
    }

    /**
     * Used to check if a User has been visited or not
     * @return a boolean that is true if we have visit the user
     */
    public boolean isVisited () {
        return visited;
    }

    public String[] getInterests() {
        return interests;
    }




}
