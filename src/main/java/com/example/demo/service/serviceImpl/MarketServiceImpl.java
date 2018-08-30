package com.example.demo.service.serviceImpl;

import com.example.demo.domain.RestResponse;
import com.example.demo.generator.pojo.Market;
import com.example.demo.mapper.MarketMapper;
import com.example.demo.service.MarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class MarketServiceImpl implements MarketService {
    @Autowired
    private MarketMapper marketMapper;
    @Override
    public RestResponse getProductInfo(String name) {
        try {
            List<Market> markets = marketMapper.selectByName(name);
            return RestResponse.success(markets);
        } catch (Exception e) {
            e.printStackTrace();
            return RestResponse.fail("数据服务器系统异常");
        }
    }

}
