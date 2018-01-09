package com.CM.mapper;

import com.CM.pojo.UserDesc;
import com.CM.pojo.UserDescExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserDescMapper {
    int countByExample(UserDescExample example);

    int deleteByExample(UserDescExample example);

    int insert(UserDesc record);

    int insertSelective(UserDesc record);

    List<UserDesc> selectByExample(UserDescExample example);

    int updateByExampleSelective(@Param("record") UserDesc record, @Param("example") UserDescExample example);

    int updateByExample(@Param("record") UserDesc record, @Param("example") UserDescExample example);
}