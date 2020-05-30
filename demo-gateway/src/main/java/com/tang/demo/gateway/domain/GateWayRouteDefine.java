package com.tang.demo.gateway.domain;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @Classname GatewayDefine
 * @Description [ 参考 RouteDefinition 类进行定义 ]
 * @Author Tang
 * @Date 2020/5/30 15:14
 * @Created by ASUS
 */
@TableName(value = "gateway_define")
public class GateWayRouteDefine implements Serializable {

    @TableId(type = IdType.AUTO)
    private String id;

    @TableField
    private String uri;

    @TableField
    private String predicates;

    @TableField
    private String filters;

    private String metadata;

    public Map<String,Object> getMetadata() {
        if (this.metadata != null) {
            return JSON.parseObject(this.metadata, Map.class);
        } else {
            return null;
        }
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getPredicates() {
        return this.predicates;
    }

    public void setPredicates(String predicates) {
        this.predicates = predicates;
    }

    public List<PredicateDefinition> getPredicateDefinition() {
        if (this.predicates != null) {
            return JSON.parseArray(this.predicates, PredicateDefinition.class);
        } else {
            return null;
        }
    }

    public String getFilters() {
        return filters;
    }

    public List<FilterDefinition> getFilterDefinition() {
        if (this.filters != null) {
            return JSON.parseArray(this.filters, FilterDefinition.class);
        } else {
            return null;
        }
    }

    public void setFilters(String filters) {
        this.filters = filters;
    }
}