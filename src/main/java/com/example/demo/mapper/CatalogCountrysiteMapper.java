package com.example.demo.mapper;

import com.example.demo.generator.pojo.CatalogCountrysite;

public interface CatalogCountrysiteMapper {
    int insert(CatalogCountrysite record);

    int insertSelective(CatalogCountrysite record);
}