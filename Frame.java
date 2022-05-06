import javax.swing.*;
import java.awt.*;
public class Frame extends JFrame {


    private SortingPanel panel = new SortingPanel();

    public Frame(){

        this.setTitle("Sorting Visualizer");
        this.getContentPane().setPreferredSize(new Dimension(1200,600));
        this.getContentPane().add(panel);
        this.pack();  //content is below and under the Jpanel dimensions
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }
}