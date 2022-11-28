package Presentation.Visualisation.HashTables;

import Business.Tree.RTree.Circle;
import Business.Tree.RTree.Rectangle;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class HashHistogram extends JPanel {
    private int histogramHeight = 200;
    private int barWidth = 50;
    private int barGap = 10;

    private JPanel barPanel;
    private JPanel labelPanel;

    private String[] labels = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    public static int[] values = new int[7];

    public static void clearBusinesses(){
        values = new int[7];
    }

    public static void addBusiness(String day) {
        switch (day) {
            case "Monday" -> values[0]++;
            case "Tuesday" -> values[1]++;
            case "Wednesday" -> values[2]++;
            case "Thursday" -> values[3]++;
            case "Friday" -> values[4]++;
            case "Saturday" -> values[5]++;
            case "Sunday" -> values[6]++;
        }
    }

    public static void deleteBusiness(String day) {
        switch (day) {
            case "Monday" -> values[0]--;
            case "Tuesday" -> values[1]--;
            case "Wednesday" -> values[2]--;
            case "Thursday" -> values[3]--;
            case "Friday" -> values[4]--;
            case "Saturday" -> values[5]--;
            case "Sunday" -> values[6]--;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        int numValues = 0;
        for (int a: values){ numValues = Math.max(numValues,a); }
        int scale = numValues/600;
        scale = Math.max(scale, 1);
        int[] valuesScaled = new int[7];
        for (int i = 0; i < 7; i++){
            valuesScaled[i] = values[i]/scale;
        }

        g2d.setColor(Color.RED);
        g2d.fill(new Rectangle2D.Double(50,700-valuesScaled[0],75, valuesScaled[0]));
        g2d.setColor(Color.BLACK);
        g2d.drawString(labels[0],70,675-valuesScaled[0]);
        g2d.drawString(String.valueOf(values[0]),70,690-valuesScaled[0]);

        g2d.setColor(Color.BLUE);
        g2d.fill(new Rectangle2D.Double(150,700-valuesScaled[1],75, valuesScaled[1]));
        g2d.setColor(Color.BLACK);
        g2d.drawString(labels[1],170,675-valuesScaled[1]);
        g2d.drawString(String.valueOf(values[1]),170,690-valuesScaled[1]);

        g2d.setColor(Color.GREEN);
        g2d.fill(new Rectangle2D.Double(250,700-valuesScaled[2],75, valuesScaled[2]));
        g2d.setColor(Color.BLACK);
        g2d.drawString(labels[2],260,675-valuesScaled[2]);
        g2d.drawString(String.valueOf(values[2]),260,690-valuesScaled[2]);

        g2d.setColor(Color.BLACK);
        g2d.fill(new Rectangle2D.Double(350,700-valuesScaled[3],75, valuesScaled[3]));
        g2d.setColor(Color.BLACK);
        g2d.drawString(labels[3],360,675-valuesScaled[3]);
        g2d.drawString(String.valueOf(values[3]),360,690-valuesScaled[3]);

        g2d.setColor(Color.ORANGE);
        g2d.fill(new Rectangle2D.Double(450,700-valuesScaled[4],75, valuesScaled[4]));
        g2d.setColor(Color.BLACK);
        g2d.drawString(labels[4],460,675-valuesScaled[4]);
        g2d.drawString(String.valueOf(values[4]),460,690-valuesScaled[4]);

        g2d.setColor(Color.MAGENTA);
        g2d.fill(new Rectangle2D.Double(550,700-valuesScaled[5],75, valuesScaled[5]));
        g2d.setColor(Color.BLACK);
        g2d.drawString(labels[5],560,675-valuesScaled[5]);
        g2d.drawString(String.valueOf(values[5]),560,690-valuesScaled[5]);

        g2d.setColor(Color.CYAN);
        g2d.fill(new Rectangle2D.Double(650,700-valuesScaled[6],75, valuesScaled[6]));
        g2d.setColor(Color.BLACK);
        g2d.drawString(labels[6],660,675-valuesScaled[6]);
        g2d.drawString(String.valueOf(values[6]),660,690-valuesScaled[6]);
    }


}
