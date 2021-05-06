package com.hzlei.controller;

import com.hzlei.entity.User;
import com.hzlei.utils.MiSportService;
import com.hzlei.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hzlei
 * @date 2021/03/17 13:30
 * Description  api
 */
@Slf4j
@RestController
@RequestMapping("/api/")
public class ApiController {

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
        String result = MiSportService.miSport(phone, password, stepNum);
        return Result.success(result);
    }

    @PostMapping("addUser")
    public Result<User> addUser(@RequestBody @Valid User user) {
        log.info(user.toString());
        return Result.success("添加成功", user);
    }

}
