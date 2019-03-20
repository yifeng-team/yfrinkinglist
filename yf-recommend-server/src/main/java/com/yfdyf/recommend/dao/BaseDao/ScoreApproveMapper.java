package com.yfdyf.recommend.dao.BaseDao;

import com.yfdyf.recommend.entity.BaseBean.ScoreApprove;

public interface ScoreApproveMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(ScoreApprove record);

    int insertSelective(ScoreApprove record);

    ScoreApprove selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(ScoreApprove record);

    int updateByPrimaryKey(ScoreApprove record);
}