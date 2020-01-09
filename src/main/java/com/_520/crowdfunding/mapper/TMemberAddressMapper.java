package com._520.crowdfunding.mapper;

import com._520.crowdfunding.domain.TMemberAddress;
import com._520.crowdfunding.domain.TMemberAddressExample;
import com._520.crowdfunding.domain.TMemberAddressKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TMemberAddressMapper {
    long countByExample(TMemberAddressExample example);

    int deleteByExample(TMemberAddressExample example);

    int deleteByPrimaryKey(TMemberAddressKey key);

    int insert(TMemberAddress record);

    int insertSelective(TMemberAddress record);

    List<TMemberAddress> selectByExample(TMemberAddressExample example);

    TMemberAddress selectByPrimaryKey(TMemberAddressKey key);

    int updateByExampleSelective(@Param("record") TMemberAddress record, @Param("example") TMemberAddressExample example);

    int updateByExample(@Param("record") TMemberAddress record, @Param("example") TMemberAddressExample example);

    int updateByPrimaryKeySelective(TMemberAddress record);

    int updateByPrimaryKey(TMemberAddress record);
}