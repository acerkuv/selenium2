package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.annotation.RegEx;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebDriving {
    public static List<String> getFeedBacksList() {
        return feedBacksList;
    }

    public static Map<Integer, String> allFeedbacksToSave = new HashMap<>();

    public static List<String> feedBacksList = new ArrayList<>();


    public void driveWeb() {
    WebDriver driver = new ChromeDriver();
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        String fbText = null, fbModel = null, fbDate = null;

        driver.get("https://www.avito.ru/brands/i338763229/all/avtomobili?src=search_seller_info&sellerId=5b33e8842e008b7de85eebcfef2af40c");
        //выводим все отзывы
        WebElement btn;
        while (true){
           try {
               btn = wait.until(ExpectedConditions.visibilityOfElementLocated(
                       By.xpath("//button[@data-marker='rating-list/moreReviewsButton']")));
               // Проверка на вякий случай
               if(btn != null) btn.click();
               else break;

           }catch (Exception e){
        // Не нашел кнопку
               break;
           }
       }
//        Ищем все
        List<WebElement> feedBacks = driver.findElements(By.cssSelector("p"));

        for (WebElement w: feedBacks) {
            String dataMarker = w.getAttribute("data-marker");
            if (dataMarker != null) {
//            Дата
                if (dataMarker.matches(".+[0-9]{2}./header/subtitle|.+[0-9]{1}./header/subtitle")) {
                    fbDate = w.getText() + "\n";
                    feedBacksList.add(fbDate);

//              Сделка сотоялась + модель <p>
                } else if (dataMarker.matches(".+[0-9]{2}./stage|.+[0-9]{1}./stage")) {
                    fbModel = w.getText() + "\n";
                    feedBacksList.add(fbModel);

                    //            Отзыв <p>
                } else if (dataMarker.matches(".+[0-9]{2}./text-section/text|.+[0-9]{1}./text-section/text")) {
                    fbText = w.getText() + "\n";
                    feedBacksList.add(fbText);
                }

            }
        }
        driver.close();
    }

//  проверяем наличие регехп
    public static String getStringByRegexp(String regEx, String text){
        String result = null;
        Pattern pattern = Pattern.compile(String.valueOf(regEx));
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()){
            result = text.substring(matcher.start(), matcher.end());
        }
        return result;
    }
    public static boolean isInString(String regexp, String text){
        if (text.matches(regexp)) return true;
        return false;
    }

}
