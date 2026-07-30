package self.syhong.akkad.service;

import self.syhong.akkad.domain.entity.RoleEntity;
import self.syhong.akkad.dto.RoleDTO;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.spring.service.IService;

/**
 * <p>
 * 用户角色 服务类
 * </p>
 *
 * @author syhong
 * @since 2026-07-29
 */
public interface IRoleService extends IService<RoleEntity> {
    public Long addRole(RoleDTO dto);

    public boolean delRole(Long id);

    public RoleDTO modRole(RoleDTO dto);

    public IPage<RoleDTO> listRole(Long page, Long size);
}
