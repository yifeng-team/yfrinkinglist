package com.yfdyf.recommend.dao.BaseDao;

import com.yfdyf.recommend.entity.BaseBean.InterfaceRecord;

public interface InterfaceRecordMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(InterfaceRecord record);

    int insertSelective(InterfaceRecord record);

    InterfaceRecord selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(InterfaceRecord record);

    int updateByPrimaryKey(InterfaceRecord record);
}