package self.syhong.akkad.service;

import self.syhong.akkad.domain.entity.PermissionEntity;
import self.syhong.akkad.dto.PermissionDTO;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.spring.service.IService;

/**
 * <p>
 * 权限管理 服务类
 * </p>
 *
 * @author syhong
 * @since 2026-07-29
 */
public interface IPermissionService extends IService<PermissionEntity> {
    public Long addPermission(PermissionDTO dto);

    public boolean delPermission(Long id);

    public PermissionDTO modPermission(PermissionDTO dto);

    public IPage<PermissionDTO> listPermission(Long page, Long size);
}
