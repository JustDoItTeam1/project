//package com.sju.program.service.login;
//
//import com.sju.program.domain.Builder;
//import com.sju.program.domain.model.LoginUser;
//import com.sju.program.mapper.BuilderMapper;
//import com.sju.program.service.IBuilderService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import javax.jnlp.PersistenceService;
//
///**
// * @author qhw
// * @date 2021/1/26
// **/
//@Service
//public class BuilderServiceImplTest implements BuilderService{
//	@Autowired
//	private IBuilderService iBuilderService;
//
//	@Autowired
//	private SysPermissionService permissionService;
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		Builder builder=iBuilderService.selectBuilderByUsername(username);
//		return createLoginUser(builder);
//	}
//
//	public UserDetails createLoginUser(Builder builder)
//	{
//		return new LoginUser(builder, permissionService.getMenuPermission(4));
//	}
//}
