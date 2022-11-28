package Persistence;

import Business.Tree.AVL.Algorithm;
import Business.Tree.AVL.LinkedTree;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Time;
import java.util.Scanner;

/**
 * The DAO classed used to obtain information about algorithms from the file
 */

public class AlgorithmCSVDAO {

    /**
     * Used for receiving data about the algorithms from the file
     * @param path a string of the path to the file
     * @return a LinkedTree containing the information from the file
     * @throws FileNotFoundException if the path to the file does not work
     */
    public LinkedTree readData(String path) throws FileNotFoundException {
        File file = new File(path);
        Scanner fileReader = new Scanner(file);
        String a = fileReader.nextLine();
        int numAlgorithms = 0;
        for (char c:a.toCharArray()){
            if (c >60) continue;
            numAlgorithms = numAlgorithms*10 + (c-'0');
        }

        Algorithm[] algorithms = new Algorithm[numAlgorithms];
        Time high = new Time(Long.MIN_VALUE);
        Time low = new Time(Long.MAX_VALUE);
        for (int i = 0; i < numAlgorithms; i++){

            String[] element =fileReader.nextLine().split(";");

            Time tmp = new Time(Long.parseLong(element[4]));
            if (tmp.after(high)) high = tmp;
            if (tmp.before(low)) low = tmp;


            algorithms[i] = new Algorithm(Integer.parseInt(element[0]),element[1],element[2],element[3],new Time(Long.parseLong(element[4])));

        }


        return new LinkedTree(algorithms);
    }

    public void appendResults(String fileName, long insertTime, long deleteTime, long findTime, long findRangeTime, long traversalTime) {
        try {
            FileWriter pw = new FileWriter("results/Trees.csv",true);
            pw.append(fileName+","+insertTime+","+deleteTime+","+findTime+","+findRangeTime+","+traversalTime+"\n");
            pw.close();
        } catch (IOException ignore) {
        }
    }
    public void appendResults(String fileName, long insertTime, long deleteTime, long findTime, long findRangeTime, long traversalTime,int height) {
        try {
            FileWriter pw = new FileWriter("results/Trees.csv",true);
            pw.append(fileName+","+insertTime+","+deleteTime+","+findTime+","+findRangeTime+","+traversalTime+","+height+"\n");
            pw.close();
        } catch (IOException ignore) {
        }
    }
}
