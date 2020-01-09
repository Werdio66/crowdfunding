package com._520.crowdfunding.mapper;

import com._520.crowdfunding.domain.TResource;
import com._520.crowdfunding.domain.TResourceExample;
import com._520.crowdfunding.domain.TResourceKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TResourceMapper {
    long countByExample(TResourceExample example);

    int deleteByExample(TResourceExample example);

    int deleteByPrimaryKey(TResourceKey key);

    int insert(TResource record);

    int insertSelective(TResource record);

    List<TResource> selectByExample(TResourceExample example);

    TResource selectByPrimaryKey(TResourceKey key);

    int updateByExampleSelective(@Param("record") TResource record, @Param("example") TResourceExample example);

    int updateByExample(@Param("record") TResource record, @Param("example") TResourceExample example);

    int updateByPrimaryKeySelective(TResource record);

    int updateByPrimaryKey(TResource record);
}