package self.syhong.akkad.service;

import self.syhong.akkad.domain.entity.RolePermissionEntity;
import com.baomidou.mybatisplus.spring.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author syhong
 * @since 2026-07-29
 */
public interface IRolePermissionService extends IService<RolePermissionEntity> {

    Long bindPermission2Role(Long roleId, Long permissionId);

    void unBindPermission(Long id);

}
