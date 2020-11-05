package com.sju.program.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sju.program.domain.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * 登录用户身份权限
 *
 * @author ruoyi
 */
public class LoginUser implements UserDetails
{
    private static final long serialVersionUID = 1L;

    /**
     * 用户唯一标识
     */
    private String token;

    /**
     * 登陆时间
     */
    private Long loginTime;

    /**
     * 过期时间
     */
    private Long expireTime;

    /**
     * 登录IP地址
     */
    private String ipaddr;

    /**
     * 登录地点
     */
    private String loginLocation;

    /**
     * 浏览器类型
     */
    private String browser;

    /**
     * 操作系统
     */
    private String os;

    /**
     * 菜单列表
     */
    private List<Menu> menus;

    /**
     * 用户信息
     */
    private Object user;


    /**
     * 权限列表
     */
    private Set<String> permissions;

    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }

    public Police getPolice() {
        return (Police)user;
    }

    public void setPolice(Police police) {
        this.user = police;
    }

    public String getToken()
    {
        return token;
    }

    public void setToken(String token)
    {
        this.token = token;
    }

    public LoginUser(Admin user)
    {
        this.user=user;
    }

    public LoginUser(Police user)
    {
        this.user=user;
    }

    public LoginUser(Builder user)
    {
        this.user=user;
    }
    public LoginUser(TrafficeStaff user)
    {
        this.user=user;
    }

    public LoginUser(Admin user, List<Menu> menus)
    {
        this.user = user;
        this.menus = menus;
    }

    public LoginUser(Admin user, Set<String> permissions)
    {
        this.user = user;
        this.permissions = permissions;
    }

    public LoginUser(Police police,Set<String> permissions){
        this.user=police;
        this.permissions=permissions;
    }

    public LoginUser(Builder builder, Set<String> permissions){
        this.user=builder;
        this.permissions=permissions;
    }

    public LoginUser(TrafficeStaff trafficeStaff, Set<String> permissions){
        this.user=trafficeStaff;
        this.permissions=permissions;
    }

    @JsonIgnore
    @Override
    public String getPassword()
    {
        return ((BaseUser)user).getUserPassword();
    }

    @Override
    public String getUsername() {
        return ((BaseUser) user).getUserName();
    }
    /**
     * 账户是否未过期,过期无法验证
     */
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    /**
     * 指定用户是否解锁,锁定的用户无法进行身份验证
     *
     * @return
     */
    @JsonIgnore
    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    /**
     * 指示是否已过期的用户的凭据(密码),过期的凭据防止认证
     *
     * @return
     */
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    /**
     * 是否可用 ,禁用的用户不能身份验证
     *
     * @return
     */
    @JsonIgnore
    @Override
    public boolean isEnabled()
    {
        return true;
    }

    public Long getLoginTime()
    {
        return loginTime;
    }

    public void setLoginTime(Long loginTime)
    {
        this.loginTime = loginTime;
    }

    public String getIpaddr()
    {
        return ipaddr;
    }

    public void setIpaddr(String ipaddr)
    {
        this.ipaddr = ipaddr;
    }

    public String getLoginLocation()
    {
        return loginLocation;
    }

    public void setLoginLocation(String loginLocation)
    {
        this.loginLocation = loginLocation;
    }

    public String getBrowser()
    {
        return browser;
    }

    public void setBrowser(String browser)
    {
        this.browser = browser;
    }

    public String getOs()
    {
        return os;
    }

    public void setOs(String os)
    {
        this.os = os;
    }

    public Long getExpireTime()
    {
        return expireTime;
    }

    public void setExpireTime(Long expireTime)
    {
        this.expireTime = expireTime;
    }

    public List<Menu> getMenus()
    {
        return menus;
    }

    public void setMenus(List<Menu> menus)
    {
        this.menus = menus;
    }

    public Object getUser()
    {
        return user;
    }

    public void setUser(Admin user)
    {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return null;
    }
}

