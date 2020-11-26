package com.sju.program.controller.login;

import com.sju.program.constant.Constants;
import com.sju.program.domain.Admin;
import com.sju.program.domain.Builder;
import com.sju.program.domain.Police;
import com.sju.program.domain.TrafficeStaff;
import com.sju.program.domain.model.LoginBody;
import com.sju.program.domain.model.LoginUser;
import com.sju.program.message.AjaxResult;
import com.sju.program.security.token.BuilderUsernamePasswordAuthticationToken;
import com.sju.program.security.token.PoliceUsernamePasswordAuthenticationToken;
import com.sju.program.security.token.TrafficStaffUsernamePasswordAuthticationToken;
import com.sju.program.service.IAdminService;
import com.sju.program.service.IMenuService;
import com.sju.program.service.IRoleService;
import com.sju.program.service.login.LoginService;
import com.sju.program.service.login.SysPermissionService;
import com.sju.program.service.login.TokenService;
import com.sju.program.utils.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
@Api(tags = "登录接口")
@RestController
public class LoginController  {
	@Autowired
	private LoginService loginService;

	@Autowired
	private IRoleService roleService;

	@Autowired
	private IMenuService menuService;

	@Autowired
	private SysPermissionService permissionService;

	@Autowired
	private TokenService tokenService;

	@Autowired
	private IAdminService userService;

	@Resource
	private AuthenticationManager authenticationManager;



	/**
	 * 登录方法
	 *
	 * @param loginBody 登录信息
	 * @return 结果
	 */
	@ApiOperation(value = "验证用户信息接口",notes = "验证用户信息,生成token")
	@PostMapping("/login")
	public AjaxResult Login(@RequestBody LoginBody loginBody) throws Exception{
			AjaxResult ajax=AjaxResult.success();
			Map<String,Object> playload=new HashMap<>();
			Authentication authentication = null;
			if (loginBody.getUsername().startsWith("admin")){
				try
				{
					// 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
					authentication = authenticationManager
							.authenticate(new UsernamePasswordAuthenticationToken(loginBody.getUsername(), loginBody.getPassword()));
				}
				catch (Exception e)
				{
					return AjaxResult.error("认证出错");
				}
				ArrayList<String> arrayList=new ArrayList<String>();
				arrayList.add("*:*:*");
				LoginUser loginUser=(LoginUser) authentication.getPrincipal();
				try {
					Admin admin=(Admin) loginUser.getUser();
					if (admin==null||admin.getAdminDeleteFlag().equals("del")){
						return AjaxResult.error("用户不存在");
					}
					playload.put("userId",admin.getAdminId());
					playload.put("userName",admin.getAdminUsername());
					playload.put("delete_flag",admin.getAdminDeleteFlag());
					playload.put("permissions",arrayList);
				}catch (ClassCastException e){
					return AjaxResult.error("用户验证失败");
				}
				playload.put("authenticate",1);//管理员权限标识为1
				String token= JwtUtils.getToken(playload);
				ajax.put(Constants.TOKEN, token);
				return ajax;
			}
			if(loginBody.getUsername().startsWith("TS")){
				try {
					// 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
					authentication = authenticationManager
							.authenticate(new TrafficStaffUsernamePasswordAuthticationToken(loginBody.getUsername(), loginBody.getPassword()));
				} catch (Exception e) {
					return AjaxResult.error("用户验证失败");
				}
				//LoginUser loginUser = (LoginUser) authentication.getPrincipal();
				//System.out.println(authentication);
				LoginUser loginUser = (LoginUser) authentication.getPrincipal();
				try {
					TrafficeStaff trafficeStaff = (TrafficeStaff) loginUser.getUser();
					if (trafficeStaff == null || trafficeStaff.getTrafficDeleteFlag().equals("del")) {
						return AjaxResult.error("用户不存在");
					}
					playload.put("userId", trafficeStaff.getTrafficId());
					playload.put("userName", trafficeStaff.getTrafficName());
					playload.put("delete_flag", trafficeStaff.getTrafficDeleteFlag());
					playload.put("permissions", loginUser.getPermissions());
				}catch (ClassCastException e){
					return AjaxResult.error("用户验证失败");
				}
				playload.put("authenticate",3);
				String token=JwtUtils.getToken(playload);
				ajax.put(Constants.TOKEN, token);
				return ajax;
			}
			if(loginBody.getUsername().startsWith("BU")){
				try
				{
					// 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
					authentication = authenticationManager
							.authenticate(new BuilderUsernamePasswordAuthticationToken(loginBody.getUsername(), loginBody.getPassword()));
				}
				catch (Exception e)
				{
					return AjaxResult.error("用户验证失败");
				}
				try {
					LoginUser loginUser = (LoginUser) authentication.getPrincipal();
					Builder builder = (Builder) loginUser.getUser();
					if (builder == null || builder.getBuilderDeleteFlag().equals("del")) {
						return AjaxResult.error("用户不存在");
					}
					playload.put("userId", builder.getBuilderId());
					playload.put("userName", builder.getBuilderName());
					playload.put("builder_username", builder.getBuilderUsername());
					playload.put("builder_corporate", builder.getBuilderCorporate());
					playload.put("builder_phone", builder.getBuilderPhone());
					playload.put("builder_address", builder.getBuilderAddress());
					playload.put("builder_enterprise_number", builder.getBuilderEnterpriseNumber());
					//playload.put("builder_update_flag", builder.getBuilderUpdateFlag());
					playload.put("delete_flag", builder.getBuilderDeleteFlag());
					playload.put("permissions", loginUser.getPermissions());
				}catch (ClassCastException e){
					return AjaxResult.error("用户验证失败");
				}
				playload.put("authenticate",4);
				String token=JwtUtils.getToken(playload);
				ajax.put(Constants.TOKEN, token);
				return ajax;
			}
			if (loginBody.getUsername().startsWith("PO")){
				try {
					// 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
					authentication = authenticationManager
							.authenticate(new PoliceUsernamePasswordAuthenticationToken(loginBody.getUsername(), loginBody.getPassword()));
				} catch (Exception e) {
					return AjaxResult.error("用户验证失败");
				}
				//LoginUser loginUser = (LoginUser) authentication.getPrincipal();
				//System.out.println(authentication);
				LoginUser loginUser = (LoginUser) authentication.getPrincipal();
				try {
					Police police = (Police) loginUser.getUser();
					if (police == null || police.getPoliceDeleteFlag().equals("del")) {
						return AjaxResult.error("用户不存在");
					}
					playload.put("userId", police.getPoliceId());
					playload.put("userName", police.getPoliceName());
					playload.put("delete_flag", police.getPoliceDeleteFlag());
					playload.put("permissions", loginUser.getPermissions());
				}catch (ClassCastException e){
					return AjaxResult.error("用户验证失败");
				}
				playload.put("authenticate",2);
				String token=JwtUtils.getToken(playload);
				ajax.put(Constants.TOKEN, token);
				return ajax;
			}
			return AjaxResult.error();
		}

}

