package Presentation.Visualisation.RTrees;

import Business.Tree.RTree.Circle;
import Business.Tree.RTree.Rectangle;
import Business.Tree.RTree.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class RtreeVisualiser extends JPanel {
    private static ArrayList<Shape> shapes = new ArrayList<>();

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        double size = Circle.scale;

        int y = 0;
        for (int horz = 0; horz < 1000; horz++) {
            int x = 0;
            for (int vert = 0; vert < 1000; vert++) {
                g2d.setColor(Color.LIGHT_GRAY);
                g2d.draw(new Rectangle2D.Double(x, y, size, size));
                x += size;
            }
            y += size;
        }
        for (Object s : shapes) {
            if (s instanceof Circle) {
                ((Circle) s).draw(g);
            } else if (s instanceof Rectangle) {
                ((Rectangle) s).draw(g);
            }
        }
    }

    public static void addShape(Shape shape){
        shapes.add(shape);
    }
    public static void clearShapes(){
        shapes = new ArrayList<>();
    }

}
