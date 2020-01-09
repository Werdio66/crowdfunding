package com._520.crowdfunding.mapper;

import com._520.crowdfunding.domain.TDictionary;
import com._520.crowdfunding.domain.TDictionaryExample;
import com._520.crowdfunding.domain.TDictionaryKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TDictionaryMapper {
    long countByExample(TDictionaryExample example);

    int deleteByExample(TDictionaryExample example);

    int deleteByPrimaryKey(TDictionaryKey key);

    int insert(TDictionary record);

    int insertSelective(TDictionary record);

    List<TDictionary> selectByExample(TDictionaryExample example);

    TDictionary selectByPrimaryKey(TDictionaryKey key);

    int updateByExampleSelective(@Param("record") TDictionary record, @Param("example") TDictionaryExample example);

    int updateByExample(@Param("record") TDictionary record, @Param("example") TDictionaryExample example);

    int updateByPrimaryKeySelective(TDictionary record);

    int updateByPrimaryKey(TDictionary record);
}