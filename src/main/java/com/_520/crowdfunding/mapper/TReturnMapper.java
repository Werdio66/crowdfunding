package com._520.crowdfunding.mapper;

import com._520.crowdfunding.domain.TReturn;
import com._520.crowdfunding.domain.TReturnExample;
import com._520.crowdfunding.domain.TReturnKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TReturnMapper {
    long countByExample(TReturnExample example);

    int deleteByExample(TReturnExample example);

    int deleteByPrimaryKey(TReturnKey key);

    int insert(TReturn record);

    int insertSelective(TReturn record);

    List<TReturn> selectByExample(TReturnExample example);

    TReturn selectByPrimaryKey(TReturnKey key);

    int updateByExampleSelective(@Param("record") TReturn record, @Param("example") TReturnExample example);

    int updateByExample(@Param("record") TReturn record, @Param("example") TReturnExample example);

    int updateByPrimaryKeySelective(TReturn record);

    int updateByPrimaryKey(TReturn record);
}