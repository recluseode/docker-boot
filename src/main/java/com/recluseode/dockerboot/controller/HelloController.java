package com.recluseode.dockerboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author wuyuexiang
 * @date 2022年10月20日 18:06
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping("/docker")
    public String helloDocker() {
        String id = UUID.randomUUID().toString();
        return "Hello Docker! id: " + id;
    }
}
