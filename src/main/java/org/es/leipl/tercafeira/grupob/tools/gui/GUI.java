package org.es.leipl.tercafeira.grupob.tools.gui;

import javax.swing.UIManager;
import com.formdev.flatlaf.FlatDarculaLaf;
import org.es.leipl.tercafeira.grupob.tools.FileUpload;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {
    public static void createGUI() {

        try {
            UIManager.setLookAndFeel( new FlatDarculaLaf() );
        // create a new JFrame object
        JFrame frame = new JFrame();

        // create two JButton objects
        JButton button1 = new JButton("Upload Local");
        JButton button2 = new JButton("Download Remoto");

        button1.setBounds(100,100,150,40);
        button2.setBounds(300,100,150,40);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FileUpload(frame).uploadLocal();
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FileUpload(frame).downloadUrl();
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
        frame.setTitle("Upload de Horário");

        // set the default close operation of the JFrame object
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - frame.getWidth()) / 2;
        int y = (screenSize.height - frame.getHeight()) / 2;
        frame.setLocation(x, y);

        // make the JFrame object visible
        frame.setVisible(true);

        } catch( Exception ex ) {
            System.err.println( "Failed to initialize theme. Using fallback." );
        }
    }
}
