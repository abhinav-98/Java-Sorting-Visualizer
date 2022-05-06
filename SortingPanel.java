import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.Timer;

public class SortingPanel extends JPanel {


    private Random random = new Random(); //to generate random numbers in the array
    private int[] array = new int[80];
    //variables to sort the array
    private int array_index;
    private int compare_index;

    //buttons to start and reset
    JButton start = new JButton("start");
    JButton reset = new JButton("reset");

    private boolean isRunning;//variable to check if algorithm is running or not

    public SortingPanel() {
        this.array_index = 0;
        this.compare_index = Integer.MAX_VALUE;

        this.setArray();

        start.setBackground(Color.WHITE);
        start.setFocusPainted(false);
        start.setBorderPainted(false);
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { //action performed when start button is clicked
                try {
                    isRunning = true; //when start button is clicked, isRunning is set to true
                    BubbleSortAnimate();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });


        reset.setBackground(Color.WHITE);
        reset.setFocusPainted(false);
        reset.setBorderPainted(false);
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { // action performed when reset button is clicked
                setArray();
                compare_index = Integer.MAX_VALUE;
                array_index = 0;
                isRunning = false;
                repaint();
            }
        });
        this.add(start);
        this.add(reset);
    }

    public int[] getArray() {
        return this.array;
    }

    public void setArray() { // setting the array again with random integers
        for (int i = 0; i < this.array.length; i++) {
            this.array[i] = random.nextInt(510)+40;
        }
    }

    public void sortOnlyOneItem() { // sorting logic --> bubble sort
        if (array[compare_index] > array[compare_index + 1]) {
            int temp = array[compare_index];
            array[compare_index] = array[compare_index + 1];
            array[compare_index+1] = temp;
        }
        if ((compare_index + 1) >= (array.length - array_index - 1)) {
            array_index++;
            compare_index = 0;
        } else {
            compare_index++;
        }
    }

    public boolean isSorted() { //checking to see if array is sorted
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                return false;
            }
        }

        return true;
    }

    public void BubbleSortAnimate() throws Exception {  //sorting animation timer, shows elements being compared
        compare_index = 0;
        Timer timer = new Timer(1, new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                if (isSorted()) {
                    compare_index = Integer.MAX_VALUE;
                    ((Timer)e.getSource()).stop();
                } else {
                    if (isRunning == true)
                        sortOnlyOneItem();
                }
                repaint();
            }
        });
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) { // sorting animation, giving colors to array and other events

        super.paintComponent(g);
        this.setBackground(Color.BLACK);
        for (int i = 0; i < array.length; i++) {
            g.setColor(Color.white);
            if (i == compare_index || i == compare_index + 1) {
                g.setColor(Color.red);
            }

            g.drawRect(i * 15, 600 - array[i], 14, array[i]);
            g.fillRect(i * 15, 600 - array[i], 14, array[i]);
        }

    }


}
