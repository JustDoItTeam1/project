package com.sju.program.mapper.sign;

import com.sju.program.domain.po.SignInfoPo;
import com.sju.program.domain.sign.SignInfo;
import com.sju.program.domain.vo.SignInfoVo;

import java.util.List;

public interface SignInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SignInfo record);

    int insertSelective(SignInfo record);

    SignInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SignInfo record);

    int updateByPrimaryKey(SignInfo record);

    List<SignInfoVo> getAllSignInfo();

    int deleteSignInfo(Long[] id);

    SignInfo getSignInfoByLongitudeAndLatitude(SignInfo signInfo);
}
