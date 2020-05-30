package com.tang.demo.gateway.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tang.demo.gateway.domain.GateWayRouteDefine;
import com.tang.demo.gateway.mapper.GateWayRouteMapper;
import com.tang.demo.gateway.service.GateWayRouteService;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tang
 * @since 2020-05-30
 */
@Service
public class GateWayRouteServiceImpl extends ServiceImpl<GateWayRouteMapper, GateWayRouteDefine>
        implements GateWayRouteService, ApplicationEventPublisherAware {

    @Resource
    public GateWayRouteMapper gateWayDefineMapper;

    @Resource
    private RouteDefinitionWriter routeDefinitionWriter;

    private ApplicationEventPublisher publisher;

    @Override
    public void notifyChanged() {
        this.publisher.publishEvent(new RefreshRoutesEvent(this));
    }

    @Override
    public List<GateWayRouteDefine> loadAllRoute() {
        return gateWayDefineMapper.selectList(null);
    }
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    /**
     * 增加路由
     *
     */
    @Override
    public String add(RouteDefinition definition) {
        routeDefinitionWriter.save(Mono.just(definition)).subscribe();
        notifyChanged();
        return "success";
    }

    /**
     * 更新路由
     */
    @Override
    public String update(RouteDefinition definition) {
        try {
            this.routeDefinitionWriter.delete(Mono.just(definition.getId()));
        } catch (Exception e) {
            return "update fail,not find route  routeId: " + definition.getId();
        }
        try {
            routeDefinitionWriter.save(Mono.just(definition)).subscribe();
            notifyChanged();
            return "success";
        } catch (Exception e) {
            return "update route  fail";
        }
    }

    /**
     * 删除路由
     *
     */
    @Override
    public String delete(String id) {
        try {
            this.routeDefinitionWriter.delete(Mono.just(id));
            notifyChanged();
            return "delete success";
        } catch (Exception e) {
            e.printStackTrace();
            return "delete fail";
        }

    }
}
