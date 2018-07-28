package com.jenpin.webflux.routers;

import com.jenpin.webflux.handlers.RobotHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * @author: Jenpin
 * @date: 2018/7/28 0028 11:11
 * @email: yuan_268311@163.com
 * @description: 编写路由器类 router
 **/
@Configuration
public class RobotRouter {
    private static final String LOCATION_PATH = "/robot";

    /**
     * 将请求路由到处理器
     * @param robotHandler
     * @return
     */
    @Bean
    public RouterFunction<ServerResponse> doRoute(RobotHandler robotHandler){
        return RouterFunctions.route(
                RequestPredicates.GET(LOCATION_PATH).and(RequestPredicates.accept(MediaType.TEXT_PLAIN)),
                robotHandler::say
        );
    }
}
