package org.es.leipl.tercafeira.grupob.Tools;

import org.apache.commons.io.FileUtils;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;

public class FileUpload {
    public static void main(String[] args) {

        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jfc.setDialogTitle("Escolha o ficheiro para fazer upload");
        jfc.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV ou JSON", "csv", "JSON");
        jfc.addChoosableFileFilter(filter);

        int returnValue = jfc.showDialog(null, "Upload");

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jfc.getSelectedFile();
            System.out.println(selectedFile.getAbsolutePath());
            System.out.println("Present Project Directory : "+ System.getProperty("user.dir"));

            File archiveFile = new File(System.getProperty("user.dir") + "/arquivo/Horario.csv");

            try{
                FileUtils.copyFile(selectedFile,archiveFile);
            } catch (IOException e) {
                System.err.format("IOException: ", e);
            }
        }
    }
}