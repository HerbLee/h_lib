package com.herb2sy.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.herb2sy.exception.JsonException;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;


public class JsonUtils {
    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        //对象中所有字段全部显示
        objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        //取消默认转换timestaps
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        //忽略空bean 转json 错误
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        //统一日期格式
        objectMapper.setDateFormat(new SimpleDateFormat(TimeUtils.STANDARD_FORMAT));
        //或略json字符串中存在但是在bean中不存在的
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * 对象转换成字符串
     *
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> String obj2Str(T obj) {
        if (obj == null)
            return null;
        try {
            return obj instanceof String ? (String) obj : objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throwException(e.getMessage());
            return null;
        }
    }

    public static <T> String obj2StrPretty(T obj) {
        if (obj == null)
            return null;
        try {
            return obj instanceof String ? (String) obj : objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (Exception e) {
            throwException(e.getMessage());
            return null;
        }
    }

    public static <T> T str2Obj(String json, Class<T> clazz) {
        if (StringUtils.isEmpty(json) || clazz == null) {
            return null;
        }

        try {
            return clazz.equals(String.class) ? (T) json : objectMapper.readValue(json, clazz);
        } catch (Exception e) {
            throwException(e.getMessage());
            return null;
        }
    }

    /**
     * @param json
     * @param clazz TypeReference<List<User>>
     * @param <T>
     * @return
     */
    public static <T> T str2Obj(String json, TypeReference<T> clazz) {
        if (StringUtils.isEmpty(json) || clazz == null) {
            return null;
        }

        try {
            return clazz.getType().equals(String.class) ? (T) json : (T) objectMapper.readValue(json, clazz);
        } catch (Exception e) {
            throwException(e.getMessage());
            return null;
        }
    }

    /**
     * @param json
     * @param collectionClass List
     * @param elementClass    User
     * @param <T>
     * @return
     */
    public static <T> T str2Obj(String json, Class<?> collectionClass, Class<?>... elementClass) {

        JavaType type = objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClass);

        try {
            return objectMapper.readValue(json, type);
        } catch (Exception e) {
            throwException(e.getMessage());
            return null;
        }
    }


    public static JSONObject getJsonObj(String json) {
        try {
            return new JSONObject(json);
        } catch (Exception e) {
            throwException(e.getMessage());
            return null;
        }

    }

    public static JSONArray getJsonArr(String json) {
        try {
            return new JSONArray(json);
        } catch (Exception e) {
            throwException(e.getMessage());
            return null;
        }

    }

    public static JSONObject getJsonObj(String json, String data) {
        try {
            JSONObject jsonObj = getJsonObj(json);
            if (jsonObj != null){
                return jsonObj.getJSONObject(data);
            }
        } catch (Exception e) {
            throwException(e.getMessage());
        }
            return null;
    }



    private static void throwException(String msg){
        try {
            throw new JsonException(StringUtils.obj2Str(msg,"数据错误"));
        } catch (JsonException e1) {
            e1.printStackTrace();
        }
    }




}