package com.yi.service.logic;


import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    void saveUser(UserVo user);
}
