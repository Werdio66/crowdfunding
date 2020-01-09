package com._520.crowdfunding.mapper;

import com._520.crowdfunding.domain.TCert;
import com._520.crowdfunding.domain.TCertExample;
import com._520.crowdfunding.domain.TCertKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TCertMapper {
    long countByExample(TCertExample example);

    int deleteByExample(TCertExample example);

    int deleteByPrimaryKey(TCertKey key);

    int insert(TCert record);

    int insertSelective(TCert record);

    List<TCert> selectByExample(TCertExample example);

    TCert selectByPrimaryKey(TCertKey key);

    int updateByExampleSelective(@Param("record") TCert record, @Param("example") TCertExample example);

    int updateByExample(@Param("record") TCert record, @Param("example") TCertExample example);

    int updateByPrimaryKeySelective(TCert record);

    int updateByPrimaryKey(TCert record);
}