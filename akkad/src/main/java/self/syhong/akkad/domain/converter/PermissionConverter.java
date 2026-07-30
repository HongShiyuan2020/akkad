package self.syhong.akkad.domain.converter;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import self.syhong.akkad.domain.entity.PermissionEntity;
import self.syhong.akkad.dto.PermissionDTO;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PermissionConverter {
    PermissionDTO entity2DTO(PermissionEntity permission);
    PermissionEntity dto2Entity(PermissionDTO dto);
}
