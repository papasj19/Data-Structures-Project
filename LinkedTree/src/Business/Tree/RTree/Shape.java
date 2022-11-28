package Business.Tree.RTree;

/**
 * This class utilizes inheritance because the rtree concept relies on two types of shape: circle and rectangle
 *
 * These attributes may not help the circle or rectangle directly but because the shapes will contain the same parent class
 * this will allow us to contain them in the same data structures without problems which really comes in handy.
 *
 * Rectangles follow kind of the same concept as this class because they become defined by their corners that compose them.
 * Separately, the shape has four attributes regarding its location.
 */

public class Shape {
    private double bottom;
    private double right;
    private double left;
    private double top;

    public Shape(double bottom, double right, double left, double top) {
        this.bottom = bottom;
        this.right = right;
        this.left = left;
        this.top = top;
    }

    public double getBottom() {
        return bottom;
    }

    public void setBottom(double bottom) {
        this.bottom = bottom;
    }

    public double getRight() {
        return right;
    }

    public void setRight(double right) {
        this.right = right;
    }

    public double getLeft() {
        return left;
    }

    public void setLeft(double left) {
        this.left = left;
    }

    public double getTop() {
        return top;
    }

    public void setTop(double top) {
        this.top = top;
    }

    public static Shape getBottomShape(Shape[] nodes) {
        double tmp = Double.MAX_VALUE;
        Shape retval = null;
        for (Shape test : nodes) {
            if (test != null && test.getBottom() < tmp) {
                retval = test;
                tmp = test.getBottom();
            }
        }
        return retval;
    }

    public static Shape getBottomShape(Shape[] nodes, Shape exclude) {
        double tmp = Double.MAX_VALUE;
        Shape retvar = null;
        for (Shape test : nodes) {
            if (test != null && test.getBottom() < tmp && test != exclude) {
                retvar = test;
                tmp = test.getBottom();
            }

        }
        return retvar;
    }

    public static Shape getTopShape(Shape[] nodes) {
        double tmp = Double.MIN_VALUE;
        Shape retval = null;
        for (Shape test : nodes) {
            if (test != null && test.getTop() > tmp) {
                retval = test;
                tmp = test.getTop();
            }
        }
        return retval;
    }

    public static Shape getTopShape(Shape[] nodes, Shape exclude) {
        double tmp = Double.MIN_VALUE;
        Shape retvar = null;
        for (Shape test : nodes) {
            if (test != null && test.getTop() > tmp && test != exclude) {
                retvar = test;
                tmp = test.getTop();
            }

        }
        return retvar;
    }

    public static Shape getLeftShape(Shape[] nodes) {
        double tmp = Double.MAX_VALUE;
        Shape retval = null;
        for (Shape test : nodes) {
            if (test != null && test.getLeft() < tmp) {
                retval = test;
                tmp = test.getLeft();
            }
        }
        return retval;
    }

    public static Shape getLeftShape(Shape[] nodes, Shape exclude) {
        double tmp = Double.MAX_VALUE;
        Shape retvar = null;
        for (Shape test : nodes) {
            if (test != null && test.getLeft() < tmp && test != exclude) {
                retvar = test;
                tmp = test.getLeft();
            }

        }
        return retvar;
    }

    public static Shape getRightShape(Shape[] nodes) {
        double tmp = Double.MIN_VALUE;
        Shape retval = null;
        for (Shape test : nodes) {
            if (test != null && test.getRight() > tmp) {
                retval = test;
                tmp = test.getRight();
            }
        }
        return retval;
    }

    public static Shape getRightShape(Shape[] nodes, Shape exclude) {
        double tmp = Double.MIN_VALUE;
        Shape retvar = null;
        for (Shape test : nodes) {
            if (test != null && test.getRight() > tmp && test != exclude) {
                retvar = test;
                tmp = test.getRight();
            }

        }
        return retvar;
    }
}
