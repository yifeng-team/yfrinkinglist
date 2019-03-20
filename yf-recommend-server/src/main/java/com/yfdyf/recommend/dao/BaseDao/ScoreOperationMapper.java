package com.yfdyf.recommend.dao.BaseDao;


import com.yfdyf.recommend.entity.BaseBean.ScoreOperation;

public interface ScoreOperationMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(ScoreOperation record);

    int insertSelective(ScoreOperation record);

    ScoreOperation selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(ScoreOperation record);

    int updateByPrimaryKey(ScoreOperation record);
}