package com.avall.ms.attachments.infrastructure.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.UnsupportedJwtException

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.security.SignatureException
import java.util.*



@Component
class JwtProvider(
    @param:Value("\${app.security.jwtSigningSecret}") private val jwtSecret: String,
    @param:Value("\${app.security.jwtExpirationInMs}") private val jwtExpirationInMs: Int
) : IJwtProvider {
    companion object {
        private val log = LoggerFactory.getLogger(this::class.java)
    }

    override fun generateToken(authentication: Authentication): String {
        val userPrincipal: UserPrincipal = authentication.principal as UserPrincipal
        return generateToken(userPrincipal.id)
    }

    override fun generateToken(customerId: String?): String {
        val now = Date()
        val expiryDate = Date(now.time + jwtExpirationInMs)
        return Jwts.builder()
            .setSubject(customerId)
            .setIssuedAt(Date())
            .setExpiration(expiryDate)
            .signWith(SignatureAlgorithm.HS512, jwtSecret)
            .compact()
    }

    override fun getUserIdFromToken(token: String?): String {
        val claims: Claims = Jwts.parser()
            .setSigningKey(jwtSecret)
            .parseClaimsJws(token)
            .getBody()
        return claims.getSubject()
    }

    override fun validateToken(authToken: String?): Boolean {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken)
            return true
        } catch (ex: SignatureException) {
            log.error("Invalid JWT signature")
        } catch (ex: MalformedJwtException) {
            log.error("Invalid JWT token")
        //} catch (ex: ExpiredJwtException) {
          //  log.error("Expired JWT token")
        } catch (ex: UnsupportedJwtException) {
            log.error("Unsupported JWT token")
        } catch (ex: IllegalArgumentException) {
            log.error("JWT claims string is empty.")
        }
        return false
    }
}
