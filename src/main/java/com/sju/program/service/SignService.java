package com.sju.program.service;

import com.sju.program.domain.po.SignInfoPo;
import com.sju.program.domain.sign.SignInfo;
import com.sju.program.domain.vo.SigninfoVo;

import java.util.List;

/**
 * @author qhw
 * @date 2021/2/26
 **/
public interface SignService {
	List<SignInfo> getAllSign();

	int insertSign(SignInfoPo signInfo);
}
