package self.syhong.akkad.domain.converter;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import self.syhong.akkad.domain.entity.UserEntity;
import self.syhong.akkad.dto.UserDTO;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserConverter {
    UserDTO entity2DTO(UserEntity user);
    UserEntity dto2Entity(UserDTO dto);
}
