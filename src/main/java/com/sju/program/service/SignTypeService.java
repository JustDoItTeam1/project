package com.sju.program.service;

import com.sju.program.domain.sign.SignType;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author qhw
 * @date 2021/3/10
 **/
@Service
public interface SignTypeService {

	List<SignType> selectAllSignType();

	SignType getSignTypeByName(String name);

	int insertSignType(SignType signType);

	int updateSignType(SignType signType);

	int deleteSignType(Long[] id) throws Exception;
}
