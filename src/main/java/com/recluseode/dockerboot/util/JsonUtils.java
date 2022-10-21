package com.recluseode.dockerboot.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.commons.lang3.ObjectUtils;

/**
 * json 工具类
 *
 * @author wuyuexiang
 * @date 2022年10月21日 01:35
 */
public class JsonUtils {

    private JsonUtils() {}

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        OBJECT_MAPPER.registerModule(new JavaTimeModule());
    }

    /**
     * 对象转 json
     *
     * @param obj 对象
     * @return json
     */
    public static String getJsonFor(Object obj) {
        if (ObjectUtils.isEmpty(obj)) {
            return "";
        }
        try {
            return OBJECT_MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * json 转对象
     *
     * @param json json
     * @param type 对象类型
     * @return 对象
     * @param <T> 类型
     */
    public static <T> T getObjectFor(String json, Class<T> type) {
        if (ObjectUtils.isEmpty(json)) {
            return null;
        }
        try {
            return OBJECT_MAPPER.readValue(json, type);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
