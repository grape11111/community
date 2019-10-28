package com.gudt.imis.community.mapper;


import com.gudt.imis.community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestParam;

@Mapper
public interface UserMapper {

    @Insert("Insert into user(account_id,name,token,gmt_create,gmt_modified) " +
            "values(#{account_id},#{name},#{token},#{gmt_create},#{gmt_modified})")
    void insetUser(User user);

    @Select("select * from user where token=#{token}")
    User findByToken(@Param("token") String token);
}
