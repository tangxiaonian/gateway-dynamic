package com.tang.consumer.client;

import com.tang.consumer.fallback.ProviderFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

/**
 * @Classname ProviderClient
 * @Description [ TODO ]
 * @Author Tang
 * @Date 2020/5/23 23:12
 * @Created by ASUS
 */
@FeignClient(value = "demo-provider",
        path= "/provider",
        fallbackFactory = ProviderFallbackFactory.class)
public interface ProviderClient {

    @GetMapping("/index")
    public Map<String, String> index();

    @GetMapping("/port")
    public String getPort();
}