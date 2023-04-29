package org.es.leipl.tercafeira.grupob.tools.gui;

import javax.swing.UIManager;
import com.formdev.flatlaf.FlatDarculaLaf;
import org.es.leipl.tercafeira.grupob.pojos.Bloco;
import org.es.leipl.tercafeira.grupob.pojos.Horario;
import org.es.leipl.tercafeira.grupob.tools.FileUpload;
import org.es.leipl.tercafeira.grupob.tools.ImportFiles;
import org.es.leipl.tercafeira.grupob.tools.gui.SwingCalendar.Calendar;
import org.es.leipl.tercafeira.grupob.tools.gui.SwingCalendar.CalendarEvent;
import org.es.leipl.tercafeira.grupob.tools.gui.SwingCalendar.DayCalendar;
import org.es.leipl.tercafeira.grupob.tools.gui.SwingCalendar.WeekCalendar;
import org.json.simple.JSONArray;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

public class GUI {
    private static Horario horario;

    public static ArrayList<CalendarEvent> getCalendarEvents() {
        return calendarEvents;
    }

    private static ArrayList<CalendarEvent> calendarEvents = new ArrayList<>();
    private static JFrame frame;
    private static JPanel calendarPanel;

    /**
     * Creates the Graphical User Interface (GUI) for the application, sets up the event listeners and displays the initial view.
     */
    public static void createGUI() {
        try {
            UIManager.setLookAndFeel( new FlatDarculaLaf() );
            frame = new JFrame();

            calendarPanel = new JPanel();
            calendarPanel.setPreferredSize(new Dimension(800, 600));
            calendarPanel.setLayout(new BorderLayout());
            calendarPanel.setBounds(0,70,800,600);


            JRadioButton weeklyView = new JRadioButton("Weekly View");
            weeklyView.setActionCommand("Weekly View");
            JRadioButton monthlyView = new JRadioButton("Monthly View");
            monthlyView.setActionCommand("Monthly View");
            JRadioButton dailyView = new JRadioButton("Daily View");
            dailyView.setActionCommand("Daily View");
            ButtonGroup group = new ButtonGroup();
            group.add(weeklyView);
            group.add(monthlyView);
            group.add(dailyView);
            JPanel radioPanel = new JPanel();
            radioPanel.setPreferredSize(new Dimension(100, 30));
            radioPanel.setLayout(new FlowLayout());
            radioPanel.setLayout(new BoxLayout(radioPanel, BoxLayout.Y_AXIS));
            radioPanel.add(weeklyView);
            radioPanel.add(monthlyView);
            radioPanel.add(dailyView);

            /**
             * This method adds an ActionListener to the weeklyView radio button. When the button is selected,
             * the calendar panel is cleared and the events are loaded onto the WeekCalendar. The panel is then
             * revalidated and repainted to display the updated calendar.
             */
            weeklyView.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    calendarPanel.removeAll();
                    calendarPanel.add(new WeekCalendar(calendarEvents));
                    try {
                        loadEventsToCalendar();
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    calendarPanel.revalidate();
                    frame.repaint();
                    calendarPanel.repaint();
                }
            });

            /**
             * This method adds an ActionListener to the dailyView radio button. When the button is selected,
             * the calendar panel is cleared and the events are loaded onto the DayCalendar. The panel is then
             * revalidated and repainted to display the updated calendar.
             */
            dailyView.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    calendarPanel.removeAll();
                    DayCalendar dayCalendar = new DayCalendar(calendarEvents);
                    calendarPanel.add(dayCalendar);
                    try {
                        loadEventsToCalendar();
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }

                    JLabel nextDay = new JLabel("Next Day");
                    /**
                     * This method adds an ActionListener to the nextDay button. When the button is clicked,
                     * the calendar panel is move to the day after today and the events are loaded onto the DayCalendar. The panel is then
                     * revalidated and repainted to display the updated calendar.
                     */
                    nextDay.addMouseListener(new MouseAdapter(){
                        @Override
                        public void mouseClicked(MouseEvent e){
                            dayCalendar.nextDay();
                        }
                    });
                    JLabel prevDay = new JLabel("Previous Day");
                    /**
                     * This method adds an ActionListener to the prevDay button. When the button is clicked,
                     * the calendar is moved to the previous day and the events are loaded onto the DayCalendar. The panel is then
                     * revalidated and repainted to display the updated calendar.
                     */
                    prevDay.addMouseListener(new MouseAdapter(){
                        @Override
                        public void mouseClicked(MouseEvent e){
                            dayCalendar.prevDay();
                        }
                    });
                    calendarPanel.add(nextDay,BorderLayout.EAST);
                    calendarPanel.add(prevDay,BorderLayout.WEST);
                    calendarPanel.revalidate();
                    frame.repaint();
                    calendarPanel.repaint();
                }
            });

            //This is SET for radioButton to load monthlycalendar. Just need to change the Commented Line
            /**
             * This method adds an ActionListener to the monthlyView radio button. When the button is selected,
             * the calendar panel is cleared and the events are loaded onto the MonthlyCalendar. The panel is then
             * revalidated and repainted to display the updated calendar.
             */
            monthlyView.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    calendarPanel.removeAll();
                    //calendarPanel.add(new MonthlyCalendar(calendarEvents));
                    try {
                        loadEventsToCalendar();
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    calendarPanel.revalidate();
                    frame.repaint();
                    calendarPanel.repaint();
                }
            });


            // create two JButton objects
            JButton button1 = new JButton("Upload Local");
            JButton button2 = new JButton("Upload Remoto");
            JButton button3 = new JButton("Convert to JSON");


            button1.setBounds(50,0,150,40);
            button2.setBounds(200,0,150,40);
            button3.setBounds(350,0,150,40);

            /**
             * ActionListener for the "Upload Local" button. Opens a file chooser to select a file from the system.
             * The file is then parsed and the schedule data is loaded into the program. The radio buttons are then added
             * to the frame and the frame is repainted if the schedule is sucessfuly loaded.
             * @param the ActionEvent representing the user's button click
             */
            button1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    FileUpload fu = new FileUpload(frame);
                    fu.uploadLocal();
                    horario = fu.getHorario();
                    if(horario!=null){
                        Container contentPane = frame.getContentPane();
                        contentPane.setLayout(new BorderLayout());
                        contentPane.add(radioPanel,BorderLayout.EAST);
                        // repaint the frame
                        frame.invalidate();
                        frame.validate();
                        frame.repaint();
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

            /**
             ActionListener for the "Export JSON" button. Converts the current schedule data to a JSON file and saves it to the system.
             If no schedule data is available, displays an error message.
             @param e the ActionEvent object representing the user's button click
             */
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

            // add the buttons to the JFrame object
            frame.add(button1);
            frame.add(button2);
            frame.add(button3);
            frame.add(calendarPanel);


            // set the layout of the JFrame object
            frame.setLayout(new BorderLayout());

            // set the preferred size of the JFrame object
            frame.setSize(800 , 800);

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

    /**
     Adds a new event to the calendarEvents list, based on the given Bloco object.
     The event's date, start time, end time, and text are extracted from the Bloco object.
     The event's color is set to pink.
     @param bloco the Bloco object to use as the basis for the new event
     @throws NullPointerException if bloco is null
     */
    public static void addEvent(Bloco bloco) throws Exception {
        if (bloco == null) {
            throw new NullPointerException("Bloco can't be null");
        }
        LocalDate data = bloco.getData();
        LocalTime end = bloco.getHoraFim();
        LocalTime start = bloco.getHoraIni();
        String text = bloco.getUc() + " " + bloco.getSala();
        if(start.getHour() > 22 || start.getHour() < 8 || end.getHour() > 22 || end.getHour() < 8){
            throw new Exception("Invalid start or end time");
        }
        CalendarEvent calendarEvent = new CalendarEvent(data, start, end, text, Color.PINK);
        calendarEvents.add(calendarEvent);
    }

    /**
     * Loads events from the 'horario' object to the calendar view.
     * If the 'horario' object is null, no events will be added.
     *
     * @throws NullPointerException if 'horario' is null
     */
    private static void loadEventsToCalendar() throws Exception {
        if (horario == null) {
            System.out.println("The 'horario' object is null. No events will be added to the calendar.");
            return;
        }
            LinkedList<Bloco> blocoList = (LinkedList<Bloco>) horario.getAulasList();
            //Posso substituir por setEvents(ArrayList<Events>...) mas tenho de ver
            //pcausa dos dataTypes
            for(Bloco b : blocoList){
                addEvent(b);
            }
    }

    public static void showGUI() {
        frame.setVisible(true);
    }
}
