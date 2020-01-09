package com._520.crowdfunding.mapper;

import com._520.crowdfunding.domain.TPermissionResource;
import com._520.crowdfunding.domain.TPermissionResourceExample;
import com._520.crowdfunding.domain.TPermissionResourceKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TPermissionResourceMapper {
    long countByExample(TPermissionResourceExample example);

    int deleteByExample(TPermissionResourceExample example);

    int deleteByPrimaryKey(TPermissionResourceKey key);

    int insert(TPermissionResource record);

    int insertSelective(TPermissionResource record);

    List<TPermissionResource> selectByExample(TPermissionResourceExample example);

    TPermissionResource selectByPrimaryKey(TPermissionResourceKey key);

    int updateByExampleSelective(@Param("record") TPermissionResource record, @Param("example") TPermissionResourceExample example);

    int updateByExample(@Param("record") TPermissionResource record, @Param("example") TPermissionResourceExample example);

    int updateByPrimaryKeySelective(TPermissionResource record);

    int updateByPrimaryKey(TPermissionResource record);
}