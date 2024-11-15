package org.example;

import java.io.IOException;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        WebDrivingTwo webDrivingTwo = new WebDrivingTwo();
        WebDrivingTwo.runToParse();

        WriteToFileTwo writeToFileTwo = new WriteToFileTwo("fb.csv");
        Map<String, String> allFeedbacks = WebDrivingTwo.getAllFeedbacksToSave();
//        allFeedbacks = (Map<String, String>) allFeedbacks.entrySet()
//                .stream().sorted(Map.Entry.<String, String>comparingByKey().reversed());

        writeToFileTwo.write(allFeedbacks);
    }
}