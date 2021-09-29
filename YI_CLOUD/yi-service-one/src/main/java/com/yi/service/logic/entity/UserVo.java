package com.yi.service.logic.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("user_tbl")
public class UserVo implements Serializable {
    private Integer id;

    private String user_name;

    private String others;
}
