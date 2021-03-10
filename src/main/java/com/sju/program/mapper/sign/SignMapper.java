package com.sju.program.mapper.sign;

import com.sju.program.domain.sign.Sign;

import java.util.List;

public interface SignMapper {
    int deleteByPrimaryKey(Long signId);

    int insert(Sign record);

    int insertSelective(Sign record);

    Sign selectByPrimaryKey(Long signId);

    int updateByPrimaryKeySelective(Sign record);

    int updateByPrimaryKey(Sign record);

    List<Sign> getAllSign();

    Sign getSignByName(String name);

    int deleteSignById(Long[] id);
}
