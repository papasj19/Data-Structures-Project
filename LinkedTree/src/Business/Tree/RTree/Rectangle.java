package Business.Tree.RTree;

import Presentation.Visualisation.RTrees.RtreeVisualiser;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Random;

public class Rectangle extends Shape {

    protected Shape[] contents;
    protected int order;
    protected int currentSize = 0;


    public Rectangle(int order) {
        super(-1,-1,-1,-1);
        this.contents = new Shape[order + 1];
        this.order = order;
    }

    public void draw(Graphics g){
        Random rand = new Random();
        Graphics2D g2d = (Graphics2D) g;
        Rectangle2D rectangle2D = new Rectangle2D.Double((getLeft())*Circle.scale, (getBottom())*Circle.scale,(getRight()-getLeft())*Circle.scale,(getTop()-getBottom())*Circle.scale);

        g2d.setColor(new Color(rand.nextFloat(),rand.nextFloat(),rand.nextFloat()));
        g2d.setStroke(new BasicStroke((float) (0.08*Circle.scale)));
        //g2d.fill(rectangle2D);
        g2d.draw(rectangle2D);
    }

    public Shape getNode(int index) {
        return contents[index];
    }

    public void addNode(Circle newCirlce) {
        setBounds(newCirlce);
        // if on the leaf layer (where we have circiles) we can simply add them to the array
        if (!isRectangles()) {
            contents[currentSize] = newCirlce;
            currentSize++;
            return;
        }

        // finds the rectangle closest to the circle we want to add
        int closeIndex = -1;
        double distance = Integer.MAX_VALUE;
        int i = 0;
        for (Shape shape : contents) {
            if (shape instanceof Rectangle && shape != null) {
                if (((Rectangle) shape).distanceTo(newCirlce) < distance) {
                    distance = ((Rectangle) shape).distanceTo(newCirlce);
                    closeIndex = i;
                }
            }
            i++;
        }

        // calls the add function recursively on the closest rectangle.
        ((Rectangle) contents[closeIndex]).addNode(newCirlce);

        // if after inserting we see that the rectangle we inserted into is full > 3 nodes within, we then want to split
        if (((Rectangle) contents[closeIndex]).isFull()){
            Shape[] nodes = ((Rectangle) contents[closeIndex]).contents;
            ((Rectangle) contents[closeIndex]).clearContents();
            // create the new rectangle to insert into
            contents[currentSize] = new Rectangle(order);
            // gets nodes at the extreme points of the rectangle
            Shape top = Shape.getTopShape(nodes);
            Shape bottom = Shape.getBottomShape(nodes,top);


            if (top instanceof Rectangle && bottom instanceof Rectangle){
                // add the top rectangle to the previously full rectangle
                ((Rectangle) contents[closeIndex]).setBounds(top);
                ((Rectangle) contents[closeIndex]).contents[0] = top;
                ((Rectangle) contents[closeIndex]).currentSize++;
                //add the bottom rectangle to the newly created rectangle
                ((Rectangle) contents[currentSize]).setBounds(bottom);
                ((Rectangle) contents[currentSize]).contents[0] = bottom;
                ((Rectangle) contents[currentSize]).currentSize++;
            } else if (top instanceof Circle && bottom instanceof Circle){
                // re insert the top and bottom cirlces into the respective rectangles
                ((Rectangle) contents[closeIndex]).addNode((Circle) top);
                ((Rectangle) contents[currentSize]).addNode((Circle) bottom);
            }

            currentSize++;
            // re inserting the remaining nodes into either the old or new rectangle
            for (Shape shape : nodes) {
                if (shape != null && shape != top && shape != bottom) {
                    if (shape instanceof Circle) {
                        if (((Rectangle) contents[closeIndex]).distanceTo(shape) < ((Rectangle) contents[currentSize-1]).distanceTo(shape)){
                            ((Rectangle) contents[closeIndex]).addNode((Circle) shape);
                        }else{
                            ((Rectangle) contents[currentSize-1]).addNode((Circle) shape);
                        }


                    } else if (shape instanceof Rectangle) {
                        if (((Rectangle) contents[closeIndex]).distanceTo(shape) < ((Rectangle) contents[currentSize-1]).distanceTo(shape)){
                            if (((Rectangle) contents[closeIndex]).currentSize == 0){
                                System.out.println("stop");
                            }
                            addNode((Rectangle) shape,closeIndex);
                        }else{
                            if (((Rectangle) contents[currentSize-1]).currentSize == 0){
                                System.out.println("stop");
                            }
                            addNode((Rectangle) shape,currentSize-1);
                        }

                    }
                }
            }
        }
    }

    /**
     * Used to remove a circle from the rtree as desired from the second option in canvas menu
     * @param toBeDeleted the information about the circle we want to delete
     * @return a boolean if we are able to delete the circle
     */
    protected boolean removeNode(Circle toBeDeleted){
        for (int i = 0; i < contents.length; i++) {
            Shape s = contents[i];
            //first option, if our shape is a rectangle we will recursively call ourselves until we find a circle
            if (s instanceof Rectangle r) {
                //this is used to save time and thusly have a time more similar to log
                //we only check the right side if the left is true thusly saving effort
                if (r.isInside(toBeDeleted) && r.removeNode(toBeDeleted)) {
                    recalculateBounds(r);
                    if (r.currentSize == 0){
                        //this will offset the rest of the contents
                        for (int j = i; j < contents.length-1; j++) {
                            contents[j] = contents[j+1];
                        }
                        //adjust the size
                        currentSize--;
                        contents[currentSize] = null;
                        recalculateBounds(this);
                    }
                    return true;
                }
                //the second option where we check the circle
            } else if (s instanceof Circle c) {
                if (c.getyCoordinate() == toBeDeleted.getyCoordinate() && c.getxCoordinate() == toBeDeleted.getxCoordinate()) {
                    for (int j = i; j < contents.length-1; j++) {
                        contents[j] = contents[j+1];
                    }
                    currentSize--;
                    contents[currentSize] = null;
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Used to add a
     * @param rectangle
     * @param index
     */
    protected void addNode(Rectangle rectangle,int index) {

        setBounds(rectangle);
        ((Rectangle) contents[index]).setBounds(rectangle);
        ((Rectangle) contents[index]).contents[((Rectangle) contents[index]).currentSize] = rectangle;
        ((Rectangle) contents[index]).currentSize++;

        if (((Rectangle) contents[index]).isFull()){
            Shape[] nodes = ((Rectangle) contents[index]).contents;
            Shape top = Shape.getTopShape(nodes);
            Shape bottom = Shape.getBottomShape(nodes,top);

            ((Rectangle) contents[index]).clearContents();
            contents[currentSize] = new Rectangle(order);


            if (top instanceof Rectangle && bottom instanceof Rectangle){
                ((Rectangle) contents[index]).setBounds(top);
                ((Rectangle) contents[index]).contents[0] = top;
                ((Rectangle) contents[index]).currentSize++;
                ((Rectangle) contents[currentSize]).setBounds(bottom);
                ((Rectangle) contents[currentSize]).contents[0] = bottom;
                ((Rectangle) contents[currentSize]).currentSize++;
            } else if (top instanceof Circle && bottom instanceof Circle){
                ((Rectangle) contents[index]).addNode((Circle) top);
                ((Rectangle) contents[currentSize]).addNode((Circle) bottom);
            }

            currentSize++;
            for (Shape shape : nodes) {
                if (shape != null && shape != top && shape != bottom) {
                    if (shape instanceof Circle) {
                        if (((Rectangle) contents[index]).distanceTo(shape) < ((Rectangle) contents[currentSize-1]).distanceTo(shape)){
                            if (((Rectangle) contents[index]).currentSize == 0){
                                System.out.println("stop");
                            }
                            ((Rectangle) contents[index]).addNode((Circle) shape);
                        }else{
                            if (((Rectangle) contents[currentSize-1]).currentSize == 0){
                                System.out.println("stop");
                            }
                            ((Rectangle) contents[currentSize-1]).addNode((Circle) shape);
                        }


                    } else if (shape instanceof Rectangle) {
                        if (((Rectangle) contents[index]).distanceTo(shape) < ((Rectangle) contents[currentSize-1]).distanceTo(shape)){
                            if (((Rectangle) contents[index]).currentSize == 0){
                                System.out.println("stop");
                            }
                            addNode((Rectangle) shape,index);
                        }else{
                            if (((Rectangle) contents[currentSize-1]).currentSize == 0){
                                System.out.println("stop");
                            }
                            addNode((Rectangle) shape,currentSize-1);
                        }

                    }
                }
            }
        }
    }



    protected void setBounds(Shape shape) {
        if (getTop() < 0) {
            setTop(shape.getTop()) ;
            setBottom( shape.getBottom());

            setLeft(shape.getLeft());
            setRight( shape.getRight());
            return;
        }
        setTop(Math.max(getTop(), shape.getTop())) ;
        setBottom(Math.min(getBottom(), shape.getBottom()));

        setLeft(Math.min(getLeft(), shape.getLeft()));
        setRight(Math.max(getRight(), shape.getRight()));

    }

    // TODO - I think ori did
    public void recalculateBounds(Rectangle rectangle) {
        double minY = Double.MAX_VALUE;
        double minX = Double.MAX_VALUE;
        double maxY = Double.MIN_VALUE;
        double maxX = Double.MIN_VALUE;

        for (Shape s : rectangle.contents) {
            if (s == null)
                continue;

            if (s.getBottom() < minY)
                minY = s.getBottom();
            if (s.getRight() < minX)
                minX = s.getRight();
            if (s.getTop() > maxY)
                maxY = s.getTop();
            if (s.getLeft() > maxX)
                maxX = s.getLeft();
        }

        rectangle.setBottom(minY);
        rectangle.setRight(minX);
        rectangle.setTop(maxY);
        rectangle.setLeft(maxX);
    }

    protected void clearContents() {
        contents = new Shape[order + 1];
        currentSize = 0;
        setTop(-1);
        setRight(-1);
        setLeft(-1);
        setBottom(-1);
    }


    protected double distanceTo(Shape shape) {
        if (isInside(shape)) return 0;
        return Math.min(
                Math.min(Math.abs(shape.getLeft() - getRight()), Math.abs(shape.getRight() - getRight())),
                Math.min(Math.abs(shape.getTop() - getTop()), Math.abs(shape.getBottom() - getBottom()))
        );
    }

    protected boolean isInside(Shape shape) {
        if (shape.getRight() <= getRight() && shape.getLeft() >= getLeft()) {
            return shape.getTop() <= getTop() && shape.getBottom() >= getBottom();
        }
        return false;
    }


    public boolean isFull() {
        return currentSize == order + 1;
    }

    protected boolean isRectangles() {
        for (Shape shape : contents) {
            if (shape instanceof Rectangle) return true;
        }
        return false;
    }


    public void paint() {
        RtreeVisualiser.addShape(this);
        if (!isRectangles()){
            for (Shape shape: contents){
                if (shape != null){
                    RtreeVisualiser.addShape(shape);
                }
            }
        } else {
            for (Shape shape: contents){
                if (shape != null){
                    ((Rectangle)shape).paint();
                }
            }
        }
    }

    public void searchByArea(double top, double bottom, double left, double right){
        for (Shape shape: contents){
            if (shape instanceof Rectangle){
                if (!((shape.getLeft() > right) || (shape.getRight() < left) || (shape.getTop() < bottom) || (shape.getBottom() > top))){
                    ((Rectangle) shape).searchByArea(top,bottom,left,right);
                }
            } else if (shape instanceof Circle){
                if (((shape.getRight() <= right) && (shape.getLeft() >= left) && (shape.getTop() <= top) && (shape.getBottom() >= bottom))){
                    ((Circle)shape).printData();
                }
            }
        }
    }

    public void specialSearch(double x, double y, double radius, String color){
        double tolerance = 10; // Change tolerance depending on if you want to show more or less circles
        for (Shape shape: contents){
            if (shape instanceof Rectangle){
                if (shape.getLeft() <= (x+radius+tolerance)){
                    if (shape.getRight() >= (x-radius-tolerance)){
                        if (shape.getTop() >= (y-radius-tolerance)){
                            if (shape.getBottom() <= (y+radius+tolerance)){
                                ((Rectangle) shape).specialSearch(x,y,radius,color);
                            }
                        }
                    }
                }
                //if (!((shape.getLeft() > (x+radius+tolerance)) || (shape.getRight() < (x-radius-tolerance)) || (shape.getTop() < (y-radius-tolerance)) || (shape.getBottom() > (y+radius+tolerance)))){

                //}
            } else if (shape instanceof Circle){
                if (((((Circle)shape).getxCoordinate() <= x + tolerance) && (((Circle)shape).getxCoordinate() >= x - tolerance)) && ((((Circle)shape).getyCoordinate() <= y + tolerance) && (((Circle)shape).getyCoordinate() >= y - tolerance))){
                    if (similarColor(((Circle)shape).getColor(),color) && ((Circle)shape).getRadius() <= radius+tolerance){
                        ((Circle)shape).printData();
                    }

                }
            }
        }
    }

    public boolean similarColor(String color1, String color2){
        double toleranceColor = 500; // Change tolerance depending on if you want to show more or less circles
        Color c1 = Color.decode(color1);
        Color c2 = Color.decode(color2);
        double distance = (c1.getRed() - c2.getRed())*(c1.getRed() - c2.getRed()) + (c1.getGreen() - c2.getGreen())*(c1.getGreen() - c2.getGreen()) + (c1.getBlue() - c2.getBlue())*(c1.getBlue() - c2.getBlue());
        return distance < toleranceColor;
    }
}