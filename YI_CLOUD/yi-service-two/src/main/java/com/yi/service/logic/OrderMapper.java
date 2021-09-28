package com.yi.service.logic;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {
    void saveData(OrderVo order);
}
