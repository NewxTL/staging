package com.newx.staging.util;

import net.sf.json.JSON;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

/**
 * Created by Newx on 2017/11/30.
 */
public class JsonUtil {

    public static JSON xml2json(String xml) {
        XMLSerializer xmlSerializer = new XMLSerializer();
        JSON json = xmlSerializer.read(xml);
        return json;
    }

    public static String json2xml(String rootName, String elementName, String arrayName, String json) {
        JSONObject jobj = JSONObject.fromObject(json);
        XMLSerializer xmlSerializer = new XMLSerializer();
        xmlSerializer.setRootName(rootName);
        xmlSerializer.setElementName(elementName);
        xmlSerializer.setArrayName(arrayName);
        xmlSerializer.setTypeHintsEnabled(false);
        xmlSerializer.setTypeHintsCompatibility(false);
        String xml = xmlSerializer.write(jobj);
        return xml;
    }

    public static<T> String objectToJson(T obj) throws JSONException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        // Convert object to JSON string
        String jsonStr = "";
        try {
            jsonStr =  mapper.writeValueAsString(obj);
        } catch (IOException e) {
            throw e;
        }
        return JSONObject.fromObject(obj).toString();
    }
}

