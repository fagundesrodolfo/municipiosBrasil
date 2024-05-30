package com.fagundes.apps.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import static java.nio.charset.StandardCharsets.UTF_8;

public class FileReaderDelegate {

    private final String DIVISOR = ",";

    public FileReaderDelegate() {

    }

    public void readFile(String fileName, FileLineResult fileLineResult) {

        BufferedReader bufferReader = null;
        InputStreamReader inputStreamReader = null;
        Boolean isFirstLine = true;

        try {
            URL resourceUrl = this.getClass().getClassLoader().getResource(fileName);
            if (resourceUrl == null) {
                throw new IllegalArgumentException("File not found: " + fileName);
            }

            // Read the file content
            inputStreamReader = new InputStreamReader(resourceUrl.openStream(), UTF_8);
            bufferReader = new BufferedReader(inputStreamReader);

            String line = "";
            while ((line = bufferReader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                fileLineResult.callBack(line.split(DIVISOR));
            }
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferReader != null) {
                    bufferReader.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public interface FileLineResult {
        void callBack(String[] line);
    }
}
