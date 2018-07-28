package com.jenpin.webflux.handlers;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * @author: Jenpin
 * @date: 2018/7/28 0028 10:46
 * @email: yuan_268311@163.com
 * @description: 处理器类Handlder
 **/
@Component
public class RobotHandler {
    public Mono<ServerResponse> say(ServerRequest request){
        return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN).body(BodyInserters.fromObject("hi,i am robot, my name is jack!"));
    }
}