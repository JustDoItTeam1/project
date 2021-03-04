package com.sju.program.service.impl;

import com.sju.program.domain.po.SignInfoPo;
import com.sju.program.domain.sign.SignInfo;
import com.sju.program.domain.vo.SigninfoVo;
import com.sju.program.mapper.sign.SigninfoMapper;
import com.sju.program.service.SignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author qhw
 * @date 2021/2/26
 **/
@Service
public class SignServiceImpl implements SignService {

	@Autowired
	private SigninfoMapper signinfoMapper;
	@Override
	public List<SignInfo> getAllSign() {
		return signinfoMapper.getAllSign();
	}

	@Override
	public int insertSign(SignInfoPo signInfo) {
		return signinfoMapper.insertSign(signInfo);
	}
}
