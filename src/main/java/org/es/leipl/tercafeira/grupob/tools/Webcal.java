package org.es.leipl.tercafeira.grupob.tools;

import java.io.*;
import java.net.*;

/**
 * @author GRUPO_B_LEI_PL
 * @version 0.0
 */
public class Webcal {

    /**
     * Url to get webcal
     */
    private String url;
    public Webcal(String url) {
        this.url = url;
    }

    /**
     * downloads the webcal calendar
     * @return Sring ICS content
     */
    public String startWebcall() throws IOException {
        URL webcalUrl = new URL(url);
        URLConnection connection = webcalUrl.openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        StringBuilder content = new StringBuilder();

        while ((line = reader.readLine()) != null) {
            content.append(line);
            content.append(System.lineSeparator());
        }

        reader.close();
        String icsContent = content.toString();

        return icsContent;
    }
}