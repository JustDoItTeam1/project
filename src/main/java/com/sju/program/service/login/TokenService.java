package com.sju.program.service.login;

import com.sju.program.constant.Constants;
import com.sju.program.domain.*;
import com.sju.program.domain.model.BaseUser;
import com.sju.program.domain.model.LoginUser;
import com.sju.program.service.IProjectService;
import com.sju.program.service.IRectificationInfoService;
import com.sju.program.service.ISiegeSchemeService;
import com.sju.program.utils.JwtUtils;
import com.sju.program.utils.ServletUtils;
import com.sju.program.utils.StringUtils;
import com.sju.program.utils.uuid.IdUtils;
import eu.bitwalker.useragentutils.UserAgent;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.TimeUnit;

//import com.qhw.demo.utils.ip.AddressUtils;
//import com.qhw.demo.utils.ip.IpUtils;

/**
 * token验证处理
 *
 * @author ruoyi
 */
@Component
public class TokenService
{
    // 令牌自定义标识
    @Value("${token.header}")
    private String header;

    // 令牌秘钥
    @Value("${token.secret}")
    private String secret;

    // 令牌有效期（默认30分钟）
    @Value("${token.expireTime}")
    private int expireTime;


    protected static final long MILLIS_SECOND = 1000;

    protected static final long MILLIS_MINUTE = 60 * MILLIS_SECOND;

    private static final Long MILLIS_MINUTE_TEN = 20 * 60 * 1000L;

    @Autowired
    ISiegeSchemeService siegeSchemeService;
    @Autowired
    IRectificationInfoService rectificationInfoService;
    @Autowired
    IProjectService projectService;

    public List<SiegeScheme> getLoginUserSiegeScheme(LoginUser loginUser){
        List<SiegeScheme> list=null;
        BaseUser baseUser=(BaseUser) loginUser.getUser();
        switch (baseUser.getAuthenticate()){
            case 1:
            case 3:
                list=siegeSchemeService.selectAllSiegeSchemeList();
                break;
            case 4:
                list= siegeSchemeService.selectSiegeSchemeByBuilderId(baseUser.getId());
                break;
            case 2:
                list=siegeSchemeService.selectPassSiegeScheme();
                break;

        }
        return list;
    }
    public List<Project> getLoginUserProject(LoginUser loginUser){
        List<Project> list = null;
        BaseUser baseUser = (BaseUser)loginUser.getUser();
        switch(baseUser.getAuthenticate()){
            case 1:
            case 3:
                list=projectService.selectAllProjectList();
                break;
            case 4:
                list= projectService.selectProjectByBuilderId(baseUser.getId());
                break;
        }
        return list;
    }

     public List<RectificationInfo> getLoginUserRectificationInfo(LoginUser loginUser){
         List<RectificationInfo> rectificationInfoList=null;
         BaseUser baseUser=(BaseUser) loginUser.getUser();
         switch (baseUser.getAuthenticate()){
             case 1:
             case 2:
             case 3:
                 rectificationInfoList=rectificationInfoService.selectRectificationInfoList();
                 break;
             case 4:
                 rectificationInfoList= rectificationInfoService.selectRectificationInfoByBuilderId(baseUser.getId());
                 break;
         }
        return rectificationInfoList;
     }


    /**
     * 获取用户身份信息
     *
     * @return 用户信息
     */
    public LoginUser getLoginUser(HttpServletRequest request)
    {
        // 获取请求携带的令牌
        String token = request.getHeader("token");
        if (StringUtils.isNotEmpty(token))
        {
            Claims claims = JwtUtils.parseToken(token);

            //String uuid = (String) claims.get(Constants.LOGIN_USER_KEY);
           // String userKey = getTokenKey(uuid);
           // LoginUser user = redisCache.getCacheObject(userKey);
            // 解析用户信息
            Object userId=(claims.get("userId"));
            String userName=(String)claims.get("userName");
            String delete_flag=(String)claims.get("delete_flag");
            Object authenticate=claims.get("authenticate");
            System.out.println(claims.get("permissions").getClass());
            ArrayList<String> permissions=(ArrayList<String>)claims.get("permissions");
            System.out.println(permissions.toString());
            if(Integer.valueOf(String.valueOf(authenticate))==1){
                Admin user=new Admin();
                user.setAdminId(Long.valueOf(String.valueOf(userId)) );
                user.setAdminUsername(userName);
                user.setAdminDeleteFlag(delete_flag);
                user.setAuthenticate(Integer.valueOf(String.valueOf(authenticate)));
               // user.setPermissions(permissions);
                LoginUser loginUser=new LoginUser(user);
                loginUser.setPermissions(permissions);
                return loginUser;
            }
            if(Integer.valueOf(String.valueOf(authenticate))==2){
                Police police=new Police();
                police.setPoliceId(Long.valueOf(String.valueOf(userId)) );
                police.setPoliceName(userName);
                police.setPoliceDeleteFlag(delete_flag);
                police.setAuthenticate(Integer.valueOf(String.valueOf(authenticate)));
                police.setPermissions(permissions);
                LoginUser loginUser=new LoginUser(police);
                loginUser.setPermissions(permissions);
                return loginUser;
            }
            if(Integer.valueOf(String.valueOf(authenticate))==3){
                TrafficeStaff trafficeStaff=new TrafficeStaff();
                trafficeStaff.setTrafficId(Long.valueOf(String.valueOf(userId)) );
                trafficeStaff.setTrafficName(userName);
                trafficeStaff.setTrafficDeleteFlag(delete_flag);
                trafficeStaff.setPermissions(permissions);
                trafficeStaff.setAuthenticate(Integer.valueOf(String.valueOf(authenticate)));
                LoginUser loginUser=new LoginUser(trafficeStaff);
                loginUser.setPermissions(permissions);
                return loginUser;
            }
            if(Integer.valueOf(String.valueOf(authenticate))==4){
                String builder_username=(String) claims.get("builder_username");
                String builder_corporate=(String) claims.get("builder_corporate");
                String builder_phone=(String) claims.get("builder_phone");
                String builder_address=(String) claims.get("builder_address");
                String builder_enterprise_number=(String) claims.get("builder_enterprise_number");
                //String builder_update_flag=(String) claims.get("builder_update_flag");
                Builder builder=new Builder();
                builder.setBuilderId(Long.valueOf(String.valueOf(userId)) );
                builder.setBuilderUsername(builder_username);
                builder.setBuilderName(userName);
                builder.setBuilderDeleteFlag(delete_flag);
                builder.setAuthenticate(Integer.valueOf(String.valueOf(authenticate)));
                builder.setUserName(builder_username);
                builder.setBuilderCorporate(builder_corporate);
                builder.setBuilderPhone(builder_phone);
                builder.setBuilderAddress(builder_address);
                builder.setBuilderEnterpriseNumber(builder_enterprise_number);
                builder.setPermissions(permissions);
               // builder.getBuilderUpdateFlag(builder_update_flag);
                LoginUser loginUser=new LoginUser(builder);
                loginUser.setPermissions(permissions);
                return loginUser;
            }

        }
        return null;
    }

    /**
     * 设置用户身份信息
     */
    public void setLoginUser(LoginUser loginUser)
    {
        if (StringUtils.isNotNull(loginUser) && StringUtils.isNotEmpty(loginUser.getToken()))
        {
            refreshToken(loginUser);
        }
    }

    /**
     * 删除用户身份信息
     */
//    public void delLoginUser(String token)
//    {
//        if (StringUtils.isNotEmpty(token))
//        {
//            String userKey = getTokenKey(token);
//            redisCache.deleteObject(userKey);
//        }
//    }

    /**
     * 创建令牌
     *
     * @param loginUser 用户信息
     * @return 令牌
     */
    public String createToken(LoginUser loginUser)
    {
        String token = IdUtils.fastUUID();
        loginUser.setToken(token);
        setUserAgent(loginUser);
        refreshToken(loginUser);

        Map<String, Object> claims = new HashMap<>();
        claims.put(Constants.LOGIN_USER_KEY, token);
        return createToken(claims);
    }

    /**
     * 验证令牌有效期，相差不足20分钟，自动刷新缓存
     *
     * @param loginUser
     * @return 令牌
     */
    public void verifyToken(LoginUser loginUser)
    {
        long expireTime = loginUser.getExpireTime();
        long currentTime = System.currentTimeMillis();
        if (expireTime - currentTime <= MILLIS_MINUTE_TEN)
        {
            refreshToken(loginUser);
        }
    }

    /**
     * 刷新令牌有效期
     *
     * @param loginUser 登录信息
     */
    public void refreshToken(LoginUser loginUser)
    {
        loginUser.setLoginTime(System.currentTimeMillis());
        loginUser.setExpireTime(loginUser.getLoginTime() + expireTime * MILLIS_MINUTE);
        // 根据uuid将loginUser缓存
        String userKey = getTokenKey(loginUser.getToken());
//        redisCache.setCacheObject(userKey, loginUser, expireTime, TimeUnit.MINUTES);
    }

    /**
     * 设置用户代理信息
     *
     * @param loginUser 登录信息
     */
    public void setUserAgent(LoginUser loginUser)
    {
        UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
        //String ip = IpUtils.getIpAddr(ServletUtils.getRequest());
        //loginUser.setIpaddr(ip);
       // loginUser.setLoginLocation(AddressUtils.getRealAddressByIP(ip));
        loginUser.setBrowser(userAgent.getBrowser().getName());
        loginUser.setOs(userAgent.getOperatingSystem().getName());
    }

    /**
     * 从数据声明生成令牌
     *
     * @param claims 数据声明
     * @return 令牌
     */
    private String createToken(Map<String, Object> claims)
    {
        String token = Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret).compact();
        return token;
    }

    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    private Claims parseToken(String token)
    {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 从令牌中获取用户名
     *
     * @param token 令牌
     * @return 用户名
     */
    public String getUsernameFromToken(String token)
    {
        Claims claims = parseToken(token);
        return claims.getSubject();
    }

    /**
     * 获取请求token
     *
     * @param request
     * @return token
     */
    private String getToken(HttpServletRequest request)
    {
        String token = request.getHeader(header);
        if (StringUtils.isNotEmpty(token) && token.startsWith(Constants.TOKEN_PREFIX))
        {
            token = token.replace(Constants.TOKEN_PREFIX, "");
        }
        return token;
    }

    private String getTokenKey(String uuid)
    {
        return Constants.LOGIN_TOKEN_KEY + uuid;
    }
}

