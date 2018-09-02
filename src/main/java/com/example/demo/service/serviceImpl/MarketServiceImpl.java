package com.example.demo.service.serviceImpl;

import com.example.demo.domain.RestResponse;
import com.example.demo.generator.pojo.Product;
import com.example.demo.generator.pojo.ProductPrice;
import com.example.demo.mapper.ProductMapper;
import com.example.demo.service.MarketService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class MarketServiceImpl implements MarketService {
    @Autowired
    private ProductMapper productMapper;
    @Override
    public RestResponse getProductInfo(String name) {
        try {
            List<Product> markets = productMapper.selectByName(name);
            return RestResponse.success(markets);
        } catch (Exception e) {
            e.printStackTrace();
            return RestResponse.fail("数据服务器系统异常");
        }
    }

    public static void main(String[] args) {
        String url = "https://test-godaddy.com/tlds/club-domain";
        String market = "es-CL";
        System.out.println(url.substring(0,8)+market.substring(3).toLowerCase()+"."+url.substring(8));
        System.out.println(market.toLowerCase());
    }

    @Override
    public RestResponse getResult(String url,String markets,String  pfId) {
        //String url = "https://test-godaddy.com/tlds/club-domain";
        StringBuffer sb = new StringBuffer();
        String[] market = markets.split(",");
        List<ProductPrice> list = new ArrayList<>();
        for (int i = 0; i < market.length; i++) {
            ProductPrice pagePrice = new ProductPrice();
            ProductPrice sqlPrice = new ProductPrice();
            url = url.substring(0,8)+market[i].substring(3).toLowerCase()+"."+url.substring(8);
            sqlPrice = mapper.getPriceFromSql(market[i],pfId);
            pagePrice = getPagePrice(market[i],url);
            boolean equals = sqlPrice.equals(pagePrice);
            if(!equals){
                sb.append("Failed Market:"+market[i]+"[pageSalePrice:"+pagePrice.getSalePrice()+",sqlSalePrice:"+sqlPrice.getSalePrice()+"][pageListPrice:"+pagePrice.getListPrice()+",sqlListPrice:"+sqlPrice.getListPrice()+"/n");
            }
        }
        return RestResponse.fail(sb.toString());
    }

    public ProductPrice getPagePrice(String market,String url){
        ProductPrice price = new ProductPrice();
        long l = System.currentTimeMillis();
        System.setProperty("webdriver.chrome.driver","D:\\Driver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        String salePrice = null;
        String listPrice = null;
        try {
            driver.get(url);
            String xpath = "//*[@class='text-purchase'] | //*[@class='text-light']";
            WebElement element = driver.findElement(By.xpath(xpath));
            salePrice = element.getText();
        } catch (Exception e) {
            driver.quit();
            //return  RestResponse.fail("this url can not get the price info,please check");
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
            price.setListPrice(listPrice);
            price.setSalePrice(salePrice);
            price.setMarket(market);
            driver.quit();
            return  price;
        }
    }

}
