package self.syhong.akkad.service.impl;

import self.syhong.akkad.domain.entity.PermissionEntity;
import self.syhong.akkad.domain.entity.RoleEntity;
import self.syhong.akkad.domain.entity.RolePermissionEntity;
import self.syhong.akkad.domain.mapper.PermissionMapper;
import self.syhong.akkad.domain.mapper.RoleMapper;
import self.syhong.akkad.domain.mapper.RolePermissionMapper;
import self.syhong.akkad.service.IRolePermissionService;
import self.syhong.akkad.utils.Assert;

import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author syhong
 * @since 2026-07-29
 */
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermissionEntity> implements IRolePermissionService {

    @Autowired
    RoleMapper roleMapper;
    @Autowired
    PermissionMapper permissionMapper;

    @Override
    public Long bindPermission2Role(Long roleId, Long permissionId) {
        Assert.assertExpression(Objects.nonNull(roleId) && Objects.nonNull(permissionId), "未提供角色或权限");
        RolePermissionEntity entity = lambdaQuery()
            .eq(RolePermissionEntity::getRoleId, roleId)
            .eq(RolePermissionEntity::getPermissionId, permissionId)
            .one();

        Assert.assertExpression(Objects.isNull(entity), "该角色已绑定该权限");

        RoleEntity role = roleMapper.selectById(roleId);
        Assert.assertExpression(Objects.nonNull(role), "该角色不存在");

        PermissionEntity permission = permissionMapper.selectById(permissionId);
        Assert.assertExpression(Objects.nonNull(permission), "该权限不存在");

        RolePermissionEntity rolePermissionEntity = RolePermissionEntity.builder()
            .roleId(roleId)
            .permissionId(permissionId)
            .build();

        this.save(rolePermissionEntity);

        return rolePermissionEntity.getId();
    }

    @Override
    public void unBindPermission(Long id) {
        Assert.assertExpression(Objects.nonNull(id), "未提供足够信息");
        RolePermissionEntity rolePermissionEntity = this.getById(id);
        Assert.assertExpression(Objects.nonNull(rolePermissionEntity), "该角色无此权限");
        this.removeById(id);
    }

}
