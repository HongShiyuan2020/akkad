package self.syhong.akkad.service;

import self.syhong.akkad.domain.entity.UserRoleEntity;

import com.baomidou.mybatisplus.spring.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author syhong
 * @since 2026-07-29
 */
public interface IUserRoleService extends IService<UserRoleEntity> {

    Long bindRole2User(Long userId, Long roleId);

    void unBindRole(Long id);
}
