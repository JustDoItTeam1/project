package com.sju.program.service.impl;

import com.sju.program.domain.vo.SignDetailVo;
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
	public List<SigninfoVo> getAllSign() {
		return signinfoMapper.getAllSign();
	}

	@Override
	public SignDetailVo getDetailById(String id) {
		return signinfoMapper.getDetailById(id);
	}
}
