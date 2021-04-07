package com.hzlei.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author hzlei
 * @date 2021/04/07 11:44
 * Description  小米运动 Dto
 */
@Data
public class MiSportDto implements Serializable {
    private String userId;
    private String loginToken;
}
