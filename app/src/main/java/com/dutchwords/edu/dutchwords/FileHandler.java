package com.dutchwords.edu.dutchwords;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    private String FILE_NAME = "dutch_words.txt";

    public void appendToFile(String definition, String translation, File FILE_DIRECTORY) throws IOException {
        FILE_DIRECTORY.mkdir();
        File file = new File(FILE_DIRECTORY, FILE_NAME);
        String line = getLineToAddToFile(definition, translation);

        try(FileOutputStream outputStream =  new FileOutputStream(file, true); OutputStreamWriter osw = new OutputStreamWriter(outputStream)) {
            osw.append(line);
            osw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private String getLineToAddToFile(String definition, String translation) {
        return definition + ":" + translation + "\r\n";
    }

    public List<String> getAllWords(File FILE_DIRECTORY) throws IOException{
        List<String> dutchWords = new ArrayList<>();
        File file = new File(FILE_DIRECTORY, FILE_NAME);

        try(FileInputStream is = new FileInputStream(file); BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String line = reader.readLine();
            while (line != null) {
                dutchWords.add(line);
                line = reader.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dutchWords;
    }
}
