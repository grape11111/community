package com.gudt.imis.community.mapper;

import com.gudt.imis.community.model.Question;
import com.gudt.imis.community.model.QuestionExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QuestionExtMapper {
    int incView(Question record);
    int incComment(Question record);
}