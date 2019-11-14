package com.gudt.imis.community.mapper;

import com.gudt.imis.community.dataobject.QuestionDTO;
import com.gudt.imis.community.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Insert("insert into question(id,title,description,gmt_create,gmt_modified,creator,tag) " +
            "values(#{id},#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    void create(Question question);

    @Select("select * from question")
    List<Question> list();


    @Select("select count(1) from question")
    int count();

    @Select("select * from question where creator=#{id}")
    List<Question> listByUserId(@Param("id") Integer id);

    @Select("select count(1) from question where creator=#{id}")
    int countByUserId(@Param("id") Integer id);

    @Select("select * from question where id=#{id}")
    Question getById(@Param("id")Integer id);
}
