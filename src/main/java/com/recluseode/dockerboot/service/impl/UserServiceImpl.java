package com.recluseode.dockerboot.service.impl;

import com.recluseode.dockerboot.entity.User;
import com.recluseode.dockerboot.repository.UserRepository;
import com.recluseode.dockerboot.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

/**
 * 用户服务
 *
 * @author wuyuexiang
 * @date 2022年10月21日 00:45
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private static final Long DEFAULT_EXPIRE = 5L * 60;

    private static final String KEY_PREFIX = "user:";

    private final UserRepository userRepository;

    private final RedisTemplate<String, User> redisTemplate;

    private final RedisTemplate<String, List<User>> listRedisTemplate;

    /**
     * 根据id获取用户
     *
     * @param id 用户id
     * @return 用户信息
     */
    @Override
    public User getById(Long id) {
        User cachedUser = redisTemplate.opsForValue().get(KEY_PREFIX + id);
        if (ObjectUtils.isEmpty(cachedUser)) {
            User user = userRepository.findById(id);
            redisTemplate.opsForValue().set(KEY_PREFIX + id, user, Duration.ofSeconds(DEFAULT_EXPIRE));
            log.info("查询数据库，存入缓存");
            return user;
        }
        log.info("从缓存获取用户");
        return cachedUser;
    }

    /**
     * 获取全量用户
     *
     * @return 全量用户信息
     */
    @Override
    public List<User> getUsers() {
        List<User> cachedList = listRedisTemplate.opsForValue().get(KEY_PREFIX + "all");
        if (ObjectUtils.isEmpty(cachedList)) {
            List<User> users = userRepository.findAll();
            listRedisTemplate.opsForValue().set(KEY_PREFIX + "all", users, Duration.ofSeconds(DEFAULT_EXPIRE));
            log.info("查询数据库，存入缓存");
            return users;
        }
        log.info("从缓存获取用户");
        return cachedList;
    }

    /**
     * 创建用户
     *
     * @param user 用户数据
     */
    @Override
    public void createUser(User user) {
        if (ObjectUtils.isEmpty(user) || ObjectUtils.isEmpty(user.getUsername())
                || ObjectUtils.isEmpty(user.getPassword())) {
            return;
        }
        int count = userRepository.save(user);
        if (count > 0) {
            listRedisTemplate.delete(KEY_PREFIX + "all");
        }
    }
}
