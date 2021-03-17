package com.hzlei.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hzlei
 * @date 2021/03/17 13:30
 * Description  api
 */
@RestController
@RequestMapping("/api/")
public class ApiController {

    @GetMapping("temp")
    public Map<String, Object> temp() {
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("msg", "操作成功");
        result.put("data", 1);
        return result;
    }
}
