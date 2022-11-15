package com.info.info_v2_backend.apiGateway.filter

import com.info.info_v2_backend.apiGateway.property.JwtProperty
import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.common.security.HeaderProperty
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import org.slf4j.LoggerFactory
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.http.server.reactive.ServerHttpResponse
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono


@Component
class GlobalFilter(
    private val jwtProperty: JwtProperty
) : AbstractGatewayFilterFactory<Any>() {
    private val log = LoggerFactory.getLogger(this.javaClass)

    override fun apply(config: Any): GatewayFilter {
        return GatewayFilter { exchange, chain ->
            val request: ServerHttpRequest = exchange.request
            val response: ServerHttpResponse = exchange.response

            log.info("Global Filter start: request id -> {}, path -> {}", request.getId(), request.path)

            if (!request.headers.containsKey(HttpHeaders.AUTHORIZATION)) {
                chain.filter(exchange).then(Mono.fromRunnable(Runnable {
                    log.info("Global Filter End: response code -> {}", response.getStatusCode())
                }))
            } else {
                println("Authorization Header Detected")
                val authorizationHeader = request.headers[HttpHeaders.AUTHORIZATION]!![0]
                val jwt = authorizationHeader.replace("Bearer ", "")
                val body = getTokenBody(jwt)
                body?: let {
                    throw BusinessException("Invalid token -> $jwt", ErrorCode.INVALID_TOKEN_ERROR)
                }

                request.mutate().header(HeaderProperty.USER_EMAIL, body.subject)

                body[HeaderProperty.COMPANY_NUMBER]?. let {
                    println(it)
                    request.mutate().header(HeaderProperty.COMPANY_NUMBER, it as String)
                }

                println(request.headers[HeaderProperty.COMPANY_NUMBER])

                chain.filter(exchange).then(Mono.fromRunnable(Runnable {
                    log.info("Global Filter End: response code -> {}", response.getStatusCode())
                }))
            }
        }
    }

    private fun getTokenBody(jwt: String): Claims? {
        var claims: Claims? = null
        try {
            claims = Jwts.parser().setSigningKey(jwtProperty.secretKey)
                .parseClaimsJws(jwt).body

            return claims
        } catch (e: Exception) {
            return null
        }
    }


}
