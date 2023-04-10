package org.es.leipl.tercafeira.grupob.Tools.GUI;

import org.es.leipl.tercafeira.grupob.Tools.FileUpload;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {
    public static void main(String[] args) {
        // create a new JFrame object
        JFrame frame = new JFrame();

        // create two JButton objects
        JButton button1 = new JButton("Upload local");
        JButton button2 = new JButton("Button 2");

        button1.setBounds(100,100,150,40);
        button2.setBounds(300,100,150,40);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileUpload fileupload = new FileUpload();
                fileupload.uploadLocal();
            }
        });

        // add the buttons to the JFrame object
        frame.add(button1);
        frame.add(button2);

        // set the layout of the JFrame object
        frame.setLayout(null);

        // set the preferred size of the JFrame object
        frame.setSize(600, 500);

        // set the title of the JFrame object
        frame.setTitle("Upload de horário");

        // set the default close operation of the JFrame object
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - frame.getWidth()) / 2;
        int y = (screenSize.height - frame.getHeight()) / 2;
        frame.setLocation(x, y);

        // make the JFrame object visible
        frame.setVisible(true);
    }
}
