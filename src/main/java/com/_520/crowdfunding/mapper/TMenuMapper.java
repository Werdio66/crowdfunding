package com._520.crowdfunding.mapper;

import com._520.crowdfunding.domain.TMenu;
import com._520.crowdfunding.domain.TMenuExample;
import com._520.crowdfunding.domain.TMenuKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TMenuMapper {
    long countByExample(TMenuExample example);

    int deleteByExample(TMenuExample example);

    int deleteByPrimaryKey(TMenuKey key);

    int insert(TMenu record);

    int insertSelective(TMenu record);

    List<TMenu> selectByExample(TMenuExample example);

    TMenu selectByPrimaryKey(TMenuKey key);

    int updateByExampleSelective(@Param("record") TMenu record, @Param("example") TMenuExample example);

    int updateByExample(@Param("record") TMenu record, @Param("example") TMenuExample example);

    int updateByPrimaryKeySelective(TMenu record);

    int updateByPrimaryKey(TMenu record);
}