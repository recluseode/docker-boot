package com.recluseode.dockerboot.controller;

import com.recluseode.dockerboot.dto.Resp;
import com.recluseode.dockerboot.entity.User;
import com.recluseode.dockerboot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户控制器
 *
 * @author wuyuexiang
 * @date 2022年10月21日 00:34
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 根据id获取用户
     *
     * @param id 用户id
     * @return 用户信息
     */
    @GetMapping("/{id}")
    public Resp getUserById(@Validated @PathVariable("id") Long id) {
        User user = userService.getById(id);
        return Resp.builder()
                .code(HttpStatus.OK.value())
                .message(HttpStatus.OK.name())
                .data(user)
                .build();
    }

    /**
     * 获取全量用户
     *
     * @return 全量用户信息
     */
    @GetMapping
    public Resp getUserList() {
        List<User> users = userService.getUsers();
        return Resp.builder()
                .code(HttpStatus.OK.value())
                .message(HttpStatus.OK.name())
                .data(users)
                .build();
    }

    /**
     * 创建用户
     *
     * @param user 用户数据
     * @return 结果
     */
    @PostMapping
    public Resp createUser(@RequestBody User user) {
        userService.createUser(user);
        return Resp.builder()
                .code(HttpStatus.OK.value())
                .message(HttpStatus.OK.name())
                .build();
    }
}
