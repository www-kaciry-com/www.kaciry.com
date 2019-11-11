package com.kaciry.utils;

import com.alibaba.fastjson.JSONObject;
import com.kaciry.entity.ResultDanmu;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

/**
 * @author kaciry
 * @date 2019/11/11 17:42
 * @description 自动获取弹幕数量
 */
public class AutoGetBarrages {
    public static int getBarrages(String value) {
        int res = 0;
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://www.kaciry.com:1207/v3?id=" + value;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        String str = restTemplate.exchange(url, HttpMethod.GET, entity, String.class).getBody();
        ResultDanmu resultDanmu = JSONObject.parseObject(str, ResultDanmu.class);
        if (resultDanmu != null) {
            res = resultDanmu.getData().size();
        }
        return res;
    }
}
