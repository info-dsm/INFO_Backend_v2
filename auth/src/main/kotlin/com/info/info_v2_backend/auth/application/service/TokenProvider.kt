package com.info.info_v2_backend.auth.application.service

import com.info.info_v2_backend.auth.adapter.input.rest.dto.response.TokenResponse
import com.info.info_v2_backend.auth.application.env.JwtProperty
import com.info.info_v2_backend.common.exception.BusinessException
import com.info.info_v2_backend.common.exception.ErrorCode
import com.info.info_v2_backend.common.auth.HeaderProperty
import io.jsonwebtoken.Claims
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import java.util.*

@Component
class TokenProvider(
    private val jwtProperty: JwtProperty
){
    fun encode(subject: String, companyNumber: String?, authLevel: String): TokenResponse {
        return TokenResponse(
            Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, jwtProperty.secretKey)
                .setSubject(subject)
                .claim(HeaderProperty.AUTH_LEVEL, authLevel)
                .claim("type", "access")
                .claim(HeaderProperty.COMPANY_NUMBER, companyNumber)
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

    fun getClaims(token: String): Claims {
        val body = decodeBody(token)
        body.subject?: throw BusinessException("?????? ?????? ?????? ???????????????.", ErrorCode.INVALID_TOKEN_ERROR)
        return body
    }

    fun isExpired(token: String): Boolean {
        val body = Jwts.parser().setSigningKey(jwtProperty.secretKey).parseClaimsJws(token).body
        val now = Date()
        if (now.after(Date(body.expiration.time))) return true
        return false
    }


}