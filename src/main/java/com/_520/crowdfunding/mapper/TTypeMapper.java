package com._520.crowdfunding.mapper;

import com._520.crowdfunding.domain.TType;
import com._520.crowdfunding.domain.TTypeExample;
import com._520.crowdfunding.domain.TTypeKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TTypeMapper {
    long countByExample(TTypeExample example);

    int deleteByExample(TTypeExample example);

    int deleteByPrimaryKey(TTypeKey key);

    int insert(TType record);

    int insertSelective(TType record);

    List<TType> selectByExample(TTypeExample example);

    TType selectByPrimaryKey(TTypeKey key);

    int updateByExampleSelective(@Param("record") TType record, @Param("example") TTypeExample example);

    int updateByExample(@Param("record") TType record, @Param("example") TTypeExample example);

    int updateByPrimaryKeySelective(TType record);

    int updateByPrimaryKey(TType record);
}