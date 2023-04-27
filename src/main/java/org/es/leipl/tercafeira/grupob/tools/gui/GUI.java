package org.es.leipl.tercafeira.grupob.tools.gui;

import javax.swing.UIManager;
import com.formdev.flatlaf.FlatDarculaLaf;
import org.es.leipl.tercafeira.grupob.pojos.Horario;
import org.es.leipl.tercafeira.grupob.tools.FileUpload;
import org.es.leipl.tercafeira.grupob.tools.ImportFiles;
import org.es.leipl.tercafeira.grupob.tools.gui.swingCalendar.Calendar;
import org.es.leipl.tercafeira.grupob.tools.gui.swingCalendar.CalendarEvent;
import org.es.leipl.tercafeira.grupob.tools.gui.swingCalendar.WeekCalendar;
import org.json.simple.JSONArray;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class GUI {
    private static Horario horario;
    private static ArrayList<CalendarEvent> calendarEvents = new ArrayList<>();
    private static JFrame frame;
    public static void createGUI() {
        try {
            UIManager.setLookAndFeel( new FlatDarculaLaf() );
            frame = new JFrame();

            // create two JButton objects
            JButton button1 = new JButton("Upload Local");
            JButton button2 = new JButton("Upload Remoto");
            JButton button3 = new JButton("Convert to JSON");
            JButton button4 = new JButton("Mostrar Calendário");

            button1.setBounds(50,50,150,40);
            button2.setBounds(200,50,150,40);
            button3.setBounds(350,50,150,40);
            button4.setBounds(500,50,150,40);


            button1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    FileUpload fu = new FileUpload(frame);
                    fu.uploadLocal();
                    horario = fu.getHorario();
                    if(horario!=null){
                        JOptionPane.showMessageDialog(frame, "Upload Horario carregado em sistema!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            });

            button2.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new FileUpload(frame).uploadUrl();
                }
            });

            button3.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (horario != null){
                        JSONArray json = ImportFiles.Horario2Json(horario);
                        ImportFiles.saveJSONtoFile(json);
                        JOptionPane.showMessageDialog(frame, "Ficheiro JSON criado em sistema!", "Success", JOptionPane.INFORMATION_MESSAGE);

                    }
                }
            });

            button4.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFrame calendario = new JFrame();
                    calendario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    calendario.setSize(800, 600);

                    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                    int x = (screenSize.width - calendario.getWidth()) / 2;
                    int y = (screenSize.height - calendario.getHeight()) / 2;
                    calendario.setLocation(x, y);

                    // make the JFrame object visible
                    calendario.setVisible(true);
                    Calendar calendar = new WeekCalendar(calendarEvents);
                    //Calendar calendar = new DayCalendar(calendarEvents);
                    calendario.add(calendar);
                    calendario.setVisible(true);

                }
            });
            // add the buttons to the JFrame object
            frame.add(button1);
            frame.add(button2);
            frame.add(button3);
            frame.add(button4);

            // set the layout of the JFrame object
            frame.setLayout(null);

            // set the preferred size of the JFrame object
            frame.setSize(800, 500);

            // set the title of the JFrame object
            frame.setTitle("Upload de Horário");

            // set the default close operation of the JFrame object
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int x = (screenSize.width - frame.getWidth()) / 2;
            int y = (screenSize.height - frame.getHeight()) / 2;
            frame.setLocation(x, y);

        } catch( Exception ex ) {
            System.err.println( "Failed to initialize theme. Using fallback." );
        }
    }

    //add Calendar events
    public static void addEvent(LocalDate date, LocalTime start, LocalTime end, String text) {
        CalendarEvent calendarEvent = new CalendarEvent(date, start, end, text, Color.PINK);
        calendarEvents.add(calendarEvent);
    }

    public static void showGUI() {
        frame.setVisible(true);
    }
}
