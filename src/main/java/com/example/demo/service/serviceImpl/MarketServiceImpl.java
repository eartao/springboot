package com.example.demo.service.serviceImpl;

import com.example.demo.mapper.MarketMapper;
import com.example.demo.domain.Market;
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
    public List<Market> getMarket() {
        List<Market> list = marketMapper.selectTotal();
        return list;
    }

}
