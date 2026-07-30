package self.syhong.akkad.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;

import self.syhong.akkad.dto.Result;
import self.syhong.akkad.dto.UserDTO;
import self.syhong.akkad.service.IUserService;

import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 系统用户基础信息表 前端控制器
 * </p>
 *
 * @author syhong
 * @since 2026-07-29
 */
@RestController
@RequestMapping("/admin/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/add")
    public Result<Long> addUser(@RequestBody UserDTO userDTO) {
        Long userId = userService.addUser(userDTO);
        if (Objects.isNull(userId)) {
            return Result.failed("添加用户失败");
        }
        return Result.success(userId);
    }

    @GetMapping("/del")
    public Result<Boolean> delUser(@RequestParam Long id) {
        if (userService.delUser(id)) {
            return Result.success(true);
        }
        return Result.failed("用户删除失败");
    }

    @PostMapping("/mod")
    public Result<UserDTO> modUser(@Valid @RequestBody UserDTO userDTO) {
        UserDTO dto = userService.modUser(userDTO);
        if (Objects.isNull(dto)) {
            return Result.failed("用户信息修改失败");
        }
        return Result.success(userDTO);
    }

    @GetMapping("/list")
    public Result<IPage<UserDTO>> listUser(
        @RequestParam(defaultValue = "1") Long page, 
        @RequestParam(defaultValue = "10") Long size
    ) {
        IPage<UserDTO> userPage = userService.listUser(page, size);
        return Result.success(userPage);
    }
}
