package self.syhong.akkad.service;

import self.syhong.akkad.domain.entity.UserEntity;
import self.syhong.akkad.dto.UserDTO;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.spring.service.IService;

/**
 * <p>
 * 系统用户基础信息表 服务类
 * </p>
 *
 * @author syhong
 * @since 2026-07-29
 */
public interface IUserService extends IService<UserEntity> {
    public Long addUser(UserDTO dto);

    public boolean delUser(Long id);

    public UserDTO modUser(UserDTO userDTO);

    public IPage<UserDTO> listUser(Long page, Long size);
}
