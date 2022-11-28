package Presentation;

/**
 * This is used for messages to be displayed to the console throughout the program
 * These messages comply with those given in the statement and will appear at the same time
 */

public enum Messages {
    /**
     * Message used from main menu when exiting (option 5)
     */
    EXIT_MM("Shutting down LinkedTree..."),

    /**
     * A sentence for displaying when the user selects the first option in the followers submenu
     */
    USER_FOLLOW_MOST("The user who follows the most accounts is: "),

    /**
     * A sentance used in the followers submenu when displaying what accounts the main account follows
     */
    USER_FOLLOWERS("Here are their followed accounts: "),

    /**
     * A sentence used when displaying the possible related accounts
     */
    RECOMMENDED_ACCOUNTS("You might be interested in following these accounts: "),

    /**
     * Displayed when the user selects the drama option in the followers subment
     */
    RUN_DRAMA("Processing the drama dataset..."),

    /**
     * used to check the interactions between users within our program
     */
    CATCH_DISCOURSE("You should catch up with the discourse in the following order: "),

    /**
     *
     * Used for finding the best order between different users
     */
    OPTIMAL_CHAIN("Finding the optimal contact chain..."),

    /**
     * Used for canvas menu option E
     *      -special search a circle
     */
    NEARBY_CIRCLE("The following circles are nearby and look similar: "),

    /**
     * Used for canvas menu option C
     *      -visualising a circle
     */
    VISUAL_CIRCLE("Generating the canvas visualization..."),

    /**
     * Used for canvas menu option B
     *      -deleting a circle
     */
    DELETE_CIRCLE("The circle was correctly removed from the canvas."),

    /**
     * Used for canvas menu option A
     *      -adding a circle
     */
    ADD_CIRCLE("The circle was correctly added to the canvas"),

    ALG_ADDED("The algorithm was correctly added to the feed. "),

    /**
     * Used for advertising menu option D
     */
    GENERATE_HISTOGRAM("Generating histogram..."),

    /**
     * Used for advertising menu option B
     */
    DELETE_BUSINESS("The business has been correctly deleted from the advertising management system."),

    /**
     * Used for advertising menu option A
     */
    ADD_BUSINESS("The business has been correctly added to the advertising management system");

    private final String message;

    Messages (String message) {
        this.message = message;
    }

    @Override
    public String toString () {
        return message;
    }
}
