package com.tang.consumer.controller;

import com.tang.consumer.client.ProviderClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Classname ConsumerController
 * @Description [ TODO ]
 * @Author Tang
 * @Date 2020/5/23 22:47
 * @Created by ASUS
 */
@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    @Resource
    private ProviderClient providerClient;

    @GetMapping("/index")
    public Map<String, String> index() {
        return providerClient.index();
    }

    @GetMapping("/port")
    public String getPort() {
        return providerClient.getPort();
    }

}