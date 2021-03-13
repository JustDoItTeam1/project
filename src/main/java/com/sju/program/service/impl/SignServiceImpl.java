package com.sju.program.service.impl;

import com.sju.program.domain.po.SignInfoPo;
import com.sju.program.domain.sign.Sign;
import com.sju.program.domain.sign.SignInfo;
import com.sju.program.domain.vo.SignInfoVo;
import com.sju.program.mapper.sign.SignInfoMapper;
import com.sju.program.mapper.sign.SignMapper;
import com.sju.program.service.SignService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author qhw
 * @date 2021/2/26
 **/
@Service
public class SignServiceImpl implements SignService {

	@Autowired
	private SignInfoMapper signInfoMapper;
	@Autowired
	private SignMapper signMapper;
	@Override
	public List<SignInfoVo> getAllSignInfo() {
		return signInfoMapper.getAllSignInfo();
	}

	@Override
	public int insertSign(Sign sign) {
		return signMapper.insert(sign);
	}

	@Override
	public int insertSignInfo(SignInfoPo signInfoPo) {
		Sign sign=signMapper.getSignByName(signInfoPo.getName());
		SignInfo signInfo=new SignInfo();
		BeanUtils.copyProperties(signInfoPo,signInfo);
		signInfo.setSignId(sign.getSignId());
		return signInfoMapper.insert(signInfo);
	}

	@Override
	public int deleteSignInfo(Long[] id) {
		return signInfoMapper.deleteSignInfo(id);

	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int deleteSign(Long[] id) throws Exception {
		try {
			int result=signMapper.deleteSignById(id);
			return result;
		}catch (Exception e){
			throw new Exception("删除失败");
		}
	}

	@Override
	public List<Sign> getAllSign() {
		return signMapper.getAllSign();
	}

	@Override
	public Sign getSignByName(String name) {
		return signMapper.getSignByName(name);
	}

	@Override
	public int updateSign(Sign sign) {
		return signMapper.updateByPrimaryKey(sign);
	}

	@Override
	public SignInfo getSignInfoByLongitudeAndLatitude(SignInfoPo signInfoPo) {
		SignInfo signInfo=new SignInfo();
		BeanUtils.copyProperties(signInfoPo,signInfo);
		return signInfoMapper.getSignInfoByLongitudeAndLatitude(signInfo);
	}

	@Override
	public Sign getSignById(Long id) {
		return signMapper.getSignById(id);
	}

	@Override
	public SignInfo selectSignInMapById(Long id) {
		return signInfoMapper.selectSignInMapById(id);
	}
}
