package apiserviciotransporte.apiserviciotransporte.config.security.jwt;


import apiserviciotransporte.apiserviciotransporte.controladores.dto.UserLoginResponseDto;
import apiserviciotransporte.apiserviciotransporte.entidades.DetalleUsuario;
import apiserviciotransporte.apiserviciotransporte.entidades.Role;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.security.core.GrantedAuthority;

import java.security.Key;
import java.util.*;
import java.util.function.Function;
import java.util.logging.Level;

@Log
@RequiredArgsConstructor
@Builder(toBuilder = true)
public class JwtProvider {

    private final String jwtSecret;
    private final long jwtAccessTokenExp;
    private final long jwtIdTokenExp;
    public UserLoginResponseDto generateUserCredentials(DetalleUsuario detalleUsuario) {
        Map<String, Object> claims = new HashMap<>();
        List<Map<String, String>> roles = detalleUsuario.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .map(role -> {
                    Map<String, String> map = new HashMap<>();
                    map.put("role", role);
                    map.put("label", Role.UserRoles.valueOf(role).getLabel());
                    return map;
                })
                .toList();

        claims.put("uuid", detalleUsuario.getId());
//        claims.put("email", detalleUsuario.getEmail());
        claims.put("name", detalleUsuario.getName());
        claims.put("phoneNumber", detalleUsuario.getPhoneNumber());
        claims.put("roles", roles);

        String idToken = this.buildToken(detalleUsuario.getEmail(), this.jwtIdTokenExp, claims);
        String accessToken = this.buildToken(detalleUsuario.getEmail(), this.jwtAccessTokenExp, Collections.emptyMap());

        return UserLoginResponseDto.builder()
                .accessToken(accessToken)
                .idToken(idToken)
                .build();
    }

    public UserLoginResponseDto refreshAuthentication(DetalleUsuario detalleUsuario) {
        return this.generateUserCredentials(detalleUsuario);
    }

    private String buildToken(String subject, long expiration, Map<String, Object> claims) {
        return Jwts.builder()
                .setSubject(subject)
                .addClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(this.jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractSubject(String token) {
        return this.extractClaim(token, Claims::getSubject);
    }

    private Date extractExpiration(String token) {
        return this.extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public boolean isTokenExpired(String token) {
        return this.extractExpiration(token).before(new Date());
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isTokenValid(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSignInKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e1) {
            log.log(Level.SEVERE, "Token is invalid");
        } catch (UnsupportedJwtException e2) {
            log.log(Level.SEVERE, "Token not supported");
        } catch (ExpiredJwtException e3) {
            log.log(Level.SEVERE, "Token has expired");
        } catch (IllegalArgumentException e4) {
            log.log(Level.SEVERE, "Token is empty");
        } catch (SignatureException e5) {
            log.log(Level.SEVERE, "Signature is invalid");
        }
        return false;
    }
}
