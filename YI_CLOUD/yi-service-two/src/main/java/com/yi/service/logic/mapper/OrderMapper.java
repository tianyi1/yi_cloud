package com.yi.service.logic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yi.service.logic.entity.OrderVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends BaseMapper<OrderVo> {
}
