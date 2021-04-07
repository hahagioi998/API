package com.hzlei.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Splitter;
import com.hzlei.dto.MiSportDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author hzlei
 * @date 2021/04/07 11:48
 * Description  MiSport 登录工具类
 */
@Slf4j
public class MiSportLoginUtil {

    public static MiSportDto login(String user, String password) {
        String url1 = "https://api-user.huami.com/registrations/+86" + user + "/tokens";
        Map<String, String> paramMap1 = new HashMap<>();
        paramMap1.put("client_id", "HuaMi");
        paramMap1.put("password", password);
        paramMap1.put("redirect_uri", "https://s3-us-west-2.amazonaws.com/hm-registration/successsignin.html");
        paramMap1.put("token", "access");
        paramMap1.put("r", "A8B45B59B78EA20C35D99FC1067400FB307DDD6D0100000018E245088136104F");
        paramMap1.put("state", "REDIRECTION");
        paramMap1.put("t", "1603185096379");
        // 发送 post 请求
        String location = getLocation(url1, paramMap1);
        String code = "";
        try {
            code = getCode(location, "access");
        } catch (Exception e) {
            log.error("MiSportLoginUtil --> 42 --> 登录失败！");

            MiSportDto miSportDto = new MiSportDto();
            miSportDto.setLoginToken("0");
            miSportDto.setUserId("0");

            return miSportDto;
        }

        if (!StringUtils.isEmpty(code)) {
            String url2 = "https://account.huami.com/v2/client/login";
            Map<String, String> paramMap2 = new HashMap<>();
            paramMap2.put("app_name", "com.xiaomi.hm.health");
            paramMap2.put("app_version", "4.6.0");
            paramMap2.put("code", code);
            paramMap2.put("country_code", "CN");
            paramMap2.put("device_id", "2C8B4939-0CCD-4E94-8CBA-CB8EA6E613A1");
            paramMap2.put("device_model", "phone");
            paramMap2.put("grant_type", "access_token");
            paramMap2.put("third_name", "huami_phone");
            String result = sendPost(url2, paramMap2);
            JSONObject jsonObject = JSON.parseObject(result);
            JSONObject tokenInfo = jsonObject.getJSONObject("token_info");
            String login_token = tokenInfo.getString("login_token");
            MiSportDto miSportDto = new MiSportDto();
            miSportDto.setLoginToken(login_token);
            String userId = tokenInfo.getString("user_id");
            miSportDto.setUserId(userId);
            return miSportDto;
        }
        log.error("MiSportLoginUtil --> 72 --> 登录失败！");
        MiSportDto miSportDto = new MiSportDto();
        miSportDto.setLoginToken("0");
        miSportDto.setUserId("0");
        return miSportDto;
    }

    public static String getCode(String url, String name) {
        String params = url.substring(url.indexOf("?") + 1, url.length());
        Map<String, String> split = Splitter.on("&").withKeyValueSeparator("=").split(params);
        return split.get(name);
    }

    /**
     * 向指定 URL 发送 POST 方法的请求
     *
     * @param url 发送请求的 URL
     *            请求参数
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, Map<String, ?> paramMap) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";

        String param = "";
        Iterator<String> it = paramMap.keySet().iterator();

        while (it.hasNext()) {
            String key = it.next();
            param += key + "=" + paramMap.get(key) + "&";
        }

        try {

            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("Accept-Charset", "utf-8");
            conn.setRequestProperty("user-agent", "MiFit/4.6.0 (iPhone; iOS 14.0.1; Scale/2.00)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            log.error("MiSportLoginUtil --> 132 --> Exception e: " + e);
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    public static String getLocation(String url, Map<String, ?> paramMap) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";

        String param = "";
        Iterator<String> it = paramMap.keySet().iterator();

        while (it.hasNext()) {
            String key = it.next();
            param += key + "=" + paramMap.get(key) + "&";
        }

        try {

            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            conn.setRequestProperty("user-agent", "MiFit/4.6.0 (iPhone; iOS 14.0.1; Scale/2.00)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义 BufferedReader 输入流来读取 URL 的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            log.info("MiSportLoginUtil --> 182 --> conn: " + conn.getURL());
            result = conn.getURL().toString();
        } catch (Exception e) {
            log.error("MiSportLoginUtil --> 185 --> Exception e: " + e);
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    public static String getTime() {
        String url = "http://api.m.taobao.com/rest/api3.do?api=mtop.common.getTimestamp";
        String result = sendGet(url, "");
        JSONObject jsonObject = JSON.parseObject(result);
        JSONObject data = jsonObject.getJSONObject("data");
        String t = data.getString("t");
        return t;
    }

    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "MiFit/4.6.0 (iPhone; iOS 14.0.1; Scale/2.00)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                log.info(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader 输入流来读取 URL 的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            log.error("MiSportLoginUtil --> 241 --> 发送GET请求出现异常！" + e);
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    public static String getAppToken(String loginToken) {
        String url = "https://account-cn.huami.com/v1/client/app_tokens?app_name=com.xiaomi.hm.health&dn=api-user.huami.com%2Capi-mifit.huami.com%2Capp-analytics.huami.com&login_token=" + loginToken + "&os_version=4.1.0";
        String result = sendGet(url, "");
        JSONObject jsonObject = JSON.parseObject(result);
        JSONObject tokenInfo = jsonObject.getJSONObject("token_info");
        String app_token = tokenInfo.getString("app_token");
        log.info("MiSportLoginUtil --> 262 --> app_token获取成功: " + app_token);
        return app_token;
    }

    public static String readTxt() {
        try {
            String encoding = "GBK";
            File file = new File("/docker/data_json.txt");
            if (file.isFile() && file.exists()) { // 判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file), encoding);// 考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    log.info("MiSportLoginUtil --> 276 --> lineTxt: " + lineTxt);
                    return lineTxt;
                }
                read.close();
            } else {
                log.error("MiSportLoginUtil --> 281 --> 找不到指定的文件(data_json.txt)");
                return "";
            }
        } catch (Exception e) {
            log.error("MiSportLoginUtil --> 285 --> 读取文件内容出错(data_json.txt)");
        }
        return "";
    }

    public static String sendPostAndToken(String url, Map<String, ?> paramMap, String appToken) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";

        String param = "";
        Iterator<String> it = paramMap.keySet().iterator();

        while (it.hasNext()) {
            String key = it.next();
            param += key + "=" + paramMap.get(key) + "&";
        }

        try {

            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("Accept-Charset", "utf-8");
            conn.setRequestProperty("user-agent", "MiFit/4.6.0 (iPhone; iOS 14.0.1; Scale/2.00)");
            conn.setRequestProperty("apptoken", appToken);
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            log.error("MiSportLoginUtil --> 331 --> Exception e: " + e);
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }
}
