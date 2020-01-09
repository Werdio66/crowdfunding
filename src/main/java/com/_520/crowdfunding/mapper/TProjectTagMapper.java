package com._520.crowdfunding.mapper;

import com._520.crowdfunding.domain.TProjectTag;
import com._520.crowdfunding.domain.TProjectTagExample;
import com._520.crowdfunding.domain.TProjectTagKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TProjectTagMapper {
    long countByExample(TProjectTagExample example);

    int deleteByExample(TProjectTagExample example);

    int deleteByPrimaryKey(TProjectTagKey key);

    int insert(TProjectTag record);

    int insertSelective(TProjectTag record);

    List<TProjectTag> selectByExample(TProjectTagExample example);

    TProjectTag selectByPrimaryKey(TProjectTagKey key);

    int updateByExampleSelective(@Param("record") TProjectTag record, @Param("example") TProjectTagExample example);

    int updateByExample(@Param("record") TProjectTag record, @Param("example") TProjectTagExample example);

    int updateByPrimaryKeySelective(TProjectTag record);

    int updateByPrimaryKey(TProjectTag record);
}