package com.sju.program.mapper.sign;

import com.sju.program.domain.sign.SignInfo;
import com.sju.program.domain.vo.SignDetailVo;
import com.sju.program.domain.vo.SigninfoVo;

import java.util.List;

public interface SigninfoMapper {
    int insert(SignInfo record);

    int insertSelective(SignInfo record);

    List<SigninfoVo> getAllSign();

    SignDetailVo getDetailById(String id);
}
