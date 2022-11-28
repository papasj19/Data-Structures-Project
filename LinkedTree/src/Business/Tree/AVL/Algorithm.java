package Business.Tree.AVL;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Algorithm {
    private final int id;
    private boolean visited;
    private String name;
    private String language;
    private String cost;
    private Time timeStamp;
    private Algorithm[] children = new Algorithm[2];
    private int height;

    /**
     * Used for creating an algorithm from information the user has given
     * @param id the id provided
     * @param name the name provided
     * @param language the language
     * @param cost the cost
     * @param timeStamp the TS
     */
    public Algorithm(int id, String name, String language, String cost, Time timeStamp) {
        this.id = id;
        this.name = name;
        this.language = language;
        this.cost = cost;
        this.timeStamp = timeStamp;
        this.height = 1;
    }

    /**
     * Used for duplicating an algorithm recived in the parameters
     * @param clone an algrithm used to copy information
     */
    public Algorithm(Algorithm clone) {
        this.id = clone.id;
        this.name = clone.name;
        this.language = clone.language;
        this.cost = clone.cost;
        this.timeStamp = clone.timeStamp;
        this.children[0] = clone.children[0];
        this.children[1] = clone.children[1];
        this.height = clone.height;
    }




    /**
     * Takes the child given in the parameters and places it on the side given
     * in the parameters
     * @param child the child to be added
     * @param side the side to add the child
     */
    public void setChild(Algorithm child, Push side) {
        this.children[side.ordinal()] = child;
    }

    public void setVisited () {
        this.visited = true;
    }

    public void unsetVisited () {
        this.visited = false;
    }

    public boolean isVisited () {
        return visited;
    }

    /**
     * Aux method used for printing data related to an algorithm
     * @return a string containing the information to an algorithm
     */
    public String getPrintData() {
        return name+": "+language+" "+cost;
    }

    /**
     * Developed in conjuction with the psuedo code provided in the estudy
     *  This process has a tendency to grab the first node then move leftward through the tree to the left child first
     */
    public void preOrder(){
        //show or visualization goes here
        for(Algorithm child : children)
            child.preOrder();

    }

    /**
     * Developed in conjuction with the psuedo code provided in the estudy
     * This process has a tendency to move leftward first through the tree extending as far down as possible
     */
    public void postOrder(){
        if(children[1] != null){
            children[1].postOrder();
        }
        if(children[0] != null){
            children[0].postOrder();
        }//show or visulaize here if needed
    }

    /**
     * Building on the shortened printData this obtains all the attributes related to a algorithm
     * and arranges them in a proper order for printing
     * @return a String with all the information
     */
    private String getFullPrintData() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return name+": "+language+" "+cost+" - "+timeStamp.toString()+" "+formatter.format(timeStamp);
    }

    /**
     * Moves horizontally through the three
     */
    public void inOrder(){
        if(this.children[1] != null){
            children[1].inOrder();
        }

        System.out.println(getFullPrintData());

        if(this.children[0] != null){
            children[0].inOrder();
        }
    }

    /**
     * Level traversal
     * similar to BFS by grouping similar depth nodes together
     * @param child
     */
    public void levels(Algorithm child){
        Queue<Algorithm> myQ = new LinkedList<>();
        myQ.add(child);
        while(!myQ.isEmpty()){
            Algorithm temp = myQ.poll();
            //show new here
            for(Algorithm c: temp.children){
                myQ.add(c);
            }
        }
    }

    /**
     *  A getter for the time stamp
     * @return the timestamp of the algorithm
     */
    public Time getTimeStamp() {
        return timeStamp;
    }

    /**
     * A getter for the balance factor
     * @return an int of the balance factor
     */
    public int getBF() {
        if (getRightChild() == null && getLeftChild() == null) return 0;
        else if (getRightChild() == null) return getLeftChild().getHeight();
        else if (getLeftChild() == null) return - getRightChild().getHeight();
        else return getLeftChild().getHeight() - getRightChild().getHeight();
    }

    /**
     * A method used to determine if an algorithm has children
     * @return a boolean that returns true if an algorithm has children
     */
    public boolean hasChildren(){
        return children[0] != null && children[1] != null;
    }


    /**
     * Obtains the right child of an algorithm
     * @return an Algorithm of the right childe
     */
    public Algorithm getRightChild(){
        return children[1];
    }
    /**
     * Obtains the left  child of an algorithm
     * @return an Algorithm of the  left children
     */
    public Algorithm getLeftChild(){
        return children[0];
    }

    /**
     * Updates the right child to the one recieved in the parameters
     * @param child the algorithm to be inserted
     */
    public void setRightChild(Algorithm child){
        children[1] = child;
    }

    /**
     * Updates the left child to the one recieved in the parameters
     * @param child the algorithm to be inserted
     */
    public void setLeftChild(Algorithm child){
        children[0] = child;
    }

    /**
     * finds the smallest leaf of a algorithm (left most leaf)
     * @return the lowest possible algorithm in that direction
     */
    public Algorithm getMinLeaf(){
        Algorithm min = this;
        while (min.children[0] != null) min = min.children[0];
        return min;
    }

    /**
     * Only tells us if there is a timestamp before or after the parameter
     * @param search the desired time to search for
     * @return 1 if right and -1 if left and 0 if match
     */
    public int leftOrRight(Time search) {
        if (timeStamp.before(search)) return 1;
        if (timeStamp.after(search)) return -1;
        return 0;
    }


    /**
     * Obtains the children of the algorithm
     * @return an array of the children of the algorithm
     */
    public Algorithm[] getChildren() {
        return children;
    }

    public void findAlgorithm (Time timeStamp) {
        int dir = leftOrRight(timeStamp);
        if (dir == 0) {
            System.out.println("Algorithm was found... " +getPrintData());
        }else if (dir == 1) {
            if (children[1] == null)  {
                System.out.println("Algorithm was not found.");
                return;
            }
            children[1].findAlgorithm(timeStamp);
        } else {
            if (children[0] == null) {
                System.out.println("Algorithm was not found.");
                return;
            }
            children[0].findAlgorithm(timeStamp);
        }

    }

    public Time getIdTimestamp (int ID){
        Time time = null;
        if(children[1] != null){
            if (children[1].id == ID) return children[1].timeStamp;
            time = children[1].getIdTimestamp(ID);
        }
        if(children[0] != null){
            if (children[0].id == ID) return children[0].timeStamp;
            if (time == null) time = children[0].getIdTimestamp(ID);
        }
        if (children[0] == null && children[1] == null) return null;

        return time;
    }

    public int getId() {
        return id;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public ArrayList<Algorithm> getRange (Time lower, Time higher){
        ArrayList<Algorithm> valid = new ArrayList<>();
        if (!this.timeStamp.after(higher) && !this.timeStamp.before(lower)){
            valid.add(this);

            if (this.children[0] != null && !this.children[0].timeStamp.after(higher) && !this.children[0].timeStamp.before(lower)) {
                for (Algorithm a : this.children[0].getRange(lower,higher)){
                    valid.add(a);
                }

            }

            if (this.children[1] != null && !this.children[1].timeStamp.after(higher) && !this.children[1].timeStamp.before(lower)) {
                for (Algorithm a : this.children[1].getRange(lower,higher)){
                    valid.add(a);
                }

            }
        } else {
            if (this.children[0] != null) {
                for (Algorithm a : this.children[0].getRange(lower,higher)){
                    valid.add(a);
                }

            }

            if (this.children[1] != null) {
                for (Algorithm a : this.children[1].getRange(lower,higher)){
                    valid.add(a);
                }

            }
        }
        return valid;
    }


}
