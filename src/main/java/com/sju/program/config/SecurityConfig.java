package com.sju.program.config;


import com.sju.program.security.JwtAuthenticationTokenFilter;
import com.sju.program.security.provider.BuilderUsernamePasswordAuthrnticationProvider;
import com.sju.program.security.provider.PoliceUsernamePasswordAuthenticationProvider;
import com.sju.program.security.provider.TrafficStaffUsernamePasswordAuthrnticationProvider;
import com.sju.program.service.impl.LogoutSuccessHandlerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

//import com.qhw.demo.security.JwtAuthenticationTokenFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("AdminDetailsServiceImpl")
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Autowired
    @Qualifier("PoliceDetailServiceImpl")
    private UserDetailsService policeDetailsServiceImpl;

    @Autowired
    @Qualifier("BuilderDetailServiceImpl")
    private UserDetailsService builderDetailServiceImpl;

    @Autowired
    @Qualifier("TrafficStaffDetailsServiceImpl")
    private UserDetailsService trafficStaffDetailsServiceImpl;

    @Autowired
    private LogoutSuccessHandlerImpl logoutSuccessHandler;

   // @Autowired
   // private JwtAuthenticationTokenFilter authenticationTokenFilter;

    //@Autowired
    //private JwtInterceptor jwtInterceptor;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 禁用 csrf, 由于使用的是JWT，我们这里不需要csrf
        http.cors().and().csrf().disable();
        // 退出登录处理器
        //http.logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());
        http.authorizeRequests()
                // 对于登录login 允许匿名访问
                .antMatchers("/login/admin").permitAll()
                .antMatchers("/login/police").permitAll()
                .antMatchers("/login/builder").permitAll()
                .antMatchers("/login/traffic").permitAll()
                .antMatchers("/policelogin/test").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/common/upload").permitAll()
                .antMatchers("/common/download").permitAll()
                .antMatchers("/program/builder/export").permitAll()
                .antMatchers("/program/builder/upload").permitAll()
                .antMatchers("/program/staff/upload").permitAll()
                .antMatchers("/program/police/upload").permitAll()
                .antMatchers("/program/admin/t").permitAll()



//                .antMatchers("/program/staff/export").permitAll()
//                .antMatchers("/program/staff/upload").permitAll()
//                .antMatchers("/program/police/upload").permitAll()
//                .antMatchers("/program/police/export").permitAll()


                .antMatchers(
                        HttpMethod.GET,
                        "/*.html",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js"
                ).permitAll()
                .antMatchers("/doc.hml").anonymous()
                .antMatchers("/swagger-ui.html").anonymous()
                .antMatchers("/swagger-resources/**").anonymous()
                .antMatchers("/webjars/**").anonymous()
                .antMatchers("/*/api-docs").anonymous()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(jwtAuthenticationTokenFilter,UsernamePasswordAuthenticationFilter.class);
                http.logout().logoutUrl("/logout").logoutSuccessHandler(logoutSuccessHandler);
    }

    /**
     * 解决 无法直接注入 AuthenticationManager
     *
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception
    {
        return super.authenticationManagerBean();
    }
    /**
     * 强散列哈希加密实现
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    /**
     * 身份认证接口
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.authenticationProvider(getPoliceUsernamePasswordAuthenticationProvider()).userDetailsService(policeDetailsServiceImpl).passwordEncoder(bCryptPasswordEncoder());
        auth.authenticationProvider(getBuilderUsernamePasswordAuthrnticationProvider()).userDetailsService(builderDetailServiceImpl).passwordEncoder(bCryptPasswordEncoder());
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
        auth.authenticationProvider(getTrafficStaffUsernamePasswordAuthrnticationProvider()).userDetailsService(trafficStaffDetailsServiceImpl).passwordEncoder(bCryptPasswordEncoder());

    }

    protected PoliceUsernamePasswordAuthenticationProvider getPoliceUsernamePasswordAuthenticationProvider(){
        PoliceUsernamePasswordAuthenticationProvider policeUsernamePasswordAuthenticationProvider=new PoliceUsernamePasswordAuthenticationProvider();
        policeUsernamePasswordAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
        policeUsernamePasswordAuthenticationProvider.setUserDetailsService(policeDetailsServiceImpl);
        return policeUsernamePasswordAuthenticationProvider;
    }


    protected BuilderUsernamePasswordAuthrnticationProvider getBuilderUsernamePasswordAuthrnticationProvider(){
        BuilderUsernamePasswordAuthrnticationProvider builderUsernamePasswordAuthrnticationProvider=new BuilderUsernamePasswordAuthrnticationProvider();
        builderUsernamePasswordAuthrnticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
        builderUsernamePasswordAuthrnticationProvider.setUserDetailsService(builderDetailServiceImpl);
        return builderUsernamePasswordAuthrnticationProvider;
    }

    protected TrafficStaffUsernamePasswordAuthrnticationProvider getTrafficStaffUsernamePasswordAuthrnticationProvider(){
        TrafficStaffUsernamePasswordAuthrnticationProvider trafficStaffUsernamePasswordAuthrnticationProvider=new TrafficStaffUsernamePasswordAuthrnticationProvider();
        trafficStaffUsernamePasswordAuthrnticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
        trafficStaffUsernamePasswordAuthrnticationProvider.setUserDetailsService(trafficStaffDetailsServiceImpl);
        return trafficStaffUsernamePasswordAuthrnticationProvider;
    }

}
