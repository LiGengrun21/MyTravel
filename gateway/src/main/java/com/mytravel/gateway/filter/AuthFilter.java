//package com.mytravel.gateway.filter;
//
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.core.Ordered;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
///**
// * @author Li Gengrun
// * @date 2024/4/20 11:35
// */
//@Component
//public class AuthFilter implements GlobalFilter, Ordered {
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        // 验证token
//        String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");
//        System.out.println("Print authHeader: "+authHeader);
//        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//            return exchange.getResponse().setComplete();  // 停止处理并返回401
//        }
//        // 如果认证信息存在，继续过滤链
//        return chain.filter(exchange);
//    }
//
//    @Override
//    public int getOrder() {
//        return -100; // 让这个filter最先执行
//    }
//}
