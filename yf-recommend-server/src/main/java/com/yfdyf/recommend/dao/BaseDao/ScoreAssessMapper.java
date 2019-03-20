package com.yfdyf.recommend.dao.BaseDao;

import com.yfdyf.recommend.entity.BaseBean.ScoreAssess;

public interface ScoreAssessMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(ScoreAssess record);

    int insertSelective(ScoreAssess record);

    ScoreAssess selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(ScoreAssess record);

    int updateByPrimaryKey(ScoreAssess record);
}