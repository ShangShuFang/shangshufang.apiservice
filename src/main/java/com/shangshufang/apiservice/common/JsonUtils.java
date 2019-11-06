package com.shangshufang.apiservice.common;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.List;

import static net.sf.json.JSONArray.toCollection;

public class JsonUtils {
    public static<T> String serializeToJson(T object){
        JSONObject jsonObject = JSONObject.fromObject(object);
        return jsonObject.toString();
    }

    public static<T> String serializeToJson(List<T> list){
        JSONArray json = JSONArray.fromObject(list);
        return json.toString();
    }

    public static<T> List deserializationToObject(String json, Class<T> tClass){
        if (json.isEmpty()){
            return null;
        }
        JSONArray jsonArray = JSONArray.fromObject(json);
        return (List) toCollection(jsonArray, tClass);
    }
}