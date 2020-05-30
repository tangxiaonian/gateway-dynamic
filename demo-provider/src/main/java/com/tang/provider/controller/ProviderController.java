package com.tang.provider.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Classname ProviderController
 * @Description [ TODO ]
 * @Author Tang
 * @Date 2020/5/23 22:52
 * @Created by ASUS
 */
@RestController
@RequestMapping("/provider")
public class ProviderController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/index")
    public Map<String, String> index() {

        try {
            System.out.println( "睡觉开始...." );
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println( "睡觉结束...." );
        Map<String, String> map = new HashMap<>();

        map.put("status", "success!");
        map.put("code", "200");

        return map;
    }

    @GetMapping("/port")
    public String getPort() {
        return port;
    }

}