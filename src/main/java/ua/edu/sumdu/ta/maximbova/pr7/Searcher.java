package ua.edu.sumdu.ta.maximbova.pr7;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.opera.OperaDriver;

/**
 *
 * @Maxim Bova
 */
public class Searcher {
    static final String PICTUREADDRESS = "D:\\Soft\\Java\\Files\\";
    public HashMap<String, String> Search(){

        String googleSearchField = "//input[@name='q']";
        String clickOption = "//span[contains(text(),'netcracker open posit')]/b[contains(text(),'s')]";
        String clearSearchField = "//in/input[@name='q']\"put[@name='q']";
        String lineForSearch = "//h3[contains(text(),'Open Positions - Netcracker')]";
        String removeObstacle = "//span[contains(text(),'Accept')]";
        String menuButton = "//div[@id='jobdept-group']//span[@class='caret']";
        String sectionButton = "//div[@id='jobdept-group']//li//a/span[text() = \"IT\"]/parent::a";
        String jobList = "//div[@class=\"job result active\"]";


        DriverFactory factory = new DriverFactory();
        WebDriver driver = factory.getDriver("opera");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("https://www.google.com");
        driver.manage().window().maximize();
        driver.findElement(By.xpath(googleSearchField)).sendKeys("netcracker open posit");

        try{
            driver.findElement(By.xpath(clickOption)).click();
        }catch(Exception e){
            driver.findElement(By.xpath(clearSearchField)).clear();
            driver.findElement(By.xpath(googleSearchField)).sendKeys("netcracker open positions");
        }

        try{
            driver.findElement(By.xpath(lineForSearch)).click();
        }catch(Exception e){
            driver.get("https://www.netcracker.com/careers/open-positions/");
        }
        driver.findElement(By.xpath(removeObstacle)).click();

        driver.findElement(By.xpath(menuButton)).click();
        driver.findElement(By.xpath(sectionButton)).click();

        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try{
            String title = driver.getTitle();
            String pictureAddress = PICTUREADDRESS + title + ".png";
            System.out.println(pictureAddress);
            FileUtils.copyFile(screenshot, new File(pictureAddress));
        }catch(Exception e){
            System.out.println(e);
        }


        List <WebElement> list = driver.findElements(By.xpath(jobList));
        HashMap <String, String> elemMap = new HashMap();

        for(int i = 0; i < list.size(); i++){
            String value = list.get(i).findElement(By.xpath(".//p")).getText();
            String key = list.get(i).findElement(By.xpath(".//h3")).getText();
            elemMap.put(key, value);
        }
        return elemMap;
    }
}
