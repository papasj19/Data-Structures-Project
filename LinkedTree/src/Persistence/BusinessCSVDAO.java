package Persistence;

import Business.Table.Business;
import Business.Table.Table;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * The DAO classed used to obtain information about business' from the file
 */

public class BusinessCSVDAO {

    /**
     * Used for receiving data about the business from the file
     * @param path a string of the path to the file
     * @return a table containing the information from the file
     * @throws FileNotFoundException if the path to the file does not work
     */
    public Table readData(String path) throws FileNotFoundException{
        File file = new File(path);
        Scanner fileReader = new Scanner(file);

        int numBusiness = Integer.valueOf(fileReader.nextLine());
        Business[] businesses = new Business[numBusiness];
        String data;
        String[] split;
        for (int i = 0; i < numBusiness; i++) {
            data = fileReader.nextLine();
            split = data.split(";");
            businesses[i] = new Business(split[0],split[1],Integer.valueOf(split[2]));
        }
        return new Table(numBusiness,businesses);
    }
}
