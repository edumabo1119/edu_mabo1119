package ua.edu.sumdu.ta.maximbova.pr7;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;

/**
 *
 * @Maxim Bova
 */
public class DriverFactory {
    public WebDriver getDriver(String driverName){
        switch (driverName){
            case "opera": {
                System.setProperty("webdriver.opera.driver",
                        "D:\\Soft\\Java\\JavaProjects\\practise07\\drivers\\operadriver.exe");
                WebDriver driver = new OperaDriver();
                return driver;
            }
            case "google": {
                System.setProperty("webdriver.chrome.driver",
                        "D:\\Soft\\Java\\JavaProjects\\practise07\\drivers\\chromedriver.exe");
                WebDriver driver = new ChromeDriver();
                return driver;
            }
            case "firefox": {
                System.setProperty("webdriver.gecko.driver",
                        "D:\\Soft\\Java\\JavaProjects\\practise07\\drivers\\geckodriver.exe");
                WebDriver driver = new FirefoxDriver();
                return driver;
            }
        }
        //

        throw new IllegalArgumentException("There is no such a driver!");
    }
}
