package com.tang.demo.gateway.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tang.demo.gateway.domain.GateWayRouteDefine;
import org.springframework.cloud.gateway.route.RouteDefinition;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tang
 * @since 2020-05-30
 */
public interface GateWayRouteService extends IService<GateWayRouteDefine> {

    public List<GateWayRouteDefine> loadAllRoute();

    public void notifyChanged();

    /**
     * 增加路由
     *
     */
    public String add(RouteDefinition definition);

    /**
     * 更新路由
     */
    public String update(RouteDefinition definition);
    /**
     * 删除路由
     *
     */
    public String delete(String id);


}
