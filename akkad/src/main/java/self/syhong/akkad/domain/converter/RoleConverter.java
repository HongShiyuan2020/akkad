package self.syhong.akkad.domain.converter;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import self.syhong.akkad.domain.entity.RoleEntity;
import self.syhong.akkad.dto.RoleDTO;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleConverter {
    RoleDTO entity2DTO(RoleEntity role);
    RoleEntity dto2Entity(RoleDTO dto);
}
