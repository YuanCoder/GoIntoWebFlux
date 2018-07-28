# Spring Boot Webflux 笔记 
Webflux 就是基于 Reactor 实现的。Spring Boot 2.0 包括一个新的 spring-webflux 模块。该模块
包含对响应式 HTTP 和 WebSocket 客户端的支持，以及对 REST，HTML 和 WebSocket 交互等程序的支
持。一般来说，Spring MVC 用于同步处理，Spring Webflux 用于异步处理。

## 特性
#### 响应式 API：Reactor 框架是 Spring Boot Webflux 响应库依赖，通过 Reactive Streams 并与其他响应库交互。提供了两种响应式 API: Mono 和 Flux。一般是将 Publisher 作为输入，在框架内部转换成 Reactor 类型并处理逻辑，然后返回 Flux 或 Mono 作为输出。  
#### 编程模型：WebFlux 提供了 WebHandler API 去定义非阻塞 API 抽象接口，编程模型实现：1.注解控制层（注解方式）。  2. 功能性端点（这种模型，全程控制了请求 - 响应的生命流程）；
#### 内嵌容器：WebFlux 默认是通过 Netty 启动 （要注意，必须是 Servlet 3.1+ 容器，如 Tomcat、Jetty；或者非 Servlet 容器，如 Netty 和 Undertow）；

## webflux组件
#### Web：Spring WebFlux；
#### 模板引擎：Thymeleaf；
#### 存储：Redis、MongoDB、Cassandra，不支持 MySQL；
#### 内嵌容器：Tomcat、Jetty、Undertow；

## 注意
#### MVC 能满⾜场景的，就不需要更改为 WebFlux。  
#### 要注意容器的支持，可以看看上⾯内嵌容器的支持。  
#### 微服务体系结构，WebFlux 和 MVC 可以混合使用。尤其开发 IO 密集型服务的时候，选择 WebFlux 去实现。  