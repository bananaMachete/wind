package com.dico.wind.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xipeifeng
 * @since 2019-7-3
 */
@RestController
public class IndexController {

    @ResponseBody
    @PostMapping(value = "/")
    public JSONObject wind(@RequestBody JSONObject jsonParam){
        System.out.println(jsonParam);
        JSONObject result = new JSONObject();
        result.put("nonce",520510201);
        result.put("servertime","2019-07-03 13:43:33");
        result.put("data_type","real-time");
        result.put("result",0);
        System.out.println("_________________________来了，老弟__________________________________");
        return result;
    }
}
