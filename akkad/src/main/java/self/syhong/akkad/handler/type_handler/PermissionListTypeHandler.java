package self.syhong.akkad.handler.type_handler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;

import self.syhong.akkad.dto.PermissionDTO;

@MappedJdbcTypes(JdbcType.OTHER)
public class PermissionListTypeHandler extends BaseListTypeHandler<PermissionDTO> {

    @Override
    protected Class<PermissionDTO> getElementType() {
        return PermissionDTO.class;
    }

}
