import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.*;

import java.util.concurrent.TimeUnit;

public class SeleniumTest {
    public static void main(String[] args) throws Exception{
        System.setProperty("webdriver.gecko.driver","D:\\Driver\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.manage().deleteAllCookies();
        driver.get("https://www.baidu.com/");
        driver.findElement(By.id("kw")).sendKeys("java");
        TimeUnit.SECONDS.sleep(5);
        driver.findElement(By.id("su")).click();
    }

}
