package com.yfdyf.recommend.dao.BaseDao;

import com.yfdyf.recommend.entity.BaseBean.ScoreDevelopment;

public interface ScoreDevelopmentMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(ScoreDevelopment record);

    int insertSelective(ScoreDevelopment record);

    ScoreDevelopment selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(ScoreDevelopment record);

    int updateByPrimaryKey(ScoreDevelopment record);
}