package self.syhong.akkad.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.OffsetDateTime;

/**
 * <p>
 * 权限管理
 * </p>
 *
 * @author syhong
 * @since 2026-07-30
 */
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@TableName("akd_permission")
@ApiModel(value = "PermissionEntity对象", description = "权限管理")
public class PermissionEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 父权限id，用于树状管理
     */
    @TableField("parent_id")
    @ApiModelProperty("父权限id，用于树状管理")
    private Long parentId;

    /**
     * 权限标识代码
     */
    @TableField("code")
    @ApiModelProperty("权限标识代码")
    private String code;

    /**
     * 权限名称
     */
    @TableField("name")
    @ApiModelProperty("权限名称")
    private String name;

    /**
     * 权限类型：MENU（菜单） BUTTON（按钮） API（接口）
     */
    @TableField("type")
    @ApiModelProperty("权限类型：MENU（菜单） BUTTON（按钮） API（接口）")
    private String type;

    /**
     * 前端路由 或 API URL 匹配规则
     */
    @TableField("path")
    @ApiModelProperty("前端路由 或 API URL 匹配规则")
    private String path;

    /**
     * 请求方法
     */
    @TableField("method")
    @ApiModelProperty("请求方法")
    private String method;

    /**
     * 排序字段
     */
    @ApiModelProperty("排序字段")
    @TableField("sort_order")
    private Integer sortOrder;

    @TableField("created_at")
    private OffsetDateTime createdAt;

    @TableField("updated_at")
    private OffsetDateTime updatedAt;
}
