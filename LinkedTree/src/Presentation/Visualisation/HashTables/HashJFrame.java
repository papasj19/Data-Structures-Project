package Presentation.Visualisation.HashTables;

import javax.swing.*;

public class HashJFrame extends JFrame {
    public HashJFrame() {
        setTitle("HashTable Histogram visualiser");
        setSize(800,800);
    }
    public void add(JPanel jPanel){
        getContentPane().add(jPanel);
    }
}
