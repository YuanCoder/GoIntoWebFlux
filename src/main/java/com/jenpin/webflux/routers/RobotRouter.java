package com.jenpin.webflux.routers;

import com.jenpin.webflux.handlers.RobotHandler;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * 编写路由器类 router
 */
public class RobotRouter {
    private static final String LOCATION_PATH = "/robot";

    /**
     * 将请求路由到处理器
     * @param robotHandler
     * @return
     */
    public RouterFunction<ServerResponse> doRoute(RobotHandler robotHandler){
        return RouterFunctions.route(
                RequestPredicates.GET(LOCATION_PATH).and(RequestPredicates.accept(MediaType.APPLICATION_JSON_UTF8)),
                robotHandler::say
        );
    }
}
