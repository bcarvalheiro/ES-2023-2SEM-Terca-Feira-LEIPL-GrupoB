package org.es.leipl.tercafeira.grupob.tools.gui;

import com.formdev.flatlaf.FlatDarculaLaf;
import org.es.leipl.tercafeira.grupob.pojos.Bloco;
import org.es.leipl.tercafeira.grupob.pojos.Horario;
import org.es.leipl.tercafeira.grupob.tools.CsvUtils;
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

/**
 * @author GRUPO_B_LEI_PL
 * @version 1.3
 */
public class GUI {

    /**
     * Variables to hold the Harario of several actions
     */
    private static Horario horarioDisplay;
    private static Horario horarioPessoal;
    private static Horario horarioUpload;

    /**
     * Variables for buttons used in several methods
     */
    private static JButton next;
    private static JButton previous;
    private static JButton today;
    private static JButton monthlyView;
    private static JButton weeklyView;
    private static JButton dailyView;
    private static JButton saveF;
    private static JButton switchHorario;

    /**
     * String variables for the dynamic horario button
     */
    private static String pessoalH = "Horario Pessoal";
    private static String fullH = "Horario Completo";

    /**
     * Boolean var that shows if the current view is personal or not
     */
    private static Boolean viewP = false;

    /**
     * Method that returns the list of calendar events
     * @return calendarEvents
     */
    public static ArrayList<CalendarEvent> getCalendarEvents() {
        return calendarEvents;
    }

    /**
     * Array if calendarEvents
     */
    private static ArrayList<CalendarEvent> calendarEvents = new ArrayList<>();

    /**
     * Main GUI frame
     */
    private static JFrame frame;

    /**
     * Calendar Panel
     */
    private static JPanel calendarPanel;

    /**
     * Creates the Graphical User Interface (GUI) for the application, sets up the event listeners and displays the initial view.
     */
    public static void createGUI() {
        try {
            UIManager.setLookAndFeel(new FlatDarculaLaf());

            frame = new JFrame();

            int frameX = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
            int frameY = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();

            JButton bUpload = new JButton("Upload Horário");
            saveF = new JButton("Gravar Horário");
            switchHorario = new JButton(pessoalH);

            bUpload.setBounds(10, 10, 150, 40);
            saveF.setBounds(160, 10, 150, 40);
            switchHorario.setBounds(310, 10, 150, 40);

            dailyView = new JButton("Daily View");
            weeklyView = new JButton("Weekly View");
            monthlyView = new JButton("Monthly View");

            monthlyView.setBounds(frameX - 160, 10, 150, 40);
            weeklyView.setBounds(frameX - 310, 10, 150, 40);
            dailyView.setBounds(frameX - 460, 10, 150, 40);

            monthlyView.setVisible(false);
            weeklyView.setVisible(false);
            dailyView.setVisible(false);

            next = new JButton(">>");
            today = new JButton("Today");
            previous = new JButton("<<");

            previous.setBounds(535, 10, 150, 40);
            today.setBounds(685, 10, 150, 40);
            next.setBounds(835, 10, 150, 40);

            next.setVisible(false);
            today.setVisible(false);
            previous.setVisible(false);

            calendarPanel = new JPanel();
            calendarPanel.setPreferredSize(new Dimension(frameX, frameY - 150));
            calendarPanel.setLayout(new BorderLayout());
            calendarPanel.setBounds(0, 70, frameX, frameY - 150);

            frame.add(bUpload);
            frame.add(saveF);
            frame.add(switchHorario);

            frame.add(monthlyView);
            frame.add(weeklyView);
            frame.add(dailyView);

            frame.add(next);
            frame.add(today);
            frame.add(previous);

            frame.add(calendarPanel);
            frame.setLayout(new BorderLayout());

            frame.setSize(1920, 1080);
            frame.setTitle("Upload de Horário");

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);


            /**
             * ActionListener for the "Upload  button. Opens a input box for the user to select if he wants
             * o upload locally or from the web
             */
            bUpload.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    uploadChoose();
                }
            });

            /**
             *  ActionListener for the "Export JSON" button. Converts the current schedule data to a JSON file and saves it to the system.
             *  If no schedule data is available, displays an error message.
             *  @param e the ActionEvent object representing the user's button click
             */
            saveF.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (horarioDisplay != null)
                        saveChoose();
                    else
                        JOptionPane.showMessageDialog(frame, "Horário vazio! Impossível guardar", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            });
            /**
             *  ActionListener for the "Horário Pessoal/Horário Completo" button. Switches the view from imported calendar
             *  to personal calendar
             *  @param e the ActionEvent object representing the user's button click
             */
            switchHorario.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int escolha;
                    if (switchHorario.getText().equals(pessoalH)) {
                        if (horarioPessoal == null) {
                            escolha = JOptionPane.showConfirmDialog(frame, "Horario Pessoal vazio! Carregar horário?");
                            if (escolha == JOptionPane.YES_OPTION) {
                                uploadChoose();
                                horarioPessoal = horarioDisplay;
                                horarioUpload = null;

                                if (!(horarioDisplay == null) && (!horarioDisplay.getAulasList().isEmpty())) {
                                    switchHorario.setText(fullH);
                                    viewP = true;
                                    switchToWeekView();
                                    sobrelotacao();
                                    sobreposicao();
                                }
                            }
                        } else {
                            switchHorario.setText(fullH);
                            horarioDisplay = horarioPessoal;
                            viewP = true;
                            switchToWeekView();
                            sobrelotacao();
                            sobreposicao();
                        }
                    } else {
                        if (horarioUpload == null)
                            JOptionPane.showMessageDialog(frame, "Tem de fazer upload de um horário primeiro!", "Failure", JOptionPane.INFORMATION_MESSAGE);
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
             *  @param e the ActionEvent object representing the user's button click
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
             *  @param e the ActionEvent object representing the user's button click
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
    /**
     * Method invoced by the daily view button to switch the calendar to day view
     */
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

    /**
     * Method invoced by the weekly view button to switch the calendar to week view
     */
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

    /**
     * Method invoced by the monthly view button to switch the calendar to month view
     */
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

    /**
     * This method makes the GUI visible
     */
    public static void showGUI() {
        frame.setVisible(true);
    }

    /**
     * This method makes specific buttons visible after a calendar is uploaded
     */
    public static void setVisible() {
        monthlyView.setVisible(true);
        weeklyView.setVisible(true);
        dailyView.setVisible(true);
        next.setVisible(true);
        today.setVisible(true);
        previous.setVisible(true);
    }

    /**
     * This method decides, based on the current calendar view, if it's to add classes or remove to the personal calendar
     * @Param: CalendarEvent – the event to treat
     */
    public static void treatClass(CalendarEvent aula) {
        if(!viewP)
            addClass(aula);
        else
            removeClass(aula);
    }

    /**
     * This method adds classes to the personal calendar
     * @Param: CalendarEvent – the event to add
     */
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

    /**
     * This method removes  classes from the personal calendar
     * @Param: CalendarEvent – the event to remove
     */
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

    /**
     * This method checks overcrowded classes
     */
    public static void sobrelotacao() {
        int sobrelotacaoCount = 0;
        DefaultListModel sobrelotacaoList = new DefaultListModel();

        for (Bloco uc : horarioDisplay.getAulasList()) {
            if (uc.getInscritos() > uc.getLotacao()) {
                sobrelotacaoCount++;
                sobrelotacaoList.addElement(uc.getTurno() + " - "+ uc.getUc() + " " + uc.getData() + " (" + uc.getHoraIniToString() + " - " + uc.getHoraFimToString() + ")");
            }
        }

        if (!sobrelotacaoList.isEmpty()) {
            JList lista = new JList(sobrelotacaoList);
            JScrollPane scrollpane = new JScrollPane(lista);
            JOptionPane.showConfirmDialog(frame, scrollpane, "Alerta - Aulas em sobrelotacao (Total: " + sobrelotacaoCount + "):", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * This method checks overlayed classes
     */
    public static void sobreposicao() {
        int sobreposicaoCount = 0;
        DefaultListModel sobreposicaoList = new DefaultListModel();

        for (Bloco uc1 : horarioDisplay.getAulasList()) {
            for (Bloco uc2 : horarioDisplay.getAulasList()) {
                if (uc1 != uc2) {
                    if ((uc1.getData().isEqual(uc2.getData())) && ((uc1.getHoraIni().compareTo(uc2.getHoraIni())) == 0) && (uc1.getHoraFim().compareTo(uc2.getHoraFim())) == 0) {
                        sobreposicaoCount++;
                        sobreposicaoList.addElement(uc1.getTurno() + " - " + uc1.getUc() + " [ X ] " + uc2.getUc() + " "+ uc1.getData() + " (" + uc1.getHoraIniToString() + " - " + uc1.getHoraFimToString() + ")");
                    }
                }
            }
        }

        if (!sobreposicaoList.isEmpty()) {
            JList lista = new JList(sobreposicaoList);
            JScrollPane scrollpane = new JScrollPane(lista);
            JOptionPane.showConfirmDialog(frame, scrollpane, "Alerta - Aulas em sobreposicao (Total: " + sobreposicaoCount + "):", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Method for choosing the save method
     */
    public static void saveChoose() {
        String[] optionsToChoose = {"CSV", "JSON"};
        String getOption= (String) JOptionPane.showInputDialog(
                frame,
                "Em que formato quer gravar o ficheiro?",
                "Gragar",
                JOptionPane.QUESTION_MESSAGE,
                null,
                optionsToChoose,
                optionsToChoose[0]);
        if (getOption != null) {
            if (getOption.equals(optionsToChoose[0]))
                saveCSV();

            if (getOption.equals(optionsToChoose[1]))
                saveJSON();
        }
    }

    /**
     * Method that saves file to json
     */
    public static void saveJSON() {
        JSONArray json = ImportFiles.horario2Json(horarioDisplay);
        try {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Save file");
            FileNameExtensionFilter filter = new FileNameExtensionFilter("JSON", "JSON");
            fileChooser.setAcceptAllFileFilterUsed(false);
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

    /**
     * Method that saves file to CSV
     */
    public static void saveCSV() {
        FileUpload uploadFile = new FileUpload(new JFrame(String.valueOf(horarioDisplay)));
        String filepath = uploadFile.saveLocal("Guardar", "CSV");
        if (filepath != null) {
            try {
                CsvUtils.blocosToCsvFile(horarioDisplay.getAulasList(), filepath);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            JOptionPane.showMessageDialog(frame, "Ficheiro CSV criado em sistema!", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Method for choosing the upload method
     */
    public static void uploadChoose() {
        String[] optionsToChoose = {"Local", "Remoto"};
        String getOption= (String) JOptionPane.showInputDialog(
                frame,
                "Como quer fazer o Upload?",
                "Upload",
                JOptionPane.QUESTION_MESSAGE,
                null,
                optionsToChoose,
                optionsToChoose[0]);

        if (getOption != null) {
            if (getOption.equals(optionsToChoose[0]))
                uploadLocal();

            if (getOption.equals(optionsToChoose[1]))
                uploadRemoto();
        }
    }

    /**
     * Method for the "Upload Local" . Opens a file chooser to select a file from the system.
     * The file is then parsed and the schedule data is loaded into the program. The buttons are then added
     * to the frame and the frame is repainted if the schedule is sucessfuly loaded.
     */
    public static void uploadLocal() {
        horarioUpload = null;
        FileUpload fu = new FileUpload(frame);
        fu.uploadLocal();
        horarioUpload = fu.getHorario();
        horarioDisplay = horarioUpload;

        if(!(horarioDisplay == null) && (!horarioDisplay.getAulasList().isEmpty())){
            setVisible();
            JOptionPane.showMessageDialog(frame, "Horario carregado em sistema!", "Success", JOptionPane.INFORMATION_MESSAGE);
            switchToWeekView();
        }
    }

    /**
     * Method for the "Upload Remoto". Opens a text box for the user to input an url, followed by
     * a save file box for the user to choose where to save the uploaded file in the system.
     * The file is then parsed and the schedule data is loaded into the program. The buttons are then added
     * to the frame and the frame is repainted if the schedule is sucessfuly loaded.
     */
    public static void uploadRemoto() {
        horarioUpload = null;
        boolean webcal;
        FileUpload fu = new FileUpload(frame);
        try {
            webcal = fu.uploadUrl();
            if (webcal) {
                horarioUpload = fu.getHorario();
                horarioDisplay = horarioUpload;
            }
            else {
                horarioPessoal = fu.getHorario();
                horarioDisplay = horarioPessoal;
            }
            if(!(horarioDisplay == null) && (!horarioDisplay.getAulasList().isEmpty())){
                setVisible();
                JOptionPane.showMessageDialog(frame, "Horario carregado em sistema!", "Success", JOptionPane.INFORMATION_MESSAGE);
                switchToWeekView();
                switchHorario.setText(fullH);
                viewP = true;
                sobrelotacao();
                sobreposicao();
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }
}

