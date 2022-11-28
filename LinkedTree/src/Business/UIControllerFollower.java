package Business;

import Business.Graph.AdjacencyList;
import Business.Graph.User;
import Persistence.FollowerCSVDAO;
import Presentation.FollowersView;
import Presentation.MenuOptions;
import Presentation.Messages;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

/**
 * A class used to display the @FollowersView
 */

public class UIControllerFollower {

    private AdjacencyList adjacencyList;
    private static FollowersView followersView;
    private FollowerCSVDAO dao;
    private String filename = "graphXS";

    /**
     * Constructor to obtain information from the file pertaining to followers
     */
    public UIControllerFollower () {
        dao = new FollowerCSVDAO();
        try {
            adjacencyList = dao.readData("data/"+filename+".paed");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        followersView = new FollowersView();
        //getResults();
    }

    /**
     * The main menu that recieves a menuoption from the followers view
     * and using this, calls the corresponding method to do what the user desires
     */
    public void start(){
        MenuOptions choice = MenuOptions.EXPLORE_NETWORK;
        while (true){
            choice = followersView.followersMenu();
            switch (choice){
                case EXPLORE_NETWORK -> explrNtwk();
                case SUGGEST_USERS -> suggUsers();
                case CONTXT_DRAM -> contextDram();
                case NETWORKING -> networking();
                case GO_BACK -> {
                    return;
                }
            }
        }

    }

    private void getResults(){
        long startTime = System.nanoTime();
        //adjacencyList.dijkstra(adjacencyList.getUser(23),);
        long dijkstras = System.nanoTime() - startTime;
        startTime = System.nanoTime();

        adjacencyList.BFS(adjacencyList.getUser(23));
        long BFS = System.nanoTime() - startTime;
        startTime = System.nanoTime();

        adjacencyList.DFS(adjacencyList.getUser(23));
        long DFS = System.nanoTime() - startTime;
        startTime = System.nanoTime();

        adjacencyList.topoSort();
        long topoSort = System.nanoTime() - startTime;
        startTime = System.nanoTime();

        dao.appendResults(filename,dijkstras/1000,BFS/1000,DFS/1000,topoSort/1000);

    }

    /**
     * Using for first option exploring network
     *      -only prints to the console
     */
    public void explrNtwk(){
        System.out.println("The user who follows the most accounts is: ");
        System.out.println(adjacencyList.getMostFollowed().getPrintData());
        long startTime = System.nanoTime();

        ArrayList<Integer>[] results = adjacencyList.BFS(adjacencyList.getMostFollowed());
        String a = ("time taken: "+((System.nanoTime()-startTime)/1000000000 )+"s "+(((System.nanoTime()-startTime)/1000000 )%1000)+"ms "+(((System.nanoTime()-startTime)/1000 )%1000)+"us");
        for (int i =0; i <2;i++){System.out.println("\nHere are their followed accounts: ");
            for (int id: results[i]){
                System.out.println(adjacencyList.getUser(id).getPrintData());
            }
        }
        System.out.println(a);
    }

    /**
     * Used for second option
     */
    public void suggUsers(){
        int idGiven = followersView.grabIdentifier();

        User temp = adjacencyList.getUser(idGiven);
        System.out.println("You may be interested in the following accounts: \n");
        int actualRec = 0;
        for (String string: adjacencyList.recommendUsers(temp)){
            if (string != null){
                System.out.println(string+"\n");
                actualRec++;
            }

        }
        if (actualRec == 0) System.out.println("No recommendations found that fit any criteria.");
        adjacencyList.setUnvisited();
    }

    /**
     * Used for third option, contextualize drama
     */
    public void contextDram(){
        boolean first = true;
        long startTime = System.nanoTime();
        LinkedList<User> queue = adjacencyList.topoSort();
        System.out.println("time taken: "+((System.nanoTime()-startTime)/1000000000 )+"s "+(((System.nanoTime()-startTime)/1000000 )%1000)+"ms "+(((System.nanoTime()-startTime)/1000 )%1000)+"us");
        for (User n : queue){
            System.out.println(((first)?"":"↓\n")+n.getId() + " - "+ n.getName()+" ("+n.getAlias()+")");
            first = false;
        }

    }

    /**
     * Used for the fourth option, networking
     */
    public void networking(){
        int startID = followersView.grabIdentifier();
        int endID = followersView.grabOtherIdentifier();
        long startTime = System.nanoTime();
        if (adjacencyList.nodeExists(startID) && adjacencyList.nodeExists(endID)){
            followersView.showMessage(Messages.OPTIMAL_CHAIN);
            int[] dijkstraPath = adjacencyList.dijkstra(adjacencyList.getUser(startID),adjacencyList.getUser(endID));
            dijkstraPath = AdjacencyList.undoPath(dijkstraPath,startID,endID);
            boolean first = true;
            if (dijkstraPath.length == 0) System.out.println("no path exists");
            else{
                for (int id : dijkstraPath){
                    System.out.print(((first)?"":" -> ")+id+" ("+adjacencyList.getUser(id).getName()+")");
                    first = false;
                }
                System.out.println();
            }

        }
        System.out.println("networking took "+((System.nanoTime()-startTime)/1000000000 )+"s "+(((System.nanoTime()-startTime)/1000000 )%1000)+"ms "+(((System.nanoTime()-startTime)/1000 )%1000)+"us");

    }


}