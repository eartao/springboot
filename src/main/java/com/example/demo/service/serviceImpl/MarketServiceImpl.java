package com.example.demo.service.serviceImpl;

import com.example.demo.domain.RestResponse;
import com.example.demo.generator.pojo.CatalogProduct;
import com.example.demo.generator.pojo.ProductPrice;
import com.example.demo.generator.pojo.Result;
import com.example.demo.mapper.CatalogProductMapper;
import com.example.demo.mapper.CatalogProductprivatelabelpricesaleMapper;
import com.example.demo.mapper.CatalogProductresellertypepriceMapper;
import com.example.demo.service.MarketService;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MarketServiceImpl implements MarketService {
    @Autowired
    private CatalogProductMapper productMapper;
    @Autowired
    private CatalogProductprivatelabelpricesaleMapper saleMapper;
    @Autowired
    private CatalogProductresellertypepriceMapper listMapper;

    @Override
    public RestResponse getProductInfo(String name) {
        try {
            List<CatalogProduct> markets = productMapper.selectByName(name);
            return RestResponse.success(markets);
        } catch (Exception e) {
            e.printStackTrace();
            return RestResponse.fail("数据服务器系统异常");
        }
    }

    public static void main(String[] args) {
        String url = "https://godaddy.com/tlds/club-domain";
        System.out.println(getUrl(url,"es-us"));
        System.out.println(url.substring(url.indexOf(".com")+4));
        System.out.println(String.format(url,"www"));
        String market = "en-gb";
        System.out.println(market.substring(3)+market.substring(0,2));
        System.out.println("======"+getUrl(url,market));
//        System.out.println(url.substring(0,8)+market.substring(3).toLowerCase()+"."+url.substring(8));
//        System.out.println(market.toLowerCase());
//        String money = "$money$ 5.   6大健康1,4  fdsaf2*/añomeiy你好uan";
//        char[] chars = money.toCharArray();
//        String result = "";
//        for (int i = 0; i < chars.length; i++) {
//            if (("0123456789.,").indexOf(chars[i] + "") != -1)
//            {
//                result += chars[i];
//            }
//        }
//        System.out.println(result);
    }

    private static String getUrl(String url, String  market) {
        market = market.replace("_","-");
        String[] text = market.split("-");
        if(url.contains("www")){
            if(market.equalsIgnoreCase("en-gb")){
                url = url.replace("www","hk");
            } else if (market.equalsIgnoreCase("es-us")) {
                url = url.replace("com/","com/"+text[0]+"/");
            } else if (market.equalsIgnoreCase("en-us")){

            } else {
                url = url.replace("www",text[1]);
            }
        } else {
            if (market.equalsIgnoreCase("en-us")){
                url = url.substring(0,8)+"www."+url.substring(8);
            }
            else if (market.equalsIgnoreCase( "en-gb"))
            {
                url = url.substring(0,8)+"uk."+url.substring(8);
            }
            else if (market.equalsIgnoreCase("es-us"))
            {
                url = url.substring(0,8)+"www."+url.substring(8);
                url = url.replace(".com/",".com/"+text[0]+"/");
            }
            else {
                url = url.substring(0,8)+market.substring(3).toLowerCase()+"."+url.substring(8);
            }
        }
        return url;
    }

    public  String getMoney(String str){
        if(StringUtils.isBlank(str)){
            return null;
        }
        char[] chars = str.toCharArray();
        String money = "";
        for (int i = 0; i < chars.length; i++) {
            if (("0123456789.,").indexOf(chars[i] + "") != -1)
            {
                money += chars[i];
            }
            if (("/").indexOf(chars[i] + "") != -1){
                return money;
            }
        }
        return money;
    }

    @Override
    public RestResponse getResult(String url,String markets,String  pfId,String listXpath,String saleXpath) {
        //String url = "https://test-godaddy.com/tlds/club-domain";
        try {
            StringBuffer sb = new StringBuffer();
            String[] market = {};
            if(StringUtils.isBlank(markets)){
                market = new String[]{"en-us"};
            }else {
                market = markets.split(",");
            }
            List<ProductPrice> list = new ArrayList<>();
            List<Result> results = new ArrayList<>();
            Map<String,String> map = new HashMap<>();
            for (int i = 0; i < market.length; i++) {
                Result result = new Result();
                ProductPrice pagePrice = new ProductPrice();
                ProductPrice sqlPrice = new ProductPrice();
                String useUrl = getUrl(url,market[i]);
                result.setMarket(market[i]);

                pagePrice = getPagePrice(market[i],useUrl,listXpath,saleXpath);
                System.out.println("============="+pagePrice);
                if(StringUtils.isBlank(pagePrice.getSalePrice())){
                    result.setPageSalePrice("No Price");
                    results.add(result);
                    continue;
                }
                result.setPageSalePrice(null == pagePrice ? null : getMoney(pagePrice.getSalePrice()));
                result.setPageListPrice(null == pagePrice ? null : getMoney(pagePrice.getListPrice()));

                map.put("market",market[i]);
                map.put("pfId",pfId);
                sqlPrice = saleMapper.getPriceFromSql(map);
                System.out.println("============="+sqlPrice);
                System.out.println(pagePrice.equals(sqlPrice));
                result.setSqlSalePrice(null == sqlPrice ? null : sqlPrice.getSalePrice());
                result.setSqlListPrice(null == sqlPrice ? null : sqlPrice.getListPrice());


                if(null != sqlPrice && sqlPrice.equals(pagePrice)){
                    continue;
                }
                System.out.println(result.toString());
                results.add(result);
            }
            return RestResponse.success(results);
        } catch (Exception e) {
            e.printStackTrace();
            return RestResponse.fail("系统异常，请联系管理员");
        }
    }

    public ProductPrice getPagePrice(String market,String url,String listXpath,String saleXpath){
        ProductPrice price = new ProductPrice();
        long l = System.currentTimeMillis();
        System.setProperty("webdriver.chrome.driver","C:\\Driver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        String salePrice = null;
        String listPrice = null;
        try {
            driver.get(url);
            if(StringUtils.isNotBlank(saleXpath) || StringUtils.isNotBlank(listXpath)){
                salePrice = driver.findElement(By.xpath(saleXpath)).getText();
                listPrice = driver.findElement(By.xpath(listXpath)).getText();
                price.setListPrice(listPrice);
                price.setSalePrice(salePrice);
                price.setMarket(market);
                driver.quit();
                return price;
            }
            String xpath = "//*[@class='text-purchase'] | //*[@class='text-light']";
            WebElement element = driver.findElement(By.xpath(xpath));
            salePrice = element.getText();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("xpath获取不到"+market+"的Price");
            driver.quit();
            return price;
        }
        WebElement element = null;
        try {
//            String xpath1 = "//*[@class='vsc-paragraph-child-block marquee-product-text child-section-']/div/div/p/span/span/span/strike";
            String xpath1 = "strike";
            element = driver.findElement(By.tagName(xpath1));
            listPrice = element.getText();
            System.out.println("salePrice:"+salePrice+"listPrice:"+listPrice);
        } catch (Exception e) {
            System.out.println("页面获取不到"+market+"的listPrice");
            System.out.println(market+"用时："+(System.currentTimeMillis()-l));
            driver.quit();
        }finally {
            price.setListPrice(listPrice);
            price.setSalePrice(salePrice);
            price.setMarket(market);
            driver.quit();
            return  price;
        }
    }

}
