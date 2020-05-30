package com.tang.demo.gateway.config;

import com.alibaba.fastjson.JSON;
import com.tang.demo.gateway.domain.GateWayRouteDefine;
import com.tang.demo.gateway.service.GateWayRouteService;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Classname MyRouteDefinitionRepository
 * @Description [ TODO ]
 * @Author Tang
 * @Date 2020/5/30 15:43
 * @Created by ASUS
 */
@Component
public class MyRouteDefinitionRepository implements RouteDefinitionRepository {

    @Resource
    public GateWayRouteService gateWayDefineServiceImpl;

    /**
     * 获取路由列表
     * @return
     */
    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        try {
            List<GateWayRouteDefine> gatewayDefineList = gateWayDefineServiceImpl.loadAllRoute();
            Map<String, RouteDefinition> routes = new LinkedHashMap<>();
            for (GateWayRouteDefine gatewayDefine: gatewayDefineList) {
                RouteDefinition definition = new RouteDefinition();
                definition.setId(gatewayDefine.getId());
                definition.setUri(new URI(gatewayDefine.getUri()));
                // predicate
                List<PredicateDefinition> predicateDefinitions = gatewayDefine.getPredicateDefinition();
                if (predicateDefinitions != null) {
                    definition.setPredicates(predicateDefinitions);
                }
                // filter
                List<FilterDefinition> filterDefinitions = gatewayDefine.getFilterDefinition();
                if (filterDefinitions != null) {
                    definition.setFilters(filterDefinitions);
                }
                // metadata
                Map<String, Object> metadata = gatewayDefine.getMetadata();
                if (metadata != null) {
                    definition.setMetadata(metadata);
                }
                routes.put(definition.getId(), definition);
            }
            return Flux.fromIterable(routes.values());
        } catch (Exception e) {
            e.printStackTrace();
            return Flux.empty();
        }
    }

    /**
     * 保存一个路由信息
     * @param route
     * @return
     */
    @Override
    public Mono<Void> save(Mono<RouteDefinition> route) {
        return route.flatMap(r -> {
            try {
                GateWayRouteDefine gatewayDefine = new GateWayRouteDefine();
                gatewayDefine.setId(r.getId());
                gatewayDefine.setUri(r.getUri().toString());
                gatewayDefine.setPredicates(JSON.toJSONString(r.getPredicates()));
                gatewayDefine.setFilters(JSON.toJSONString(r.getFilters()));
                gatewayDefine.setMetadata(JSON.toJSONString(r.getMetadata()));
                gateWayDefineServiceImpl.save(gatewayDefine);
                return Mono.empty();
            } catch (Exception e) {
                e.printStackTrace();
                return Mono.defer(() ->
                        Mono.error(new NotFoundException("RouteDefinition save error: "+ r.getId()))
                );
            }
        });
    }

    /**
     * 删除路由信息
     * @param routeId
     * @return
     */
    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        return routeId.flatMap(id -> {
            try {
                gateWayDefineServiceImpl.delete(id);
                return Mono.empty();
            } catch (Exception e) {
                e.printStackTrace();
                return Mono.defer(() -> Mono.error(new NotFoundException("RouteDefinition delete error: " + routeId)));
            }
        });
    }
}