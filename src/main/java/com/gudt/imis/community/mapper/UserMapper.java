package com.gudt.imis.community.mapper;


import com.gudt.imis.community.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestParam;

@Mapper
public interface UserMapper {

    @Insert("Insert into user(account_id,name,token,gmt_create,gmt_modified,bio,avatar_url) " +
            "values(#{accountId},#{name},#{token},#{gmtCreate},#{gmtModified},#{bio},#{avatarUrl})")
    void insetUser(User user);

    @Select("select * from user where token=#{token}")
    User findByToken(@Param("token") String token);

    @Select("select * from user where id=#{id}")
    User findById(@Param("id") Integer creator);

    @Select("select * from user where account_id=#{accountId}")
    User findByAccountId(@Param("accountId")String accountId);

    @Update("update user set token=#{token},name=#{name},gmt_create=#{gmtCreate},gmt_modified=#{gmtModified},avatar_url=#{avatarUrl} where account_id=#{accountId}")
    boolean updateUser(User user);
}

