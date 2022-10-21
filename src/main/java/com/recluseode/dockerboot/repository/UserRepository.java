package com.recluseode.dockerboot.repository;

import com.recluseode.dockerboot.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author wuyuexiang
 * @date 2022年10月21日 00:18
 */
@Mapper
public interface UserRepository {

    User findById(Long id);

    List<User> findAll();

    int save(User user);
}
