package Business.Tree.AVL;

import java.sql.Time;
import java.util.ArrayList;

public class LinkedTree {
    private Algorithm root;


    public LinkedTree(Algorithm[] algorithms) {
        this.root = algorithms[0];
        long startTime = System.nanoTime();

        for (int i = 1; i < algorithms.length; i++) {
            root = insert(root, algorithms[i]);
        }
        System.out.println("loading file took "+((System.nanoTime()-startTime)/1000000000 )+"s "+(((System.nanoTime()-startTime)/1000000 )%1000)+"ms "+(((System.nanoTime()-startTime)/1000 )%1000)+"us");

    }

    public void insert(Algorithm temp) {
        long startTime = System.nanoTime();
        root = insert(root, temp);
        System.out.println("time taken: "+((System.nanoTime()-startTime)/1000000000 )+"s "+(((System.nanoTime()-startTime)/1000000 )%1000)+"ms "+(((System.nanoTime()-startTime)/1000 )%1000)+"us");
    }

    public void delete(int search) {
        long startTime = System.nanoTime();
        Time time = (root.getId() == search) ? root.getTimeStamp() : root.getIdTimestamp(search);
        if (time == null) {
            System.out.println("Algorithm with that ID does not exist");
            return;
        }
        root = deleteRecursive(root, time);
        System.out.println("time taken: "+((System.nanoTime()-startTime)/1000000000 )+"s "+(((System.nanoTime()-startTime)/1000000 )%1000)+"ms "+(((System.nanoTime()-startTime)/1000 )%1000)+"us");
        //recalculateBalanceFactor(root);
    }

    private Algorithm deleteRecursive(Algorithm ourRoot, Time search) {
        if (ourRoot == null) return null;

        if (ourRoot.leftOrRight(search) == 1) { // if the node we are looking for is on the right of our current node
            Algorithm newNode = deleteRecursive(ourRoot.getRightChild(), search);
            ourRoot.setRightChild(newNode);
            //rootNode.decrementBF();
        } else if (ourRoot.leftOrRight(search) == -1) { // if the node we are looking for is on the left of our current node
            Algorithm newNode = deleteRecursive(ourRoot.getLeftChild(), search);
            ourRoot.setLeftChild(newNode);
            //rootNode.incrementBF();
        } else {
            //System.out.println("DELETING NODE: " + rootNode.getName());
            if (!ourRoot.hasChildren())
                return null; // if the node we are deleting doesnt have any children, we can just replace it with null
            if (ourRoot.getLeftChild() == null)
                return new Algorithm(ourRoot.getRightChild()); // if our algorithm has only one child, we just replace it with that child
            if (ourRoot.getRightChild() == null)
                return new Algorithm(ourRoot.getLeftChild()); // if our algorithm has only one child, we just replace it with that child


            Algorithm newRoot = new Algorithm(ourRoot.getRightChild().getMinLeaf()); // finding the node that can replace the node we are deleting without disrupting the order

            newRoot.setRightChild(ourRoot.getRightChild()); // setting the children of the new node we will use to replace the old root
            newRoot.setLeftChild(ourRoot.getLeftChild());

            ourRoot = newRoot;
            ourRoot.setRightChild(deleteRecursive(ourRoot.getRightChild(), ourRoot.getTimeStamp())); // deleting the
        }

        ourRoot.setHeight(getNewHeight(ourRoot));

        /*int bf = ourRoot.getBF();
        if (bf > 1 && ourRoot.getLeftChild().getBF() >= 0) {
            return new Algorithm(rotate(Push.RIGHT, ourRoot));
        }
        if (bf < -1 && ourRoot.getLeftChild().getBF() <= 0) {
            return new Algorithm(rotate(Push.LEFT, ourRoot));
        }
        if (bf > 1 && ourRoot.getLeftChild().getBF() < 0) {
            ourRoot.setLeftChild(rotate(Push.LEFT, ourRoot.getLeftChild()));
            return new Algorithm(rotate(Push.RIGHT, ourRoot));
        }
        if (bf < -1 && ourRoot.getLeftChild().getBF() > 0) {
            ourRoot.setRightChild(rotate(Push.RIGHT, ourRoot.getRightChild()));
            return new Algorithm(rotate(Push.LEFT, ourRoot));
        }*/

        return new Algorithm(ourRoot);
    }


    public int getNewHeight(Algorithm node) {
        return 1 + Math.max((node.getLeftChild() == null) ? 0 : node.getLeftChild().getHeight(), (node.getRightChild() == null) ? 0 : node.getRightChild().getHeight());
    }

    public Algorithm rotate(Push direction, Algorithm node) {
        if (direction == Push.RIGHT) {
            Algorithm left = node.getLeftChild();
            Algorithm childRight = left.getRightChild();
            left.setRightChild(node);
            node.setLeftChild(childRight);
            node.setHeight(getNewHeight(node));
            left.setHeight(getNewHeight(left));
            return new Algorithm(left);
        } else {
            Algorithm right = node.getRightChild();
            Algorithm childLeft = right.getLeftChild();
            right.setLeftChild(node);
            node.setRightChild(childLeft);
            node.setHeight(getNewHeight(node));
            right.setHeight(getNewHeight(right));
            return new Algorithm(right);
        }
    }


    public void findAlgorithm(long time) {
        //root.findAlgorithm(new Time(time));
        searchExact(root, new Time(time));
    }

    private void searchExact(Algorithm root, Time time) {

        if (root == null || root.getTimeStamp().getTime() == time.getTime()) {
            System.out.println((root != null) ? "Algorithm was found... " + root.getPrintData() : "Algorithm not found");
            return;
        }
        if (root.getTimeStamp().before(time)) {
            searchExact(root.getRightChild(), time);
        } else {
            searchExact(root.getLeftChild(), time);
        }
    }

    public void listAlgorithm() {
        System.out.println();
        long startTime = System.nanoTime();
        root.inOrder();
        System.out.println("traversing tree took "+((System.nanoTime()-startTime)/1000000000 )+"s "+(((System.nanoTime()-startTime)/1000000 )%1000)+"ms "+(((System.nanoTime()-startTime)/1000 )%1000)+"us");
        System.out.println();
    }

    /**
     * Process to determine if its possible to add a child to a algorithm
     *
     * @param newNode the algorithm to be determined if its possible to insert
     */
    public Algorithm insert(Algorithm ourRoot, Algorithm newNode) {
        if (ourRoot == null) {
            return new Algorithm(newNode);
        } else if (ourRoot.getTimeStamp().after(newNode.getTimeStamp())) {
            ourRoot.setLeftChild(insert(ourRoot.getLeftChild(), newNode));

        } else if (ourRoot.getTimeStamp().before(newNode.getTimeStamp())) {
            ourRoot.setRightChild(insert(ourRoot.getRightChild(), newNode));

        } else return new Algorithm(ourRoot);

        ourRoot.setHeight(getNewHeight(ourRoot));

        return ourRoot;
        //return balance(ourRoot, newNode);
    }

    public ArrayList<Algorithm> findRange(long i, long l) {
        return root.getRange(new Time(i), new Time(l));
    }

    public void traverse() {
        root.postOrder();
    }

    public Algorithm balance(Algorithm ourRoot, Algorithm newNode) {
        if (ourRoot.getBF() <= 1 && ourRoot.getBF() >= -1) return ourRoot;
        else{
            if (ourRoot.getBF() > 1) {
                if (!newNode.getTimeStamp().before(ourRoot.getLeftChild().getTimeStamp())) {
                    ourRoot.setLeftChild(rotate(Push.LEFT, ourRoot.getLeftChild()));
                }
                return new Algorithm(rotate(Push.RIGHT, ourRoot));
            }else {
                if (!newNode.getTimeStamp().after(ourRoot.getRightChild().getTimeStamp())) {
                    ourRoot.setRightChild(rotate(Push.RIGHT, ourRoot.getRightChild()));
                }
                return new Algorithm(rotate(Push.LEFT, ourRoot));
            }
        }
    }

    public int getRootHeight() {
        return root.getHeight();
    }
}
