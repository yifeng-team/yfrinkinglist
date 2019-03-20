package com.yfdyf.recommend.dao.jsonDao;

public interface AcceptCardMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(AcceptCard record);

    int insertSelective(AcceptCard record);

    AcceptCard selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(AcceptCard record);

    int updateByPrimaryKey(AcceptCard record);
}