package com.jenpin.webflux.handlers;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * 处理器类Handlder
 */
public class RobotHandler {
    public Mono<ServerResponse> say(ServerRequest request){
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(BodyInserters.fromObject("i am robot!"));
    }
}