package self.syhong.akkad.service.impl;

import self.syhong.akkad.domain.converter.RoleConverter;
import self.syhong.akkad.domain.entity.RoleEntity;
import self.syhong.akkad.domain.mapper.RoleMapper;
import self.syhong.akkad.dto.RoleDTO;
import self.syhong.akkad.service.IRoleService;
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
 * 用户角色 服务实现类
 * </p>
 *
 * @author syhong
 * @since 2026-07-29
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, RoleEntity> implements IRoleService {

    @Autowired
    RoleConverter roleConverter;

    @Override
    public Long addRole(RoleDTO dto) {
        Assert.assertExpression(
            Objects.nonNull(dto) &&
            Objects.nonNull(dto.getCode()) &&
            Objects.nonNull(dto.getName()),
            "角色信息不能为空"
        );

        List<RoleEntity> existRoles = lambdaQuery()
            .eq(RoleEntity::getCode, dto.getCode())
            .list();

        Assert.assertExpression(CollectionUtils.isEmpty(existRoles), "该角色代码已存在");

        RoleEntity role = roleConverter.dto2Entity(dto);
        if (this.save(role)) {
            return role.getId();
        }
        return null;
    }

    @Override
    public boolean delRole(Long id) {
        Assert.assertExpression(Objects.nonNull(id), "未选择角色");

        RoleEntity existRole = lambdaQuery()
            .eq(RoleEntity::getId, id)
            .one();
        Assert.assertExpression(Objects.nonNull(existRole), "该角色不存在");

        this.removeById(id);

        return true;
    }

    @Override
    public RoleDTO modRole(RoleDTO dto) {
        Assert.assertExpression(
            Objects.nonNull(dto) &&
            Objects.nonNull(dto.getId()) &&
            Objects.nonNull(dto.getCode()) &&
            Objects.nonNull(dto.getName()),
            "角色信息不合法"
        );

        RoleEntity role = roleConverter.dto2Entity(dto);
        this.updateById(role);
        RoleDTO result = roleConverter.entity2DTO(role);

        return result;
    }

    @Override
    public IPage<RoleDTO> listRole(Long page, Long size) {
        Assert.assertExpression(page >= 1, "page 必须大于等于 1");
        Assert.assertExpression(size >= 1, "size 必须大于等于 1");

        long offset = (page - 1) * size;
        List<RoleDTO> roleDTOs = baseMapper.listRoleWithPermissions(offset, size);
        Long total = this.count();

        Page<RoleDTO> roleDTOPage = new Page<>(page, size);
        roleDTOPage.setRecords(roleDTOs);
        roleDTOPage.setTotal(total);

        return roleDTOPage;
    }
}
