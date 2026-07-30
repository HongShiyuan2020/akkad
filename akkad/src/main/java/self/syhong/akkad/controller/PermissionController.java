package self.syhong.akkad.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;

import self.syhong.akkad.dto.PermissionDTO;
import self.syhong.akkad.dto.Result;
import self.syhong.akkad.service.IPermissionService;

import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 权限管理 前端控制器
 * </p>
 *
 * @author syhong
 * @since 2026-07-29
 */
@RestController
@RequestMapping("/admin/permission")
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;

    @PostMapping("/add")
    public Result<Long> addPermission(@RequestBody PermissionDTO permissionDTO) {
        Long permissionId = permissionService.addPermission(permissionDTO);
        if (Objects.isNull(permissionId)) {
            return Result.failed("添加权限失败");
        }
        return Result.success(permissionId);
    }

    @GetMapping("/del")
    public Result<Boolean> delPermission(@RequestParam Long id) {
        if (permissionService.delPermission(id)) {
            return Result.success(true);
        }
        return Result.failed("权限删除失败");
    }

    @PostMapping("/mod")
    public Result<PermissionDTO> modPermission(@Valid @RequestBody PermissionDTO permissionDTO) {
        PermissionDTO dto = permissionService.modPermission(permissionDTO);
        if (Objects.isNull(dto)) {
            return Result.failed("权限信息修改失败");
        }
        return Result.success(dto);
    }

    @GetMapping("/list")
    public Result<IPage<PermissionDTO>> listPermission(
        @RequestParam(defaultValue = "1") Long page,
        @RequestParam(defaultValue = "10") Long size
    ) {
        IPage<PermissionDTO> permissionPage = permissionService.listPermission(page, size);
        return Result.success(permissionPage);
    }
}
