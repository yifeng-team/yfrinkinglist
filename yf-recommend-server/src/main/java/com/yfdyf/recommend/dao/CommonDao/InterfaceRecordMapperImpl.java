package com.yfdyf.recommend.dao.CommonDao;

import com.yfdyf.recommend.entity.BaseBean.InterfaceRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InterfaceRecordMapperImpl implements com.yfdyf.recommend.dao.BaseDao.InterfaceRecordMapper {

    @Autowired
    private InterfaceRecordMapperImpl interfaceRecordMapper;

    @Override
    public int deleteByPrimaryKey(String uuid) {
        return interfaceRecordMapper.deleteByPrimaryKey(uuid);
    }

    @Override
    public int insert(InterfaceRecord record) {
        return interfaceRecordMapper.insert(record);
    }

    @Override
    public int insertSelective(InterfaceRecord record) {
        return interfaceRecordMapper.insertSelective(record);
    }

    @Override
    public InterfaceRecord selectByPrimaryKey(String uuid) {
        return interfaceRecordMapper.selectByPrimaryKey(uuid);
    }

    @Override
    public int updateByPrimaryKeySelective(InterfaceRecord record) {
        return interfaceRecordMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(InterfaceRecord record) {
        return interfaceRecordMapper.updateByPrimaryKey(record);
    }
}
