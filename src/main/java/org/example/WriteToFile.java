package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class WriteToFile {
    public WriteToFile(String filename) {
        this.filename = filename;
    }
    String filename;
    public void write(List<String> values) throws IOException {
        File myFile = new File(filename);
        FileOutputStream outputStream = new FileOutputStream(myFile);
        for (String s: values){
            byte[] buffer = s.getBytes();
            outputStream.write(buffer);
        }
        outputStream.close();
    }
}
