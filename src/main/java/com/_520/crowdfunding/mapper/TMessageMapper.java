package com._520.crowdfunding.mapper;

import com._520.crowdfunding.domain.TMessage;
import com._520.crowdfunding.domain.TMessageExample;
import com._520.crowdfunding.domain.TMessageKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TMessageMapper {
    long countByExample(TMessageExample example);

    int deleteByExample(TMessageExample example);

    int deleteByPrimaryKey(TMessageKey key);

    int insert(TMessage record);

    int insertSelective(TMessage record);

    List<TMessage> selectByExample(TMessageExample example);

    TMessage selectByPrimaryKey(TMessageKey key);

    int updateByExampleSelective(@Param("record") TMessage record, @Param("example") TMessageExample example);

    int updateByExample(@Param("record") TMessage record, @Param("example") TMessageExample example);

    int updateByPrimaryKeySelective(TMessage record);

    int updateByPrimaryKey(TMessage record);
}