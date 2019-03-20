package com.yfdyf.recommend.dao.BaseDao;

import com.yfdyf.recommend.entity.BaseBean.BasePerson;

public interface BasePersonMapper {
    int deleteByPrimaryKey(String personCode);

    int insert(BasePerson record);

    int insertSelective(BasePerson record);

    BasePerson selectByPrimaryKey(String personCode);

    int updateByPrimaryKeySelective(BasePerson record);

    int updateByPrimaryKey(BasePerson record);
}