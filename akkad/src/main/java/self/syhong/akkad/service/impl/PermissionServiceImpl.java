package self.syhong.akkad.service.impl;

import self.syhong.akkad.domain.converter.PermissionConverter;
import self.syhong.akkad.domain.entity.PermissionEntity;
import self.syhong.akkad.domain.mapper.PermissionMapper;
import self.syhong.akkad.dto.PermissionDTO;
import self.syhong.akkad.service.IPermissionService;
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
 * 权限管理 服务实现类
 * </p>
 *
 * @author syhong
 * @since 2026-07-29
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, PermissionEntity> implements IPermissionService {

    @Autowired
    PermissionConverter permissionConverter;

    @Override
    public Long addPermission(PermissionDTO dto) {
        Assert.assertExpression(
            Objects.nonNull(dto) &&
            Objects.nonNull(dto.getCode()) &&
            Objects.nonNull(dto.getName()) &&
            Objects.nonNull(dto.getType()),
            "权限信息不能为空"
        );

        List<PermissionEntity> existPermissions = lambdaQuery()
            .eq(PermissionEntity::getCode, dto.getCode())
            .list();

        Assert.assertExpression(CollectionUtils.isEmpty(existPermissions), "该权限代码已存在");

        PermissionEntity permission = permissionConverter.dto2Entity(dto);
        if (this.save(permission)) {
            return permission.getId();
        }
        return null;
    }

    @Override
    public boolean delPermission(Long id) {
        Assert.assertExpression(Objects.nonNull(id), "未选择权限");

        PermissionEntity existPermission = lambdaQuery()
            .eq(PermissionEntity::getId, id)
            .one();
        Assert.assertExpression(Objects.nonNull(existPermission), "该权限不存在");

        this.removeById(id);

        return true;
    }

    @Override
    public PermissionDTO modPermission(PermissionDTO dto) {
        Assert.assertExpression(
            Objects.nonNull(dto) &&
            Objects.nonNull(dto.getId()) &&
            Objects.nonNull(dto.getCode()) &&
            Objects.nonNull(dto.getName()) &&
            Objects.nonNull(dto.getType()),
            "权限信息不合法"
        );

        PermissionEntity permission = permissionConverter.dto2Entity(dto);
        this.updateById(permission);
        PermissionDTO result = permissionConverter.entity2DTO(permission);

        return result;
    }

    @Override
    public IPage<PermissionDTO> listPermission(Long page, Long size) {
        Page<PermissionEntity> permissionPageInfo = new Page<>(page, size);

        IPage<PermissionEntity> permissionPage = lambdaQuery()
            .orderByDesc(PermissionEntity::getId)
            .page(permissionPageInfo);

        return permissionPage.convert(permissionConverter::entity2DTO);
    }
}
