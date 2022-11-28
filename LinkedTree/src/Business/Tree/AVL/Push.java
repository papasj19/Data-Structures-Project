package Business.Tree.AVL;

/**
 * A class used to represent the direction the user is moving through the tree
 */
public enum Push {
    //The two possible directions to move through the tree
    LEFT,
    RIGHT;

    /**
     * A helpful auxiliary method used to obtain the opposite direction
     * @return The opposite direction than the current
     */
    public Push getOpposite(){
        if (this == LEFT)
            return RIGHT;
        return LEFT;
    }
}
