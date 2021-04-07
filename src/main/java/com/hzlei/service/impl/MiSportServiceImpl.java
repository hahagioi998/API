package com.hzlei.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hzlei.dto.MiSportDto;
import com.hzlei.service.MiSportService;
import com.hzlei.utils.MiSportLoginUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hzlei
 * @date 2021/04/07 11:47
 * Description  MiSport
 */
@Slf4j
@Service
public class MiSportServiceImpl implements MiSportService {

    @Override
    public String miSport(String phone, String password, String stepNum) {
        // 登陆
        MiSportDto login = MiSportLoginUtil.login(phone, password);
        if ("0".equals(login.getLoginToken())) {
            log.info("MiSportServiceImpl --> 30 --> 登录失败");
            return "登录失败";
        }
        //获取时间戳
        String time = MiSportLoginUtil.getTime();
        //获取appToken
        String appToken = MiSportLoginUtil.getAppToken(login.getLoginToken());
        //读取txt文件
        String txt = MiSportLoginUtil.readTxt();
        String format = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        txt += format + "\"}]";
        String da932FFFFE8816E7 = txt.replace("321123", "DA932FFFFE8816E7");
        String replace = da932FFFFE8816E7.replace("12345", stepNum + "");

        String url = "https://api-mifit-cn.huami.com/v1/data/band_data.json?&t=" + time;
        Map<String, String> paramMap = new HashMap<>();
        log.info("MiSportServiceImpl --> 46 --> txt: " + replace);
        paramMap.put("data_json", replace);
        paramMap.put("userid", login.getUserId());
        paramMap.put("device_type", "0");
        paramMap.put("last_sync_data_time", "1589917081");
        paramMap.put("last_deviceid", "DA932FFFFE8816E7");
        String result = MiSportLoginUtil.sendPostAndToken(url, paramMap, appToken);
        log.info("MiSportServiceImpl --> 53 --> result: " + result);
        JSONObject jsonObject = JSON.parseObject(result);
        return "修改步数："+ stepNum + " " + jsonObject.getString("message") + "！";
    }
}
