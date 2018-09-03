package com.example.demo.mapper;

import com.example.demo.generator.pojo.CatalogProduct;

import java.util.List;

public interface CatalogProductMapper {
    int insert(CatalogProduct record);

    int insertSelective(CatalogProduct record);

    List<CatalogProduct> selectByName(String name);
}