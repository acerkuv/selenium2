package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebDrivingTwo {
    static String startUrl = "https://www.avito.ru/brands/i338763229/all/avtomobili?src=search_seller_info&sellerId=5b33e8842e008b7de85eebcfef2af40c";
    static   WebDriver driver = new ChromeDriver();
    static   WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    public static Map<String, String> getAllFeedbacksToSave() {
        return allFeedbacksToSave;
    }

    public static Map<String, String> allFeedbacksToSave = new HashMap<>();

    public static void runToParse(){
        driver.get(startUrl);
        clickToExpandFeedbacks();
        // all elements on a page that consist tap <p>
        List<WebElement> listOfP = driver.findElements(By.cssSelector("p"));
        List<WebElement> listH5 = driver.findElements(By.cssSelector("h5"));
        List<WebElement> allStarsDiv = driver.findElements(By.cssSelector("div"));
//        num of feedback and name of the owner
        for (WebElement w: listH5){
            if(w != null){
                String textTag = w.getAttribute("data-marker");
                String text = w.getText();
                if(textTag != null && text != null
                        && isInString("..+title", textTag)){
                    String num =  getStringByRegexp("(\\d++)", textTag);
                    allFeedbacksToSave.put(num, text);
                }
            }
        }
//        Chek data and deal
        checkTheTagAndText("re..+/subtitle$", listOfP);

//        Check all <p> to join String текст
        checkTheTagAndText("re..+/text$", listOfP);

//        Добавляем звезды
        countStarsInFeedBack(allStarsDiv);

        driver.quit();

    }

//   Todo Stars <div>
    public static void countStarsInFeedBack(List<WebElement> starsList){
        Map<String, Integer> collectStars = new HashMap<>();
        for (WebElement w: starsList) {
            String starTag = w.getAttribute("data-marker");
            if (starTag != null && isInString("^re..+star.$", starTag)) {
                String num = getStringByRegexp("(\\d++)", starTag);
                System.out.println(num);
//                порядковый номер звезды
                String numOfStar = starTag.substring(starTag.length()-1);
                String starStat  =  w.findElement(By.cssSelector("path")).getAttribute("fill");
//                System.out.println("Отзыв= " + num + " номер звезды= " + numOfStar + " fill= " + starStat);
                if (collectStars.get(num) == null && starStat.equals("#ffb021")){
                     collectStars.put(num, 1);
                } else if (collectStars.get(num) != null  && starStat.equals("#ffb021")) {
                     int tempInt = collectStars.get(num);
                     tempInt++;
                     collectStars.put(num, tempInt);
                }
            }
        }
        System.out.println(collectStars);
        for (String s: collectStars.keySet()){
            String tempFeedback = allFeedbacksToSave.get(s);
            tempFeedback += collectStars.get(s);
            allFeedbacksToSave.put(s, tempFeedback);
        }

    }
// Clean Strind from "\n"
    public static String clearStr(String string){
        return string.replace("\n", " ");
    }
//    check the tag on
    public static void checkTheTagAndText(String regexp , List<WebElement> webElements){
        for (WebElement w: webElements){
            if(w != null){
                String textTag = w.getAttribute("data-marker");
                String text = w.getText();
//                Убираем перенос строки если есть
                text = clearStr(text);

                if(textTag != null && isInString(regexp, textTag)){
//                    gen num of the feedback
                    String num =  getStringByRegexp("(\\d++)", textTag);
                    String temp = allFeedbacksToSave.get(num);
                    temp += ";" + text;
                    allFeedbacksToSave.put(num, temp);
                }

            }
        }

    }

//    Get any from string by pathern
    public static String getStringByRegexp(String regEx, String text){
        String result = null;
        Pattern pattern = Pattern.compile(String.valueOf(regEx));
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()){
            result = text.substring(matcher.start(), matcher.end());
        }
        return result;
    }

//    Check is the tag
    public static boolean isInString(String regexp, String text){
        if (text.matches(regexp)) return true;
        return false;
    }
//    Кликаем, чтобы развернуть объявления
    public static void clickToExpandFeedbacks(){
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
    }


}
