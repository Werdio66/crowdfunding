package com._520.crowdfunding.mapper;

import com._520.crowdfunding.domain.TMember;
import com._520.crowdfunding.domain.TMemberExample;
import com._520.crowdfunding.domain.TMemberKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TMemberMapper {
    long countByExample(TMemberExample example);

    int deleteByExample(TMemberExample example);

    int deleteByPrimaryKey(TMemberKey key);

    int insert(TMember record);

    int insertSelective(TMember record);

    List<TMember> selectByExample(TMemberExample example);

    TMember selectByPrimaryKey(TMemberKey key);

    int updateByExampleSelective(@Param("record") TMember record, @Param("example") TMemberExample example);

    int updateByExample(@Param("record") TMember record, @Param("example") TMemberExample example);

    int updateByPrimaryKeySelective(TMember record);

    int updateByPrimaryKey(TMember record);
}