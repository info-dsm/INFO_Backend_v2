package com.info.info_v2_backend.apiGateway.filter

import com.info.info_v2_backend.apiGateway.property.JwtProperty
import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.common.auth.HeaderProperty
import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import org.slf4j.LoggerFactory
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.http.server.reactive.ServerHttpResponse
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import java.util.*


@Component
class GlobalFilter(
    private val jwtProperty: JwtProperty
) : AbstractGatewayFilterFactory<Any>() {
    private val log = LoggerFactory.getLogger(this.javaClass)
    private val ALLOW_PATH_LIST = arrayListOf("/employment", "/auth")

    override fun apply(config: Any): GatewayFilter {
        return GatewayFilter { exchange, chain ->
            val request: ServerHttpRequest = exchange.request
            val response: ServerHttpResponse = exchange.response

            log.info("Global Filter start: request id -> {}, path -> {}", request.getId(), request.path)
            if (request.headers.containsKey(HeaderProperty.USER_EMAIL)) {
                request.mutate().header(HeaderProperty.USER_EMAIL, null)
            }
            if (request.headers.containsKey(HeaderProperty.AUTH_LEVEL)) {
                request.mutate().header(HeaderProperty.AUTH_LEVEL, null)
            }
            if (request.headers.containsKey(HeaderProperty.COMPANY_NUMBER)) {
                request.mutate().header(HeaderProperty.COMPANY_NUMBER, null)
            }

            if (!request.headers.containsKey(HttpHeaders.AUTHORIZATION)) {
                if (!ALLOW_PATH_LIST.any { request.path.toString().startsWith(it) }) {
                    log.info("Token cannot found.")
                    throw BusinessException(errorCode = ErrorCode.TOKEN_NEED_ERROR)
                } else {
                    chain.filter(exchange).then(Mono.fromRunnable(Runnable {
                        log.info("Global Filter End: response code -> {}", response.getStatusCode())
                    }))
                }
            } else {
                val authorizationHeader = (request.headers[HttpHeaders.AUTHORIZATION]?: throw BusinessException(errorCode = ErrorCode.NO_AUTHORIZATION_ERROR))[0]
                val jwt = authorizationHeader.replace("Bearer ", "")
                println("jwt: $jwt")
                val body = getTokenBody(jwt)
                body?: let {
                    throw BusinessException("Invalid token -> $jwt", ErrorCode.INVALID_TOKEN_ERROR)
                }
                request.mutate().header(HeaderProperty.USER_EMAIL, body.subject)
                body[HeaderProperty.COMPANY_NUMBER]?. let {
                    log.info("Company Number: $it")
                    request.mutate().header(HeaderProperty.COMPANY_NUMBER, it as String)
                }
                body[HeaderProperty.AUTH_LEVEL]?.let {
                    request.mutate().header(HeaderProperty.AUTH_LEVEL, it as String)
                }
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
            val now = Date()
            return claims
        } catch (e: ExpiredJwtException) {
            throw BusinessException(null, ErrorCode.EXPIRED_TOKEN_ERROR)
        } catch (e: Exception) {
            println(e)
            return null
        }
    }


}
