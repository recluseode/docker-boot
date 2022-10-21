package com.recluseode.dockerboot.service;

import com.recluseode.dockerboot.entity.User;

import java.util.List;

/**
 * 用户服务
 *
 * @author wuyuexiang
 * @date 2022年10月21日 00:44
 */
public interface UserService {

    /**
     * 根据id获取用户
     *
     * @param id 用户id
     * @return 用户信息
     */
    User getById(Long id);

    /**
     * 获取全量用户
     *
     * @return 全量用户信息
     */
    List<User> getUsers();

    /**
     * 创建用户
     *
     * @param user 用户数据
     */
    void createUser(User user);
}
