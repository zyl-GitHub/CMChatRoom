package com.CM.mapper;

import com.CM.pojo.FileCategory;
import com.CM.pojo.FileCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FileCategoryMapper {
    int countByExample(FileCategoryExample example);

    int deleteByExample(FileCategoryExample example);

    int insert(FileCategory record);

    int insertSelective(FileCategory record);

    List<FileCategory> selectByExample(FileCategoryExample example);

    int updateByExampleSelective(@Param("record") FileCategory record, @Param("example") FileCategoryExample example);

    int updateByExample(@Param("record") FileCategory record, @Param("example") FileCategoryExample example);
}