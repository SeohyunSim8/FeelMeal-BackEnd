package feelmeal.global.common.jwt;

import feelmeal.global.common.exception.CustomException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

import static feelmeal.global.common.exception.ResponseCode.INVALID_JWT;

@Slf4j
@Service
public class JwtService {
    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.access_exp}")
    private long accessExpiration;

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    /* JWT 생성 */
    public String createJwt(Long memberId) {
        Date now = new Date();

        return Jwts.builder()
                .setHeaderParam("type", "jwt")
                .claim("memberId", memberId)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + accessExpiration)) // 토큰 만료 시간
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /* JWT에서 memberId 추출 */
    public Long getMemberId(String accessToken) throws CustomException {
        // JWT 파싱을 진행한다
        Jws<Claims> claims;
        try {
            // SecretKey로 서명 검증
            JwtParser parser = Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build();

            claims = parser.parseClaimsJws(accessToken);
        } catch (ExpiredJwtException expiredJwtException) {
            // 유효기간이 지났다면 예외 처리를 진행한다
            throw new CustomException(INVALID_JWT);
        } catch (Exception exception) {
            log.error("JWT ERROR", exception);
            throw new CustomException(INVALID_JWT);
        }

        // 3. memberId 추출
        return claims.getBody().get("memberId", Long.class);
    }

    /* SecurityContext에서 memberId를 추출한다 */
    public Long getMemberIdFromUserDetails() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;

        return Long.parseLong(userDetails.getUsername());
    }
}