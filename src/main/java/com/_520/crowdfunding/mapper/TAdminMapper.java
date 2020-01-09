package com._520.crowdfunding.mapper;

import com._520.crowdfunding.domain.TAdmin;
import com._520.crowdfunding.domain.TAdminExample;
import com._520.crowdfunding.domain.TAdminKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TAdminMapper {
    long countByExample(TAdminExample example);

    int deleteByExample(TAdminExample example);

    int deleteByPrimaryKey(TAdminKey key);

    int insert(TAdmin record);

    int insertSelective(TAdmin record);

    List<TAdmin> selectByExample(TAdminExample example);

    TAdmin selectByPrimaryKey(TAdminKey key);

    int updateByExampleSelective(@Param("record") TAdmin record, @Param("example") TAdminExample example);

    int updateByExample(@Param("record") TAdmin record, @Param("example") TAdminExample example);

    int updateByPrimaryKeySelective(TAdmin record);

    int updateByPrimaryKey(TAdmin record);
}