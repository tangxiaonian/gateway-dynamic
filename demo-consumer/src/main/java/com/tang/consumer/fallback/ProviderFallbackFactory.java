package com.tang.consumer.fallback;

import com.tang.consumer.client.ProviderClient;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Classname ProviderFallbackFactory
 * @Description [ TODO ]
 * @Author Tang
 * @Date 2020/5/23 23:17
 * @Created by ASUS
 */
@Component
public class ProviderFallbackFactory implements FallbackFactory<ProviderClient> {

    @Override
    public ProviderClient create(Throwable throwable) {
        return new ProviderClient(){
            @Override
            public Map<String, String> index() {
                Map<String, String> map = new HashMap<>();
                map.put("status", "降级...!");
                map.put("code", "400");
                return map;
            }

            @Override
            public String getPort() {
                return "9000";
            }
        };
    }

}