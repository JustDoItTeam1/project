package com.sju.program.mapper.sign;

import com.sju.program.domain.sign.SignType;

import java.util.List;

public interface SignTypeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SignType record);

    int insertSelective(SignType record);

    SignType selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SignType record);

    int updateByPrimaryKey(SignType record);

    List<SignType> selectAllSignType();

    int deleteById(Long[] id);

    SignType getSignTypeByName(String name);
}
