package com.yfdyf.recommend.dao.BaseDao;


import com.yfdyf.recommend.entity.BaseBean.TimeWorking;

public interface TimeWorkingMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(TimeWorking record);

    int insertSelective(TimeWorking record);

    TimeWorking selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(TimeWorking record);

    int updateByPrimaryKey(TimeWorking record);
}