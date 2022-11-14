package com.info.info_v2_backend.apiGateway.filter

import com.info.info_v2_backend.apiGateway.exception.GlobalExceptionHandler
import org.slf4j.LoggerFactory
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.http.server.reactive.ServerHttpResponse
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class GlobalFilter : AbstractGatewayFilterFactory<Any>() {
    private val log = LoggerFactory.getLogger(this.javaClass)

    override fun apply(config: Any): GatewayFilter {
        return GatewayFilter { exchange, chain ->
            // custom pre filter
            val request: ServerHttpRequest = exchange.request
            val response: ServerHttpResponse = exchange.response
            log.info("Global Filter start: request id -> {}, path -> {}", request.getId(), request.path)
            chain.filter(exchange).then(Mono.fromRunnable(Runnable {
                log.info("Global Filter End: response code -> {}", response.getStatusCode())
            }))
        }
    }

}
