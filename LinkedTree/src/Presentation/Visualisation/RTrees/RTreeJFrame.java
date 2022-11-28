package Presentation.Visualisation.RTrees;

import javax.swing.*;

public class RTreeJFrame extends JFrame {
    public RTreeJFrame() {
        setTitle("R_Tree visualiser");
        setSize(800,800);
    }
    public void add(JPanel jPanel){
        getContentPane().add(jPanel);
    }
}
