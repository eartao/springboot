package com.example.demo.mapper;

import com.example.demo.domain.Market;

import java.util.List;

public interface MarketMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Market record);

    int insertSelective(Market record);

    Market selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Market record);

    int updateByPrimaryKey(Market record);

    List<Market>  selectTotal();
}