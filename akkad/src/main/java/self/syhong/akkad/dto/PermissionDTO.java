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
public class PermissionDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long    id;

    private Long    parentId;
    private String  code;
    private String  name;
    private String  type;
    private String  path;
    private String  method;
    private Integer sortOrder;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;


    private List<PermissionDTO> childs;
}
