package org.es.leipl.tercafeira.grupob.tools;

import org.es.leipl.tercafeira.grupob.pojos.*;
import org.json.simple.*;

import javax.swing.*;
import javax.swing.filechooser.*;
import java.io.*;

public class ExportToFile {

    private ExportToFile() {}


    /**
     * Exports 'Horario' to JSON or CSV file
     * If the 'horario' object is null, no file will be saved
     * @param horarioDisplay Schedule to be saved
     * @param frame parent frame
     * @param format 'csv' or 'json' format
     */

    public static void exportToFormat(Horario horarioDisplay, JFrame frame, String format) {
        String formatAllCaps = format.toUpperCase();
        if (horarioDisplay != null) {
            String filepath = ExportToFile.saveLocal("Save "+ formatAllCaps +" File", format,frame);
            if (filepath != null) {
                try {
                    if (format.equals("csv"))
                        CsvUtils.blocosToCsvFile(horarioDisplay.getAulasList(), filepath);
                    else if (format.equals("json")) {
                        JSONArray json = ImportFiles.horario2Json(horarioDisplay);
                        ImportFiles.saveJSONtoFile(json, filepath);
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                JOptionPane.showMessageDialog(frame, "File "+ formatAllCaps +" file saved!",
                        "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(frame, "Schedule must be loaded","Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Method to return a filepath from JFileChooser
     * @param title title of the JFrame
     * @param extension extension of the file
     * @param frame parent JFrame
     *
     * @return filepath String
     */
    public static String saveLocal(String title, String extension, JFrame frame) {
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jfc.setDialogTitle(title);
        jfc.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter(extension, extension);
        jfc.addChoosableFileFilter(filter);
        int returnValue = jfc.showSaveDialog(frame);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jfc.getSelectedFile();
            System.out.println(selectedFile.getAbsolutePath());
            if (verifyExtension(selectedFile.getAbsolutePath(), extension)) {
                return selectedFile.getAbsolutePath();
            }else{
                return selectedFile.getAbsolutePath() + "." + extension;
            }
        }
        return null;
    }


    /**
     * Method to check if filepath has extension
     */
    private static boolean verifyExtension ( String filepath, String extension){
        int extensionlength = extension.length();
        String filepathExtension = filepath.substring(filepath.length() - extensionlength - 1);
        return ("." + extension).equals(filepathExtension);
    }


}
