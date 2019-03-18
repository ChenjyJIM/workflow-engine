package com.graduate.engine.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.graduate.engine.exception.BusinessException;
import com.graduate.engine.mapper.GuestMapper;
import com.graduate.engine.mapper.LoginMapper;
import com.graduate.engine.mapper.PersonMemberMapper;
import com.graduate.engine.model.Guest;
import com.graduate.engine.model.Login;
import com.graduate.engine.model.PersonMember;
import com.graduate.engine.request.PasswordChangeRequest;
import com.graduate.engine.request.RegisterRequest;
import com.graduate.engine.service.LoginService;
import com.graduate.engine.utils.BeanUtils;
import com.graduate.engine.utils.DateUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

/**
 * 注册登录相关service
 * @author jimmy
 */
@Service
public class LoginServiceImpl implements LoginService {

    private static final Integer MEMBERID_PREFIX = 201900000;

    @Resource
    private LoginMapper loginMapper;
    @Resource
    private PersonMemberMapper personMemberMapper;
    @Resource
    private GuestMapper guestMapper;

    public int add(RegisterRequest registerRequest) {
        String passwordHash = passwordToHash(registerRequest.getLoginPassword());
        Login login = new Login();
        login.setLoginName(registerRequest.getLoginName());
        login.setLoginPassword(passwordHash);
        if (registerRequest.getType() == 1) {
            PersonMember personMember = BeanUtils.copyBean(registerRequest, PersonMember.class);
            try {
                personMember.setBirthday(DateUtils.getTimeStampByUTC(registerRequest.getBirthday()));
            } catch (ParseException e) {
                //pass
            }
            Long memberId = MEMBERID_PREFIX + System.currentTimeMillis() % 100000;
            personMember.setPersonMemberId(memberId);
            personMember.setPersonMemberDate(DateUtils.getCurrentSecondTimestamp());
            // 此处检查memberId是否重复
            if (personMemberMapper.selectByPersonMemberID(memberId) != null) {
                try {
                    // 主线程暂停一会儿，防止速度太快生成的memberId重复
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                }
                Long anotherMemberId = MEMBERID_PREFIX + System.currentTimeMillis() % 100000;
                personMember.setPersonMemberId(anotherMemberId);
                personMemberMapper.insertSelective(personMember);
                login.setPersonId(personMemberMapper.selectByPersonMemberID(anotherMemberId).getPersonId());
            }else {
                System.out.println(personMemberMapper.insertSelective(personMember));
                login.setPersonId(personMemberMapper.selectByPersonMemberID(memberId).getPersonId());
            }
        }else if(registerRequest.getType() == 2){
            Guest guest = new Guest();
            guest.setGuestEmail(registerRequest.getMail());
            guest.setGuestName(registerRequest.getName());
            guest.setMemo(registerRequest.getMemo());
            guest.setGuestPhone(registerRequest.getPhone1());
            guest.setGuestSex(registerRequest.getSex());
            guest.setExpirePeriod(30);
            guest.setRegisterDate(System.currentTimeMillis()/1000);
            guest.setLastLogin(System.currentTimeMillis()/1000);
            System.out.println(guestMapper.insertSelective(guest));
            login.setGuestId(guestMapper.selectByGuestName(guest.getGuestName()).getGuestId());
        }else {
            throw new BusinessException("不支持类型！请检查");
        }
        return loginMapper.addLoginUser(login);
    }

    public Login findByName(String name) {
        Login login = new Login();
        login.setLoginName(name);
        return loginMapper.findLoginUser(login);
    }

    public Login findById(Long id) {
        Login login = new Login();
        login.setLoginId(id);
        return loginMapper.findLoginUser(login);
    }


    public int updateLastLogin(final Long guestId) {
        final Long now = System.currentTimeMillis() / 1000;
        Guest guest = new Guest(){
            {
                setLastLogin(now);
                setGuestId(guestId);
            }
        };
        return guestMapper.updateByPrimaryKeySelective(guest);
    }

    public int passwordChange(PasswordChangeRequest request) {
        Login login = new Login();
        login.setLoginId(request.getLoginId());
        Login userInDataBase = loginMapper.findLoginUser(login);
        if (passwordToHash(request.getPassword()).equals(userInDataBase.getLoginPassword())) {
            login.setLoginPassword(passwordToHash(request.getNewPassword()));
        }else {
            throw new BusinessException("原密码错误！");
        }
        return loginMapper.updatePassword(login);
    }

    //用于密码加密存储
    private String passwordToHash(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.update(password.getBytes());
            byte[] src = digest.digest();
            StringBuilder stringBuilder = new StringBuilder();
            // 字节数组转16进制字符串
            for (byte aSrc : src) {
                String s = Integer.toHexString(aSrc & 0xFF);
                if (s.length() < 2) {
                    stringBuilder.append('0');
                }
                stringBuilder.append(s);
            }
            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException ignore) {
        }
        return null;
    }

    public boolean comparePassword(Login loginUser, Login userInDataBase) {
        // 将用户提交的密码转换为 hash
        return passwordToHash(loginUser.getLoginPassword())
                // 数据库中的 password 已经是 hash，不用转换
                .equals(userInDataBase.getLoginPassword());
    }

    /**
     * 登录后返回token给前端，前端请求时将token放到header里
     * @param login
     * @return
     */
    public String getToken(Login login) {
        String token = "";
        try {
            token = JWT.create()
                    // 将 user id 保存到 token 里面
                    .withAudience(login.getLoginId().toString())
                    // 以 password 作为 token 的密钥
                    .sign(Algorithm.HMAC256(login.getLoginPassword()));
        } catch (UnsupportedEncodingException ignore) {
        }
        return token;
    }

}
