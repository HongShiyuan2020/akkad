package self.syhong.akkad.domain.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import self.syhong.akkad.domain.entity.UserEntity;
import self.syhong.akkad.dto.UserDTO;

/**
 * <p>
 * 系统用户基础信息表 Mapper 接口
 * </p>
 *
 * @author syhong
 * @since 2026-07-30
 */
public interface UserMapper extends BaseMapper<UserEntity> {
    List<UserDTO> listUserWithRoles(
        @Param("offset") long offset,
        @Param("pageSize") long pageSize
    );
}
