package com.jenpin.webflux.config;

import com.jenpin.webflux.handlers.RobotHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Jenpin
 * @date: 2018/8/2 0002 16:47
 * @email: yuan_268311@163.com
 * @description: websocket配置类
 **/
@Component
public class WebSocketConfiguration {
    @Autowired
    @Bean
    public HandlerMapping webSocketMapping(final RobotHandler robotHandler) {
        final Map<String, WebSocketHandler> map = new HashMap<>();
        map.put("/echo", robotHandler);

        final SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
        mapping.setOrder(Ordered.HIGHEST_PRECEDENCE);
        mapping.setUrlMap(map);
        return mapping;
    }

    @Bean
    public WebSocketHandlerAdapter handlerAdapter() {
        return new WebSocketHandlerAdapter();
    }
}
