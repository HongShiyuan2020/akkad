package self.syhong.akkad.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import self.syhong.akkad.dto.Result;
import self.syhong.akkad.service.IUserRoleService;

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
@RequestMapping("/admin/ur")
public class UserRoleController {

    @Autowired
    IUserRoleService userRoleService;

    @GetMapping("/bind")
    public Result<Long> bindRole2User(@RequestParam Long userId, @RequestParam Long roleId) {
        return Result.success(userRoleService.bindRole2User(userId, roleId));
    }

    @GetMapping("/unbind")
    public Result<Void> unBindRole(@RequestParam Long id) {
        userRoleService.unBindRole(id);
        return Result.success(null);
    }
}
