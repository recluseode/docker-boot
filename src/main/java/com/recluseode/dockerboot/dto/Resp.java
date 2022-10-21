package com.recluseode.dockerboot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wuyuexiang
 * @date 2022年10月21日 00:41
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Resp {

    private Integer code;
    private String message;
    private Object data;
}
