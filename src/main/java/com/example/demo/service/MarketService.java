package com.example.demo.service;

import com.example.demo.domain.RestResponse;

import java.util.List;

public interface MarketService {
    RestResponse getProductInfo(String name);

    RestResponse getResult(String url,String markets,String  pfId,String listXpath,String saleXpath);
}
