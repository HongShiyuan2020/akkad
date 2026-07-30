package self.syhong.akkad.service.impl;

import self.syhong.akkad.domain.entity.RoleEntity;
import self.syhong.akkad.domain.entity.UserEntity;
import self.syhong.akkad.domain.entity.UserRoleEntity;
import self.syhong.akkad.domain.mapper.RoleMapper;
import self.syhong.akkad.domain.mapper.UserMapper;
import self.syhong.akkad.domain.mapper.UserRoleMapper;
import self.syhong.akkad.service.IUserRoleService;
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
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRoleEntity> implements IUserRoleService {

    @Autowired
    RoleMapper roleMapper;
    @Autowired
    UserMapper userMapper;

    @Override
    public Long bindRole2User(Long userId, Long roleId) {
        Assert.assertExpression(Objects.nonNull(userId) && Objects.nonNull(roleId), "未提供用户或角色");
        UserRoleEntity entity = lambdaQuery()
            .eq(UserRoleEntity::getUserId, userId)
            .eq(UserRoleEntity::getRoleId, roleId)
            .one();
        
        Assert.assertExpression(Objects.isNull(entity), "该用户已绑定该角色");

        RoleEntity role = roleMapper.selectById(roleId);
        Assert.assertExpression(Objects.nonNull(role), "该角色不存在"); 

        UserEntity user = userMapper.selectById(userId);
        Assert.assertExpression(Objects.nonNull(user), "该用户不存在");

        UserRoleEntity userRoleEntity = UserRoleEntity.builder()
            .userId(userId)
            .roleId(roleId)
            .build();
        
        this.save(userRoleEntity);

        return userRoleEntity.getId();
    }

    @Override
    public void unBindRole(Long id) {
        Assert.assertExpression(Objects.nonNull(id), "未提供足够信息");
        UserRoleEntity userRoleEntity = this.getById(id);
        Assert.assertExpression(Objects.nonNull(userRoleEntity), "该用户无此角色");
        this.removeById(id);
    }
}
