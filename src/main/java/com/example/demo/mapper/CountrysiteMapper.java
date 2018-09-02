package com.example.demo.mapper;

import com.example.demo.generator.pojo.Countrysite;

public interface CountrysiteMapper {
    int insert(Countrysite record);

    int insertSelective(Countrysite record);
}