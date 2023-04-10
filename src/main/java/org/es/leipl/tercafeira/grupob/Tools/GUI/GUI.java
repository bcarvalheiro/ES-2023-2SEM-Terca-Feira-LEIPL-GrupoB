package org.es.leipl.tercafeira.grupob.Tools.GUI;

import javax.swing.*;
import java.awt.*;

public class GUI {

    public static void main(String[] args) {
        // create a new JFrame object
        JFrame frame = new JFrame();

        // create two JButton objects
        JButton button1 = new JButton("Button 1");
        JButton button2 = new JButton("Button 2");

        button1.setBounds(100,100,100,40);
        button2.setBounds(200,100,100,40);

        // add the buttons to the JFrame object
        frame.add(button1, BorderLayout.NORTH);
        frame.add(button2, BorderLayout.SOUTH);

        // set the layout of the JFrame object
        frame.setLayout(new BorderLayout());

        // set the preferred size of the JFrame object
        frame.setSize(400, 300);

        // set the title of the JFrame object
        frame.setTitle("Swing Box Example");

        // set the default close operation of the JFrame object
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // make the JFrame object visible
        frame.setVisible(true);
    }
}
