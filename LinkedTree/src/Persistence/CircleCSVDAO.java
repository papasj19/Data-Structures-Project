package Persistence;

import Business.Tree.RTree.Circle;
import Business.Tree.RTree.R_Tree;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class will create an rtree from the given paed file relating/containing information about circles
 */

public class CircleCSVDAO {

    public R_Tree readData(String path) throws FileNotFoundException{
        File file = new File(path);
        Scanner fileReader = new Scanner(file);

        int numCircles = Integer.valueOf(fileReader.nextLine());
        Circle[] circles = new Circle[numCircles];
        String data;
        String[] split;

        for (int i = 0; i < numCircles; i++) {
            data = fileReader.nextLine();
            split = data.split(";");
            circles[i] = new Circle(Double.valueOf(split[0]), Double.valueOf(split[1]), Double.valueOf(split[2]), split[3]);
            circles[i].setCount(i+1);
        }

        return new R_Tree(circles);

    }
}
