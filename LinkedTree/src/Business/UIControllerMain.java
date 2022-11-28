package Business;

import Business.UIControllerFollower;
import Presentation.MainView;
import Presentation.MenuOptions;
import Presentation.Messages;

import static Presentation.MenuOptions.EXIT;
import static Presentation.MenuOptions.FEED;

/**
 * A UIController for the main used to implement the main view
 */

public class UIControllerMain {
    private MainView mainView;
    private UIControllerFollower follower;
    private UIControllerCanvas canvas;
    private UIControllerFeed feed;
    private UIControllerAdvertising advertising;
    /**
     * Constructor that calls to mainView
     */
    public UIControllerMain() {mainView = new MainView();}


    /**
     * Recieve the choice of the user from mainview
     * Using menuoptions we decide where the user will proceed
     */
    public void start(){
        MenuOptions choice = FEED;
        while (choice != EXIT){
            choice = mainView.mainMenu();
            switch (choice){
                case FOLLOWERS -> follower();
                case FEED -> feed();
                case CANVAS -> canvas();
                case ADVERTISING -> advertising();
                case EXIT -> exitMM();
            }
        }

    }

    public void advertising(){
        advertising = new UIControllerAdvertising();
        advertising.start();
    }


    /**\
     * If the user selects the third option of the menu canvas
     */
    public void canvas() {
        canvas = new UIControllerCanvas();
        canvas.start();
    }

    /**j
     * If the user selects the first option of followers the UIController related to follower is called
     */
    public void follower(){
        //mainView.showMessage(mess);
        follower = new UIControllerFollower();
        follower.start();
    }

    /**
     * If the user selects the second option of trees the UIController related to trees is called
     */
    public void feed(){
        //mainView.showMessage(mess);
        feed = new UIControllerFeed();
        feed.start();
    }

    /**
     * Used when user wants to exit the main menu
     */
    public void exitMM(){
        mainView.showMessage(Messages.EXIT_MM);
    }
}
