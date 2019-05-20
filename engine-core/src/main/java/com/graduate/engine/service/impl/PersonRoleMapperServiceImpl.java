package com.graduate.engine.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduate.engine.mapper.PersonRoleMapperMapper;
import com.graduate.engine.model.PersonRoleMapper;
import com.graduate.engine.service.PersonRoleMapperService;
import com.graduate.engine.utils.Constant;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class PersonRoleMapperServiceImpl extends ServiceImpl<PersonRoleMapperMapper, PersonRoleMapper> implements PersonRoleMapperService {
    @Resource
    PersonRoleMapperMapper personRoleMapperMapper;

    @Override
    public int deleteBatch(Long[] roleIds) {
        return baseMapper.deleteBatch(roleIds);
    }

    @Override
    public void addGuestRole(Long loginId) {
        PersonRoleMapper personRoleMapper = new PersonRoleMapper();
        personRoleMapper.setRoleId(Constant.GUEST_ID);
        personRoleMapper.setPersonId(loginId);
        baseMapper.insert(personRoleMapper);
    }

    @Override
    public void addUserRole(Long loginId, Long roleId) {
        PersonRoleMapper personRoleMapper = new PersonRoleMapper();
        personRoleMapper.setRoleId(roleId);
        personRoleMapper.setPersonId(loginId);
        baseMapper.insert(personRoleMapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUserRole(Long loginId, Long roleId) {
        //查找用户的角色
        PersonRoleMapper personRole = selectByPersonId(loginId);
        //如果角色没变无操作，角色改变则删除原记录，并添加新记录
        if (!roleId.equals(personRole.getRoleId())) {
            personRoleMapperMapper.deleteById(personRole.getPersonRoleId());
            addUserRole(loginId,roleId);
        }
    }

    @Override
    public PersonRoleMapper selectByPersonId(Long personId) {
        return personRoleMapperMapper.selectByPersonId(personId);
    }
}
