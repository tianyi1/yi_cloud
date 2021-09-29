package com.yi.service.logic.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("order_tbl")
public class OrderVo {
    private Integer id;

    private String user_name;

    private String others;
}
