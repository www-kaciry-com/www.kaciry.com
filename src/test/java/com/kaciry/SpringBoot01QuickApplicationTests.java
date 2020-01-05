package com.kaciry;

import com.alibaba.fastjson.JSONObject;
import com.kaciry.entity.ResultDanmu;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBoot01QuickApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void rtPostObject() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://www.kaciry.com:1207/v3?id=av750617502947079826300569.mp4";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        String strbody = restTemplate.exchange(url, HttpMethod.GET, entity, String.class).getBody();
        String str = "{\n" +
                "    \"code\": 0,\n" +
                "    \"data\": [\n" +
                "        [\n" +
                "            11.506867,\n" +
                "            0,\n" +
                "            16777215,\n" +
                "            \"DIYgod\",\n" +
                "            \"测试\"\n" +
                "        ]\n" +
                "    ]\n" +
                "}";

        ResultDanmu resultDanmu0 = JSONObject.parseObject(strbody, ResultDanmu.class);

        String outObj = resultDanmu0.getData().get(0).toString();

        String[] datas = outObj.substring(1, outObj.length() - 1).split(",");
        System.out.println("個數-->" + resultDanmu0.getData().size());

        for (String data : datas) {
            System.out.println(data);
        }
    }

    @Test
    public void addAllStr() {
        List<String> list1 = new ArrayList<>();
        list1.add("1");
        list1.add("2");

        List<String> list2 = new ArrayList<>();
        list2.add("3");
        list2.add("4");

        list1.addAll(list2);
        System.out.println(list1);
    }
}
