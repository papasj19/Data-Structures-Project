package Business.Tree.RTree;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Circle extends Shape {
    public static double scale = 0.01;
    private int count;
    private double xCoordinate;
    private double yCoordinate;
    private double radius;
    private String color;

    public Circle(double xCoordinate, double yCoordinate, double radius, String color) {
        super(yCoordinate-radius,xCoordinate+radius,xCoordinate-radius,yCoordinate+radius);
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.radius = radius;
        this.color = color;
    }
    public void setCount(int i){
        count = i;
    }

    /**
     * Used when representing our rtree visually
     * @param g
     */
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Ellipse2D.Double circle = new Ellipse2D.Double((getLeft())*scale, (getBottom())*scale, radius*2*scale, radius*2*scale);

        g2d.setColor(Color.decode(color));
        g2d.setStroke(new BasicStroke((float) (0.12*scale)));
        //g2d.fill(circle);
        g2d.draw(circle);
        //g2d.setColor(Color.black);
        //g2d.drawString(String.valueOf(count),((float) (xCoordinate*scale)), (float) ((float)yCoordinate*scale));

    }

    /**
     * Builds a string containing information about the circle to be displayed for the searching options ofthe canvas menu
     *
     * @return a string of the information of the particular circle
     */
    public String buildCircleString() {
        String circleInfo = color + " (" + xCoordinate + ", " + yCoordinate + ") r=" + radius;
        return circleInfo;
    }

    // getter functions for centre, radius and a bounding rectangle
    public double getxCoordinate() {
        return xCoordinate;
    }

    public double getyCoordinate() {
        return yCoordinate;
    }

    public double getRadius() {
        return radius;
    }

    public String getColor() {
        return color;
    }

    public static Circle getTopRightCicle(Circle[] circles) {
        // currently does not include radius
        Circle max = new Circle(Integer.MIN_VALUE, Integer.MIN_VALUE, 0, null);
        for (Circle test : circles) {
            if (test != null) {
                if (test.getxCoordinate() > max.getxCoordinate()) {
                    max.xCoordinate = test.getxCoordinate();
                }
                if (test.getyCoordinate() > max.getyCoordinate()) {
                    max.yCoordinate = test.getyCoordinate();
                }
            }
        }
        return max;
    }

    public static Circle getBottomLeftCicle(Circle[] circles) {
        Circle min = new Circle(Integer.MAX_VALUE, Integer.MAX_VALUE, 0, null);
        for (Circle test : circles) {
            if (test != null) {
                if (test.getyCoordinate() < min.getyCoordinate()) {
                    min = test;
                }
                if (test.getxCoordinate() < min.getxCoordinate()) {
                    min = test;
                }
            }

        }
        return min;
    }

    /**
     * Prints the data relating to a circle
     */
    public void printData() {
        System.out.println(color+" ("+xCoordinate+", "+yCoordinate+") r = "+radius);
    }

    public void setxCoordinate(double xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public void setyCoordinate(double yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
