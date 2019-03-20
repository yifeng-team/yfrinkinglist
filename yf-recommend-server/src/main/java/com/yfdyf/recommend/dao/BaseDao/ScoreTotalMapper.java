package com.yfdyf.recommend.dao.BaseDao;


import com.yfdyf.recommend.entity.BaseBean.ScoreTotal;

public interface ScoreTotalMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(ScoreTotal record);

    int insertSelective(ScoreTotal record);

    ScoreTotal selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(ScoreTotal record);

    int updateByPrimaryKey(ScoreTotal record);
}