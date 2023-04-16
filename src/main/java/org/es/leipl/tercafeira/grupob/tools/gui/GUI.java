package org.es.leipl.tercafeira.grupob.tools.gui;

import javax.swing.UIManager;
import com.formdev.flatlaf.FlatDarculaLaf;
import org.es.leipl.tercafeira.grupob.tools.FileUpload;

import javax.swing.*;
import java.awt.*;

public final class GUI {

    private static GUI INSTANCE;

    /**
     * Private constructor
     */
    private GUI() {
    }

    /**
     * Creates/Returns the Instance of this class
     * @return the Instance of this class
     */
    public static GUI getINSTANCE() {
        if(INSTANCE == null) {
            INSTANCE = new GUI();
        }
        return INSTANCE;
    }

    /**
     * Creates a GUI to Download a Remote CSV File or to Upload a local CSV File
     */
    public static void createGUI() {

        try {
            UIManager.setLookAndFeel( new FlatDarculaLaf() );
        JFrame frame = new JFrame();

        JButton button1 = new JButton("Upload Local");
        JButton button2 = new JButton("Download Remoto");

        button1.setBounds(100,100,150,40);
        button2.setBounds(300,100,150,40);

        button1.addActionListener(e -> new FileUpload(frame).uploadLocal());

        button2.addActionListener(e -> new FileUpload(frame).downloadUrl());

        frame.add(button1);
        frame.add(button2);

        frame.setLayout(null);

        frame.setSize(600, 500);

        frame.setTitle("Upload de Horário");

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - frame.getWidth()) / 2;
        int y = (screenSize.height - frame.getHeight()) / 2;
        frame.setLocation(x, y);

        frame.setVisible(true);

        } catch( Exception ex ) {
            System.err.println( "Failed to initialize theme. Using fallback." );
        }
    }
}
