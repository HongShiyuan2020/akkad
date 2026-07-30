package self.syhong.akkad.service.impl;

import self.syhong.akkad.domain.converter.UserConverter;
import self.syhong.akkad.domain.entity.UserEntity;
import self.syhong.akkad.domain.mapper.UserMapper;
import self.syhong.akkad.dto.UserDTO;
import self.syhong.akkad.service.IUserRoleService;
import self.syhong.akkad.service.IUserService;
import self.syhong.akkad.utils.Assert;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * <p>
 * 系统用户基础信息表 服务实现类
 * </p>
 *
 * @author syhong
 * @since 2026-07-29
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements IUserService {

    @Autowired
    UserConverter userConverter;
    @Autowired
    IUserRoleService userRoleService;
    @Autowired
    UserMapper userMapper;

    @Override
    public Long addUser(UserDTO dto) {
        
        Assert.assertExpression(
            Objects.nonNull(dto) && 
            Objects.nonNull(dto.getUsername()) && 
            Objects.nonNull(dto.getEmail()) &&
            Objects.nonNull(dto.getPasswordHash()),
            "用户信息不能为空"
        );

        List<UserEntity> existUsers = lambdaQuery()
            .eq(UserEntity::getUsername, dto.getUsername())
            .or()
            .eq(UserEntity::getEmail, dto.getEmail())
            .list();

        Assert.assertExpression(CollectionUtils.isEmpty(existUsers), "该用户名或邮件已存在");

        UserEntity user = userConverter.dto2Entity(dto);
        if (this.save(user)) {
            System.out.println(user.getId());
            return user.getId();
        }
        System.out.println(user.getId());
        return null;
    }

    @Override
    public boolean delUser(Long id) {
        Assert.assertExpression(Objects.nonNull(id), "未选择用户");

        UserEntity existUser = lambdaQuery()
            .eq(UserEntity::getId, id)
            .one();
        Assert.assertExpression(Objects.nonNull(existUser), "该用户不存在");
        
        this.removeById(id);
        
        return true;
    }

    @Override
    public UserDTO modUser(UserDTO userDTO) {
        Assert.assertExpression(
            Objects.nonNull(userDTO) &&
            Objects.nonNull(userDTO.getId()) &&
            Objects.nonNull(userDTO.getUsername()) &&
            Objects.nonNull(userDTO.getEmail()) &&
            Objects.nonNull(userDTO.getPasswordHash()), 
            "用户信息不合法"
        );

        UserEntity user = userConverter.dto2Entity(userDTO);
        this.updateById(user);
        userDTO = userConverter.entity2DTO(user);

        return userDTO;
    }

    @Override
    public IPage<UserDTO> listUser(Long page, Long size) {
        Assert.assertExpression(page >= 1, "page 必须大于等于 1");
        Assert.assertExpression(size >= 1, "size 必须大于等于 1");

        long offset = (page-1)*size;
        List<UserDTO> userDTOs = userMapper.listUserWithRoles(offset, size);
        Long total = this.count();

        Page<UserDTO> userDTOPage = new Page<>(page, size);
        userDTOPage.setRecords(userDTOs);
        userDTOPage.setTotal(total);

        return userDTOPage;
    }

}
