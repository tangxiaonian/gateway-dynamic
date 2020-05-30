package com.tang.demo.gateway.controller;

import com.tang.demo.gateway.service.GateWayRouteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author tang
 * @since 2020-05-30
 */
@RestController
@RequestMapping("/gateway")
public class GateWayRouteController {

    @Resource
    private GateWayRouteService gateWayRouteServiceImpl;

    @GetMapping("/loadConfig")
    public String loadConfig() {
        gateWayRouteServiceImpl.notifyChanged();
        return "success";
    }

}

