package self.syhong.akkad.dto;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long   id;

    private String username;
    private String email;
    private String passwordHash;
    private String stauts;
    private String iconUrl;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;

    private List<RoleDTO> roles;
}
