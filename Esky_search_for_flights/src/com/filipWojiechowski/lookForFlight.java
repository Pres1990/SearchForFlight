package com.filipWojiechowski;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.HashMap;
import java.util.Map;

public class lookForFlight {

    public static void main(String[] args) throws InterruptedException {

        Map<String, Object> prefs = new HashMap<String, Object>(); //dissable notofication
        prefs.put("profile.default_content_setting_values.notifications", 2);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Pres\\Desktop\\selenium\\chromedriver.exe");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize(); //maximize windows
        driver.get("https://www.esky.pl/"); //open webpage
        driver.findElement(By.xpath("//*[@value='RoundTrip']")).click(); //select type of trip
        driver.findElement(By.xpath("//*[@id='departureRoundtrip0']")).sendKeys("WAW"); //select departure airport
        driver.findElement(By.xpath("//*[@id='arrivalRoundtrip0']")).sendKeys("JFK"); //select return airport
        driver.findElement(By.xpath("//*[@id='departureDateRoundtrip0']")).click(); // open departure calendar

        while (!driver.findElement(By.xpath("//*[@id=\'ui-datepicker-div\']/div/div/span[1]")).getText().contains("Luty")) {  // select departure month
            driver.findElement(By.xpath("//*[@id=\'ui-datepicker-div\']/div/a[2]")).click();
        }

        int departureDate = driver.findElements(By.className("ui-state-default")).size();
        for (int i = 0; i < departureDate; i++) { // select departure day
            String text = driver.findElements(By.className("ui-state-default")).get(i).getText();
            if (text.equalsIgnoreCase("14")) {
                driver.findElements(By.className("ui-state-default")).get(i).click();
                break;
            }
        }

        driver.findElement(By.xpath("//*[@id='departureDateRoundtrip1']")).click(); // open return calendar
        while (!driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/div/span[1]")).getText().contains("Lipiec")) { // select return month
            driver.findElement(By.xpath("//*[@id='ui-datepicker-div']/div/a[2]/span")).click();
        }
        int returnDate = driver.findElements(By.className("ui-state-default")).size();

        for (int i = 0; i < returnDate; i++) { // select return date
            String text = driver.findElements(By.className("ui-state-default")).get(i).getText();

            if (text.equalsIgnoreCase("28")) {
                driver.findElements(By.className("ui-state-default")).get(i).click();
                break;
            }
        }
        driver.findElement(By.xpath("//*[@class='btn transaction qsf-search']")).click(); //search for flights



    }
}