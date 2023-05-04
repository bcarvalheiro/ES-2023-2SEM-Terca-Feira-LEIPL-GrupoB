package org.es.leipl.tercafeira.grupob.tools;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
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


public class FileUpload {
    JFrame parent;
    private static Horario horario;
    public FileUpload(JFrame parent) {
        this.parent = parent;
    }

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
            File archiveFile = new File(System.getProperty("user.dir") + "/arquivo/Horario.csv");
            try{
                horario = ImportFiles.CSVImport(selectedFile.getAbsolutePath());
            } catch (Exception e){
                e.printStackTrace();
            }
            try{
                FileUtils.copyFile(selectedFile,archiveFile);
            } catch (IOException e) {
                System.err.format("IOException: ", e);
            }
        }
    }

    public void uploadUrl() {
        String url = JOptionPane.showInputDialog(parent, "Coloque o url para download");

        if(!url.isEmpty()) {
            File download = downloadCSV(url);
            if (!(download.length() == 0)) {
                String extension = FilenameUtils.getExtension(download.getAbsolutePath());

                try {
                    horario = ImportFiles.CSVImport(download.getAbsolutePath());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private File downloadCSV(String url) {
        try {
            URL csvUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) csvUrl.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                String fileName = url.substring(url.lastIndexOf("/") + 1);
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Save CSV File");
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

                    JOptionPane.showMessageDialog(null, "CSV file downloaded successfully.");
                    return archiveFile;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Failed to download CSV file. Response code: " + responseCode);
                return null;
            }
        } catch (MalformedURLException e) {
            JOptionPane.showMessageDialog(null, "Invalid URL: " + url);
            return  null;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error downloading CSV file: " + e.getMessage());
            return null;
        }
        return null;
    }

    public static Horario getHorario(){
        return horario;
    }
}