package Business;

import Business.Table.Business;
import Business.Table.Table;
import Persistence.BusinessCSVDAO;
import Presentation.AdvertisingView;
import Presentation.MenuOptions;
import Presentation.Messages;
import Presentation.Visualisation.HashTables.HashHistogram;
import Presentation.Visualisation.HashTables.HashJFrame;

import java.io.FileNotFoundException;
public class UIControllerAdvertising {

    private Table table;
    private static AdvertisingView advertisingView;
    private BusinessCSVDAO businessCSVDAO;


    /**
     * The advertising menu
     */
    public void start() {
        businessCSVDAO = new BusinessCSVDAO();
        try {
            table = businessCSVDAO.readData("data/tablesXL.paed");

            advertisingView = new AdvertisingView();
            MenuOptions choice = MenuOptions.ADD_ALGORITHM;
            while (true) {
                choice = advertisingView.advertisingMenu();
                switch (choice) {
                    case ADD_BUSINESS -> addBusiness();
                    case REMOVE_BUSINESS -> removeBusiness();
                    case CHECK_BUSINESS -> checkBusiness();
                    case WEEKLY_HISTOGRAM -> weeklyHistogram();
                    case GO_BACK -> {
                        return;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * First menu option, to add a business
     */
    private void addBusiness() {
        String businessName = advertisingView.grabBusinessName();
        String dayInterested = advertisingView.grabDayInterested();
        int priceWilling = advertisingView.grabPrice();
        long startTime = System.nanoTime();
        Business newBusiness = new Business(businessName,dayInterested,priceWilling);
        table.insert(newBusiness);
        System.out.println("time taken: "+((System.nanoTime()-startTime)/1000000000 )+"s "+(((System.nanoTime()-startTime)/1000000 )%1000)+"ms "+(((System.nanoTime()-startTime)/1000 )%1000)+"us");
        advertisingView.showMessage(Messages.ADD_BUSINESS);
    }

    /**
     * Second menu option, to remove a business
     */
    private void removeBusiness() {
        String businessName = advertisingView.grabBusinessNameDelete();
        long startTime = System.nanoTime();
        table.remove(businessName);
        System.out.println("time taken: "+((System.nanoTime()-startTime)/1000000000 )+"s "+(((System.nanoTime()-startTime)/1000000 )%1000)+"ms "+(((System.nanoTime()-startTime)/1000 )%1000)+"us");
        advertisingView.showMessage(Messages.DELETE_BUSINESS);
    }



    /**
     * Third menu option, to show information relating to a business to the console
     */
    private void checkBusiness() {
        System.out.println();
        String businessName = advertisingView.grabBusinessNameQuery();
        long startTime = System.nanoTime();
        System.out.println(table.buildBusinessString(businessName));
        System.out.println("time taken: "+((System.nanoTime()-startTime)/1000000000 )+"s "+(((System.nanoTime()-startTime)/1000000 )%1000)+"ms "+(((System.nanoTime()-startTime)/1000 )%1000)+"us");
        System.out.println();
    }

    /**
     * The last option for the advertising menu
     */
    private void weeklyHistogram() {
        long startTime = System.nanoTime();
        HashJFrame hashJFrame = new HashJFrame();
        hashJFrame.add(new HashHistogram());
        hashJFrame.setVisible(true);
        System.out.println("time taken: "+((System.nanoTime()-startTime)/1000000000 )+"s "+(((System.nanoTime()-startTime)/1000000 )%1000)+"ms "+(((System.nanoTime()-startTime)/1000 )%1000)+"us");
        advertisingView.showMessage(Messages.GENERATE_HISTOGRAM);
    }

}
