package com.hzlei.dto;

/**
 * @author hzlei
 * @date 2021/04/07 11:44
 * Description  小米运动 Dto
 */
public class MiSportDto {
    private String userId;
    private String loginToken;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLoginToken() {
        return loginToken;
    }

    public void setLoginToken(String loginToken) {
        this.loginToken = loginToken;
    }
}
