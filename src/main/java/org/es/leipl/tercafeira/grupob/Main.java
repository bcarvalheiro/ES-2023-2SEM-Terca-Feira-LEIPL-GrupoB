package org.es.leipl.tercafeira.grupob;

import net.fortuna.ical4j.data.ParserException;
import org.es.leipl.tercafeira.grupob.Tools.FileUpload;
import org.es.leipl.tercafeira.grupob.Tools.ImportFiles;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, ParserException {
        ImportFiles.CSVtoJSONPOJOS("C:/Users/bruno/ES_SchedulePlanner/arquivo/Horario.csv");


//        Scanner scanner = new Scanner (System.in);
//        int choice;
//        do{
//            System.out.println("Hello, how do you want to load your CSV file?");
//            System.out.println("1 - From a local file");
//            System.out.println("2 - From a web link");
//            System.out.println("3 - From a webcal link");
//            System.out.println("4 - Exit");
//
//            choice = scanner.nextInt();
//            scanner.nextLine();
//            String url;
//            switch(choice){
//                case 1:
//                    //open file chooser
//                    break;
//                case 2:
//                    System.out.println("Please enter the URL to the file");
//                    url = scanner.nextLine();
//                    ImportFiles.importFile(url);
//                    break;
//                case 3:
//                    System.out.println("Please enter the webcal link to the file");
//                    url = scanner.nextLine();
//                    ImportFiles.importFile(url);
//                    break;
//                case 4:
//                    System.out.println("Goodbye");
//                    break;
//                case 5:
//                    System.out.println("5- Insira o path do ficheiro");
//                    url = scanner.nextLine();
//                    ImportFiles.CSVtoJSon(url);
//                    break;
//
//                default:
//                    System.out.println("Invalid option");
//                    break;
//            }
//        }while(choice != 5);
    }
}