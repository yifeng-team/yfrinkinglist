package com.yfdyf.recommend.dao.CommonDao;

import com.yfdyf.recommend.dao.BaseDao.ScoreApproveMapper;
import com.yfdyf.recommend.entity.BaseBean.ScoreApprove;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ScoreApproveMapperImpl implements ScoreApproveMapper {

    @Autowired
    private ScoreApproveMapper scoreApproveMapper;

    @Override
    public int deleteByPrimaryKey(String uuid) {
        return scoreApproveMapper.deleteByPrimaryKey(uuid);
    }

    @Override
    public int insert(ScoreApprove record) {
        return scoreApproveMapper.insert(record);
    }

    @Override
    public int insertSelective(ScoreApprove record) {
        return scoreApproveMapper.insertSelective(record);
    }

    @Override
    public ScoreApprove selectByPrimaryKey(String uuid) {
        return scoreApproveMapper.selectByPrimaryKey(uuid);
    }

    @Override
    public int updateByPrimaryKeySelective(ScoreApprove record) {
        return scoreApproveMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(ScoreApprove record) {
        return scoreApproveMapper.updateByPrimaryKey(record);
    }
}
