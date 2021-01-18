package com.example.springbootshiro.repository;

import com.example.springbootshiro.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {
    @Select("select * from mybatis.user where name = #{name}")
    User findUserByName(@Param("name") String name);
}
