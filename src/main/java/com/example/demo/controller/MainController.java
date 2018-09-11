package com.example.demo.controller;

import com.example.demo.domain.RestResponse;
import com.example.demo.service.MarketService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@EnableAutoConfiguration
public class MainController {
    @Autowired
    private MarketService service;
    @RequestMapping("getProductInfo")
    @ResponseBody
    public RestResponse getMarket(@RequestParam(value = "name",required = false)String name){
        if(StringUtils.isBlank(name)){
            return RestResponse.fail("please input product name");
        }
        if(name.length()<=3){
            return RestResponse.fail("please input at least 3 letter");
        }
        RestResponse response = service.getProductInfo(name);
        return response;
    }

    @RequestMapping("getResult")
    public RestResponse getResult(String url,String markets,String  pfId,String listXpath,String saleXpath){
        if(StringUtils.isBlank(url)){
            return RestResponse.fail("url cannt be null");
        }
        if(!url.contains("http")){
            return RestResponse.fail("pls input correct url like this------>https://godaddy.com");
        }
        if(StringUtils.isBlank(pfId)){
            return RestResponse.fail("please choose a pfId");
        }
        RestResponse response = service.getResult(url,markets,pfId,listXpath,saleXpath);
        return response;
    }


    @RequestMapping("getPriceFromPage")
    public RestResponse getPriceFromPage(@RequestParam(required = true,value = "url")String url,@RequestParam(value = "markets") List<String> markets){
        if(StringUtils.isBlank(url)){
            return RestResponse.fail("url cannt be null");
        }
        //RestResponse response = service.getPriceFromPage(url);
        return null;
    }

    public static void main(String[] args) {
        
    }


}
