package com.graduate.engine.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graduate.engine.exception.BasicException;
import com.graduate.engine.mapper.LoginMapper;
import com.graduate.engine.mapper.PersonRoleMapperMapper;
import com.graduate.engine.mapper.RoleMapper;
import com.graduate.engine.model.Role;
import com.graduate.engine.service.PersonRoleMapperService;
import com.graduate.engine.service.RoleMenuService;
import com.graduate.engine.service.RoleService;
import com.graduate.engine.utils.Constant;
import com.graduate.engine.utils.DateUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Resource
    private LoginMapper loginMapper;
    @Resource
    private RoleMenuService roleMenuService;
    @Resource
    private PersonRoleMapperService personRoleMapperService;

    @Override
    public void saveRole(Role role) {
        role.setCreateTime(new Date());
        this.save(role);

        checkPerms(role);
        roleMenuService.saveOrUpdate(role.getRoleId(),role.getMenuId());
    }

    @Override
    public void updateRole(Role role) {
        this.updateById(role);

        checkPerms(role);
        roleMenuService.saveOrUpdate(role.getRoleId(),role.getMenuId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteRole(Long roleId) {
        this.removeById(roleId);

        //删除角色菜单关联
        roleMenuService.deleteBatch(new Long[]{roleId});
        //删除角色用户关联
        personRoleMapperService.deleteBatch(new Long[]{roleId});
    }

    @Override
    public List<Role> getRoleList(Map<String, Object> map) {
        List<Role> roleList = (List<Role>) this.listByMap(map);
        for (Role role : roleList) {
            role.setFormatTime(DateUtils.format(role.getCreateTime(),"yyyy-MM-dd HH:mm:ss"));
        }
        return roleList;
    }

    /**
     * 检查角色是否有菜单的权限
     */
    private void checkPerms(Role role) {
        if (role.getCreateUserId() == Constant.SUPER_ADMIN) {
            return ;
        }

        //如果不是管理员，查询用户所能访问的菜单列表
        List<Long> menuIdList = loginMapper.queryAllMenu(role.getCreateUserId());
        if (!menuIdList.containsAll(role.getMenuId())) {
            throw new BasicException("新增角色的权限，已超出你的权限范围");
        }
    }
}
