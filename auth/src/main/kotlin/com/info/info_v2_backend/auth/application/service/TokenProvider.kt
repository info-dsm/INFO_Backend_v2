package com.info.info_v2_backend.auth.application.service

import com.info.info_v2_backend.auth.adapter.input.rest.dto.response.TokenResponse
import com.info.info_v2_backend.auth.application.env.JwtProperty
import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import io.jsonwebtoken.Claims
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.util.*

@Component
class TokenProvider(
    private val jwtProperty: JwtProperty,
    private val customAuthDetailsService: AuthDetailsService
){
    fun encode(subject: String): TokenResponse {
        return TokenResponse(
            Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, jwtProperty.secretKey)
                .setSubject(subject)
                .claim("type", "access")
                .setIssuedAt(Date())
                .setExpiration(Date(Date().time + (jwtProperty.accessExpiredAt * 1000)))
                .compact()
            ,
            Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, jwtProperty.secretKey)
                .claim("type", "refresh")
                .setIssuedAt(Date())
                .setExpiration(Date(Date().time + (jwtProperty.refreshExpiredAt * 1000)))
                .compact()
        )
    }

    fun decodeBody(token: String): Claims {
        try {
            return Jwts.parser().setSigningKey(jwtProperty.secretKey).parseClaimsJws(token).body
        } catch (e: JwtException) {
            throw BusinessException(
                e.message.toString(),
                ErrorCode.INVALID_TOKEN_ERROR
            )
        }
    }

    fun getSubjectWithExpiredCheck(token: String): String {
        val body = decodeBody(token)
        val now = Date()
        if (now.after(Date(body.expiration.time))) throw BusinessException("토큰이 만료되었습니다. -> ${Date(body.expiration.time)}", ErrorCode.EXPIRED_TOKEN_ERROR)
        return body.subject
            ?: throw BusinessException("토큰 내부 값이 비었습니다.", ErrorCode.INVALID_TOKEN_ERROR)
    }

    fun isExpired(token: String): Boolean {
        val body = Jwts.parser().setSigningKey(jwtProperty.secretKey).parseClaimsJws(token).body
        val now = Date()
        return now.after(Date(now.time + body.expiration.time))
    }

    fun authentication(token: String): Authentication {
        val userDetails: UserDetails = customAuthDetailsService.loadUserByUsername(getSubjectWithExpiredCheck(token))
        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }

}