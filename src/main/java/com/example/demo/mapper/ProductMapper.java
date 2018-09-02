package com.example.demo.mapper;

import com.example.demo.generator.pojo.Product;

import java.util.List;

public interface ProductMapper {
    int insert(Product record);

    int insertSelective(Product record);

    List<Product> selectByName(String name);
}