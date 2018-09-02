package com.example.demo.mapper;

import com.example.demo.generator.pojo.Countrymarket;

public interface CountrymarketMapper {
    int insert(Countrymarket record);

    int insertSelective(Countrymarket record);
}