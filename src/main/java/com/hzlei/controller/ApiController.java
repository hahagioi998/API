package com.hzlei.controller;

import com.hzlei.service.MiSportService;
import com.hzlei.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    private static final Logger log = LoggerFactory.getLogger(ApiController.class);

    @Autowired
    private MiSportService miSport;

    @GetMapping("temp/{data}")
    public Map<String, Object> temp(@PathVariable String data) {
        log.info("/api/temp/{" + data + "}");
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("msg", "操作成功");
        result.put("data", data);
        return result;
    }
    @GetMapping("mi/{phone}/{password}/{stepNum}")
    public Result<String> miSport(@PathVariable String phone, @PathVariable String password, @PathVariable String stepNum) {
        log.info("/api/mi/{" + phone + "}/{" + password + "}/{" + stepNum + "}");
        String result = miSport.miSport(phone, password, stepNum);
        return Result.success(result);
    }

}
