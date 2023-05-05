package org.es.leipl.tercafeira.grupob.tools;

import org.apache.commons.io.FileUtils;
import org.es.leipl.tercafeira.grupob.pojos.Horario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ForkJoinPool;

/**
 * @author GRUPO_B_LEI_PL
 * @version 1.3
 */

public class FileUpload {

    /**
     * Stores the parent frame
     */
    JFrame parent;

    /**
     * Var for horario
     */
    private static Horario horario;
    public FileUpload(JFrame parent) {
        this.parent = parent;
    }

    /**
     * Checks the file extension
     * @param file
     * @return String - extension
     */
    private static String checkExtension(File file){
        String extension = FilenameUtils.getExtension(file.getAbsolutePath());
        return extension;
    }

    /**
     * Method to upload selected local user file to the system
     */
    public void uploadLocal() {
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jfc.setDialogTitle("Escolha o ficheiro para fazer upload");
        jfc.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV ou JSON", "csv", "JSON");
        jfc.addChoosableFileFilter(filter);
        int returnValue = jfc.showOpenDialog(parent);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jfc.getSelectedFile();
            System.out.println(selectedFile.getAbsolutePath());
            System.out.println("Present Project Directory : "+ System.getProperty("user.dir"));
            System.out.println(checkExtension(selectedFile).toLowerCase());
            if (checkExtension(selectedFile).toLowerCase().equals("csv")) {
                File archiveFile = new File(System.getProperty("user.dir") + "/arquivo/Horario.csv");
                try{
                    FileUtils.copyFile(selectedFile,archiveFile);
                } catch (IOException e) {
                    System.err.format("IOException: ", e);
                }

                try{
                    horario = ImportFiles.importFile(selectedFile.getAbsolutePath());
                } catch (Exception e){
                    e.printStackTrace();
                }
            }

            else if (checkExtension(selectedFile).toLowerCase().equals("json")) {
                File archiveFile = new File(System.getProperty("user.dir") + "/arquivo/Horario.JSON");
                try{
                    FileUtils.copyFile(selectedFile,archiveFile);
                } catch (IOException e) {
                    System.err.format("IOException: ", e);
                }

                try{
                    horario = ImportFiles.importFile(selectedFile.getAbsolutePath());
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
            else
                JOptionPane.showConfirmDialog(parent, "Ficheiro importado com formato incorreto", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    public String saveLocal(String title, String extension) {
        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jfc.setDialogTitle(title);
        jfc.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter(extension, extension);
        jfc.addChoosableFileFilter(filter);
        int returnValue = jfc.showSaveDialog(parent);
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
     * Method to upload selected remote user file to the system
     */
    public void uploadUrl() throws IOException {
        String url = JOptionPane.showInputDialog(parent, "Coloque o url para download");
        if(url != null) {
            if (!url.isEmpty()) {
                if(url.startsWith("webcal://")){
                    String link = url.replace("webcal", "https");
                    File file = ImportFiles.downloadWebcal(link);
                    if(file != null){
                        try {
                            horario = ImportFiles.importICS(file);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                File download = downloadFile(url);
                if (download != null) {
                    if (!(download.length() == 0)) {
                        try {
                            horario = ImportFiles.importFile(download.getAbsolutePath());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
    private boolean verifyExtension ( String filepath, String extension){
        int extensionLegth = extension.length();
        String filepathExtension = filepath.substring(filepath.length() - extensionLegth - 1);
        return ("." + extension).equals(filepathExtension);

    }
    /**
     * Method to download remote user file to the system
     */
    private File downloadFile(String url) {
        try {
            URL csvUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) csvUrl.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                String fileName = url.substring(url.lastIndexOf("/") + 1);
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Save File");
                fileChooser.setAcceptAllFileFilterUsed(false);
                FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV ou JSON", "csv", "JSON");
                fileChooser.addChoosableFileFilter(filter);
                fileChooser.setSelectedFile(new File(fileName));
                int userSelection = fileChooser.showSaveDialog(null);

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File archiveFile = fileChooser.getSelectedFile();
                    InputStream inputStream = connection.getInputStream();
                    FileOutputStream outputStream = new FileOutputStream(archiveFile);

                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }

                    outputStream.close();
                    inputStream.close();

                    JOptionPane.showMessageDialog(null, "File downloaded successfully.");
                    return archiveFile;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Failed to download file. Response code: " + responseCode);
                return null;
            }
        } catch (MalformedURLException e) {
            JOptionPane.showMessageDialog(null, "Invalid URL: " + url);
            return  null;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error downloading file: " + e.getMessage());
            return null;
        }
        return null;
    }

    /**
     * Returns uploaded horario
     * @return Horario
     */
    public static Horario getHorario(){
        return horario;
    }
}