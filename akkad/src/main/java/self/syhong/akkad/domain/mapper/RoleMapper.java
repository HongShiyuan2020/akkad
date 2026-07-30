package self.syhong.akkad.domain.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import self.syhong.akkad.domain.entity.RoleEntity;
import self.syhong.akkad.dto.RoleDTO;

/**
 * <p>
 * 用户角色 Mapper 接口
 * </p>
 *
 * @author syhong
 * @since 2026-07-30
 */
public interface RoleMapper extends BaseMapper<RoleEntity> {
    List<RoleDTO> listRoleWithPermissions(
        @Param("offset") long offset,
        @Param("pageSize") long pageSize
    );
}
