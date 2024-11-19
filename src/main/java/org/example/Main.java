package org.example;

import java.io.IOException;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {



        WebDrivingTwo webDrivingTwo = new WebDrivingTwo("https://www.avito.ru/brands/i195276908/all?src=search_seller_info&sellerId=9455326a5ed54207e344928caea60671");
        webDrivingTwo.runToParse();

        WriteToFileTwo writeToFileTwo = new WriteToFileTwo("iStore-shop116.csv");
        Map<String, String> allFeedbacks = WebDrivingTwo.getAllFeedbacksToSave();

        writeToFileTwo.write(allFeedbacks);
    }
}
