package com._520.crowdfunding.mapper;

import com._520.crowdfunding.domain.TAdvertisement;
import com._520.crowdfunding.domain.TAdvertisementExample;
import com._520.crowdfunding.domain.TAdvertisementKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TAdvertisementMapper {
    long countByExample(TAdvertisementExample example);

    int deleteByExample(TAdvertisementExample example);

    int deleteByPrimaryKey(TAdvertisementKey key);

    int insert(TAdvertisement record);

    int insertSelective(TAdvertisement record);

    List<TAdvertisement> selectByExample(TAdvertisementExample example);

    TAdvertisement selectByPrimaryKey(TAdvertisementKey key);

    int updateByExampleSelective(@Param("record") TAdvertisement record, @Param("example") TAdvertisementExample example);

    int updateByExample(@Param("record") TAdvertisement record, @Param("example") TAdvertisementExample example);

    int updateByPrimaryKeySelective(TAdvertisement record);

    int updateByPrimaryKey(TAdvertisement record);
}