package kr.mooner510.dsmpractice.security.component

import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.UnsupportedJwtException
import io.jsonwebtoken.security.Keys
import kr.mooner510.dsmpractice.global.error.ErrorCode
import kr.mooner510.dsmpractice.global.error.data.GlobalError
import kr.mooner510.dsmpractice.security.data.entity.user.User
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import java.time.Instant
import java.util.*

@Component
class TokenProvider(
    @Value("\${security.key}") val keyString: String,
    @Value("\${security.access}") val accessTime: Long,
    @Value("\${security.refresh}") val refreshTime: Long,
) {
    private val key = Keys.hmacShaKeyFor(keyString.toByteArray())
    private val parser = Jwts.parser().verifyWith(key).build()

    // jwt 를 사용하면 db조회해서 유저를 일일이 찾을 필요 없음
    // jwt에는 유저의 정보가 간략하게 들어있음
    // 대신 중요한 정보는 넣으면 안됨. 위험한지 생각하고 넣어야함
    fun newAccess(user: User): String {
        // 유저에 대한 간략한 정보 = id, name
        // 발급시간, 만료시간

        val now = Instant.now()

        return Jwts.builder()
            .id(user.id.toString())
            .subject("acc")
            .claim("name", user.name)
            .issuedAt(Date.from(now))
            .expiration(Date.from(now.plusSeconds(accessTime)))
            .compact()
    }

    fun newRefresh(user: User): String {
        throw NotImplementedError()
    }

    fun getUser(token: String, isAccessToken: Boolean): User {
        try {
            val claims = parser.parseSignedClaims(token).payload

            if (isAccessToken && claims.subject != "acc"){
                throw GlobalError(ErrorCode.UNSUPPORTED_TOKEN)
            }

            return User(
                claims.id.toLong(),
                claims.get("name", String::class.java),
                ""
            )
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is UnsupportedJwtException) {
                throw GlobalError(ErrorCode.UNSUPPORTED_TOKEN)
            } else if (e is ExpiredJwtException) {
                throw GlobalError(ErrorCode.EXPIRED_TOKEN)
            }
            throw e
        }
    }
}
