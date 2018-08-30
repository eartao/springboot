import org.apache.catalina.filters.WebdavFixFilter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class SeleniumTest {
    public static void main(String[] args) throws Exception {
        chrome();
    }

    public static void phantomjs() {
        long l = System.currentTimeMillis();
//        //设置必要参数
        DesiredCapabilities dcaps = new DesiredCapabilities();
//        //ssl证书支持
//        dcaps.setCapability("acceptSslCerts", true);
//        //截屏支持
//        dcaps.setCapability("takesScreenshot", true);
//        //css搜索支持
//        dcaps.setCapability("cssSelectorsEnabled", true);
//        //js支持
//        dcaps.setJavascriptEnabled(true);
        dcaps.setCapability("phantomjs.binary.path", "D:\\Driver\\phantomjs.exe");
        PhantomJSDriver driver = new PhantomJSDriver(dcaps);
        //设置隐性等待（作用于全局）
        //driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        //打开页面
        String salePrice = null;
        String listPrice = null;
        try {
            driver.get("https://ar.test-godaddy.com/tlds/club-domain");
//        WebDriverWait webDriverWait = new WebDriverWait(driver,10);
            String xpath = "//*[@class='text-purchase'] | //*[@class='text-light']";
            WebElement element = driver.findElement(By.xpath(xpath));
            salePrice = element.getText();
        } catch (Exception e) {
            System.out.println("this url can not get the price info,please check");
            driver.quit();
        }
//        String xpath1 = "//*[@class='vsc-paragraph-child-block marquee-product-text child-section-']/div/div/p/span/span/span/strike";
        WebElement element = null;
        try {
            String xpath1 = "strike";
            element = driver.findElementByTagName(xpath1);
            listPrice = element.getText();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("listPrice:"+listPrice+"---salePrice:"+salePrice+"--tagName:"+element.getTagName());
        System.out.println("用时："+(System.currentTimeMillis()-l));
    }
    public static void chrome() throws Exception{
        long l = System.currentTimeMillis();
        System.setProperty("webdriver.chrome.driver","D:\\Driver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        String salePrice = null;
        String listPrice = null;
        try {
            driver.get("https://test-godaddy.com/tlds/club-domain");
//        WebDriverWait webDriverWait = new WebDriverWait(driver,10);
            String xpath = "//*[@class='text-purchase'] | //*[@class='text-light']";
            WebElement element = driver.findElement(By.xpath(xpath));
            salePrice = element.getText();
        } catch (Exception e) {
            System.out.println("this url can not get the price info,please check");
            driver.quit();
        }
        WebElement element = null;
        try {
//            String xpath1 = "//*[@class='vsc-paragraph-child-block marquee-product-text child-section-']/div/div/p/span/span/span/strike";
            String xpath1 = "strike";
            element = driver.findElement(By.tagName(xpath1));
            listPrice = element.getText();
            System.out.println("salePrice:"+salePrice+"listPrice:"+listPrice);
        } catch (Exception e) {
            System.out.println("获取不到listPrice");
            System.out.println("用时："+(System.currentTimeMillis()-l));
            driver.quit();
            System.out.println("listPrice:"+listPrice+"---salePrice:"+salePrice);
        }finally {
            driver.quit();
        }
    }
}
