package com.sju.program.service;

import com.sju.program.domain.vo.SignDetailVo;
import com.sju.program.domain.vo.SigninfoVo;

import java.util.List;

/**
 * @author qhw
 * @date 2021/2/26
 **/
public interface SignService {
	List<SigninfoVo> getAllSign();

	SignDetailVo getDetailById(String id);
}
