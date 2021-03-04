package com.sju.program.mapper.sign;

import com.sju.program.domain.po.SignInfoPo;
import com.sju.program.domain.sign.SignInfo;
import com.sju.program.domain.vo.SigninfoVo;

import java.util.List;

public interface SigninfoMapper {

    int insertSign(SignInfoPo record);

    int insertSelective(SignInfo record);

    List<SignInfo> getAllSign();

}
