package com.graduate.engine.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.graduate.engine.annotation.LoginRequired;
import com.graduate.engine.exception.BusinessException;
import com.graduate.engine.model.Login;
import com.graduate.engine.service.LoginService;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;


/**
 * @author jimmy
 * @Description: API 的拦截器, 做相关鉴权
 */
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Resource
    private LoginService loginService;


    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        // 判断接口是否需要登录
        LoginRequired methodAnnotation = method.getAnnotation(LoginRequired.class);
        // 有 @LoginRequired 注解，需要认证
        if (methodAnnotation != null) {
            // 执行认证,从 http 请求头中取出 token
            String token = request.getHeader("token");
            if (token == null) {
                throw new BusinessException("无token，请重新登录");
            }
            Long loginId;
            try {
                // 获取 token 中的 user id
                loginId = Long.parseLong(JWT.decode(token).getAudience().get(0));
            } catch (JWTDecodeException e) {
                throw new BusinessException("token无效，请重新登录");
            }
            Login user = loginService.findById(loginId);
            if (user == null) {
                throw new BusinessException("用户不存在，请重新登录");
            }
            // 验证 token
            try {
                JWTVerifier verifier =  JWT.require(Algorithm.HMAC256(user.getLoginPassword())).build();
                try {
                    verifier.verify(token);
                } catch (JWTVerificationException e) {
                    throw new BusinessException("token无效，请重新登录");
                }
            } catch (UnsupportedEncodingException ignore) {}
            request.setAttribute("currentUser", user);
            return true;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }
}
