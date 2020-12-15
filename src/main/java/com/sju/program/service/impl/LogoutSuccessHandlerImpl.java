package com.sju.program.service.impl;

import com.alibaba.fastjson.JSON;
import com.sju.program.constant.Constants;
import com.sju.program.constant.HttpStatus;
import com.sju.program.domain.model.LoginUser;
import com.sju.program.message.AjaxResult;
import com.sju.program.utils.ServletUtils;
import com.sju.program.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义退出处理类 返回成功
 * @author qhw
 * @date 2020/12/15
 **/
@Configuration
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {

		/**
		 * 退出处理
		 *
		 * @return
		 */
		@Override
		public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
				throws IOException, ServletException
		{

			ServletUtils.renderString(response, JSON.toJSONString(AjaxResult.error(HttpStatus.SUCCESS, "退出成功")));
		}
	}

