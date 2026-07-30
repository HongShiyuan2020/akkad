package self.syhong.akkad.handler.type_handler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;


import self.syhong.akkad.dto.RoleDTO;

@MappedJdbcTypes(JdbcType.OTHER)
public class RoleListTypeHandler extends BaseListTypeHandler<RoleDTO> {

    @Override
    protected Class<RoleDTO> getElementType() {
        return RoleDTO.class;
    }

}
