package org.es.leipl.tercafeira.grupob.tools.gui;

import com.formdev.flatlaf.FlatDarculaLaf;
import org.es.leipl.tercafeira.grupob.pojos.Bloco;
import org.es.leipl.tercafeira.grupob.pojos.Horario;
import org.es.leipl.tercafeira.grupob.tools.FileUpload;
import org.es.leipl.tercafeira.grupob.tools.ImportFiles;
import org.es.leipl.tercafeira.grupob.tools.gui.swingCalendar.CalendarEvent;
import org.es.leipl.tercafeira.grupob.tools.gui.swingCalendar.DayCalendar;
import org.es.leipl.tercafeira.grupob.tools.gui.swingCalendar.MonthCalendar;
import org.es.leipl.tercafeira.grupob.tools.gui.swingCalendar.WeekCalendar;
import org.json.simple.JSONArray;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GUI {
    private static Horario horarioDisplay;
    private static Horario horarioPessoal;
    private static Horario horarioUpload;
    private static JButton next;
    private static JButton previous;
    private static JButton today;
    private static JButton monthlyView;
    private static JButton weeklyView;
    private static JButton dailyView;
    private static JButton saveJ;
    private static String pessoalH = "Horario Pessoal";
    private static String fullH = "Horario Completo";
    private static Boolean viewP = false;

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

            int frameX = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
            int frameY = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();

            JButton bUploadL = new JButton("Upload Local");
            JButton buploadR = new JButton("Upload Remoto");
            JButton switchHorario = new JButton(pessoalH);

            bUploadL.setBounds(10,10,150,40);
            buploadR.setBounds(160,10,150,40);
            switchHorario.setBounds(310,10,150,40);

            saveJ = new JButton("Gravar em JSON");
            dailyView = new JButton("Daily View");
            weeklyView = new JButton("Weekly View");
            monthlyView = new JButton("Monthly View");

            monthlyView.setBounds(frameX-160,10,150,40);
            weeklyView.setBounds(frameX-310,10,150,40);
            dailyView.setBounds(frameX-460,10,150,40);
            saveJ.setBounds(460,10,150,40);

            monthlyView.setVisible(false);
            weeklyView.setVisible(false);
            dailyView.setVisible(false);
            saveJ.setVisible(false);

            next = new JButton("Next");
            today = new JButton("Today");
            previous = new JButton("Previous");

            previous.setBounds(620,10,150,40);
            today.setBounds(770,10,150,40);
            next.setBounds(920,10,150,40);

            next.setVisible(false);
            today.setVisible(false);
            previous.setVisible(false);

            calendarPanel = new JPanel();
            calendarPanel.setPreferredSize(new Dimension(frameX, frameY-150));
            calendarPanel.setLayout(new BorderLayout());
            calendarPanel.setBounds(0,70,frameX,frameY-150);

            frame.add(bUploadL);
            frame.add(buploadR);
            frame.add(saveJ);
            frame.add(switchHorario);

            frame.add(monthlyView);
            frame.add(weeklyView);
            frame.add(dailyView);

            frame.add(next);
            frame.add(today);
            frame.add(previous);

            frame.add(calendarPanel);
            frame.setLayout(new BorderLayout());

            frame.setSize(1920 , 1080);
            frame.setTitle("Upload de Horário");

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);


            /**
             * ActionListener for the "Upload Local" button. Opens a file chooser to select a file from the system.
             * The file is then parsed and the schedule data is loaded into the program. The radio buttons are then added
             * to the frame and the frame is repainted if the schedule is sucessfuly loaded.
             * @param the ActionEvent representing the user's button click
             */
            bUploadL.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    horarioUpload = null;
                    FileUpload fu = new FileUpload(frame);
                    fu.uploadLocal();
                    horarioUpload = fu.getHorario();
                    horarioDisplay = horarioUpload;

                    if(!(horarioDisplay == null) && (!horarioDisplay.getAulasList().isEmpty())){
                        setVisible();
                        JOptionPane.showMessageDialog(frame, "Horario carregado em sistema!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        switchToWeekView();
                        sobrelotacao();
                    }
                }
            });

            buploadR.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    horarioUpload = null;
                    FileUpload fu = new FileUpload(frame);
                    fu.uploadUrl();
                    horarioUpload = fu.getHorario();
                    horarioDisplay = horarioUpload;

                    if(!(horarioDisplay == null) && (!horarioDisplay.getAulasList().isEmpty())){
                        setVisible();
                        JOptionPane.showMessageDialog(frame, "Horario carregado em sistema!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        switchToWeekView();
                        sobrelotacao();
                    }
                }
            });

            /**
             ActionListener for the "Export JSON" button. Converts the current schedule data to a JSON file and saves it to the system.
             If no schedule data is available, displays an error message.
             @param e the ActionEvent object representing the user's button click
             */
            saveJ.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (horarioDisplay != null){
                        JSONArray json = ImportFiles.horario2Json(horarioDisplay);
                        try {
                            JFileChooser fileChooser = new JFileChooser();
                            fileChooser.setDialogTitle("Save file");
                            FileNameExtensionFilter filter = new FileNameExtensionFilter("JSON", "JSON");
                            fileChooser.addChoosableFileFilter(filter);
                            fileChooser.setApproveButtonText("Save");
                            int result = fileChooser.showSaveDialog(frame);

                            String filePath = "";
                            if (result == JFileChooser.APPROVE_OPTION) {
                                filePath = fileChooser.getSelectedFile().getPath() + ".json";
                                System.out.println("File path: " + filePath);
                                ImportFiles.saveJSONtoFile(json, filePath);
                                JOptionPane.showMessageDialog(frame, "Ficheiro JSON criado em sistema!", "Success", JOptionPane.INFORMATION_MESSAGE);
                            }

                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
            });

            switchHorario.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int escolha;
                    if (switchHorario.getText().equals(pessoalH)) {
                        if(horarioPessoal == null) {
                            escolha = JOptionPane.showConfirmDialog(frame, "Horario Pessoal vazio! Carregar horário?");
                            if(escolha == JOptionPane.YES_OPTION) {
                                FileUpload fu = new FileUpload(frame);
                                fu.uploadLocal();
                                horarioPessoal = fu.getHorario();
                                horarioDisplay = horarioPessoal;

                                if(!(horarioDisplay == null) && (!horarioDisplay.getAulasList().isEmpty())){
                                    setVisible();
                                    JOptionPane.showMessageDialog(frame, "Horario carregado!", "Success", JOptionPane.INFORMATION_MESSAGE);
                                    switchHorario.setText(fullH);
                                    viewP = true;
                                    switchToWeekView();
                                }
                            }
                        }
                        else {
                            switchHorario.setText(fullH);
                            horarioDisplay = horarioPessoal;
                            viewP = true;
                            switchToWeekView();
                        }
                    }
                    else {
                        if(horarioUpload == null)
                            JOptionPane.showMessageDialog(frame,"Tem de fazer upload de um horário primeiro!","Failure", JOptionPane.INFORMATION_MESSAGE);
                        else {
                            switchHorario.setText(pessoalH);
                            horarioDisplay = horarioUpload;
                            viewP = false;
                            switchToWeekView();
                        }
                    }
                }
            });

            /**
             * This method adds an ActionListener to the dailyView button. When the button is selected,
             * the calendar panel is cleared and the events are loaded onto the DayCalendar. The panel is then
             * revalidated and repainted to display the updated calendar.
             */
            dailyView.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    switchToDayView();
                }
            });

            /**
             * This method adds an ActionListener to the weeklyView button. When the button is selected,
             * the calendar panel is cleared and the events are loaded onto the WeekCalendar. The panel is then
             * revalidated and repainted to display the updated calendar.
             */
            weeklyView.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    switchToWeekView();
                }
            });

            /**
             * This method adds an ActionListener to the monthlyView button. When the button is selected,
             * the calendar panel is cleared and the events are loaded onto the MonthlyCalendar. The panel is then
             * revalidated and repainted to display the updated calendar.
             */
            monthlyView.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    switchToMonthView();
                }
            });

        } catch( Exception ex ) {
            System.err.println( "Failed to initialize theme. Using fallback." );
        }
    }

    /**
     Adds a new event to the calendarEvents list, based on the given Bloco object.
     The event's date, start time, end time, and text are extracted from the Bloco object.
     The event's color is set to pink.
     @param bloco the Bloco object to use as the basis for the new event
     @throws NullPointerException if bloco is null
     */
    public static void addEvent(Bloco bloco) {
        if (bloco == null) {
            throw new NullPointerException("Bloco can't be null");
        }
        LocalDate data = bloco.getData();
        LocalTime end = bloco.getHoraFim();
        LocalTime start = bloco.getHoraIni();
        String text = bloco.getUc() + ";" + bloco.getSala() + ";" + bloco.getCursosToString() + ";" + bloco.getTurmasToString() + ";" + bloco.getTurno();
        if(!(start.getHour() > 22 || start.getHour() < 8 || end.getHour() > 22 || end.getHour() < 8)) {
            CalendarEvent calendarEvent = new CalendarEvent(data, start, end, text, Color.PINK);
            calendarEvents.add(calendarEvent);
        }
    }

    /**
     * Loads events from the 'horario' object to the calendar view.
     * If the 'horario' object is null, no events will be added.
     *
     * @throws NullPointerException if 'horario' is null
     */
    private static void loadEventsToCalendar() throws Exception {
        if (horarioDisplay == null) {
            System.out.println("The 'horario' object is null. No events will be added to the calendar.");
            return;
        }
        LinkedList<Bloco> blocoList = (LinkedList<Bloco>) horarioDisplay.getAulasList();
        for(Bloco b : blocoList){
            addEvent(b);
        }
    }

    private static void switchToDayView(){
        calendarPanel.removeAll();
        calendarEvents = new ArrayList<>();
        DayCalendar dayCalendar = new DayCalendar(calendarEvents, LocalDate.now());
        calendarPanel.add(dayCalendar);

        dayCalendar.addCalendarEventClickListener(c -> treatClass(c.getCalendarEvent()));
        today.addActionListener(e -> dayCalendar.goToToday());

        try {
            loadEventsToCalendar();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        /**
         * This method adds an ActionListener to the nextDay button. When the button is clicked,
         * the calendar panel is move to the day after today and the events are loaded onto the DayCalendar. The panel is then
         * revalidated and repainted to display the updated calendar.
         */
        next.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                dayCalendar.nextDay();
            }
        });
        /**
         * This method adds an ActionListener to the prevDay button. When the button is clicked,
         * the calendar is moved to the previous day and the events are loaded onto the DayCalendar. The panel is then
         * revalidated and repainted to display the updated calendar.
         */
        previous.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                dayCalendar.prevDay();
            }
        });
        calendarPanel.revalidate();
        frame.repaint();
        calendarPanel.repaint();
    }

    private static void switchToWeekView(){
        calendarPanel.removeAll();
        calendarEvents = new ArrayList<>();
        WeekCalendar weekCalendar = new WeekCalendar(calendarEvents);
        calendarPanel.add(weekCalendar);

        weekCalendar.addCalendarEventClickListener(c -> treatClass(c.getCalendarEvent()));
        today.addActionListener(e -> weekCalendar.goToToday());

        try {
            loadEventsToCalendar();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        /**
         * This method adds an ActionListener to the nextWeek button. When the button is clicked,
         * the calendar panel is move to the week after today and the events are loaded onto the WeekCalendar. The panel is then
         * revalidated and repainted to display the updated calendar.
         */
        next.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                weekCalendar.nextWeek();
            }
        });
        /**
         * This method adds an ActionListener to the prevWeek button. When the button is clicked,
         * the calendar is moved to the previous week and the events are loaded onto the WeekCalendar. The panel is then
         * revalidated and repainted to display the updated calendar.
         */
        previous.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                weekCalendar.prevWeek();
            }
        });
        calendarPanel.revalidate();
        frame.repaint();
        calendarPanel.repaint();
    }

    private static void switchToMonthView(){
        calendarPanel.removeAll();
        calendarEvents = new ArrayList<>();
        MonthCalendar monthCalendar = new MonthCalendar(calendarEvents);
        calendarPanel.add(monthCalendar);

        monthCalendar.addCalendarEventClickListener(c -> treatClass(c.getCalendarEvent()));
        today.addActionListener(e -> monthCalendar.goToToday());

        try {
            loadEventsToCalendar();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        /**
         * This method adds an ActionListener to the nextMonth button. When the button is clicked,
         * the calendar panel is move to the Month after today and the events are loaded onto the MonthCalendar. The panel is then
         * revalidated and repainted to display the updated calendar.
         */
        next.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                monthCalendar.nextMonth();
            }
        });
        /**
         * This method adds an ActionListener to the prevDay button. When the button is clicked,
         * the calendar is moved to the previous month and the events are loaded onto the MonthCalendar. The panel is then
         * revalidated and repainted to display the updated calendar.
         */
        previous.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                monthCalendar.prevMonth();
            }
        });

        calendarPanel.revalidate();
        frame.repaint();
        calendarPanel.repaint();
    }


    public static void showGUI() {
        frame.setVisible(true);
    }

    public static void setVisible() {
        monthlyView.setVisible(true);
        weeklyView.setVisible(true);
        dailyView.setVisible(true);
        next.setVisible(true);
        today.setVisible(true);
        previous.setVisible(true);
        saveJ.setVisible(true);
    }
    public static void treatClass(CalendarEvent aula) {
        if(!viewP)
            addClass(aula);
        else
            removeClass(aula);
    }

    public static void addClass(CalendarEvent aula) {
        List<Bloco> aExistentes = new LinkedList<>();
        String message = "";;
        String[] aulastring = aula.getText().split(";");
        String aulatext = aulastring[0];
        int escolha = JOptionPane.showConfirmDialog(frame, "Adicionar aula " + aulatext + " ao horário?");
        if (horarioPessoal != null)
            aExistentes = horarioPessoal.getAulasList();

        if (escolha == JOptionPane.YES_OPTION) {
            horarioPessoal = new Horario();
            String turno = aulastring[4];
            List<Bloco> aulas = horarioUpload.getAulasList();

            for (Bloco uc : aulas) {
                if(uc.getTurno().equals(turno)) {
                    if (aExistentes.contains(uc)) {
                        message = "Aula já existe no horário!";
                        break;
                    }
                    else{
                        horarioPessoal.addAula(uc);
                        message = "Aula adicionada ao horário!";
                    }
                }
            }
            JOptionPane.showMessageDialog(frame, message);
        }
    }

    public static void removeClass(CalendarEvent aula) {
        List<Bloco> toRemove = new LinkedList<>();
        String[] aulastring = aula.getText().split(";");
        String aulatext = aulastring[0];
        int escolha = JOptionPane.showConfirmDialog(frame, "Remover aula " + aulatext + " do horário?");

        if (escolha == JOptionPane.YES_OPTION) {
            String turno = aulastring[4];
            List<Bloco> aulas = horarioPessoal.getAulasList();

            for (Bloco uc : aulas) {
                if(uc.getTurno().equals(turno)) {
                    toRemove.add(uc);
                }
            }
        }
        horarioPessoal.removeAulas(toRemove);
        JOptionPane.showMessageDialog(frame, "Aula retirada do horário");
        switchToWeekView();
    }

    public static void sobrelotacao() {
        int sobrelotacaoCount = 0;
        DefaultListModel sobrelotacaoList = new DefaultListModel();

        for (Bloco uc : horarioDisplay.getAulasList()) {
            if (uc.getInscritos() > uc.getLotacao()) {
                sobrelotacaoCount++;
                sobrelotacaoList.addElement(uc.getTurno() + " - "+ uc.getUc() + " " + uc.getData() + " (" + uc.getHoraIniToString() + " - " + uc.getHoraFimToString() + ")");
            }
        }

        JList lista = new JList(sobrelotacaoList);
        JScrollPane scrollpane = new JScrollPane(lista);
        JOptionPane.showConfirmDialog(frame, scrollpane, "Alerta - Aulas em sobrelotacao (Total: " + sobrelotacaoCount + "):",JOptionPane.WARNING_MESSAGE);
    }

    public static void sobreposicao() {
        int sobreposicaoCount = 0;
        DefaultListModel sobreposicaoList = new DefaultListModel();

        for (Bloco uc : horarioDisplay.getAulasList()) {
            if (uc.getInscritos() > uc.getLotacao()) {
                sobreposicaoCount++;
                sobreposicaoList.addElement(uc.getTurno() + " - "+ uc.getUc() + " " + uc.getData() + " (" + uc.getHoraIniToString() + " - " + uc.getHoraFimToString() + ")");
            }
        }

        JList lista = new JList(sobreposicaoList);
        JScrollPane scrollpane = new JScrollPane(lista);
        JOptionPane.showConfirmDialog(frame, scrollpane, "Alerta - Aulas em sobreposicao (Total: " + sobreposicaoCount + "):",JOptionPane.WARNING_MESSAGE);
    }
}
