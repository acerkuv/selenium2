package org.example;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class WriteToFileTwo {
    public WriteToFileTwo(String filename) {
        this.filename = filename;
    }
    String filename;
    public void write(Map<String, String> values) throws IOException {
        File myFile = new File(filename);
        FileOutputStream outputStream = new FileOutputStream(myFile);
//        heder
        String headerStr = "Номер;Имя;Дата;Отзыв";
        byte[] first = headerStr.getBytes();
        outputStream.write(first);
        for (String s: values.keySet()){
            byte[] buffer = (s + ";" + values.get(s) + "\n" ).getBytes();
            outputStream.write(buffer);
        }
        outputStream.close();
    }
}