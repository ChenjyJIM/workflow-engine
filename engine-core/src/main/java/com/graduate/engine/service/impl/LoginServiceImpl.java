package com.graduate.engine.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduate.engine.exception.BusinessException;
import com.graduate.engine.mapper.GuestMapper;
import com.graduate.engine.mapper.LoginMapper;
import com.graduate.engine.mapper.PersonMemberMapper;
import com.graduate.engine.model.Guest;
import com.graduate.engine.model.Login;
import com.graduate.engine.model.PersonMember;
import com.graduate.engine.model.PersonMemberApplicationDetails;
import com.graduate.engine.model.viewobject.UserInfoVo;
import com.graduate.engine.request.PasswordChangeRequest;
import com.graduate.engine.request.RegisterRequest;
import com.graduate.engine.service.LoginService;
import com.graduate.engine.utils.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

/**
 * 注册登录相关service
 *
 * @author jimmy
 */
@Service
public class LoginServiceImpl extends ServiceImpl<LoginMapper, Login> implements LoginService {

    @Resource
    private LoginMapper loginMapper;
    @Resource
    private PersonMemberMapper personMemberMapper;
    @Resource
    private GuestMapper guestMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addGuest(RegisterRequest registerRequest) {
        String passwordHash = passwordToHash(registerRequest.getLoginPassword());
        Login login = new Login();
        login.setLoginName(registerRequest.getLoginName());
        login.setLoginPassword(passwordHash);
        if (registerRequest.getType() == 2) {
            Guest guest = new Guest();
            guest.setGuestEmail(registerRequest.getMail());
            guest.setGuestName(registerRequest.getName());
            guest.setMemo(registerRequest.getMemo());
            guest.setGuestPhone(registerRequest.getPhone1());
            guest.setGuestSex(registerRequest.getSex());
            guest.setExpirePeriod(30);
            guest.setRegisterDate(System.currentTimeMillis() / 1000);
            guest.setLastLogin(System.currentTimeMillis() / 1000);
            guestMapper.insertSelective(guest);
            login.setGuestId(guestMapper.selectByGuestName(guest.getGuestName()).getGuestId());
        } else {
            throw new BusinessException("不支持类型！请检查");
        }
        return loginMapper.addLoginUser(login);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String addPersonMember(PersonMemberApplicationDetails details) {
        //生成Login信息
        Login login = new Login();
        String passwordHash = passwordToHash(Constant.INIT_PASSWORD);
        String loginName = createLoginName(details.getMail());
        login.setLoginName(loginName);
        login.setLoginPassword(passwordHash);
        //将details的信息复制到PersonMember中
        PersonMember personMember = BeanUtils.copyBean(details, PersonMember.class);
        Long memberId = createMemberId();
        personMember.setPersonMemberId(memberId);
        personMember.setPersonMemberDate(DateUtils.getCurrentSecondTimestamp());
        personMemberMapper.insertSelective(personMember);
        login.setPersonId(personMemberMapper.selectByPersonMemberID(memberId).getPersonId());
        if (1 == loginMapper.addLoginUser(login)) {
            return loginName;
        } else {
            throw new RuntimeException("注册失败");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Login addUser(UserInfoVo userInfoVo) throws ParseException {
        String passwordHash = passwordToHash(userInfoVo.getLoginPassword());
        Login login = new Login();
        login.setLoginName(userInfoVo.getLoginName());
        login.setLoginPassword(passwordHash);
        PersonMember personMember = BeanUtils.copyBean(userInfoVo, PersonMember.class);
        Long memberId = createMemberId();
        personMember.setPersonMemberId(memberId);
        personMember.setBirthday(DateUtils.getTimeStampByUTC(userInfoVo.getFormatBirthday()));
        personMember.setPersonMemberDate(DateUtils.getCurrentSecondTimestamp());
        personMemberMapper.insertSelective(personMember);
        login.setPersonId(personMemberMapper.selectByPersonMemberID(memberId).getPersonId());
        if (1 == loginMapper.addLoginUser(login)) {
            return findByName(userInfoVo.getLoginName());
        } else {
            throw new RuntimeException("注册失败");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUser(UserInfoVo userInfoVo) throws ParseException {
        String passwordHash = passwordToHash(userInfoVo.getLoginPassword());
        Login login = findById(userInfoVo.getId());
        login.setLoginPassword(passwordHash);
        loginMapper.updatePassword(login);
        PersonMember personMember = BeanUtils.copyBean(userInfoVo, PersonMember.class);
        personMember.setBirthday(DateUtils.getTimeStampByUTC(userInfoVo.getFormatBirthday()));
        personMemberMapper.updateByPrimaryKeySelective(personMember);
    }

    @Override
    public UserInfoVo getUser(Long userId) {
        Login login = getById(userId);
        UserInfoVo userInfoVo = new UserInfoVo();

        if (login.getPersonId() != null) {
            PersonMember personMember = personMemberMapper.selectByPrimaryKey(login.getPersonId());
            userInfoVo = BeanUtils.copyBean(personMember, UserInfoVo.class);
            userInfoVo.setFormatBirthday(DateUtils.getDateStrByTimestamp(userInfoVo.getBirthday(), "yyyy-MM-dd"));
        } else if (login.getGuestId() != null) {
            Guest guest = guestMapper.selectByPrimaryKey(login.getGuestId());
            userInfoVo.setMail(guest.getGuestEmail());
            userInfoVo.setName(guest.getGuestName());
            userInfoVo.setSex(guest.getGuestSex());
            userInfoVo.setPhone1(guest.getGuestPhone());
            userInfoVo.setMemo(guest.getMemo());
        } else {
            throw new RuntimeException("角色账户有问题!无法寻找角色信息.");
        }
        userInfoVo.setId(userId);
        userInfoVo.setLoginName(login.getLoginName());
        return userInfoVo;
    }

    private String createLoginName(String name) {
        Random random = new Random();
        String loginName = name + random.nextInt(10);
        if (findByName(loginName) == null) {
            return loginName;
        } else {
            return createLoginName(loginName);
        }
    }

    private Long createMemberId() {
        Random random = new Random();
        Long memberId =
                (Constant.MEMBER_ID_PREFIX + System.currentTimeMillis() % 100000) * 10 + random.nextInt(10);
        if (personMemberMapper.selectByPersonMemberID(memberId) != null) {
            return createMemberId();
        }
        return memberId;
    }

    @Override
    public Login findByName(String name) {
        Login login = new Login();
        login.setLoginName(name);
        return loginMapper.findLoginUser(login);
    }

    @Override
    public Login findById(Long id) {
        Login login = new Login();
        login.setLoginId(id);
        return loginMapper.findLoginUser(login);
    }

    @Override
    public int updateLastLogin(final Long guestId) {
        final Long now = System.currentTimeMillis() / 1000;
        Guest guest = new Guest() {
            {
                setLastLogin(now);
                setGuestId(guestId);
            }
        };
        return guestMapper.updateByPrimaryKeySelective(guest);
    }

    @Override
    public int passwordChange(PasswordChangeRequest request) {
        Login login = new Login();
        login.setLoginId(request.getLoginId());
        Login userInDataBase = loginMapper.findLoginUser(login);
        if (Objects.equals(passwordToHash(request.getPassword()), userInDataBase.getLoginPassword())) {
            login.setLoginPassword(passwordToHash(request.getNewPassword()));
        } else {
            throw new BusinessException("原密码错误！");
        }
        return loginMapper.updatePassword(login);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String loginName = (String) params.get("loginName");

        IPage<Login> page = this.page(
                new Query<Login>().getPage(params),
                new QueryWrapper<Login>()
                        .like(StringUtils.isNotBlank(loginName), "login_name", loginName)
        );

        return new PageUtils(page);
    }

    @Override
    public void deleteBatch(Long[] userIds) {
        loginMapper.stopBatch(userIds);
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

    @Override
    public boolean comparePassword(Login loginUser, Login userInDataBase) {
        // 将用户提交的密码转换为 hash
        return passwordToHash(loginUser.getLoginPassword())
                // 数据库中的 password 已经是 hash，不用转换
                .equals(userInDataBase.getLoginPassword());
    }

    /**
     * 登录后返回token给前端，前端请求时将token放到header里
     *
     * @param login
     * @return
     */
    @Override
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
