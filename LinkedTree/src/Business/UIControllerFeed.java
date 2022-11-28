package Business;

import Business.Tree.AVL.Algorithm;
import Business.Tree.AVL.LinkedTree;
import Persistence.AlgorithmCSVDAO;
import Presentation.FeedView;
import Presentation.MenuOptions;

import java.io.FileNotFoundException;
import java.sql.Time;
import java.util.ArrayList;

/**
 * A class used to visualize FeedView
 */

public class UIControllerFeed {

    private FeedView feedView;
    private LinkedTree tree;
    private AlgorithmCSVDAO dao;
    private final String fileName = "treeXS-sorted";

    /**
     * Constructor that reads the data from the file in path
     */
    public UIControllerFeed() {

        feedView = new FeedView();
        dao = new AlgorithmCSVDAO();
        try {
            tree = dao.readData("data/"+fileName+".paed");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        getResults();
        //tree.delete(new Time(495732814));
    }
    private void getResults(){
        long startTime = System.nanoTime();
        tree.insert(new Algorithm(-1,"Test Node","Testing","good",new Time(26423465)));
        long insertTime = System.nanoTime() - startTime;
        startTime = System.nanoTime();



        tree.traverse();
        long traversalTime = System.nanoTime() - startTime;
        startTime = System.nanoTime();

        tree.findAlgorithm(26423465L);
        long findTime = System.nanoTime() - startTime;
        startTime = System.nanoTime();

        tree.findRange(0,26423465L);
        long findRangeTime = System.nanoTime() - startTime;
        startTime = System.nanoTime();

        tree.delete(-1);
        long deleteTime = System.nanoTime() - startTime;

        dao.appendResults(fileName+" - BST",insertTime/1000,deleteTime/1000,findTime/1000,findRangeTime/1000,traversalTime/1000,tree.getRootHeight());

    }
    /**
     * Recieves the menu option from the feed menu in feedView
     *      We take this menu option and call the corresponding method
     */
    public void start(){
        while(true){
            MenuOptions choice = feedView.feedMenu();
            switch (choice) {
                case ADD_ALGORITHM -> addAlgorithm();
                case REMOVE_ALGORITHM -> removeAlgorithm();
                case LIST_ALGORITHMS -> listAlgorithms();
                case SEARCH_TIMESTAMP_EXACT -> searchTSExact();
                case SEARCH_TIMESTAMP_RANGE -> searchTSRange();
            }
            if(choice == MenuOptions.GO_BACK)
                break;
        }

    }

    /**
     * When the user selects option B from the menu
     */
    private void removeAlgorithm() {
        tree.delete(feedView.grabAlgorithmIdentifier());
    }

    /**
     * Used for option A from the menu
     *      -Uses some feedView methods to obtain information from the user
     *      -We then create an algorithm using this recieved information
     */
    private void addAlgorithm() {
        int id = feedView.grabAlgorithmIdentifier();
        String name = feedView.grabAlgorithmName();
        String language = feedView.grabAlgorithmLanguage();
        String cost = feedView.grabAlgorithmCost();
        String timeStamp = feedView.grabAlgorithmTS();

        long startTime = System.nanoTime();
        // i know this is wrong im just trying to visualize things
        Algorithm temp = new Algorithm(id,name,language,cost,new Time(Long.parseLong(timeStamp)));
        tree.insert(temp);
        System.out.println("time taken: "+((System.nanoTime()-startTime)/1000000000 )+"s "+(((System.nanoTime()-startTime)/1000000 )%1000)+"ms "+(((System.nanoTime()-startTime)/1000 )%1000)+"us");
         //Message.ALG_ADDED;
    }

    private void listAlgorithms(){
        long startTime = System.nanoTime();
        tree.listAlgorithm();
        System.out.println("time taken: "+((System.nanoTime()-startTime)/1000000000 )+"s "+(((System.nanoTime()-startTime)/1000000 )%1000)+"ms "+(((System.nanoTime()-startTime)/1000 )%1000)+"us");
    }

    /**
     * Using a method from feedView this will ask the user for a TS to search for and will
     * print this on the console
     */
    private void searchTSExact(){
        long startTime = System.nanoTime();
        String input = feedView.askAlgorithmTSSearchExact();
        System.out.println("time taken: "+((System.nanoTime()-startTime)/1000000000 )+"s "+(((System.nanoTime()-startTime)/1000000 )%1000)+"ms "+(((System.nanoTime()-startTime)/1000 )%1000)+"us");
        tree.findAlgorithm(Long.parseLong(input));
    }

    /**
     * Using methods from feedView, this will ask the user for a max and min TS to search for
     */
    private void searchTSRange(){
        String min = feedView.askAlgorithmTSSearchRangeMinimum();
        String max = feedView.askAlgorithmTSSearchRangeMaximum();
        long startTime = System.nanoTime();
        ArrayList<Algorithm> a = tree.findRange(Long.parseLong(min), Long.parseLong(max));
        System.out.println("time taken: "+((System.nanoTime()-startTime)/1000000000 )+"s "+(((System.nanoTime()-startTime)/1000000 )%1000)+"ms "+(((System.nanoTime()-startTime)/1000 )%1000)+"us");

        System.out.println("results found: "+a.size());
        for (Algorithm al : a){
            System.out.println(al.getPrintData());
        }
    }




}
