package com.example.demo.service;

import com.example.demo.domain.RestResponse;

import java.util.List;

public interface MarketService {
    RestResponse getProductInfo(String name);
}
