package com._520.crowdfunding.mapper;

import com._520.crowdfunding.domain.TParam;
import com._520.crowdfunding.domain.TParamExample;
import com._520.crowdfunding.domain.TParamKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TParamMapper {
    long countByExample(TParamExample example);

    int deleteByExample(TParamExample example);

    int deleteByPrimaryKey(TParamKey key);

    int insert(TParam record);

    int insertSelective(TParam record);

    List<TParam> selectByExample(TParamExample example);

    TParam selectByPrimaryKey(TParamKey key);

    int updateByExampleSelective(@Param("record") TParam record, @Param("example") TParamExample example);

    int updateByExample(@Param("record") TParam record, @Param("example") TParamExample example);

    int updateByPrimaryKeySelective(TParam record);

    int updateByPrimaryKey(TParam record);
}