package com.example.demo.controller;

import com.example.demo.domain.Market;
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
    @RequestMapping("getMarket")
    @ResponseBody
    public List<Market> getMarket(){
        System.out.println("进入方法");
        List<Market> list = service.getMarket();
        return list;
    }
    @RequestMapping("aa")
    public String get(){
        return "hello world java Nih dfadffa";
    }

}
