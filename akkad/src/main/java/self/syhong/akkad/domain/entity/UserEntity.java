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
 * 系统用户基础信息表
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
@TableName("akd_user")
@ApiModel(value = "UserEntity对象", description = "系统用户基础信息表")
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
    @ApiModelProperty("自增主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户登录名
     */
    @TableField("username")
    @ApiModelProperty("用户登录名")
    private String username;

    /**
     * 电子邮箱
     */
    @TableField("email")
    @ApiModelProperty("电子邮箱")
    private String email;

    @TableField("password_hash")
    private String passwordHash;

    /**
     * 账号状态：0-禁用，1-正常，2-锁定
     */
    @TableField("status")
    @ApiModelProperty("账号状态：0-禁用，1-正常，2-锁定")
    private Short status;

    /**
     * 头像链接
     */
    @TableField("icon_url")
    @ApiModelProperty("头像链接")
    private String iconUrl;

    /**
     * 记录创建时间
     */
    @TableField("created_at")
    @ApiModelProperty("记录创建时间")
    private OffsetDateTime createdAt;

    /**
     * 记录更新时间
     */
    @TableField("updated_at")
    @ApiModelProperty("记录更新时间")
    private OffsetDateTime updatedAt;
}
