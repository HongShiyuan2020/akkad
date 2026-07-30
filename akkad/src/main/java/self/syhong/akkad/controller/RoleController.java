package self.syhong.akkad.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;

import self.syhong.akkad.dto.Result;
import self.syhong.akkad.dto.RoleDTO;
import self.syhong.akkad.service.IRoleService;

import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 用户角色 前端控制器
 * </p>
 *
 * @author syhong
 * @since 2026-07-29
 */
@RestController
@RequestMapping("/admin/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @PostMapping("/add")
    public Result<Long> addRole(@RequestBody RoleDTO roleDTO) {
        Long roleId = roleService.addRole(roleDTO);
        if (Objects.isNull(roleId)) {
            return Result.failed("添加角色失败");
        }
        return Result.success(roleId);
    }

    @GetMapping("/del")
    public Result<Boolean> delRole(@RequestParam Long id) {
        if (roleService.delRole(id)) {
            return Result.success(true);
        }
        return Result.failed("角色删除失败");
    }

    @PostMapping("/mod")
    public Result<RoleDTO> modRole(@Valid @RequestBody RoleDTO roleDTO) {
        RoleDTO dto = roleService.modRole(roleDTO);
        if (Objects.isNull(dto)) {
            return Result.failed("角色信息修改失败");
        }
        return Result.success(dto);
    }

    @GetMapping("/list")
    public Result<IPage<RoleDTO>> listRole(
        @RequestParam(defaultValue = "1") Long page,
        @RequestParam(defaultValue = "10") Long size
    ) {
        IPage<RoleDTO> rolePage = roleService.listRole(page, size);
        return Result.success(rolePage);
    }
}
