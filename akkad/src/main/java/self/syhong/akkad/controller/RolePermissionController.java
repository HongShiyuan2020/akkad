package self.syhong.akkad.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import self.syhong.akkad.dto.Result;
import self.syhong.akkad.service.IRolePermissionService;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author syhong
 * @since 2026-07-29
 */
@RestController
@RequestMapping("/admin/rp")
public class RolePermissionController {

    @Autowired
    IRolePermissionService rolePermissionService;

    @GetMapping("/bind")
    public Result<Long> bindPermission2Role(@RequestParam Long roleId, @RequestParam Long permissionId) {
        return Result.success(rolePermissionService.bindPermission2Role(roleId, permissionId));
    }

    @GetMapping("/unbind")
    public Result<Void> unBindPermission(@RequestParam Long id) {
        rolePermissionService.unBindPermission(id);
        return Result.success(null);
    }
}
