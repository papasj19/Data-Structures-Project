package Persistence;

import Business.Graph.AdjacencyList;
import Business.Graph.Edge;
import Business.Graph.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Time;
import java.util.Scanner;

/**
 * A class used to implement a graph given the dataset from the .paed file
 */

public class FollowerCSVDAO {

    public AdjacencyList readData(String path) throws FileNotFoundException {
        File file = new File(path);
        Scanner fileReader = new Scanner(file);

        int numUsers = Integer.valueOf(fileReader.nextLine());
        int nodesIter = 0;
        User[] nodes = new User[numUsers];
        String data;
        String[] split;
        boolean nodesL = true;
        int maxID = 0;
        for (int i = 0; i < numUsers; i++) {


            data = fileReader.nextLine();
            split = data.split(";");
            if (Integer.valueOf(split[0]) > maxID) maxID = Integer.valueOf(split[0]);
            if (split.length == 3) {
                nodes[nodesIter] = new User(Integer.valueOf(split[0]), split[1], split[2]);
            } else {
                nodes[nodesIter] = new User(Integer.valueOf(split[0]), split[1], split[2], split[3].split(","));
            }
            nodesIter++;
        }


        int numEdges = Integer.valueOf(fileReader.nextLine());
        int edgesIter = 0;
        Edge[] edges = new Edge[numEdges];
        boolean edgesL = true;
        for (int i = 0; i < numEdges; i++) {
            data = fileReader.nextLine();
            split = data.split(";");

            edges[edgesIter] = new Edge(Integer.valueOf(split[0]), Integer.valueOf(split[1]), new Time(Integer.valueOf(split[2])), Integer.valueOf(split[3]));
            edgesIter++;

        }


        return new AdjacencyList(nodes,edges,maxID);
    }

    public void appendResults(String fileName, long dijkstras, long BFS, long DFS, long toposort) {
        try {
            FileWriter pw = new FileWriter("results/Graphs.csv",true);
            pw.append(fileName+","+dijkstras+","+BFS+","+DFS+","+toposort+"\n");
            pw.close();
        } catch (IOException ignore) {
        }
    }
}