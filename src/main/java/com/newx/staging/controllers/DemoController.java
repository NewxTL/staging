package com.newx.staging.controllers;

import com.alibaba.fastjson.JSONObject;
import com.newx.staging.mapper.DemoRepository;
import com.newx.staging.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Newx on 2017/11/7.
 */
@Controller
@RequestMapping("/public/demo")
public class DemoController {

    @Autowired
    private DemoService demoService;

    @RequestMapping(value = "/ds1", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JSONObject> ds1Insert(@RequestBody JSONObject object) {
        JSONObject jsonObject = new JSONObject();
        try {
            demoService.ds1Insert(object.getString("name"), object.getString("age"));
            jsonObject.put("success", "Y");
        } catch (Exception e) {
            jsonObject.put("success", "N");
            jsonObject.put("errorMessage", e.getMessage());
            return ResponseEntity.ok().body(jsonObject);
        }
        return ResponseEntity.ok().body(jsonObject);
    }

    @RequestMapping(value = "/ds2", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JSONObject> ds2Insert(@RequestBody JSONObject object) {
        JSONObject jsonObject = new JSONObject();
        try {
            demoService.ds2Insert(object.getString("name"), object.getString("age"));
            jsonObject.put("success", "Y");
        } catch (Exception e) {
            jsonObject.put("success", "N");
            jsonObject.put("errorMessage", e.getMessage());
            return ResponseEntity.ok().body(jsonObject);
        }
        return ResponseEntity.ok().body(jsonObject);
    }

}
