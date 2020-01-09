package com._520.crowdfunding.mapper;

import com._520.crowdfunding.domain.TTag;
import com._520.crowdfunding.domain.TTagExample;
import com._520.crowdfunding.domain.TTagKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TTagMapper {
    long countByExample(TTagExample example);

    int deleteByExample(TTagExample example);

    int deleteByPrimaryKey(TTagKey key);

    int insert(TTag record);

    int insertSelective(TTag record);

    List<TTag> selectByExample(TTagExample example);

    TTag selectByPrimaryKey(TTagKey key);

    int updateByExampleSelective(@Param("record") TTag record, @Param("example") TTagExample example);

    int updateByExample(@Param("record") TTag record, @Param("example") TTagExample example);

    int updateByPrimaryKeySelective(TTag record);

    int updateByPrimaryKey(TTag record);
}