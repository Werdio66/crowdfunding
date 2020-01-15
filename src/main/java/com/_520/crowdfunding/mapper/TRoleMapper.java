package com._520.crowdfunding.mapper;

import com._520.crowdfunding.domain.TRole;
import com._520.crowdfunding.domain.TRoleExample;
import com._520.crowdfunding.domain.TRoleKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TRoleMapper {
    long countByExample(TRoleExample example);

    int deleteByExample(TRoleExample example);

    int deleteByPrimaryKey(TRoleKey key);

    int insert(TRole record);

    int insertSelective(TRole record);

    List<TRole> selectByExample(TRoleExample example);

    TRole selectByPrimaryKey(TRoleKey key);

    int updateByExampleSelective(@Param("record") TRole record, @Param("example") TRoleExample example);

    int updateByExample(@Param("record") TRole record, @Param("example") TRoleExample example);

    int updateByPrimaryKeySelective(TRole record);

    int updateByPrimaryKey(TRole record);

    List<TRole> listRoleByAdminId(Integer id);
}