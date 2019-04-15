package com.graduate.engine.shiro;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.graduate.engine.exception.BusinessException;
import com.graduate.engine.model.Login;
import com.graduate.engine.service.ShiroService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.Set;

/**
 * shiro 身份认证
 */
@Component
public class MyShiroRealm extends AuthorizingRealm {
    @Resource
    private ShiroService shiroService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof ShiroToken;
    }

    /**
     * 授权(验证权限时调用)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Login login = (Login)principalCollection.getPrimaryPrincipal();
        Long userId = login.getLoginId();

        //用户权限列表
        Set<String> permsSet = shiroService.getUserPermissions(userId);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permsSet);
        return info;
    }

    /**
     * 认证(登录时调用)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String accessToken = (String) authenticationToken.getPrincipal();
        long loginId;
        try {
            // 获取 token 中的 user id
            loginId = Long.parseLong(JWT.decode(accessToken).getAudience().get(0));
        } catch (JWTDecodeException e) {
            throw new BusinessException("token无效，请重新登录");
        }
        Login user = shiroService.queryUser(loginId);
        if (user == null) {
            throw new BusinessException("用户不存在，请重新登录");
        }
        // 验证 token
        try {
            JWTVerifier verifier =  JWT.require(Algorithm.HMAC256(user.getLoginPassword())).build();
            try {
                verifier.verify(accessToken);
            } catch (JWTVerificationException e) {
                throw new BusinessException("无效token，请重新登录");
            }
        } catch (UnsupportedEncodingException ignore) {}
        return new SimpleAuthenticationInfo(user, accessToken, getName());
    }
}
