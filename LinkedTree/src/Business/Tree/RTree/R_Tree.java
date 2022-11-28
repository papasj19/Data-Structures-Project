package Business.Tree.RTree;


import Presentation.Visualisation.RTrees.RTreeJFrame;
import Presentation.Visualisation.RTrees.RtreeVisualiser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class R_Tree {

    //in class it was 3?? (i most likely move)
    private int order = 3;
    private Rectangle root;
    private RTreeJFrame jFrame;
    private double furthest = 0;
    public R_Tree(Circle[] circles) {
        root = new Rectangle(this.order);

        for (Circle circle: circles){
            insert(circle);
           
        }
        System.out.println();
    }

    public boolean delete(float x, float y){
        boolean removeNode =  root.removeNode(new Circle(x, y, 0, null));
        root.recalculateBounds(root);
        double a = Math.max(root.getTop(),root.getRight());
        Circle.scale = 656/a;
        return removeNode;
    }

    public void insert(Circle toBeInserted){
        root.addNode(toBeInserted);
        furthest = Math.max(furthest,Math.max(toBeInserted.getxCoordinate()+toBeInserted.getRadius(),toBeInserted.getyCoordinate()+toBeInserted.getRadius()));
        Circle.scale = 656/furthest;


        if (root.isFull()){
            Shape[] nodes = root.contents;
            root.clearContents();
            root.contents[0] = new Rectangle(order);
            root.contents[1] = new Rectangle(order);
            Shape top = Shape.getTopShape(nodes);
            Shape bottom = Shape.getBottomShape(nodes,top);
            if (top instanceof Rectangle && bottom instanceof Rectangle){
                root.setBounds(top);
                root.setBounds(bottom);
                ((Rectangle) root.contents[0]).setBounds(top);
                ((Rectangle) root.contents[0]).contents[0] = top;
                ((Rectangle) root.contents[0]).currentSize++;
                ((Rectangle) root.contents[1]).setBounds(bottom);
                ((Rectangle) root.contents[1]).contents[0] = bottom;
                ((Rectangle) root.contents[1]).currentSize++;
            } else if (top instanceof Circle && bottom instanceof Circle){
                ((Rectangle) root.contents[0]).addNode((Circle) top);
                ((Rectangle) root.contents[1]).addNode((Circle) bottom);
            } else {
                System.out.println("WHAT");
            }

            root.currentSize++;
            root.currentSize++;
            for (Shape shape : nodes) {
                if (shape != null && shape != top && shape != bottom) {

                    if (shape instanceof Circle) {
                        root.addNode((Circle) shape);


                    } else {
                        if (((Rectangle)root.contents[0]).distanceTo(shape) < ((Rectangle)root.contents[1]).distanceTo(shape)){
                            if (((Rectangle) root.contents[0]).currentSize == 0){
                                System.out.println("stop");
                            }
                            root.addNode((Rectangle) shape,0);
                        }else{
                            if (((Rectangle) root.contents[1]).currentSize == 0){
                                System.out.println("stop");
                            }
                            root.addNode((Rectangle) shape,1);
                        }
                    }
                }
            }

        }
    }

    /**
     * Used to calculate the distance between two points
     * @param origin the 1st point
     * @param destination the 2nd point
     * @return a double of the distance between the points
     */
    public double calcDistBtwnCircle(Circle origin, Circle destination) {
        double ac = Math.abs(destination.getyCoordinate() - origin.getyCoordinate());
        double cb = Math.abs(destination.getxCoordinate() - origin.getxCoordinate());
        return Math.hypot(ac, cb);
    }

    /**
     * Used to calculate the midpoint between two points
     * @param origin the 1st point
     * @param destination the 2nd point
     * @return a new point containing the location of the midpoint
     */
    public Circle calcMidpoint(Circle origin, Circle destination){
        double x = ((origin.getxCoordinate() - destination.getxCoordinate()) / 2);
        double y = ((origin.getyCoordinate() - destination.getyCoordinate()) / 2);
        Circle newPoint = new Circle(x,y,0,null);
        return newPoint;
    }


    public void paint () {
        RtreeVisualiser.clearShapes();
        jFrame = new RTreeJFrame();
        jFrame.add(new RtreeVisualiser());
        jFrame.setVisible(true);
        root.paint();
        root = root;
    }

    public void searchArea(float top, float bottom, float left, float right) {
        System.out.println();
        root.searchByArea(top,bottom,left,right);
        System.out.println();
    }

    public void specialSearch(float x,float y,float radius, String color){
        System.out.println();
        root.specialSearch(x,y,radius,color);
        System.out.println();
    }
}
