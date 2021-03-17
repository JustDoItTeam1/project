package com.sju.program.service;

import com.sju.program.domain.po.SignInfoPo;
import com.sju.program.domain.sign.Sign;
import com.sju.program.domain.sign.SignInfo;
import com.sju.program.domain.vo.SignInfoVo;

import java.util.List;

/**
 * @author qhw
 * @date 2021/2/26
 **/
public interface SignService {
	List<SignInfoVo> getAllSignInfo();

	List<Sign> getAllSign();

	int insertSign(Sign sign);

	int insertSignInfo(SignInfoPo signInfo);

	int deleteSignInfo(Long[] id);

	int deleteSign(Long[] id) throws Exception;

	Sign getSignByName(String name);

	int updateSign(Sign sign);

	SignInfo getSignInfoByLongitudeAndLatitude(SignInfoPo signInfoPo);

	Sign getSignById(Long id);

	SignInfo selectSignInMapById(Long id);

	List<SignInfoVo> selectSignInfoByName(List<SignInfoVo> list,String s);

	List<SignInfoVo> getSignInfoByBuilderId(Long id);
}
