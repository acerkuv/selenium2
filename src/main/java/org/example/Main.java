package org.example;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
       WebDrivingTwo webDriving = new WebDrivingTwo();
       WebDrivingTwo.runToParse();

       WriteToFileTwo writeToFileTwo = new WriteToFileTwo("fb.csv");
       Map<String, String> mapList = WebDrivingTwo.getAllFeedbacksToSave();
       writeToFileTwo.write(mapList);

    }
}