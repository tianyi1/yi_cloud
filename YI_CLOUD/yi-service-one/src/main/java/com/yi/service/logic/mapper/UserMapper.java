package com.yi.service.logic.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yi.service.logic.entity.UserVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<UserVo> {

}
