package org.example;

import java.io.*;
import java.nio.charset.StandardCharsets;
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
        String headerStr = "Номер;Имя;Дата;Отзыв;Звезд;Фото\n";
        byte[] first = headerStr.getBytes(StandardCharsets.UTF_8);
        outputStream.write(first);
        for (String s: values.keySet()){
            byte[] buffer = (s + ";" + values.get(s) + "\n" ).getBytes(StandardCharsets.UTF_8);
            outputStream.write(buffer);
        }
        outputStream.close();
    }
}
