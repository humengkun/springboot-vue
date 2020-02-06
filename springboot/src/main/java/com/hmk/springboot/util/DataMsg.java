package com.hmk.springboot.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public final class DataMsg implements Serializable {

    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    private int code;//状态码
    private String msg = "";//提示信息
    private Object data;//数据.无此变量有参构造默认值{}

    public static DataMsg ok(Object data) {
        return new DataMsg(data);
    }

    public static DataMsg ok() {
        return new DataMsg(null);
    }

    public static DataMsg error(String msg, Object data) {
        return new DataMsg(msg, data);
    }

    public static DataMsg error(int code, String msg) {
        return new DataMsg(code, msg, null);
    }

    public static DataMsg error(String msg) {
        return new DataMsg(msg, null);
    }

    public static DataMsg tokerExpired() {
        return new DataMsg(-1, "登录失效", null);
    }

    /**
     * 错误的时候调用
     *
     * @param data
     */
    private DataMsg(Object data) {
        this.code = 1;
        this.msg = "OK";
        this.data = data == null ? Collections.EMPTY_MAP : data;
    }

    /**
     * token失效的时候调用
     *
     * @param msg
     * @param data
     */
    private DataMsg(String msg, Object data) {
        this.code = 0;
        this.msg = msg;
        this.data = data == null ? Collections.EMPTY_MAP : data;
    }


    /**
     * 样板:{"code":1,"data":[{"test":"value","test2":"value"}],"msg":"提示消息"}
     * 成功有返回的时候调用
     *
     * @param code 状态码
     * @param msg  提示信息
     * @param data 返回数据
     */
    private DataMsg(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data == null ? Collections.EMPTY_MAP : data;
    }

    /**
     * 将json结果集转化为DataMsg对象
     *
     * @param jsonData json数据
     * @param clazz    DataMsg中的object类型
     * @return
     */
    public static DataMsg formatToPojo(String jsonData, Class<?> clazz) {
        try {
            if (clazz == null) {
                return MAPPER.readValue(jsonData, DataMsg.class);
            }
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (clazz != null) {
                if (data.isObject()) {
                    obj = MAPPER.readValue(data.traverse(), clazz);
                } else if (data.isTextual()) {
                    obj = MAPPER.readValue(data.asText(), clazz);
                }
            }
            return new DataMsg(jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 没有object对象的转化
     *
     * @param json
     * @return
     */
    public static DataMsg format(String json) {
        try {
            return MAPPER.readValue(json, DataMsg.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Object是集合转化
     *
     * @param jsonData json数据
     * @param clazz    集合中的类型
     * @return
     */
    public static DataMsg formatToList(String jsonData, Class<?> clazz) {
        try {
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (data.isArray() && data.size() > 0) {
                obj = MAPPER.readValue(data.traverse(),
                        MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
            }
            return new DataMsg(jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


}
