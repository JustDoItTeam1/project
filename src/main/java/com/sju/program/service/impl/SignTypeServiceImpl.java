package com.sju.program.service.impl;

import com.sju.program.domain.sign.SignType;
import com.sju.program.mapper.sign.SignTypeMapper;
import com.sju.program.service.SignTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author qhw
 * @date 2021/3/10
 **/
@Service
public class SignTypeServiceImpl implements SignTypeService {

	@Autowired
	private SignTypeMapper signTypeMapper;
	@Override
	public List<SignType> selectAllSignType() {
		return signTypeMapper.selectAllSignType();
	}

	@Override
	public SignType getSignTypeByName(String name) {
		return signTypeMapper.getSignTypeByName(name);
	}

	@Override
	public int insertSignType(SignType signType) {
		return signTypeMapper.insertSelective(signType);
	}

	@Override
	public int updateSignType(SignType signType) {
		return signTypeMapper.updateByPrimaryKey(signType);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int deleteSignType(Long[] id) throws Exception{
		try {
			int result=signTypeMapper.deleteById(id);
			return result;
		}catch (Exception e){
			throw new Exception("删除标牌类型失败");
		}

	}
}
