package com._520.crowdfunding.mapper;

import com._520.crowdfunding.domain.TTransaction;
import com._520.crowdfunding.domain.TTransactionExample;
import com._520.crowdfunding.domain.TTransactionKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TTransactionMapper {
    long countByExample(TTransactionExample example);

    int deleteByExample(TTransactionExample example);

    int deleteByPrimaryKey(TTransactionKey key);

    int insert(TTransaction record);

    int insertSelective(TTransaction record);

    List<TTransaction> selectByExample(TTransactionExample example);

    TTransaction selectByPrimaryKey(TTransactionKey key);

    int updateByExampleSelective(@Param("record") TTransaction record, @Param("example") TTransactionExample example);

    int updateByExample(@Param("record") TTransaction record, @Param("example") TTransactionExample example);

    int updateByPrimaryKeySelective(TTransaction record);

    int updateByPrimaryKey(TTransaction record);
}