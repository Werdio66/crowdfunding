package com._520.crowdfunding.mapper;

import com._520.crowdfunding.domain.TOrder;
import com._520.crowdfunding.domain.TOrderExample;
import com._520.crowdfunding.domain.TOrderKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TOrderMapper {
    long countByExample(TOrderExample example);

    int deleteByExample(TOrderExample example);

    int deleteByPrimaryKey(TOrderKey key);

    int insert(TOrder record);

    int insertSelective(TOrder record);

    List<TOrder> selectByExample(TOrderExample example);

    TOrder selectByPrimaryKey(TOrderKey key);

    int updateByExampleSelective(@Param("record") TOrder record, @Param("example") TOrderExample example);

    int updateByExample(@Param("record") TOrder record, @Param("example") TOrderExample example);

    int updateByPrimaryKeySelective(TOrder record);

    int updateByPrimaryKey(TOrder record);
}